package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.EventCourseDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDetailDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.PaymentMethodDao;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.EventCoursePayment;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.PaymentMethod;

@Service
public class EventCoursePaymentService extends BaseServiceLinovCommunityImpl {

	private EventCoursePaymentDao eventCoursePaymentDao;
	private EventCoursePaymentDetailDao eventCoursePaymentDetailDao;
	private PaymentMethodDao paymentMethodDao;
	private FileDao fileDao;
	private EventCourseDao eventCourseDao;

	@Autowired
	public void setEventCoursePaymentDao(EventCoursePaymentDao eventCoursePaymentDao) {
		this.eventCoursePaymentDao = eventCoursePaymentDao;
	}

	@Autowired
	public void setEventCoursePaymentDetailDao(EventCoursePaymentDetailDao eventCoursePaymentDetailDao) {
		this.eventCoursePaymentDetailDao = eventCoursePaymentDetailDao;
	}

	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Autowired
	public void setEventCourseDao(EventCourseDao eventCourseDao) {
		this.eventCourseDao = eventCourseDao;
	}

	public InsertEventCoursePaymentDtoRes pay(String content, MultipartFile file) throws Exception {
			InsertEventCoursePaymentDtoReq dataReq = new ObjectMapper().readValue(content,
					InsertEventCoursePaymentDtoReq.class);

			EventCoursePayment eventCoursePaymentsave = new EventCoursePayment();
			PaymentMethod eventCourseType = paymentMethodDao.findById(dataReq.getIdPaymentMethod());
			eventCoursePaymentsave.setPaymentMethod(eventCourseType);

			eventCoursePaymentsave.setTotalPrice(dataReq.getTotalPrice());
			eventCoursePaymentsave.setIsAccept(false);
			eventCoursePaymentsave.setCreatedBy(getIdFromPrincipal());
			
			try {
				begin();
			if (file != null) {
				File dataFile = new File();
				String extName = getExtension(file);
				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				File fileSave = fileDao.save(dataFile);
				eventCoursePaymentsave.setFile(fileSave);
			}

			eventCoursePaymentsave = eventCoursePaymentDao.save(eventCoursePaymentsave);

			String[] idPaymentDetails = dataReq.getIdEvenCoursePaymentDetails();

			for (String idPaymentDetail : idPaymentDetails) {
				EventCoursePaymentDetail eventCoursePaymentDetailUpdate = eventCoursePaymentDetailDao
						.findById(idPaymentDetail);
				eventCoursePaymentDetailUpdate.setEventCoursePayment(eventCoursePaymentsave);
				eventCoursePaymentDetailUpdate.setUpdatedBy(getIdFromPrincipal());
				eventCoursePaymentDetailUpdate = eventCoursePaymentDetailDao.save(eventCoursePaymentDetailUpdate);
			}

			InsertEventCoursePaymentDtoDataRes resultData = new InsertEventCoursePaymentDtoDataRes();
			resultData.setId(eventCoursePaymentsave.getId());

			InsertEventCoursePaymentDtoRes result = new InsertEventCoursePaymentDtoRes();
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

	public UpdateEventCoursePaymentDtoRes confirmPayment(UpdateEventCoursePaymentDtoReq
			dataReq) throws Exception {
		EventCoursePayment eventCoursePaymentUpdate;
		try {
			begin();
			eventCoursePaymentUpdate = eventCoursePaymentDao.findById(dataReq.getId());
			eventCoursePaymentUpdate.setIsAccept(true);
			eventCoursePaymentUpdate.setInvoice(generateCode(7));
			eventCoursePaymentUpdate.setUpdatedBy(getIdFromPrincipal());
			eventCoursePaymentUpdate = eventCoursePaymentDao.save(eventCoursePaymentUpdate);
			
			List<EventCoursePaymentDetail> eventCoursePaymentDetailDatas = eventCoursePaymentDetailDao
					.getByEventCoursePaymentId(dataReq.getId());
			eventCoursePaymentDetailDatas.forEach(eventCoursePaymentDetailData -> {
				try {
					EventCourse eventCourseUpdate = eventCourseDao.findById(eventCoursePaymentDetailData.getEventCourse().getId());
					eventCourseUpdate.setIsActive(true);
					eventCourseUpdate.setUpdatedBy(getIdFromPrincipal());
					eventCourseUpdate = eventCourseDao.save(eventCourseUpdate);
					
				} catch (Exception e) {
					e.printStackTrace();
					rollback();
				}
				
			});
			
			UpdateEventCoursePaymentDtoRes result = new UpdateEventCoursePaymentDtoRes();
			UpdateEventCoursePaymentDtoDataRes dataRes = new UpdateEventCoursePaymentDtoDataRes();
			dataRes.setVersion(eventCoursePaymentUpdate.getVersion());
			result.setData(dataRes);
			result.setMessage("Updated");
			commit();
			return result;
		} catch (Exception err) {
			err.printStackTrace();
			rollback();
			throw new Exception(err);
		}

	}
}
