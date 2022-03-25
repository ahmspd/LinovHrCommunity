package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PaymentMethodDao;
import com.lawencon.linovhrcommunity.dto.paymentmethod.DeleteByIdPaymentMethodRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodDtoDataRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodPageDtoDataRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodPageDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetByIdPaymentMethodDtoDataRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetByIdPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.InsertPaymentMethodDtoDataRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.linovhrcommunity.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.UpdatePaymentMethodDtoDataRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.linovhrcommunity.dto.paymentmethod.UpdatePaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.model.PaymentMethod;

@Service
public class PaymentMethodService extends BaseServiceLinovCommunityImpl {

	private PaymentMethodDao paymentMethodDao;
	
	@Autowired
	public PaymentMethodService(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

	public InsertPaymentMethodDtoRes insert(InsertPaymentMethodDtoReq dataReq) throws Exception {
		try {
			PaymentMethod newPaymentMethod = new PaymentMethod();
			String paymentMethodCode = dataReq.getCode();
			String paymentMethodName = dataReq.getPaymentName();
			newPaymentMethod.setCode(paymentMethodCode);
			newPaymentMethod.setPaymentName(paymentMethodName);
			newPaymentMethod.setCreatedBy("1");
			
			begin();
			newPaymentMethod = paymentMethodDao.save(newPaymentMethod);			
			commit();
			
			InsertPaymentMethodDtoDataRes dataRes = new InsertPaymentMethodDtoDataRes();
			dataRes.setId(newPaymentMethod.getId());
			
			InsertPaymentMethodDtoRes insertRes = new InsertPaymentMethodDtoRes();
			insertRes.setData(dataRes);
			insertRes.setMessage("Success Insert New PaymentMethod !");
			
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdatePaymentMethodDtoRes update(UpdatePaymentMethodDtoReq dataReq) throws Exception {
		try {
			PaymentMethod editPaymentMethod = paymentMethodDao.findById(dataReq.getId());
			String paymentMethodCode = dataReq.getCode();
			String paymentMethodName = dataReq.getPaymentName();
			editPaymentMethod.setCode(paymentMethodCode);
			editPaymentMethod.setPaymentName(paymentMethodName);
			editPaymentMethod.setUpdatedBy("1");
			editPaymentMethod.setVersion(dataReq.getVersion());
			
			begin();
			editPaymentMethod = paymentMethodDao.save(editPaymentMethod);			
			commit();
			
			UpdatePaymentMethodDtoDataRes dataRes = new UpdatePaymentMethodDtoDataRes();
			dataRes.setVersion(editPaymentMethod.getVersion());
			
			UpdatePaymentMethodDtoRes updateRes = new UpdatePaymentMethodDtoRes();
			updateRes.setData(dataRes);
			updateRes.setMessage("Success Update PaymentMethod !");
			
			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetByIdPaymentMethodDtoRes findById(String id) throws Exception {
		PaymentMethod paymentMethodById = paymentMethodDao.findById(id);
		
		GetByIdPaymentMethodDtoDataRes dataById = new GetByIdPaymentMethodDtoDataRes();
		dataById.setId(paymentMethodById.getId());
		dataById.setCode(paymentMethodById.getCode());
		dataById.setPaymentName(paymentMethodById.getPaymentName());
		dataById.setIsActive(paymentMethodById.getIsActive());
		dataById.setVersion(paymentMethodById.getVersion());
		
		GetByIdPaymentMethodDtoRes findByIdRes = new GetByIdPaymentMethodDtoRes();
		findByIdRes.setData(dataById);
		
		return findByIdRes;
	}

	public GetAllPaymentMethodDtoRes findAll() throws Exception {
		List<PaymentMethod> listPaymentMethods = new ArrayList<>();
		listPaymentMethods = paymentMethodDao.findAll();
		
		List<GetAllPaymentMethodDtoDataRes> dataAll = new ArrayList<>();

		listPaymentMethods.forEach(paymentMethod -> {
			GetAllPaymentMethodDtoDataRes data = new GetAllPaymentMethodDtoDataRes();
			data.setId(paymentMethod.getId());
			data.setCode(paymentMethod.getCode());
			data.setPaymentName(paymentMethod.getPaymentName());
			data.setIsActive(paymentMethod.getIsActive());
			data.setVersion(paymentMethod.getVersion());
			
			dataAll.add(data);
		});
		
		GetAllPaymentMethodDtoRes findAllRes = new GetAllPaymentMethodDtoRes();
		findAllRes.setData(dataAll);

		return findAllRes;
	}
	
	public GetAllPaymentMethodPageDtoRes getAllWithPage(int startPage, int maxPage) throws Exception {
		Long total = paymentMethodDao.countAll();
		List<PaymentMethod> listPaymentMethods = new ArrayList<>();
		listPaymentMethods = paymentMethodDao.getAll(startPage, maxPage);
		
		List<GetAllPaymentMethodPageDtoDataRes> dataAll = new ArrayList<>();

		listPaymentMethods.forEach(paymentMethod -> {
			GetAllPaymentMethodPageDtoDataRes data = new GetAllPaymentMethodPageDtoDataRes();
			data.setId(paymentMethod.getId());
			data.setCode(paymentMethod.getCode());
			data.setPaymentName(paymentMethod.getPaymentName());
			data.setIsActive(paymentMethod.getIsActive());
			data.setVersion(paymentMethod.getVersion());
			
			dataAll.add(data);
		});
		
		GetAllPaymentMethodPageDtoRes findAllRes = new GetAllPaymentMethodPageDtoRes();
		findAllRes.setData(dataAll);
		findAllRes.setTotal(total);

		return findAllRes;
	}

	public DeleteByIdPaymentMethodRes deleteById(String id) throws Exception {
		DeleteByIdPaymentMethodRes delRes = new DeleteByIdPaymentMethodRes();
		try {
			begin();
			boolean isDeleted = paymentMethodDao.deleteById(id);
			commit();
			
			if(isDeleted) {
				delRes.setMessage("Delete Success !");
			}else {
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
