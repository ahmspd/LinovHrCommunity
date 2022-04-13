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
import com.lawencon.linovhrcommunity.constant.PriceListDuration;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.OrderDao;
import com.lawencon.linovhrcommunity.dao.OrderDetailDao;
import com.lawencon.linovhrcommunity.dao.PaymentMethodDao;
import com.lawencon.linovhrcommunity.dao.PriceListDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dao.UserMemberDao;
import com.lawencon.linovhrcommunity.dto.email.EmailTemplate;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.usermember.GetAllUserMemberDtoDataRes;
import com.lawencon.linovhrcommunity.dto.usermember.GetAllUserMemberDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.InsertUserMemberDtoDataRes;
import com.lawencon.linovhrcommunity.dto.usermember.InsertUserMemberDtoReq;
import com.lawencon.linovhrcommunity.dto.usermember.InsertUserMemberDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberAcceptDtoDataRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberAcceptDtoReq;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberAcceptDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberPaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberPaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberPaymentDtoRes;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.Order;
import com.lawencon.linovhrcommunity.model.OrderDetail;
import com.lawencon.linovhrcommunity.model.PaymentMethod;
import com.lawencon.linovhrcommunity.model.PriceList;
import com.lawencon.linovhrcommunity.model.User;
import com.lawencon.linovhrcommunity.model.UserMember;

@Service
public class UserMemberService extends BaseServiceLinovCommunityImpl {
	private UserMemberDao userMemberDao;
	private UserDao userDao;
	private PaymentMethodDao paymentMethodDao;
	private OrderDao orderDao;
	private PriceListDao priceListDao;
	private OrderDetailDao orderDetailDao;
	private FileDao fileDao;

	@Autowired
	public void setUserMemberDao(UserMemberDao userMemberDao) {
		this.userMemberDao = userMemberDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Autowired
	public void setPriceLisDao(PriceListDao priceListDao) {
		this.priceListDao = priceListDao;
	}

	@Autowired
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public InsertUserMemberDtoRes insert(InsertUserMemberDtoReq data) throws Exception {
		try {
			begin();
			User user = userDao.findById(getIdFromPrincipal());
			PaymentMethod paymentMethod = paymentMethodDao.findById(data.getIdPaymentMethod());

			Order newOrder = new Order();
			newOrder.setIdUser(user);
			newOrder.setIdPaymentMethod(paymentMethod);
			newOrder.setIsAccept(false);
			newOrder.setCreatedBy(getIdFromPrincipal());
			newOrder = orderDao.save(newOrder);

			PriceList priceList = priceListDao.findById(data.getIdPriceList());

			UserMember newUserMember = new UserMember();
			newUserMember.setPriceList(priceList);
			newUserMember.setCreatedBy(getIdFromPrincipal());
			newUserMember = userMemberDao.save(newUserMember);

			OrderDetail newOrderDetail = new OrderDetail();
			newOrderDetail.setOrder(newOrder);
			newOrderDetail.setUserMember(newUserMember);
			newOrderDetail.setCreatedBy(getIdFromPrincipal());
			newOrderDetail = orderDetailDao.save(newOrderDetail);

			InsertUserMemberDtoDataRes dataRes = new InsertUserMemberDtoDataRes();

			if (newOrderDetail != null) {
				dataRes.setId(newUserMember.getId());
				dataRes.setIdOrder(newOrder.getId());
				dataRes.setIdOrderDetail(newOrderDetail.getId());
			}

			InsertUserMemberDtoRes insertRes = new InsertUserMemberDtoRes();
			insertRes.setData(dataRes);
			insertRes.setMessage("Sucess");

			commit();
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateUserMemberPaymentDtoRes updatePayment(String content, MultipartFile file) throws Exception {
		UpdateUserMemberPaymentDtoReq data = new ObjectMapper().readValue(content, UpdateUserMemberPaymentDtoReq.class);

		try {
			begin();

			Order updateOrder = orderDao.findById(data.getIdOrder());

			if (file != null) {
				File dataFile = new File();
				String extName = getExtension(file);
				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				File fileSave = fileDao.save(dataFile);
				updateOrder.setFile(fileSave);
				updateOrder.setUpdatedBy(getIdFromPrincipal());
				updateOrder = orderDao.save(updateOrder);
			}
			commit();

			UpdateUserMemberPaymentDtoDataRes dataRes = new UpdateUserMemberPaymentDtoDataRes();
			dataRes.setVersion(updateOrder.getVersion());

			UpdateUserMemberPaymentDtoRes updateRes = new UpdateUserMemberPaymentDtoRes();
			updateRes.setData(dataRes);
			updateRes.setMessage("Success");

			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	public UpdateUserMemberAcceptDtoRes updateAccept(UpdateUserMemberAcceptDtoReq data) throws Exception {
		ExecutorService excecutorService = Executors.newFixedThreadPool(1);
		try {
			begin();
			OrderDetail orderDetail = userMemberDao.getByUserMember(data.getIdUserMember());
			UserMember userMember = userMemberDao.findById(data.getIdUserMember());
			Order order = orderDao.findById(orderDetail.getOrder().getId());
			PriceList priceList = priceListDao.findById(userMember.getPriceList().getId());
			User userPremium = userDao.findById(order.getIdUser().getId());

			Integer duration = 0;

			if (priceList.getCode() == PriceListDuration.ONEMONTH.getCode()) {
				duration = PriceListDuration.ONEMONTH.getDuration();
			} else if (priceList.getCode() == PriceListDuration.THREEMONTH.getCode()) {
				duration = PriceListDuration.THREEMONTH.getDuration();
			}

			boolean updateDateEnd = userMemberDao.updateDateEnd(duration, userMember.getId(), getIdFromPrincipal());

			order.setIsAccept(true);
			String invoice = generateCode(15);
			order.setInvoice(invoice);
			order = orderDao.save(order);

			userPremium.setIsMember(true);
			userPremium = userDao.save(userPremium);

			GetUserDtoDataRes userData = userDao.getUserByIs(order.getCreatedBy());
			
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setFrom("LawenconCommunity");
			emailTemplate.setSubject("Invoice User Premium");
			emailTemplate.setTo(userData.getEmail());
			Map<String, Object> model = new HashMap<>();
			model.put("profileName", userData.getFullName());
			model.put("invoice", invoice);
			emailTemplate.setModel(model);
			
			excecutorService.submit(()->{			
				sendEmail("image/premium-member.png","EmailTemplatePaymentEventCourse.flth",emailTemplate);
			});
			excecutorService.shutdown();
			
			commit();

			UpdateUserMemberAcceptDtoRes updateRes = new UpdateUserMemberAcceptDtoRes();

			if (updateDateEnd) {
				UpdateUserMemberAcceptDtoDataRes dataRes = new UpdateUserMemberAcceptDtoDataRes();
				dataRes.setVersion(userMember.getVersion());
				updateRes.setData(dataRes);
				updateRes.setMessage("Sucess");
			}

			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

	}

	public GetAllUserMemberDtoRes getAllIsNotAccept(Boolean isAccept, int startPage, int maxPage) throws Exception {
		
		try {
			begin();
			List<GetAllUserMemberDtoDataRes> listData = userMemberDao.getAllToAccept(isAccept, startPage, maxPage);
			Integer total = listData.size();
			GetAllUserMemberDtoRes getAllRes = new GetAllUserMemberDtoRes();
			getAllRes.setTotal(total);
			getAllRes.setData(listData);
			commit();

			return getAllRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
