package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.CategoryDao;
import com.lawencon.linovhrcommunity.dao.CategoryDetailDao;
import com.lawencon.linovhrcommunity.dao.EventCourseDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDetailDao;
import com.lawencon.linovhrcommunity.dao.EventCourseTypeDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.PriceListDao;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoRes;
import com.lawencon.linovhrcommunity.model.Category;
import com.lawencon.linovhrcommunity.model.CategoryDetail;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;
import com.lawencon.linovhrcommunity.model.EventCourseType;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.PriceList;

@Service
public class EventCourseService extends BaseServiceLinovCommunityImpl {
	
	private EventCourseDao eventCourseDao;
	private CategoryDao categoryDao;
	private CategoryDetailDao categoryDetailDao;
	private EventCourseTypeDao eventCourseTypeDao;
	private FileDao fileDao;
	private PriceListDao priceListDao;
	private EventCoursePaymentDetailDao eventCoursePaymentDetailDao;
	
	@Autowired
	public void setEventCourseDao(EventCourseDao eventCourseDao) {
		this.eventCourseDao = eventCourseDao;
	}
	
	@Autowired
	public void setCategoryDetailDao(CategoryDetailDao categoryDetailDao) {
		this.categoryDetailDao = categoryDetailDao;
	}
	
	@Autowired
	public void setEventCourseTypeDao(EventCourseTypeDao eventCourseTypeDao) {
		this.eventCourseTypeDao = eventCourseTypeDao;
	}

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setPriceListDao(PriceListDao priceListDao) {
		this.priceListDao = priceListDao;
	}

	@Autowired
	public void setEventCoursePaymentDetailDao(EventCoursePaymentDetailDao eventCoursePaymentDetailDao) {
		this.eventCoursePaymentDetailDao = eventCoursePaymentDetailDao;
	}

	public InsertEventCourseDtoRes pay(String content, MultipartFile file) throws Exception {
		InsertEventCourseDtoReq data = new ObjectMapper().readValue(content, InsertEventCourseDtoReq.class);
		
		EventCourse eventCourseSave = new EventCourse();
		EventCourseType eventCourseType = eventCourseTypeDao.findById(data.getIdEventCourseType());

		eventCourseSave.setEventCourseType(eventCourseType);
		eventCourseSave.setTitle(data.getTitle());
		eventCourseSave.setContents(data.getContents());
		eventCourseSave.setEventCourseLocation(data.getEventCourseLocation());
		eventCourseSave.setPrice(data.getPrice());
		eventCourseSave.setDateStart(data.getDateStart());
		eventCourseSave.setDateEnd(data.getDateEnd());
		eventCourseSave.setTimeStart(data.getTimeStart());
		eventCourseSave.setTimeEnd(data.getTimeEnd());
		eventCourseSave.setIsActive(false);
		eventCourseSave.setCreatedBy(getIdFromPrincipal());
		
		PriceList priceListData = priceListDao.findById(data.getIdPriceList());
		eventCourseSave.setPriceList(priceListData);
		
		try {
			begin();
			if(file != null) {
				File dataFile = new File();
				String extName = getExtension(file);
				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				File fileSave = fileDao.save(dataFile);
				eventCourseSave.setFile(fileSave);
			}
			
			eventCourseSave = eventCourseDao.save(eventCourseSave);
			
			String[] idCategories = data.getIdCategories();
			for (String idCategory : idCategories) {
				Category dataCategory = categoryDao.getById(idCategory);
				CategoryDetail categoryDetailSave = new CategoryDetail();
				categoryDetailSave.setCategory(dataCategory);
				categoryDetailSave.setEventCourse(eventCourseSave);
				categoryDetailSave.setCreatedBy(getIdFromPrincipal());
				categoryDetailSave = categoryDetailDao.save(categoryDetailSave);
			}
			
			EventCoursePaymentDetail eventCoursePaymentDetailSave = new EventCoursePaymentDetail();
			eventCoursePaymentDetailSave.setEventCourse(eventCourseSave);
			eventCoursePaymentDetailSave.setCreatedBy(getIdFromPrincipal());
			eventCoursePaymentDetailSave = eventCoursePaymentDetailDao.save(eventCoursePaymentDetailSave);
			InsertEventCourseDtoDataRes resultData = new InsertEventCourseDtoDataRes();
			resultData.setId(eventCourseSave.getId());
			
			InsertEventCourseDtoRes result = new InsertEventCourseDtoRes();
			result.setData(resultData);
			result.setMessage("Success");
			commit();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
	}
	
	public GetAllEventCourseDtoRes getAllActive(String type) throws Exception {
		List<GetAllEventCourseDtoDataRes> dataRes = eventCourseDao.getAllActive(type);
		dataRes.forEach(data -> {
			try {
				data.setDataCategoryDetail(categoryDetailDao.getCategoryDetailByEventCourse(data.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
		});
		
		GetAllEventCourseDtoRes result = new GetAllEventCourseDtoRes();
		
		result.setData(dataRes);
		
		return result;
	}
	
	public GetAllEventCourseDtoRes getByEventCoursePaymentId(String id) throws Exception {
		List<GetAllEventCourseDtoDataRes> dataRes = eventCourseDao.getByEventCoursePaymentId(id);
		GetAllEventCourseDtoRes result = new GetAllEventCourseDtoRes();
		result.setData(dataRes);
		
		return result;
	}

}






