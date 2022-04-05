package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.BookmarkDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.IndustryDao;
import com.lawencon.linovhrcommunity.dao.PositionDao;
import com.lawencon.linovhrcommunity.dao.ProfileDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoReq;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoRes;
import com.lawencon.linovhrcommunity.model.City;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.Industry;
import com.lawencon.linovhrcommunity.model.Position;
import com.lawencon.linovhrcommunity.model.Profile;
import com.lawencon.linovhrcommunity.model.Province;
import com.lawencon.linovhrcommunity.model.Role;
import com.lawencon.linovhrcommunity.model.User;

@Service
public class UserService extends BaseServiceLinovCommunityImpl implements UserDetailsService{

	private UserDao userDao;
	private ProfileDao profileDao;
	private BookmarkDao bookmarkDao;
	private FileDao fileDao;
	private IndustryDao industryDao;
	private PositionDao positionDao;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public UserService(UserDao userDao, ProfileDao profileDao, BookmarkDao bookmarkDao, FileDao fileDao,
						IndustryDao industryDao, PositionDao positionDao) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.bookmarkDao = bookmarkDao;
		this.fileDao = fileDao;
		this.positionDao = positionDao;
		this.industryDao = industryDao;
	}
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
			
			Industry dataIndustry = industryDao.getById(data.getIdIndustry());
			
			profileData.setIndustry(dataIndustry);
			
			Position dataPosition = positionDao.getById(data.getIdPosition());
			
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
	
	public GetUserDtoRes getByUserId(String idUser) throws Exception {
		GetUserDtoDataRes data = userDao.getUserByIs(idUser);
		GetUserDtoRes result = new GetUserDtoRes();
		result.setData(data);
		return result;
	}
	
	public UpdateUserDtoRes updateUser(String content, MultipartFile file) throws Exception {
		UpdateUserDtoReq data = new ObjectMapper().readValue(content, UpdateUserDtoReq.class);
		
		Profile profileData = profileDao.findById(data.getId());
		profileData.setFullName(data.getFullName());
		profileData.setPhoneNumber(data.getPhoneNumber());
		profileData.setPostalCode(data.getPostalCode());
		
		Industry industryData = new Industry();
		industryData.setId(data.getIdIndustry());
		industryData.setVersion(0);
		
		Position positionData = new Position();
		positionData.setId(data.getIdPosition());
		positionData.setVersion(0);
		
		Province provinceData = new Province();
		provinceData.setId(data.getId());
		provinceData.setVersion(0);
		
		City cityData = new City();
		cityData.setId(data.getId());
		cityData.setVersion(0);
		
		profileData.setIndustry(industryData);
		profileData.setPosition(positionData);
		profileData.setProvince(provinceData);
		profileData.setCity(cityData);
		
		try {
			begin();
				File fileSave = new File();
				if(file != null) {
					File dataFile = new File();
					String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					dataFile.setExtensions(extName);
					dataFile.setContents(file.getBytes());
					dataFile.setCreatedBy(getIdFromPrincipal());
					fileSave = fileDao.save(dataFile);
				}
				profileData.setFile(fileSave);
				profileData = profileDao.save(profileData);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		UpdateUserDtoDataRes dataRes = new UpdateUserDtoDataRes();
		dataRes.setVersion(profileData.getVersion());
		UpdateUserDtoRes result = new UpdateUserDtoRes();
		result.setMessage("success");
		result.setData(dataRes);
		
		return result;
	}
	
	public UpdateUserDtoRes updateByRegistraionCode(UpdateUserDtoReq data) throws Exception {
		User userData = userDao.findById(data.getId());
		userData.setIsActive(true);
		try {
			begin();
			userData = userDao.save(userData);
			commit();
			
			UpdateUserDtoDataRes dataRes = new UpdateUserDtoDataRes();
			dataRes.setVersion(userData.getVersion());

			UpdateUserDtoRes result = new UpdateUserDtoRes();
			result.setData(dataRes);
			result.setMessage(stringBuilder("Update ", userData.getEmail(), " Success !"));
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}