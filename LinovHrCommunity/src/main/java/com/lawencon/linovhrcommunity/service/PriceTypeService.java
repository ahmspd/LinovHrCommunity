package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PriceTypeDao;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoDataReq;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteByIdPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteMultiplePriceTypeDtoDataReq;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteMultiplePriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteMultiplePriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypePageDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypePageDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetByIdPriceTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetByIdPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.InsertPriceTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricetype.InsertPriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.InsertPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.UpdatePriceTypeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricetype.UpdatePriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.UpdatePriceTypeDtoRes;
import com.lawencon.linovhrcommunity.model.PriceType;

@Service
public class PriceTypeService extends BaseServiceLinovCommunityImpl {

	private PriceTypeDao priceTypeDao;
	private String createdById = "1";

	@Autowired
	public PriceTypeService(PriceTypeDao priceTypeDao) {
		this.priceTypeDao = priceTypeDao;
	}

	public InsertPriceTypeDtoRes insert(InsertPriceTypeDtoReq dataReq) throws Exception {
		PriceType addPriceType = new PriceType();
		addPriceType.setCode(dataReq.getCode());
		addPriceType.setPriceTypeName(dataReq.getPriceTypeName());
		addPriceType.setCreatedBy(getIdFromPrincipal());

		PriceType priceTypeAdded;
		try {
			begin();
			priceTypeAdded = priceTypeDao.save(addPriceType);
			commit();

			InsertPriceTypeDtoDataRes data = new InsertPriceTypeDtoDataRes();
			data.setId(priceTypeAdded.getId());

			InsertPriceTypeDtoRes dataRes = new InsertPriceTypeDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Insert ", priceTypeAdded.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdatePriceTypeDtoRes update(UpdatePriceTypeDtoReq dataReq) throws Exception {
		PriceType updatePriceType = priceTypeDao.getById(dataReq.getId());
		updatePriceType.setPriceTypeName(dataReq.getPriceTypeName());
		updatePriceType.setUpdatedBy(createdById);
		updatePriceType.setVersion(dataReq.getVersion());

		PriceType priceTypeUpdated;
		try {
			begin();
			priceTypeUpdated = priceTypeDao.save(updatePriceType);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		UpdatePriceTypeDtoDataRes data = new UpdatePriceTypeDtoDataRes();
		data.setVersion(priceTypeUpdated.getVersion());

		UpdatePriceTypeDtoRes dataRes = new UpdatePriceTypeDtoRes();
		dataRes.setData(data);
		dataRes.setMessage(stringBuilder("Update ", priceTypeUpdated.getCode(), " Success !"));
		return dataRes;
	}

	public GetAllPriceTypeDtoRes findAll() throws Exception {
		List<GetAllPriceTypeDtoDataRes> datas = new ArrayList<GetAllPriceTypeDtoDataRes>();
		List<PriceType> priceTypes = priceTypeDao.getAll();

		priceTypes.forEach(priceType -> {
			GetAllPriceTypeDtoDataRes data = new GetAllPriceTypeDtoDataRes();
			data.setId(priceType.getId());
			data.setCode(priceType.getCode());
			data.setPriceTypeName(priceType.getPriceTypeName());
			data.setIsActive(priceType.getIsActive());
			data.setVersion(priceType.getVersion());
			datas.add(data);
		});

		GetAllPriceTypeDtoRes dataRes = new GetAllPriceTypeDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}
	
	public GetAllPriceTypePageDtoRes getAllWithPage(int startPage, int maxPage) throws Exception {
		Long total = priceTypeDao.countAll();
		List<PriceType> priceTypes = priceTypeDao.getAll(startPage, maxPage);
		List<GetAllPriceTypePageDtoDataRes> datas = new ArrayList<GetAllPriceTypePageDtoDataRes>();

		priceTypes.forEach(priceType -> {
			GetAllPriceTypePageDtoDataRes data = new GetAllPriceTypePageDtoDataRes();
			data.setId(priceType.getId());
			data.setCode(priceType.getCode());
			data.setPriceTypeName(priceType.getPriceTypeName());
			data.setIsActive(priceType.getIsActive());
			data.setVersion(priceType.getVersion());
			datas.add(data);
		});

		GetAllPriceTypePageDtoRes result = new GetAllPriceTypePageDtoRes();
		result.setData(datas);
		result.setTotal(total);
		
		return result;
	}

	public GetByIdPriceTypeDtoRes findById(String id) throws Exception {
		PriceType getPriceType = priceTypeDao.getById(id);

		GetByIdPriceTypeDtoDataRes data = new GetByIdPriceTypeDtoDataRes();
		data.setId(getPriceType.getId());
		data.setCode(getPriceType.getCode());
		data.setPriceTypeName(getPriceType.getPriceTypeName());
		data.setIsActive(getPriceType.getIsActive());
		data.setVersion(getPriceType.getVersion());

		GetByIdPriceTypeDtoRes dataRes = new GetByIdPriceTypeDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdPriceTypeDtoRes deleteById(String id) throws Exception {
		DeleteByIdPriceTypeDtoRes dataRes = new DeleteByIdPriceTypeDtoRes();
		try {
			begin();
			boolean isDeleted = priceTypeDao.deleteById(id);
			commit();

			if (isDeleted) {
				dataRes.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}

			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public DeleteMultiplePriceTypeDtoRes deleteMultiple(DeleteMultiplePriceTypeDtoReq data) throws Exception {
		DeleteMultiplePriceTypeDtoRes dataRes = new DeleteMultiplePriceTypeDtoRes();
		boolean isDeleted = false;
		try {
			begin();
			List<DeleteMultiplePriceTypeDtoDataReq> dataReq = data.getData();
			for(int i=0; i<dataReq.size();i++) {
				isDeleted = priceTypeDao.deleteById(dataReq.get(i).getId());
			}

			if (isDeleted) {
				dataRes.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}
			commit();

			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
