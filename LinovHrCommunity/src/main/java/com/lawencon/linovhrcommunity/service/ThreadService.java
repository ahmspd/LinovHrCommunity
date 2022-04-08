package com.lawencon.linovhrcommunity.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.constant.ThreadTypeCode;
import com.lawencon.linovhrcommunity.dao.BookmarkDao;
import com.lawencon.linovhrcommunity.dao.CategoryDao;
import com.lawencon.linovhrcommunity.dao.CategoryDetailDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.LikeDao;
import com.lawencon.linovhrcommunity.dao.PollingDao;
import com.lawencon.linovhrcommunity.dao.PollingDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dao.ThreadTypeDao;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryThreadDetail;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetail.GetPollingDetailByPollingIdDto;
import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDetailDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateArticleDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateArticleDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.UpdateArticleDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateThreadStatusDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateThreadStatusDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateThreadStatusDtoReq;
import com.lawencon.linovhrcommunity.dto.threaddetail.GetThreadDetailDataDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadPageDtoRes;
import com.lawencon.linovhrcommunity.model.Category;
import com.lawencon.linovhrcommunity.model.CategoryDetail;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.Polling;
import com.lawencon.linovhrcommunity.model.PollingDetail;
import com.lawencon.linovhrcommunity.model.ThreadModel;
import com.lawencon.linovhrcommunity.model.ThreadType;

@Service
public class ThreadService extends BaseServiceLinovCommunityImpl {

	private ThreadTypeDao threadTypeDao;
	private ThreadModelDao threadDao;
	private PollingDao pollingDao;
	private PollingDetailDao pollingDetailDao;
	private FileDao fileDao;
	private CategoryDetailDao categoryDetailDao;
	private CategoryDao categoryDao;
	private ThreadDetailDao threadDetailDao;
	private BookmarkDao bookmarkDao;
	private LikeDao likeDao;

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
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setCategoryDetailDao(CategoryDetailDao categoryDetailDao) {
		this.categoryDetailDao = categoryDetailDao;
	}

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Autowired
	public void setPollingDetailDao(PollingDetailDao pollingDetailDao) {
		this.pollingDetailDao = pollingDetailDao;
	}

	@Autowired
	public void setPollingDao(PollingDao pollingDao) {
		this.pollingDao = pollingDao;
	}

	@Autowired
	public void setThreadDao(ThreadModelDao threadDao) {
		this.threadDao = threadDao;
	}

	@Autowired
	public void setThreadTypeDao(ThreadTypeDao threadTypeDao) {
		this.threadTypeDao = threadTypeDao;
	}

	public InsertThreadDtoRes insert(String content, MultipartFile file) throws Exception {
		InsertThreadDtoReq data = new ObjectMapper().readValue(content, InsertThreadDtoReq.class);
		ThreadType threadType = threadTypeDao.getById(data.getIdThreadType());

		ThreadModel dataThread = new ThreadModel();
		dataThread.setTitle(data.getTitle());
		dataThread.setContents(data.getContents());
		dataThread.setThreadType(threadType);
		if (data.getIsPremium() != null) {
			dataThread.setIsPremium(data.getIsPremium());
		} else {
			dataThread.setIsPremium(false);
		}
		dataThread.setCreatedBy(getIdFromPrincipal());
		if (data.getIsActive() != null) {
			dataThread.setIsActive(data.getIsActive());
		}
		try {
			begin();
			if (file != null) {
				File dataFile = new File();
				String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
						file.getOriginalFilename().length());

				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				dataFile = fileDao.save(dataFile);
				dataThread.setFile(dataFile);
			}
			dataThread = threadDao.save(dataThread);
			if (threadType.getCode().equals(ThreadTypeCode.POLLING.getDetail())) {
				Polling dataPolling = new Polling();
				dataPolling.setThreadModel(dataThread);
				dataPolling.setPollingName(data.getPollingName());
				dataPolling.setCreatedBy(getIdFromPrincipal());

				Polling pollingSave = pollingDao.save(dataPolling);

				List<InsertPollingDetailDtoReq> listPollingDetail = data.getDataPolling();

				for (int i = 0; i < listPollingDetail.size(); i++) {
					PollingDetail dataPollingDetail = new PollingDetail();
					dataPollingDetail.setPolling(pollingSave);
					dataPollingDetail.setPollingName(listPollingDetail.get(i).getPollingName());
					dataPollingDetail.setCreatedBy(getIdFromPrincipal());
					pollingDetailDao.save(dataPollingDetail);
				}
			}

			List<GetAllCategoryThreadDetail> listCategory = data.getDataCategory();
			for (int i = 0; i < listCategory.size(); i++) {
				Category dataCategory = new Category();
				dataCategory = categoryDao.getById(listCategory.get(i).getId());
				CategoryDetail dataCategoryDetail = new CategoryDetail();
				dataCategoryDetail.setCategory(dataCategory);
				dataCategoryDetail.setThreadModel(dataThread);
				dataCategoryDetail.setCreatedBy(getIdFromPrincipal());
				dataCategoryDetail = categoryDetailDao.save(dataCategoryDetail);
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		InsertThreadDtoDataRes resultData = new InsertThreadDtoDataRes();
		resultData.setId(dataThread.getId());

		InsertThreadDtoRes result = new InsertThreadDtoRes();
		result.setData(resultData);
		result.setMessage("Success");

		return result;
	}

	public GetAllThreadPageDtoRes getThreadByUser(String idUser,int startPage, int maxPage) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = threadDao.getThreadByUser(idUser, startPage, maxPage);
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
	
	public GetAllThreadPageDtoRes getAllThreadWithPage(int startPage, int maxPage) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = threadDao.getAllThreadWithPage(startPage, maxPage);
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

	public GetThreadDtoRes getThreadPremium(Boolean isPremium) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadPremium(isPremium);
		for (int i = 0; i < data.size(); i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
					.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadDtoRes getThreadByType(String idType) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadByType(idType);
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
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadDtoRes getArticleNotAccpet(String idType) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getArticleNotAccept(idType);
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
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadDtoRes getAllThread() throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getAllThread();
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
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadPollingDtoRes getAllThreadPolling() throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPolling(ThreadTypeCode.POLLING.getDetail());
		for (int i = 0; i < data.size(); i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
					.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadPollingDtoRes getThreadPollingByUser(String idUser) throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPollingByUser(ThreadTypeCode.POLLING.getDetail(),
				idUser);
		for (int i = 0; i < data.size(); i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
					.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);

		return result;
	}

	public GetThreadPollingDetailDtoRes getThreadPollingById(String idUser) throws Exception {
		GetThreadPollingDetailDtoDataRes data = threadDao.getDetailThreadPolling(ThreadTypeCode.POLLING.getDetail(),
				idUser);
		List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
				.getCategoryDetailByThread(data.getId());
		List<GetPollingDetailByPollingIdDto> pollingDetail = pollingDetailDao
				.getPollingDetailByIdPolling(data.getIdPolling());
		data.setDataCategoryDetail(categoryDetail);
		data.setDataPollingDetail(pollingDetail);
		GetThreadPollingDetailDtoRes result = new GetThreadPollingDetailDtoRes();
		result.setData(data);
		return result;
	}

	public UpdateThreadStatusDtoRes updateStatus(UpdateThreadStatusDtoReq dataReq) throws Exception {
		ThreadModel threadModel = threadDao.findById(dataReq.getId());
		threadModel.setIsActive(dataReq.getIsActive());
		threadModel.setUpdatedBy(getIdFromPrincipal());
		try {
			begin();
			threadModel = threadDao.save(threadModel);
			commit();
			UpdateThreadStatusDtoDataRes dataRes = new UpdateThreadStatusDtoDataRes();
			dataRes.setVersion(threadModel.getVersion());
			UpdateThreadStatusDtoRes result = new UpdateThreadStatusDtoRes();
			result.setData(dataRes);
			result.setMessage("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateArticleDtoRes updateArticle(UpdateArticleDtoReq data) throws Exception {
		ThreadModel dataThread = threadDao.findById(data.getId());
		dataThread.setTitle(data.getTitle());
		dataThread.setContents(data.getContents());
		try {
			begin();
			dataThread = threadDao.save(dataThread);
			commit();
			UpdateArticleDtoDataRes dataRes = new UpdateArticleDtoDataRes();
			dataRes.setVersion(dataThread.getVersion());
			UpdateArticleDtoRes result = new UpdateArticleDtoRes();
			result.setData(dataRes);
			result.setMessage("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetThreadDetailDtoRes getThreadDetail(String idThread) throws Exception {
		GetThreadDataDtoRes data = threadDao.getThreadDetail(idThread);
//		List<GetThreadDataDtoRes> data = threadDao.getThreadByType(idType);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		// Format LocalDateTime to String
//		for(int i =0; i<data.size();i++) {
		String id = data.getId();
		String formattedDateTime = data.getCreatedAt().format(dateTimeFormatter);
		Integer totalCommet = threadDetailDao.getCountComment(id);
		Integer totalBookmark = bookmarkDao.getCountBookmark(id);
		Integer totalLike = likeDao.getCountLike(id);
		data.setDate(formattedDateTime);
		data.setComment(totalCommet);
		data.setBookmark(totalBookmark);
		data.setLike(totalLike);

		List<GetThreadDetailDataDtoRes> threadComment = threadDetailDao.getThreadDetailData(idThread);
		List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao
				.getCategoryDetailByThread(data.getId());
		List<GetPollingDetailByPollingIdDto> pollingDetail = pollingDetailDao
				.getPollingDetailByIdPolling(data.getIdPolling());
		data.setDataCategoryDetail(categoryDetail);
		data.setDataThreadComment(threadComment);
		data.setDataThreadPolling(pollingDetail);
		GetThreadDetailDtoRes result = new GetThreadDetailDtoRes();
		result.setData(data);
		return result;
	}

	public GetAllThreadPageDtoRes getAllArticleWithPage(String idType, int startPage, int maxPage) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = threadDao.getThreadByTypeWithPage(idType, startPage, maxPage);
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

		Integer totalPage = threadDao.getCountThreadByType(idType);
		result.setData(data);
		result.setTotal(totalPage);

		return result;
	}

	public GetAllThreadPageDtoRes getAllArticleActiveWithPage(String idType, int startPage, int maxPage,
			Boolean isActive) throws Exception {
		GetAllThreadPageDtoRes result = new GetAllThreadPageDtoRes();

		List<GetThreadDataDtoRes> data = threadDao.getThreadByTypeWithPage(idType, startPage, maxPage, isActive);
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

		Integer totalPage = threadDao.getCountThreadByType(idType, isActive);
		result.setData(data);
		result.setTotal(totalPage);

		return result;
	}
}
