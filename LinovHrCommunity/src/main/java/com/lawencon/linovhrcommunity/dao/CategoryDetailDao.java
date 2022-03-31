package com.lawencon.linovhrcommunity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.model.CategoryDetail;

@Repository
public class CategoryDetailDao extends BaseDaoImpl<CategoryDetail> {

	public List<GetCategoryDetailByThreadDtoRes> getCategoryDetailByThread(String idThread) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tcd.id as idCategoryDetail, tc.id as idCategory, tc.category_name ");
		sql.append("from t_category_detail tcd left join t_category tc on tc.id = tcd.id_category ");
		sql.append("where tcd.id_thread = :idThread ");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("idThread", idThread)
				.getResultList();
		List<GetCategoryDetailByThreadDtoRes> dataRes = new ArrayList<GetCategoryDetailByThreadDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetCategoryDetailByThreadDtoRes resData = new GetCategoryDetailByThreadDtoRes();
			resData.setIdCategoryDetail(obj[0].toString());
			resData.setIdCategory(obj[1].toString());
			resData.setCategoryName(obj[2].toString());
			dataRes.add(resData);
		});
		return dataRes;
	}
	
	public List<GetCategoryDetailByEventCourseDtoRes> getCategoryDetailByEventCourse(String idEventCourse) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tcd.id as idCategoryDetail, tc.id as idCategory, tc.category_name ");
		sql.append("from t_category_detail tcd left join t_category tc on tc.id = tcd.id_category ");
		sql.append("where tcd.id_event_course = :idEventCourse ");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("idEventCourse", idEventCourse)
				.getResultList();
		List<GetCategoryDetailByEventCourseDtoRes> dataRes = new ArrayList<GetCategoryDetailByEventCourseDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetCategoryDetailByEventCourseDtoRes resData = new GetCategoryDetailByEventCourseDtoRes();
			resData.setIdCategoryDetail(obj[0].toString());
			resData.setIdCategory(obj[1].toString());
			resData.setCategoryName(obj[2].toString());
			dataRes.add(resData);
		});
		return dataRes;
	}
}











