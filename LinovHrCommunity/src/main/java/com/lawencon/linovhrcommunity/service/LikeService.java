package com.lawencon.linovhrcommunity.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.BookmarkDao;
import com.lawencon.linovhrcommunity.dao.CategoryDetailDao;
import com.lawencon.linovhrcommunity.dao.LikeDao;
import com.lawencon.linovhrcommunity.dao.ThreadDetailDao;
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
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadPageDtoRes;
import com.lawencon.linovhrcommunity.model.Like;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Service
public class LikeService extends BaseServiceLinovCommunityImpl {
	private LikeDao likeDao;
	private ThreadModelDao threadModelDao;
	private CategoryDetailDao categoryDetailDao;
	private BookmarkDao bookmarkDao;
	private ThreadDetailDao threadDetailDao;
	private ThreadModelDao threadDao;

	@Autowired
	public void setBookmarkDao(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}
	@Autowired
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}
	@Autowired
	public void setThreadDetailDao(ThreadDetailDao threadDetailDao) {
		this.threadDetailDao = threadDetailDao;
	}

	@Autowired
	public void setThreadModelDao(ThreadModelDao threadModelDao) {
		this.threadModelDao = threadModelDao;
	}
	@Autowired
	public void setThreadDao(ThreadModelDao threadDao) {
		this.threadDao = threadDao;
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
	
	public GetAllThreadPageDtoRes getByUser(int startPage, int maxPage) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = likeDao.getThreadLike(getIdFromPrincipal(), startPage, maxPage);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		// Format LocalDateTime to String
		for (int i = 0; i < data.size(); i++) {
			String id = data.get(i).getId();
			String formattedDateTime = data.get(i).getCreatedAt().format(dateTimeFormatter);
			Integer totalCommet = threadDetailDao.getCountComment(id);
			Integer totalBookmark = bookmarkDao.getCountBookmark(id);
			Integer totalLike = likeDao.getCountLike(id);
			data.get(i).setDate(formattedDateTime);
			data.get(i).setComment(totalCommet);
			data.get(i).setBookmark(totalBookmark);
			data.get(i).setLike(totalLike);
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
					.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}

		Integer totalPage = threadDao.getCountAllThread();
		result.setData(data);
		result.setTotal(totalPage);

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
