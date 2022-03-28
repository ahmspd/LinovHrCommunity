package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.BookmarkDao;
import com.lawencon.linovhrcommunity.dao.ProfileDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoReq;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoRes;
import com.lawencon.linovhrcommunity.model.Industry;
import com.lawencon.linovhrcommunity.model.Position;
import com.lawencon.linovhrcommunity.model.Profile;
import com.lawencon.linovhrcommunity.model.Role;
import com.lawencon.linovhrcommunity.model.User;

@Service
public class UserService extends BaseServiceLinovCommunityImpl implements UserDetailsService{

	private UserDao userDao;
	private ProfileDao profileDao;
	private BookmarkDao bookmarkDao;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public UserService(UserDao userDao, ProfileDao profileDao) {
		this.userDao = userDao;
		this.profileDao = profileDao;
	}
//	email
//	password
//	idRole
//	fullName
//	phoneNumber
//	company
//	idIndustry
//	idPosition
	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
		Role roleData = new Role();
		roleData.setId(data.getIdRole());
		roleData.setVersion(0);
		
		User userData = new User();
		userData.setEmail(data.getEmail());
		userData.setRole(roleData);
		String passwordEncode = passwordEncoder.encode(data.getPassword());
		userData.setPassword(passwordEncode);
		userData.setRegistrationCode("123456");
		userData.setIsActive(false);
		
		User users = new User();
		try {
			begin();
			users = userDao.save(userData);
			commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
		if(users.getId() != null) {
			Profile profileData = new Profile();
			profileData.setFullName(data.getFullName());
			profileData.setPhoneNumber(data.getPhoneNumber());
			profileData.setCompany(data.getCompany());
			
			Industry dataIndustry = new Industry();
			dataIndustry.setId(data.getIdIndustry());
			dataIndustry.setVersion(0);
			
			profileData.setIndustry(dataIndustry);
			
			Position dataPosition = new Position();
			dataPosition.setId(data.getIdPosition());
			dataPosition.setVersion(0);
			
			profileData.setPosition(dataPosition);
			
			profileData.setUser(users);
			
			try {
				begin();
				profileDao.save(profileData);
				commit();
			}
			catch(Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}

		}
		
		InsertUserDtoDataRes dataResult = new InsertUserDtoDataRes();
		dataResult.setId(users.getId());
		InsertUserDtoRes result = new InsertUserDtoRes();
		result.setData(dataResult);
		
		return result;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			LoginUserDtoDataRes data = userDao.getUserByEmail(username);
			return new org.springframework.security.core.userdetails.User(data.getEmail(), data.getPassword(),
					new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Invalid Username Or Password");
		}
	}
	
	public RegistrationCodeDtoRes insertRegistraionCode(RegistrationCodeDtoReq data) throws Exception {
		User userData = userDao.getById(data.getId());
		RegistrationCodeDtoRes result = new RegistrationCodeDtoRes();
		if(userData.getRegistrationCode().equals(data.getRegistrationCode())) {
			userData.setIsActive(true);
			
			begin();
			User userUpdate = userDao.save(userData);
			commit();
			
			RegistrationCodeDtoDataRes dataRes = new RegistrationCodeDtoDataRes();
			dataRes.setVersion(userUpdate.getVersion());
			
			result.setData(dataRes);
			result.setMessage("Success");
		}else {
			result.setMessage("Registraion Code Wrong");
		}

		return result;
	}
}