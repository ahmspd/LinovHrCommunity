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
import com.lawencon.linovhrcommunity.dto.bookmark.DeleteBookmarkDtoRes;
import com.lawencon.linovhrcommunity.dto.bookmark.GetBookmarkThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.bookmark.GetBookmarkThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.bookmark.InsertBookmarkDtoDataRes;
import com.lawencon.linovhrcommunity.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.linovhrcommunity.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadPageDtoRes;
import com.lawencon.linovhrcommunity.model.Bookmark;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Service
public class BookmarkService extends BaseServiceLinovCommunityImpl {
	private BookmarkDao bookmarkDao;
	private ThreadModelDao threadModelDao;
	private CategoryDetailDao categoryDetailDao;
	private ThreadDetailDao threadDetailDao;
	private LikeDao likeDao;

	@Autowired
	public void setThreadDetailDao(ThreadDetailDao threadDetailDao) {
		this.threadDetailDao = threadDetailDao;
	}

	@Autowired
	public void setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	@Autowired
	public void setCategoryDetailDao(CategoryDetailDao categoryDetailDao) {
		this.categoryDetailDao = categoryDetailDao;
	}

	@Autowired
	public void setThreadModelDao(ThreadModelDao threadModelDao) {
		this.threadModelDao = threadModelDao;
	}

	@Autowired
	public void setBookmarkDao(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	public InsertBookmarkDtoRes insert(InsertBookmarkDtoReq data) throws Exception {
		try {
			ThreadModel threadData = threadModelDao.findById(data.getIdThread());
			Bookmark newBookmark = new Bookmark();
			newBookmark.setThread(threadData);
			newBookmark.setCreatedBy(getIdFromPrincipal());

			begin();
			newBookmark = bookmarkDao.save(newBookmark);
			commit();

			InsertBookmarkDtoDataRes dataRes = new InsertBookmarkDtoDataRes();
			dataRes.setId(newBookmark.getId());

			InsertBookmarkDtoRes result = new InsertBookmarkDtoRes();
			result.setData(dataRes);
			result.setMessage("success");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetAllThreadPageDtoRes getByUser(int startPage, int maxPage) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = bookmarkDao.getBookmark(getIdFromPrincipal(), startPage, maxPage);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

		Integer totalPage = data.size();
		result.setData(data);
		result.setTotal(totalPage);

		return result;
	}

	public DeleteBookmarkDtoRes delete(String id) throws Exception {
		DeleteBookmarkDtoRes result = new DeleteBookmarkDtoRes();
		try {
			begin();
			boolean isDelete = bookmarkDao.deleteById(id);
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

	public GetBookmarkThreadDtoRes getBookmarkThread(String idUser, String idThread) throws Exception {
		Bookmark data = bookmarkDao.getBookmarkThread(idUser, idThread);
		GetBookmarkThreadDtoRes result = new GetBookmarkThreadDtoRes();
		try {
			GetBookmarkThreadDtoDataRes dataRes = new GetBookmarkThreadDtoDataRes();
			dataRes.setIdThread(data.getThread().getId());
			dataRes.setIdBookmark(data.getId());
			result.setData(dataRes);
		} catch (NullPointerException e) {

		}
		return result;
	}
}
