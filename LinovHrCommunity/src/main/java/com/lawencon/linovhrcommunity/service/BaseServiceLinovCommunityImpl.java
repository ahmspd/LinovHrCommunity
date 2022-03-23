package com.lawencon.linovhrcommunity.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.base.BaseServiceImpl;

public class BaseServiceLinovCommunityImpl extends BaseServiceImpl {
	
	public String generateCode(int input) {
		String codeString = "AEL0987654321";
		StringBuilder sb = new StringBuilder(input);

		for (int i = 0; i < input; i++) {
			int index = (int) (codeString.length() * Math.random());
			sb.append(codeString.charAt(index));
		}
		return sb.toString();
	}

	public String getExtension(MultipartFile fileName) {
		String extension = fileName.getOriginalFilename().substring(fileName.getOriginalFilename().lastIndexOf(".") + 1,
				fileName.getOriginalFilename().length());
		return extension;
	}
	
	public String stringBuilder(String ... args) {
		StringBuilder str = new StringBuilder();
		
		for (String word:args) {
			str.append(word);
		}
		
		return str.toString();
	}
	
}
