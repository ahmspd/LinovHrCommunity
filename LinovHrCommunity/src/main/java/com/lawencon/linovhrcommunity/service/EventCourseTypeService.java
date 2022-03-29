package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.EventCourseTypeDao;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.DeleteByIdEventCourseTypeRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypePageDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypePageDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetByIdEventCourseTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetByIdEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.InsertEventCourseTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.InsertEventCourseTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.InsertEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.UpdateEventCourseTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.UpdateEventCourseTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.UpdateEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.model.EventCourseType;

@Service
public class EventCourseTypeService extends BaseServiceLinovCommunityImpl {

	private EventCourseTypeDao eventCourseTypeDao;

	@Autowired
	public EventCourseTypeService(EventCourseTypeDao eventCourseTypeDao) {
		this.eventCourseTypeDao = eventCourseTypeDao;
	}

	public InsertEventCourseTypeDtoRes insert(InsertEventCourseTypeDtoReq dataReq) throws Exception {
		try {
			EventCourseType newEventCourseType = new EventCourseType();
			String code = dataReq.getCode();
			String name = dataReq.getEventTypeName();
			newEventCourseType.setCode(code);
			newEventCourseType.setEventTypeName(name);
			newEventCourseType.setCreatedBy(getIdFromPrincipal());

			begin();
			newEventCourseType = eventCourseTypeDao.save(newEventCourseType);
			commit();

			InsertEventCourseTypeDtoDataRes dataRes = new InsertEventCourseTypeDtoDataRes();
			dataRes.setId(newEventCourseType.getId());

			InsertEventCourseTypeDtoRes insertRes = new InsertEventCourseTypeDtoRes();
			insertRes.setData(dataRes);
			insertRes.setMessage("Success Insert New EventCourseType !");

			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateEventCourseTypeDtoRes update(UpdateEventCourseTypeDtoReq dataReq) throws Exception {
		try {
			EventCourseType editEventCourseType = eventCourseTypeDao.findById(dataReq.getId());
			
			String eventCourseTypeCode = dataReq.getCode();
			String eventCourseTypeName = dataReq.getEventTypeName();
			editEventCourseType.setCode(eventCourseTypeCode);
			editEventCourseType.setEventTypeName(eventCourseTypeName);
			editEventCourseType.setUpdatedBy(getIdFromPrincipal());
			editEventCourseType.setVersion(dataReq.getVersion());

			begin();
			editEventCourseType = eventCourseTypeDao.save(editEventCourseType);
			commit();

			UpdateEventCourseTypeDtoDataRes dataRes = new UpdateEventCourseTypeDtoDataRes();
			dataRes.setVersion(editEventCourseType.getVersion());

			UpdateEventCourseTypeDtoRes updateRes = new UpdateEventCourseTypeDtoRes();
			updateRes.setData(dataRes);
			updateRes.setMessage("Success Update EventCourseType !");

			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetByIdEventCourseTypeDtoRes findById(String id) throws Exception {
		EventCourseType eventCourseTypeById = eventCourseTypeDao.findById(id);

		GetByIdEventCourseTypeDtoDataRes dataById = new GetByIdEventCourseTypeDtoDataRes();
		dataById.setId(eventCourseTypeById.getId());
		dataById.setCode(eventCourseTypeById.getCode());
		dataById.setEventTypeName(eventCourseTypeById.getEventTypeName());
		dataById.setIsActive(eventCourseTypeById.getIsActive());
		dataById.setVersion(eventCourseTypeById.getVersion());

		GetByIdEventCourseTypeDtoRes findByIdRes = new GetByIdEventCourseTypeDtoRes();
		findByIdRes.setData(dataById);

		return findByIdRes;
	}

	public GetAllEventCourseTypeDtoRes findAll() throws Exception {
		List<EventCourseType> listEventCourseTypes = new ArrayList<>();
		listEventCourseTypes = eventCourseTypeDao.findAll();

		List<GetAllEventCourseTypeDtoDataRes> dataAll = new ArrayList<>();

		listEventCourseTypes.forEach(eventCourseType -> {
			GetAllEventCourseTypeDtoDataRes data = new GetAllEventCourseTypeDtoDataRes();
			data.setId(eventCourseType.getId());
			data.setCode(eventCourseType.getCode());
			data.setEventTypeName(eventCourseType.getEventTypeName());
			data.setIsActive(eventCourseType.getIsActive());
			data.setVersion(eventCourseType.getVersion());

			dataAll.add(data);
		});

		GetAllEventCourseTypeDtoRes findAllRes = new GetAllEventCourseTypeDtoRes();
		findAllRes.setData(dataAll);

		return findAllRes;
	}
	
	public GetAllEventCourseTypePageDtoRes getAllWithPage(int startPage, int maxPage) throws Exception {
		Long total = eventCourseTypeDao.countAll();
		
		List<EventCourseType> listEventCourseTypes = new ArrayList<>();
		listEventCourseTypes = eventCourseTypeDao.getAll(startPage, maxPage);

		List<GetAllEventCourseTypePageDtoDataRes> dataAll = new ArrayList<>();

		listEventCourseTypes.forEach(eventCourseType -> {
			GetAllEventCourseTypePageDtoDataRes data = new GetAllEventCourseTypePageDtoDataRes();
			data.setId(eventCourseType.getId());
			data.setCode(eventCourseType.getCode());
			data.setEventTypeName(eventCourseType.getEventTypeName());
			data.setIsActive(eventCourseType.getIsActive());
			data.setVersion(eventCourseType.getVersion());

			dataAll.add(data);
		});

		GetAllEventCourseTypePageDtoRes findAllRes = new GetAllEventCourseTypePageDtoRes();
		findAllRes.setData(dataAll);
		findAllRes.setTotal(total);

		return findAllRes;
	}

	public DeleteByIdEventCourseTypeRes deleteById(String id) throws Exception {
		DeleteByIdEventCourseTypeRes delRes = new DeleteByIdEventCourseTypeRes();
		try {
			begin();
			boolean isDeleted = eventCourseTypeDao.deleteById(id);
			commit();

			if (isDeleted) {
				delRes.setMessage("Delete Success !");
			} else {
				throw new Exception("Delete Failed !");
			}

			return delRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
