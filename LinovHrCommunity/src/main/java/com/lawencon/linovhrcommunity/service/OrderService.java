package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.OrderDao;
import com.lawencon.linovhrcommunity.dto.order.GetByIdOrderDtoDataRes;
import com.lawencon.linovhrcommunity.dto.order.GetByIdOrderDtoRes;
import com.lawencon.linovhrcommunity.model.Order;

@Service
public class OrderService {

	private OrderDao orderDao;

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public GetByIdOrderDtoRes findById(String id) throws Exception {
		Order getOrder = orderDao.findById(id);
		GetByIdOrderDtoDataRes dataRes = new GetByIdOrderDtoDataRes();
		GetByIdOrderDtoRes result = new GetByIdOrderDtoRes();
		
		if(getOrder != null) {
			dataRes.setId(getOrder.getId());
			dataRes.setIdUser(getOrder.getIdUser().getId());
			dataRes.setIsAccept(getOrder.getIsAccept());
			dataRes.setIdFile((getOrder.getFile() != null)?getOrder.getFile().getId():null);
			dataRes.setIdPaymentMethod((getOrder.getIdPaymentMethod() != null)?getOrder.getIdPaymentMethod().getId():null);
			dataRes.setInvoice((getOrder.getInvoice() != null)?getOrder.getInvoice():null);
			dataRes.setCreatedBy(getOrder.getCreatedBy());
			dataRes.setVersion(getOrder.getVersion());
			dataRes.setIsActive(getOrder.getIsActive());
			result.setData(dataRes);
			
		} else {
			result.setData(null);
		}
		
		return result;
	}
	
	
}
