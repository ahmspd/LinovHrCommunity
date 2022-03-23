package com.lawencon.linovhrcommunity.service;

import com.lawencon.base.BaseServiceImpl;

public class BaseServiceLinovCommunityImpl extends BaseServiceImpl  {
	public String generateCode(int input) {
        String codeString = "AEL0987654321";
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < input; i++) {
            int index = (int) (codeString.length() * Math.random());
            sb.append(codeString.charAt(index));
        }
        return sb.toString();
    }
}
