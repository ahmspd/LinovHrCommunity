package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.CityDao;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoDataRes;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoRes;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityDtoDataRes;
import com.lawencon.linovhrcommunity.dto.city.GetAllCityDtoRes;

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
	
	public GetAllCityDtoRes getAllCity() throws Exception {
		List<GetAllCityDtoDataRes> dataRes = cityDao.getAllCity();
		GetAllCityDtoRes result = new GetAllCityDtoRes();
		result.setData(dataRes);
		
		return result;
	}
}
