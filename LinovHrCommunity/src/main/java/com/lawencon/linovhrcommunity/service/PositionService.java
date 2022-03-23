package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PositionDao;
import com.lawencon.linovhrcommunity.dto.position.DeleteByIdPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.GetAllPositionDtoDataRes;
import com.lawencon.linovhrcommunity.dto.position.GetAllPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.GetByIdPositionDtoDataRes;
import com.lawencon.linovhrcommunity.dto.position.GetByIdPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.InsertPositionDtoDataRes;
import com.lawencon.linovhrcommunity.dto.position.InsertPositionDtoReq;
import com.lawencon.linovhrcommunity.dto.position.InsertPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.UpdatePositionDtoDataRes;
import com.lawencon.linovhrcommunity.dto.position.UpdatePositionDtoReq;
import com.lawencon.linovhrcommunity.dto.position.UpdatePositionDtoRes;
import com.lawencon.linovhrcommunity.model.Position;

@Service
public class PositionService extends BaseServiceLinovCommunityImpl {

	private PositionDao positionDao;
	private String createdById = "1";

	@Autowired
	public PositionService(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public InsertPositionDtoRes insert(InsertPositionDtoReq dataReq) throws Exception {
		Position addPosition = new Position();
		addPosition.setPositionName(dataReq.getPositionName());
		addPosition.setCode(dataReq.getCode());
		addPosition.setCreatedBy(createdById);

		Position positionAdded;
		try {
			begin();
			positionAdded = positionDao.save(addPosition);
			commit();
			
			InsertPositionDtoDataRes data = new InsertPositionDtoDataRes();
			data.setId(positionAdded.getId());

			InsertPositionDtoRes dataRes = new InsertPositionDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Insert ", positionAdded.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdatePositionDtoRes update(UpdatePositionDtoReq dataReq) throws Exception {
		Position updatePosition = positionDao.findById(dataReq.getId());
		updatePosition.setPositionName(dataReq.getPositionName());
		updatePosition.setCode(dataReq.getCode());
		updatePosition.setUpdatedBy(createdById);
		updatePosition.setVersion(dataReq.getVersion());

		Position positionUpdated;
		try {
			begin();
			positionUpdated = positionDao.save(updatePosition);
			commit();
			
			UpdatePositionDtoDataRes data = new UpdatePositionDtoDataRes();
			data.setVersion(positionUpdated.getVersion());

			UpdatePositionDtoRes dataRes = new UpdatePositionDtoRes();
			dataRes.setData(data);
			dataRes.setMessage(stringBuilder("Update ", positionUpdated.getCode(), " Success !"));
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetAllPositionDtoRes findAll() throws Exception {
		List<GetAllPositionDtoDataRes> datas = new ArrayList<GetAllPositionDtoDataRes>();
		List<Position> positions = positionDao.findAll();

		positions.forEach(position -> {
			GetAllPositionDtoDataRes data = new GetAllPositionDtoDataRes();
			data.setId(position.getId());
			data.setPositionName(position.getPositionName());
			data.setCode(position.getCode());
			data.setIsActive(position.getIsActive());
			data.setVersion(position.getVersion());
			datas.add(data);
		});

		GetAllPositionDtoRes dataRes = new GetAllPositionDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}

	public GetByIdPositionDtoRes findById(String id) throws Exception {
		Position getPosition = positionDao.findById(id);

		GetByIdPositionDtoDataRes data = new GetByIdPositionDtoDataRes();
		data.setId(getPosition.getId());
		data.setPositionName(getPosition.getPositionName());
		data.setCode(getPosition.getCode());
		data.setIsActive(getPosition.getIsActive());
		data.setVersion(getPosition.getVersion());

		GetByIdPositionDtoRes dataRes = new GetByIdPositionDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdPositionDtoRes deleteById(String id) throws Exception {
		DeleteByIdPositionDtoRes dataRes = new DeleteByIdPositionDtoRes();
		try {
			begin();
			boolean isDeleted = positionDao.deleteById(id);
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
