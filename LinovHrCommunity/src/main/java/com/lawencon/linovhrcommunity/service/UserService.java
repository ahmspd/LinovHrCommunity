package com.lawencon.linovhrcommunity.service;

import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.model.User;

public class UserService extends BaseServiceLinovCommunityImpl{

	public InsertUserDtoDataRes insert(InsertUserDtoReq data) throws Exception {
		User userData = new User();
		userData.setEmail(data.getEmail());
//		userData.set
		
		return null;
	}
}