package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.BookmarkDao;
import com.lawencon.linovhrcommunity.dao.CityDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.IndustryDao;
import com.lawencon.linovhrcommunity.dao.PositionDao;
import com.lawencon.linovhrcommunity.dao.ProfileDao;
import com.lawencon.linovhrcommunity.dao.ProvinceDao;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.email.EmailTemplate;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoReq;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdatePasswordDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.UpdatePasswordDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UpdatePasswordDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UserForgotPasswordDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.UserForgotPasswordDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UserForgotPasswordDtoRes;
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
	private ProvinceDao provinceDao;
	private CityDao cityDao;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public UserService(UserDao userDao, ProfileDao profileDao, BookmarkDao bookmarkDao, FileDao fileDao,
						IndustryDao industryDao, PositionDao positionDao,ProvinceDao provinceDao,CityDao cityDao) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.bookmarkDao = bookmarkDao;
		this.fileDao = fileDao;
		this.positionDao = positionDao;
		this.industryDao = industryDao;
		this.provinceDao = provinceDao;
		this.cityDao = cityDao;
	}
	
	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
		ExecutorService excecutorService = Executors.newFixedThreadPool(1);
		
		Role roleData = new Role();
		roleData.setId(data.getIdRole());
		roleData.setVersion(0);
		
		User userData = new User();
		userData.setEmail(data.getEmail());
		userData.setRole(roleData);
		String passwordEncode = passwordEncoder.encode(data.getPassword());
		userData.setPassword(passwordEncode);
		String registrationCode = generateCode(6);
		userData.setRegistrationCode(registrationCode);
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
				profileData = profileDao.save(profileData);
				commit();
			}
			catch(Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}

			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setFrom("LawenconCommunity");
			emailTemplate.setSubject("Registration Code");
			emailTemplate.setTo(users.getEmail());
			Map<String, Object> model = new HashMap<>();
			model.put("profileName", profileData.getFullName());
			model.put("userEmail", users.getEmail());
			model.put("registrationCode", registrationCode);
			emailTemplate.setModel(model);
			
			excecutorService.submit(()->{			
				sendEmail("image/Security_Flatline.png","EmailTemplateRegistration.flth",emailTemplate);
			});
			excecutorService.shutdown();
		}
		
		InsertUserDtoDataRes dataResult = new InsertUserDtoDataRes();
		dataResult.setId(users.getId());
		InsertUserDtoRes result = new InsertUserDtoRes();
		result.setData(dataResult);
		
		return result;
	}
	
	public UpdatePasswordDtoRes updatePassword(UpdatePasswordDtoReq dataReq) throws Exception {
		User userData = userDao.findById(dataReq.getId());
		String passwordEncode = passwordEncoder.encode(dataReq.getPassword());
		userData.setPassword(passwordEncode);
		try {
			begin();
			userData = userDao.save(userData);
			commit();
			UpdatePasswordDtoDataRes dataRes = new UpdatePasswordDtoDataRes();
			dataRes.setVersion(userData.getVersion());
			UpdatePasswordDtoRes result = new UpdatePasswordDtoRes();
			result.setData(dataRes);
			result.setMessage("success");
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
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
		GetUserDtoDataRes data = new ObjectMapper().readValue(content, GetUserDtoDataRes.class);
		
		Profile profileData = profileDao.findById(data.getIdProfile());
		profileData.setFullName(data.getFullName());
		profileData.setPhoneNumber(data.getPhoneNumber());
		profileData.setPostalCode(data.getPostalCode());
		
		
		if(data.getIdPosition()!=null) {
			Industry industryData = industryDao.findById(data.getIdIndustry());
			profileData.setIndustry(industryData);			
		}
		if(data.getIdPosition()!=null) {			
			Position positionData = positionDao.findById(data.getIdPosition());
			profileData.setPosition(positionData);
		}
		if(data.getIdProvince()!=null) {
			Province provinceData = provinceDao.findById(data.getIdProvince());			
			profileData.setProvince(provinceData);
		}
		if(data.getIdCity()!=null) {			
			City cityData = cityDao.findById(data.getIdCity());
			profileData.setCity(cityData);
		}
		
		profileData.setPhoneNumber(data.getPhoneNumber());
		profileData.setPostalCode(data.getPostalCode());
		profileData.setCompany(data.getCompany());
		profileData.setTwitter(data.getTwitter());
		profileData.setInstagram(data.getInstagram());
		profileData.setFacebook(data.getFacebook());
		
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
					profileData.setFile(fileSave);
				}
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
	
	public UserForgotPasswordDtoRes forgotPassword(UserForgotPasswordDtoReq dataReq) throws Exception {
		ExecutorService excecutorService = Executors.newFixedThreadPool(1);
		UserForgotPasswordDtoRes result = new UserForgotPasswordDtoRes();
		LoginUserDtoDataRes userData = userDao.getUserByEmail(dataReq.getEmail());
		UserForgotPasswordDtoDataRes dataRes = new UserForgotPasswordDtoDataRes();
//		if(userData!=null) {			
			try {
				User user = userDao.findById(userData.getId());
				String passwordGenerate = generateCode(8);
				String passwordEncode = passwordEncoder.encode(passwordGenerate);
				user.setPassword(passwordEncode);
				begin();
				user = userDao.save(user);
				commit();
				
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setFrom("LawenconCommunity");
				emailTemplate.setSubject("New Password");
				emailTemplate.setTo(userData.getEmail());
				Map<String, Object> model = new HashMap<>();
				model.put("profileName", userData.getFullName());
				model.put("userEmail", userData.getEmail());
				model.put("newPassword", passwordGenerate);
				emailTemplate.setModel(model);
				
				excecutorService.submit(()->{			
					sendEmail("image/new-password.png","EmailTemplateUserMemberConfirm.flth",emailTemplate);
				});
				excecutorService.shutdown();
				
				dataRes.setVersion(user.getVersion());
				result.setData(dataRes);
				result.setMessage("success");
			}
			catch (Exception e) {
//				e.printStackTrace();
				rollback();
				throw new Exception(e);
				
			}
		return result;
	}
}