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
import com.lawencon.linovhrcommunity.dao.EventCourseDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDetailDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.PaymentMethodDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.email.EmailTemplate;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetAllEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetAllEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetReportEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetReportEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
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
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

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

	//confirm by admin
	public UpdateEventCoursePaymentDtoRes confirmPayment(UpdateEventCoursePaymentDtoReq
			dataReq) throws Exception {
		ExecutorService excecutorService = Executors.newFixedThreadPool(1);
		EventCoursePayment eventCoursePaymentUpdate;
		try {
			begin();
			eventCoursePaymentUpdate = eventCoursePaymentDao.findById(dataReq.getId());
			eventCoursePaymentUpdate.setIsAccept(true);
			String invoice = generateCode(7);
			eventCoursePaymentUpdate.setInvoice(invoice);
			eventCoursePaymentUpdate.setUpdatedBy(getIdFromPrincipal());
			eventCoursePaymentUpdate = eventCoursePaymentDao.save(eventCoursePaymentUpdate);
			
			GetUserDtoDataRes userData = userDao.getUserByIs(eventCoursePaymentUpdate.getCreatedBy());
			
			List<EventCoursePaymentDetail> eventCoursePaymentDetailDatas = eventCoursePaymentDetailDao
					.getByEventCoursePaymentId(dataReq.getId());
			eventCoursePaymentDetailDatas.forEach(eventCoursePaymentDetailData -> {
				try {
					EventCourse eventCourseUpdate = eventCourseDao.findById(eventCoursePaymentDetailData.getEventCourse().getId());
					eventCourseUpdate.setIsActive(true);
					eventCourseUpdate.setUpdatedBy(getIdFromPrincipal());
					eventCourseUpdate = eventCourseDao.save(eventCourseUpdate);
					
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
	
	public GetAllEventCoursePaymentDtoRes getAllUnAccepted(Boolean isAccept, int startPage, int maxPage) throws Exception {
		List<GetAllEventCoursePaymentDtoDataRes> dataRes = eventCoursePaymentDao.getAllUnaccepted(isAccept, startPage, maxPage);
		Integer total = dataRes.size();
		GetAllEventCoursePaymentDtoRes result = new GetAllEventCoursePaymentDtoRes();
		result.setTotal(total);
		result.setData(dataRes);
		return result;
	}
	
	public GetReportEventCoursePaymentDtoRes getReportPaymentEventCourse(int start, int max) throws Exception {
		List<GetReportEventCoursePaymentDtoDataRes> dataRes = eventCoursePaymentDao.getAllReportPaymentEventCourse(start, max);
		Integer total = dataRes.size();
		Float totalPrice = 0f;
		for(int i=0; i<dataRes.size(); i++) {
			totalPrice += dataRes.get(i).getTotalPrice();
		}
		GetReportEventCoursePaymentDtoRes result = new GetReportEventCoursePaymentDtoRes();
		result.setData(dataRes);
		result.setTotal(total);
		result.setTotalPrice(totalPrice);
		
		return result;
	}
}
