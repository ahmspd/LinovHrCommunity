package com.lawencon.linovhrcommunity.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.bookmark.GetBookmarkDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.model.Bookmark;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Repository
public class BookmarkDao extends BaseDaoImpl<Bookmark> {

	public Bookmark save(Bookmark data) throws Exception {
		return super.save(data);
	}

	public Bookmark findById(String id) throws Exception {
		return getById(id);
	}

	public List<Bookmark> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<GetBookmarkDtoDataRes> getBookmark(String idUser) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select tt.id as idThread , tt.title , tt.contents, tt.id_file as idFile , tf.extensions , tf.contents as fileContents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name, tb.id as idBookmark ");
		sql.append("from t_bookmark tb left join t_thread tt on tb.id_thread = tt.id ");
		sql.append("left join t_file tf on tt.id_file = tf.id left join t_thread_type ttt  ");
		sql.append("on tt.id_thread_type = ttt.id left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tb.created_by = :idUser");

		List<?> results = createNativeQuery(sql.toString()).setParameter("idUser", idUser).getResultList();
		List<GetBookmarkDtoDataRes> dataRes = new ArrayList<GetBookmarkDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetBookmarkDtoDataRes reqData = new GetBookmarkDtoDataRes();
			reqData.setIdThread(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile(obj[3].toString());
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			reqData.setId(obj[11].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}

	public Bookmark getBookmarkThread(String idUser, String idThread) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id, tb.id as bookmarkId ");
		sql.append("from t_bookmark tb left join t_thread tt on tb.id_thread = tt.id ");
		sql.append("left join t_file tf on tt.id_file = tf.id left join t_thread_type ttt  ");
		sql.append("on tt.id_thread_type = ttt.id left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tb.created_by = :idUser ");
		sql.append("and tt.id = :idThread");

		Bookmark bookmark = new Bookmark();
		Object result = null;
		try {
			result = createNativeQuery(sql.toString()).setParameter("idUser", idUser).setParameter("idThread", idThread)
					.getSingleResult();

		} catch (NoResultException e) {
		}
		Object[] obj = (Object[]) result;
		if(obj!=null) {			
			bookmark.setId((obj[1] != null) ? obj[1].toString() : null);
			
			ThreadModel threadModel = new ThreadModel();
			threadModel.setId((obj[0] != null) ? obj[0].toString() : null);
			
			bookmark.setThread(threadModel);
		}
		return bookmark;
	}
	
	public Integer getCountBookmark(String id) throws Exception {
		String sql = "select count(id) from t_bookmark tb where tb.id_thread = :id";
		Object result = null;
		Integer res = 0;
		try {
			result = createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			res = Integer.valueOf(result.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
