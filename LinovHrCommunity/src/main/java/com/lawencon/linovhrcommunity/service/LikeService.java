package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.CategoryDetailDao;
import com.lawencon.linovhrcommunity.dao.LikeDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.like.DeleteLikeDtoRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeDtoRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.like.InsertLikeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.like.InsertLikeDtoReq;
import com.lawencon.linovhrcommunity.dto.like.InsertLikeDtoRes;
import com.lawencon.linovhrcommunity.model.Like;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Service
public class LikeService extends BaseServiceLinovCommunityImpl {
	private LikeDao likeDao;
	private ThreadModelDao threadModelDao;
	private CategoryDetailDao categoryDetailDao;

	@Autowired
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	@Autowired
	public void setThreadModelDao(ThreadModelDao threadModelDao) {
		this.threadModelDao = threadModelDao;
	}

	@Autowired
	public void setCategoryDetailDao(CategoryDetailDao categoryDetailDao) {
		this.categoryDetailDao = categoryDetailDao;
	}
	
	public InsertLikeDtoRes insert(InsertLikeDtoReq data) throws Exception {
		try {
			ThreadModel threadData = threadModelDao.findById(data.getIdThread());
			Like newLike = new Like();
			newLike.setIdThread(threadData);
			newLike.setCreatedBy(getIdFromPrincipal());
			
			begin();
			newLike = likeDao.save(newLike);
			commit();
			
			InsertLikeDtoDataRes dataRes = new InsertLikeDtoDataRes();
			dataRes.setId(newLike.getId());
			
			InsertLikeDtoRes result = new InsertLikeDtoRes();
			result.setData(dataRes);
			result.setMessage("success");
			
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetLikeDtoRes getByUser(String idUser) throws Exception {
		List<GetLikeDtoDataRes> dataRes = likeDao.getThreadLike(idUser);
		for (int i = 0; i < dataRes.size(); i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
					.getCategoryDetailByThread(dataRes.get(i).getId());
			dataRes.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetLikeDtoRes result = new GetLikeDtoRes();
		result.setData(dataRes);
		return result;
	}
	
	public DeleteLikeDtoRes delete(String id) throws Exception {
		DeleteLikeDtoRes result = new DeleteLikeDtoRes();
		try {
			begin();
			boolean isDelete = likeDao.deleteById(id);
			commit();
			if (isDelete) {
				result.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public GetLikeThreadDtoRes getBookmarkThread(String idUser, String idThread) throws Exception {
		Like data = likeDao.getLikeThread(idUser, idThread);
		GetLikeThreadDtoRes result = new GetLikeThreadDtoRes();
		try {
			GetLikeThreadDtoDataRes dataRes = new GetLikeThreadDtoDataRes();
			dataRes.setIdThread(data.getIdThread().getId());
			dataRes.setIdLike(data.getId());
			result.setData(dataRes);
		}
		catch(NullPointerException e) {
			
		}
		return result;
	}
}
