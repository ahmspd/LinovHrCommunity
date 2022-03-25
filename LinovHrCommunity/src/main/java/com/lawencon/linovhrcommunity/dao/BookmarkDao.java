package com.lawencon.linovhrcommunity.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.model.Bookmark;

@Repository
public class BookmarkDao extends BaseDaoImpl<Bookmark>{

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
	
	public List<GetThreadDataDtoRes> getBookmark(String idUser) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name ");
		sql.append("from t_bookmark tb left join t_thread tt on tb.id_thread = tt.id ");
		sql.append("left join t_file tf on tt.id_file = tf.id left join t_thread_type ttt  ");
		sql.append("on tt.id_thread_type = ttt.id left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tb.created_by = :idUser");

		List<?> results = createNativeQuery(sql.toString()).setParameter("idUser", idUser).getResultList();
		List<GetThreadDataDtoRes> dataRes = new ArrayList<GetThreadDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDataDtoRes reqData = new GetThreadDataDtoRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile(obj[3].toString());
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
}

