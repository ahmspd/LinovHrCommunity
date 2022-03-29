package com.lawencon.linovhrcommunity.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoDataRes;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Repository
public class ThreadModelDao extends BaseDaoImpl<ThreadModel> {

	public ThreadModel save(ThreadModel data) throws Exception {
		return super.save(data);
	}

	public ThreadModel findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadModel> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<GetThreadDataDtoRes> getAllThread() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents, tt.id_file as idFile , tf.extensions , tf.contents as fileContents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id");

		List<?> results = createNativeQuery(sql.toString()).getResultList();
		List<GetThreadDataDtoRes> dataRes = new ArrayList<GetThreadDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDataDtoRes reqData = new GetThreadDataDtoRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetThreadDataDtoRes> getThreadByUser(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents, tt.id_file as idFile , tf.extensions , tf.contents as fileContents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tu.id = :id");

		List<?> results = createNativeQuery(sql.toString()).setParameter("id", id).getResultList();
		List<GetThreadDataDtoRes> dataRes = new ArrayList<GetThreadDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDataDtoRes reqData = new GetThreadDataDtoRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetThreadDataDtoRes> getThreadPremium(Boolean isPremium) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents, tt.id_file as idFile , tf.extensions , tf.contents as fileContents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tt.is_premium = :isPremium");

		List<?> results = createNativeQuery(sql.toString()).setParameter("isPremium", isPremium).getResultList();
		List<GetThreadDataDtoRes> dataRes = new ArrayList<GetThreadDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDataDtoRes reqData = new GetThreadDataDtoRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetThreadDataDtoRes> getThreadByType(String idType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents, tt.id_file as idFile , tf.extensions , tf.contents as fileContents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id where tt.id_thread_type = :idType");

		List<?> results = createNativeQuery(sql.toString()).setParameter("idType", idType).getResultList();
		List<GetThreadDataDtoRes> dataRes = new ArrayList<GetThreadDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDataDtoRes reqData = new GetThreadDataDtoRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(((Timestamp) obj[8]).toLocalDateTime());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetThreadPollingDtoDataRes> getAllThreadPolling(String codeThread) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents as threadContent, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name, tpl.polling_name, tpl.id as tplId ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id  ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id ");
		sql.append("left join t_polling tpl on tpl.id_thread = tt.id ");
		sql.append("where tt.id_thread_type = (select id from t_thread_type ttt where ttt.code= :codeThread);");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("codeThread", codeThread)
				.getResultList();
		List<GetThreadPollingDtoDataRes> dataRes = new ArrayList<GetThreadPollingDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadPollingDtoDataRes reqData = new GetThreadPollingDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(obj[8].toString());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			reqData.setPollingName(obj[11].toString());
			reqData.setIdPolling(obj[12].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetThreadPollingDtoDataRes> getAllThreadPollingByUser(String codeThread, String idUser) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tt.id , tt.title , tt.contents as threadContent, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name, tpl.polling_name, tpl.id as tplId ");
		sql.append("from t_thread tt left join t_file tf on tt.id_file = tf.id  ");
		sql.append("left join t_thread_type ttt on tt.id_thread_type = ttt.id  ");
		sql.append("left join t_user tu on tu.id = tt.created_by ");
		sql.append("left join t_profile tp on tp.id_user = tu.id ");
		sql.append("left join t_polling tpl on tpl.id_thread = tt.id ");
		sql.append("where tt.id_thread_type = (select id from t_thread_type ttt where ttt.code= :codeThread) ");
		sql.append("and tt.created_by = :idUser ");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("idUser", idUser)
				.setParameter("codeThread", codeThread)
				.getResultList();
		List<GetThreadPollingDtoDataRes> dataRes = new ArrayList<GetThreadPollingDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadPollingDtoDataRes reqData = new GetThreadPollingDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setTitle(obj[1].toString());
			reqData.setContents(obj[2].toString());
			reqData.setIdFile((obj[3]!=null)? obj[3].toString():null);
			reqData.setThreadTypeName(obj[6].toString());
			reqData.setIsPremium(Boolean.valueOf(obj[7].toString()));
			reqData.setCreatedAt(obj[8].toString());
			reqData.setCreatedBy(obj[9].toString());
			reqData.setFullName(obj[10].toString());
			reqData.setPollingName(obj[11].toString());
			reqData.setIdPolling(obj[12].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
}