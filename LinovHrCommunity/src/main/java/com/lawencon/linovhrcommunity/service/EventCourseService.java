package com.lawencon.linovhrcommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import com.lawencon.linovhrcommunity.dao.OrderDao;
import com.lawencon.linovhrcommunity.dao.OrderDetailDao;
import com.lawencon.linovhrcommunity.dao.PaymentMethodDao;
import com.lawencon.linovhrcommunity.dao.PriceListDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.email.EmailTemplate;
import com.lawencon.linovhrcommunity.dto.eventcourse.ConfirmPayJoinEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.ConfirmPayJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetByIdEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetByIdEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetOrderEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetOrderEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetProfileJoinEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetProfileJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetReportEventCourseById;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetReportEventCourseByIdRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.JoinEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.JoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.PayJoinEventCourseDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.PayJoinEventCourseDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcourse.PayJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
import com.lawencon.linovhrcommunity.model.Category;
import com.lawencon.linovhrcommunity.model.CategoryDetail;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;
import com.lawencon.linovhrcommunity.model.EventCourseType;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.Order;
import com.lawencon.linovhrcommunity.model.OrderDetail;
import com.lawencon.linovhrcommunity.model.PaymentMethod;
import com.lawencon.linovhrcommunity.model.PriceList;
import com.lawencon.linovhrcommunity.model.User;

@Service
public class EventCourseService extends BaseServiceLinovCommunityImpl {

	private EventCourseDao eventCourseDao;
	private CategoryDao categoryDao;
	private CategoryDetailDao categoryDetailDao;
	private EventCourseTypeDao eventCourseTypeDao;
	private FileDao fileDao;
	private PriceListDao priceListDao;
	private EventCoursePaymentDetailDao eventCoursePaymentDetailDao;
	private UserDao userDao;
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private PaymentMethodDao paymentMethodDao;

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

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Autowired
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}
	
	public GetByIdEventCourseDtoRes getById(String id) throws Exception {
		EventCourse getEventCourse = eventCourseDao.findById(id);
		
		GetByIdEventCourseDtoDataRes data = new GetByIdEventCourseDtoDataRes();
		data.setId(getEventCourse.getId());
		data.setContents(getEventCourse.getContents());
		data.setTitle(getEventCourse.getTitle());
		data.setEventCourseLocation(getEventCourse.getEventCourseLocation());
		data.setPrice(getEventCourse.getPrice());
		data.setDateStart(getEventCourse.getDateStart());
		data.setDateEnd(getEventCourse.getDateEnd());
		data.setTimeStart(getEventCourse.getTimeStart());
		data.setTimeEnd(getEventCourse.getTimeEnd());
		data.setIdFile((getEventCourse.getFile() != null)?getEventCourse.getFile().getId():null);
		data.setCreatedBy(getEventCourse.getCreatedBy());
		data.setCreatedAt(getEventCourse.getCreatedAt());
		
		GetByIdEventCourseDtoRes dataRes = new GetByIdEventCourseDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public InsertEventCourseDtoRes insert(String content, MultipartFile file) throws Exception {
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
			if (file != null) {
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
		for(int i=0; i<dataRes.size();i++) {
			dataRes.get(i).setDataCategoryDetail(categoryDetailDao.getCategoryDetailByEventCourse(dataRes.get(i).getId()));
			dataRes.get(i).setIsJoin(eventCourseDao.isJoin(getIdFromPrincipal(), dataRes.get(i).getId()));
		}

		GetAllEventCourseDtoRes result = new GetAllEventCourseDtoRes();
		result.setData(dataRes);

		return result;
	}

	public GetAllEventCourseDtoRes getByEventCoursePaymentId(String id) throws Exception {
		List<GetAllEventCourseDtoDataRes> dataRes = eventCourseDao.getByEventCoursePaymentId(id);
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

	public GetAllEventCourseDtoRes getByCreatedBy(String type) throws Exception {
		List<GetAllEventCourseDtoDataRes> dataRes = eventCourseDao.getByCreatedBy(getIdFromPrincipal(), type);
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

	public JoinEventCourseDtoRes joinEventCourse(String id) throws Exception {
		Order orderSave = new Order();
		OrderDetail orderDetailSave = new OrderDetail();

		try {
			begin();
			User getUser = userDao.getById(getIdFromPrincipal());
			orderSave.setIdUser(getUser);
			orderSave.setIsAccept(false);
			orderSave.setCreatedBy(getIdFromPrincipal());
			orderSave = orderDao.save(orderSave);

			orderDetailSave.setOrder(orderSave);

			EventCourse getEventCourse = eventCourseDao.getById(id);
			orderDetailSave.setEventCourse(getEventCourse);

			orderDetailSave.setCreatedBy(getIdFromPrincipal());
			orderDetailSave = orderDetailDao.save(orderDetailSave);

			JoinEventCourseDtoRes result = new JoinEventCourseDtoRes();
			JoinEventCourseDtoDataRes dataRes = new JoinEventCourseDtoDataRes();
			dataRes.setId(orderSave.getId());
			result.setData(dataRes);
			result.setMessage("Order Created");

			commit();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public PayJoinEventCourseDtoRes payJoin(String content, MultipartFile file) throws Exception {
		PayJoinEventCourseDtoReq dataReq = new ObjectMapper().readValue(content, PayJoinEventCourseDtoReq.class);
		
		try {
			begin();
			Order orderUpdate = orderDao.findById(dataReq.getIdOrder());
			if (file != null) {
				File dataFile = new File();
				String extName = getExtension(file);
				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				File fileSave = fileDao.save(dataFile);
				orderUpdate.setFile(fileSave);
			}

			PaymentMethod getPaymentMethod = paymentMethodDao.getById(dataReq.getIdPaymentMethod());
			orderUpdate.setIdPaymentMethod(getPaymentMethod);
			
			orderUpdate.setUpdatedBy(getIdFromPrincipal());
			orderUpdate = orderDao.save(orderUpdate);

			PayJoinEventCourseDtoRes result = new PayJoinEventCourseDtoRes();
			PayJoinEventCourseDtoDataRes dataRes = new PayJoinEventCourseDtoDataRes();
			dataRes.setId(orderUpdate.getId());
			result.setData(dataRes);
			result.setMessage("Pay Success");

			commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	//user to user
	public ConfirmPayJoinEventCourseDtoRes confirmJoinPayment(String id) throws Exception {
		ExecutorService excecutorService = Executors.newFixedThreadPool(1);
		try {
			begin();
			Order orderConfirm = orderDao.getById(id);
			orderConfirm.setIsAccept(true);
			String invoice = generateCode(7);
			orderConfirm.setInvoice(invoice);
			orderConfirm.setUpdatedBy(getIdFromPrincipal());
			orderConfirm = orderDao.save(orderConfirm);
			
			GetUserDtoDataRes userData = userDao.getUserByIs(orderConfirm.getCreatedBy());
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setFrom("LawenconCommunity");
			emailTemplate.setSubject("Invoice Event Course");
			emailTemplate.setTo(userData.getEmail());
			Map<String, Object> model = new HashMap<>();
			model.put("profileName", userData.getFullName());
			model.put("invoice", invoice);
			emailTemplate.setModel(model);
			
			excecutorService.submit(()->{			
				sendEmail("image/online-payment.png","EmailTemplatePaymentEventCourse.flth",emailTemplate);
			});
			excecutorService.shutdown();
			
			ConfirmPayJoinEventCourseDtoRes result = new ConfirmPayJoinEventCourseDtoRes();
			ConfirmPayJoinEventCourseDtoDataRes dataRes = new ConfirmPayJoinEventCourseDtoDataRes();
			dataRes.setId(orderConfirm.getId());
			result.setData(dataRes);
			result.setMessage("Confirm Success");
			
			commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetAllEventCourseDtoRes getJoinedEventCourse() throws Exception {
		List<GetAllEventCourseDtoDataRes> dataRes = eventCourseDao.getJoinedEventCourse(getIdFromPrincipal());
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
	
	public GetProfileJoinEventCourseDtoRes getProfileJoin(String idEventCourse) throws Exception {
		List<GetProfileJoinEventCourseDtoDataRes> dataRes = eventCourseDao.getProfileJoin(idEventCourse);
		GetProfileJoinEventCourseDtoRes result = new GetProfileJoinEventCourseDtoRes();
		result.setData(dataRes);

		return result;
	}
	
	public GetOrderEventCourseDtoRes getOrderEventCourse(String idEventCourse) throws Exception {
		List<GetOrderEventCourseDtoDataRes> dataRes = eventCourseDao.getOrderEventCourse(idEventCourse);
		GetOrderEventCourseDtoRes result = new GetOrderEventCourseDtoRes();
		result.setData(dataRes);

		return result;
		
	}
	
	public GetReportEventCourseByIdRes getReportUserJoinByAdmin(int start, int max) throws Exception {
		List<GetReportEventCourseById> dataRes = eventCourseDao.getAllReportEventCourse(start, max);
		Integer total = dataRes.size();
		GetReportEventCourseByIdRes result = new GetReportEventCourseByIdRes();
		result.setData(dataRes);
		result.setTotal(total);
		
		return result;
	}
	
	public GetReportEventCourseByIdRes getReportUserJoinByIdEvent(String idEvent) throws Exception {
		List<GetReportEventCourseById> dataRes = eventCourseDao.getReportEventCourseById(idEvent);
		Integer total = dataRes.size();
		Float totalPrice = 0F;
		for(int i=0; i<dataRes.size();i++) {
			totalPrice += dataRes.get(i).getPrice();
		}
		GetReportEventCourseByIdRes result = new GetReportEventCourseByIdRes();
		result.setData(dataRes);
		result.setTotal(total);
		result.setTotalPrice(totalPrice);
		
		return result;
	}
	
	public GetReportEventCourseByIdRes getReportUserJoinByUser(String idUser) throws Exception {
		List<GetReportEventCourseById> dataRes = eventCourseDao.getReportEventCourseByUser(idUser);
		Integer total = dataRes.size();
		Float totalPrice = 0F;
		for(int i=0; i<dataRes.size();i++) {
			totalPrice += dataRes.get(i).getPrice();
		}
		GetReportEventCourseByIdRes result = new GetReportEventCourseByIdRes();
		result.setData(dataRes);
		result.setTotal(total);
		result.setTotalPrice(totalPrice);
		
		return result;
	}

}












