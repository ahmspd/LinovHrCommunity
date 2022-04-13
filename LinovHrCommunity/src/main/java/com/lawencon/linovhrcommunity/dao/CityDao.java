package com.lawencon.linovhrcommunity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoDataRes;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityDtoDataRes;
import com.lawencon.linovhrcommunity.model.City;

@Repository
public class CityDao extends BaseDaoImpl<City> {

	public City save(City data) throws Exception {
		return super.save(data);
	}

	public City findById(String id) throws Exception {
		return getById(id);
	}

	public List<City> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<GetAllCityByProvinceDtoDataRes> getAllCityByProvince(String codeProvince) throws Exception {
		String sql = "select tc.id, tc.code , tc.city_name from t_city tc where tc.code_province = :codeProvince";
		List<?> results = createNativeQuery(sql)
				.setParameter("codeProvince", codeProvince)
				.getResultList();
		List<GetAllCityByProvinceDtoDataRes> dataRes = new ArrayList<GetAllCityByProvinceDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllCityByProvinceDtoDataRes reqData = new GetAllCityByProvinceDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setCode(obj[1].toString());
			reqData.setCityName(obj[2].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
	
	public List<GetAllCityDtoDataRes> getAllCity() throws Exception {
		String sql = "select tc.id, tc.code , tc.city_name from t_city tc";
		List<?> results = createNativeQuery(sql)
				.getResultList();
		List<GetAllCityDtoDataRes> dataRes = new ArrayList<GetAllCityDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllCityDtoDataRes reqData = new GetAllCityDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setCode(obj[1].toString());
			reqData.setCityName(obj[2].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
}
