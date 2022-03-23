package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.IndustryDao;
import com.lawencon.linovhrcommunity.dto.industry.DeleteByIdIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.GetAllIndustryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.GetByIdIndustryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.industry.GetByIdIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.InsertIndustryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.industry.InsertIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.InsertIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.UpdateIndustryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.linovhrcommunity.model.Industry;

@Service
public class IndustryService extends BaseServiceLinovCommunityImpl {

	private IndustryDao industryDao;
	private String createdById = "1";

	@Autowired
	public IndustryService(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}

	public InsertIndustryDtoRes insert(InsertIndustryDtoReq dataReq) throws Exception {
		Industry addIndustry = new Industry();
		addIndustry.setIndustryName(dataReq.getIndustryName());
		addIndustry.setCode(dataReq.getCode());
		addIndustry.setCreatedBy(createdById);

		Industry industryAdded;
		try {
			begin();
			industryAdded = industryDao.save(addIndustry);
			commit();
			
			InsertIndustryDtoDataRes data = new InsertIndustryDtoDataRes();
			data.setId(industryAdded.getId());

			InsertIndustryDtoRes dataRes = new InsertIndustryDtoRes();
			dataRes.setData(data);
			
			dataRes.setMessage(stringBuilder("Insert ", industryAdded.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq dataReq) throws Exception {
		Industry updateIndustry = industryDao.findById(dataReq.getId());
		updateIndustry.setIndustryName(dataReq.getIndustryName());
		updateIndustry.setCode(dataReq.getCode());
		updateIndustry.setUpdatedBy(createdById);
		updateIndustry.setVersion(dataReq.getVersion());

		Industry industryUpdated;
		try {
			begin();
			industryUpdated = industryDao.save(updateIndustry);
			commit();
			
			UpdateIndustryDtoDataRes data = new UpdateIndustryDtoDataRes();
			data.setVersion(industryUpdated.getVersion());

			UpdateIndustryDtoRes dataRes = new UpdateIndustryDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Update ", industryUpdated.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetAllIndustryDtoRes findAll() throws Exception {
		List<GetAllIndustryDtoDataRes> datas = new ArrayList<GetAllIndustryDtoDataRes>();
		List<Industry> industrys = industryDao.findAll();

		industrys.forEach(industry -> {
			GetAllIndustryDtoDataRes data = new GetAllIndustryDtoDataRes();
			data.setId(industry.getId());
			data.setIndustryName(industry.getIndustryName());
			data.setCode(industry.getCode());
			data.setIsActive(industry.getIsActive());
			data.setVersion(industry.getVersion());
			datas.add(data);
		});

		GetAllIndustryDtoRes dataRes = new GetAllIndustryDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}

	public GetByIdIndustryDtoRes findById(String id) throws Exception {
		Industry getIndustry = industryDao.findById(id);

		GetByIdIndustryDtoDataRes data = new GetByIdIndustryDtoDataRes();
		data.setId(getIndustry.getId());
		data.setIndustryName(getIndustry.getIndustryName());
		data.setCode(getIndustry.getCode());
		data.setIsActive(getIndustry.getIsActive());
		data.setVersion(getIndustry.getVersion());

		GetByIdIndustryDtoRes dataRes = new GetByIdIndustryDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdIndustryDtoRes deleteById(String id) throws Exception {
		DeleteByIdIndustryDtoRes dataRes = new DeleteByIdIndustryDtoRes();
		try {
			begin();
			boolean isDeleted = industryDao.deleteById(id);
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

}
