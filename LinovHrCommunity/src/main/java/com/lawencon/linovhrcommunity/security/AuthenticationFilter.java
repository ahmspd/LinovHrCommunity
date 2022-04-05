package com.lawencon.linovhrcommunity.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.dto.user.GetUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoDataRes;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoRes;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JwtBuilderComponent jwtBuilderComponent;
	private UserDao userDao;

	public AuthenticationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent jwtBuilderComponent,
			UserDao userDao) {
		this.authenticationManager = authenticationManager;
		this.jwtBuilderComponent = jwtBuilderComponent;
		this.userDao = userDao;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginUserDtoReq loginDto = new LoginUserDtoReq();
		try {
			loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginUserDtoReq.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		try {
			LoginUserDtoDataRes user = userDao.getUserByEmail(authResult.getName());
			String token = jwtBuilderComponent.generateToken(Duration.ofDays(1), user.getId());
			GetUserDtoDataRes loginDtoData = new GetUserDtoDataRes();
			loginDtoData.setId(user.getId());
			loginDtoData.setEmail(user.getEmail());
			loginDtoData.setIsMember(user.getIsMember());
			loginDtoData.setIdRole(user.getIdRole());
			loginDtoData.setRoleCode(user.getRoleCode());
			loginDtoData.setRoleName(user.getRoleName());
			loginDtoData.setIdProfile(user.getIdProfile());
			loginDtoData.setFullName(user.getFullName());
			loginDtoData.setPhoneNumber(user.getPhoneNumber());
			loginDtoData.setIdFile(user.getIdFile());
			loginDtoData.setToken(token);

			LoginUserDtoRes loginDtoRes = new LoginUserDtoRes();
			loginDtoRes.setData(loginDtoData);

			String result = new ObjectMapper().writeValueAsString(loginDtoRes);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().append(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
			LoginUserDtoRes loginDtoRes = new LoginUserDtoRes();
			loginDtoRes.setMessage("Invalid Login");
			
			String loginUserDto = new ObjectMapper().writeValueAsString(loginDtoRes);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(loginUserDto);
	}
}
