package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PriceListDao;
import com.lawencon.linovhrcommunity.dao.PriceTypeDao;
import com.lawencon.linovhrcommunity.dto.pricelist.DeleteByIdPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.DeleteMultiplePriceListDtoDataReq;
import com.lawencon.linovhrcommunity.dto.pricelist.DeleteMultiplePriceListDtoReq;
import com.lawencon.linovhrcommunity.dto.pricelist.DeleteMultiplePriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetAllPriceListDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetAllPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetAllPriceListPageDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetAllPriceListPageDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetByIdPriceListDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetByIdPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.InsertPriceListDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricelist.InsertPriceListDtoReq;
import com.lawencon.linovhrcommunity.dto.pricelist.InsertPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.UpdatePriceListDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pricelist.UpdatePriceListDtoReq;
import com.lawencon.linovhrcommunity.dto.pricelist.UpdatePriceListDtoRes;
import com.lawencon.linovhrcommunity.model.PriceList;
import com.lawencon.linovhrcommunity.model.PriceType;

@Service
public class PriceListService extends BaseServiceLinovCommunityImpl {

	private PriceListDao priceListDao;
	private PriceTypeDao priceTypeDao;

	@Autowired
	public PriceListService(PriceListDao priceListDao,PriceTypeDao priceTypeDao) {
		this.priceListDao = priceListDao;
		this.priceTypeDao = priceTypeDao;
	}

	public InsertPriceListDtoRes insert(InsertPriceListDtoReq dataReq) throws Exception {
		
		PriceType priceTypeData = new PriceType();
		priceTypeData.setId(dataReq.getIdPriceType());
		priceTypeData.setVersion(0);
		
		PriceList addPriceList = new PriceList();
		addPriceList.setCode(dataReq.getCode());
		addPriceList.setPriceName(dataReq.getPriceName());
		addPriceList.setPrice(dataReq.getPrice());
		addPriceList.setPriceType(priceTypeData);
		addPriceList.setCreatedBy(getIdFromPrincipal());

		PriceList priceListAdded;
		try {
			begin();
			valBkNotExist(dataReq.getCode());
			valBkNotNull(dataReq.getCode());
			priceListAdded = priceListDao.save(addPriceList);
			commit();
			
			InsertPriceListDtoDataRes data = new InsertPriceListDtoDataRes();
			data.setId(priceListAdded.getId());

			InsertPriceListDtoRes dataRes = new InsertPriceListDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Insert ", priceListAdded.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdatePriceListDtoRes update(UpdatePriceListDtoReq dataReq) throws Exception {
		PriceType priceTypeData = priceTypeDao.getById(dataReq.getIdPriceType());
		
		PriceList updatePriceList = priceListDao.findById(dataReq.getId());
		updatePriceList.setPriceType(priceTypeData);
		updatePriceList.setPriceName(dataReq.getPriceName());
		updatePriceList.setPrice(dataReq.getPrice());
		updatePriceList.setUpdatedBy(getIdFromPrincipal());
		updatePriceList.setVersion(dataReq.getVersion());

		PriceList priceListUpdated;
		try {
			begin();
			valIdNotNull(dataReq.getId());
			valBkNotNull(dataReq.getCode());
			valIdExist(dataReq.getId());
			priceListUpdated = priceListDao.save(updatePriceList);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		UpdatePriceListDtoDataRes data = new UpdatePriceListDtoDataRes();
		data.setVersion(priceListUpdated.getVersion());

		UpdatePriceListDtoRes dataRes = new UpdatePriceListDtoRes();
		dataRes.setData(data);
		dataRes.setMessage(stringBuilder("Update ", priceListUpdated.getCode(), " Success !"));
		return dataRes;
	}

	public GetAllPriceListDtoRes findAll() throws Exception {
		List<GetAllPriceListDtoDataRes> datas = new ArrayList<GetAllPriceListDtoDataRes>();
		List<PriceList> priceLists = priceListDao.findAll();

		priceLists.forEach(priceList -> {
			GetAllPriceListDtoDataRes data = new GetAllPriceListDtoDataRes();
			data.setId(priceList.getId());
			data.setPriceName(priceList.getPriceName());
			data.setPriveTypeName(priceList.getPriceType().getPriceTypeName());
			data.setPrice(priceList.getPrice());
			data.setIsActive(priceList.getIsActive());
			data.setVersion(priceList.getVersion());
			datas.add(data);
		});

		GetAllPriceListDtoRes dataRes = new GetAllPriceListDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}
	
	public GetAllPriceListPageDtoRes getAllWithPage(int startPage, int maxPage) throws Exception {
		Long total = priceListDao.countAll();
		List<PriceList> priceLists = priceListDao.getAll(startPage, maxPage);
		List<GetAllPriceListPageDtoDataRes> datas = new ArrayList<GetAllPriceListPageDtoDataRes>();

		priceLists.forEach(priceList -> {
			GetAllPriceListPageDtoDataRes data = new GetAllPriceListPageDtoDataRes();
			data.setId(priceList.getId());
			data.setCode(priceList.getCode());
			data.setPriceName(priceList.getPriceName());
			data.setPriveTypeName(priceList.getPriceType().getPriceTypeName());
			data.setPrice(priceList.getPrice());
			data.setIsActive(priceList.getIsActive());
			data.setVersion(priceList.getVersion());
			datas.add(data);
		});

		GetAllPriceListPageDtoRes dataRes = new GetAllPriceListPageDtoRes();
		dataRes.setData(datas);
		dataRes.setTotal(total);
		
		return dataRes;
	}

	public GetByIdPriceListDtoRes findById(String id) throws Exception {
		PriceList getPriceList = priceListDao.findById(id);

		GetByIdPriceListDtoDataRes data = new GetByIdPriceListDtoDataRes();
		data.setId(getPriceList.getId());
		data.setIdPriceType(getPriceList.getPriceType().getId());
		data.setCode(getPriceList.getCode());
		data.setPriceName(getPriceList.getPriceName());
		data.setPriceTypeName(getPriceList.getPriceType().getPriceTypeName());
		data.setPrice(getPriceList.getPrice());
		data.setIsActive(getPriceList.getIsActive());
		data.setVersion(getPriceList.getVersion());

		GetByIdPriceListDtoRes dataRes = new GetByIdPriceListDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdPriceListDtoRes deleteById(String id) throws Exception {
		DeleteByIdPriceListDtoRes dataRes = new DeleteByIdPriceListDtoRes();
		try {
			begin();
			valIdExist(id);
			boolean isDeleted = priceListDao.deleteById(id);
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

	public DeleteMultiplePriceListDtoRes deleteMultiple(DeleteMultiplePriceListDtoReq data) throws Exception {
		DeleteMultiplePriceListDtoRes dataRes = new DeleteMultiplePriceListDtoRes();
		boolean isDeleted = false;
		try {
			begin();
			List<DeleteMultiplePriceListDtoDataReq> dataReq = data.getData();
			for(int i=0; i<dataReq.size();i++) {
				valIdExist(dataReq.get(i).getId());
				isDeleted = priceListDao.deleteById(dataReq.get(i).getId());
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
	
	private void valBkNotExist(String code) {
		Integer res = priceListDao.isPriceListCodeExist(code);
		if(res == 1) {
			throw new RuntimeException("PriceList Code Exist");
		}
	}
	
	private void valIdExist(String id) {
		Integer res = priceListDao.isPriceListIdExist(id);
		if(res == 0) {
			throw new RuntimeException("PriceList Id Not Exist");
		}
	}
	private void valIdNotNull(String id) {
		Integer res = priceListDao.isPriceListIdExist(id);
		if(res == 0) {
			throw new RuntimeException("PriceList Id Not Exist");
		}
	}
	private void valBkNotNull(String code) {
		if(code==null) {
			throw new RuntimeException("PriceList Code Is Null");
		}
	}
}
