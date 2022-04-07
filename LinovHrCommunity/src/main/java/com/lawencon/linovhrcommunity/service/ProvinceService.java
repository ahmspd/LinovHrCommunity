package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.ProvinceDao;
import com.lawencon.linovhrcommunity.dto.province.GetAllProvinceDtoDataRes;
import com.lawencon.linovhrcommunity.dto.province.GetAllProvinceDtoRes;
import com.lawencon.linovhrcommunity.model.Province;

@Service
public class ProvinceService extends BaseServiceLinovCommunityImpl {
	private ProvinceDao provinceDao;
	
	@Autowired
	public ProvinceService(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	public GetAllProvinceDtoRes getAllProvince() throws Exception {
		List<GetAllProvinceDtoDataRes> dataRes = new ArrayList<GetAllProvinceDtoDataRes>();
		List<Province> listProvince = provinceDao.findAll();
		
		listProvince.forEach(listData ->{
			GetAllProvinceDtoDataRes data = new GetAllProvinceDtoDataRes();
			data.setCode(listData.getCode());
			data.setId(listData.getId());
			data.setProvinceName(listData.getProvinceName());
			dataRes.add(data);
		});
		GetAllProvinceDtoRes result = new GetAllProvinceDtoRes();
		result.setData(dataRes);
		
		return result;
	}
}
