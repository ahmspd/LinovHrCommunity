package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.CityDao;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoDataRes;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoRes;

@Service
public class CityService extends BaseServiceLinovCommunityImpl {
	private CityDao cityDao;

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	
	public GetAllCityByProvinceDtoRes getAllCityByProvince(String codeProvince) throws Exception {
		List<GetAllCityByProvinceDtoDataRes> dataRes = cityDao.getAllCityByProvince(codeProvince);
		GetAllCityByProvinceDtoRes result = new GetAllCityByProvinceDtoRes();
		result.setData(dataRes);
		
		return result;
	}
}
