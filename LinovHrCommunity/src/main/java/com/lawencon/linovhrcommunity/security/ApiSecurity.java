package com.lawencon.linovhrcommunity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lawencon.linovhrcommunity.dao.UserDao;
import com.lawencon.linovhrcommunity.service.UserService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	private JwtBuilderComponent jwtBuilderComponent;
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setJwtBuilderComponent(JwtBuilderComponent jwtBuilderComponent) {
		this.jwtBuilderComponent = jwtBuilderComponent;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(super.authenticationManager(), jwtBuilderComponent, userDao);
		http.addFilter(authenticationFilter);
		
		AuthorizationFilter authorizationFilter = new AuthorizationFilter(super.authenticationManager(), jwtBuilderComponent);
		http.addFilter(authorizationFilter);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST,"/users");
		web.ignoring().antMatchers(HttpMethod.GET,"/positions");
		web.ignoring().antMatchers(HttpMethod.GET,"/industries");
		web.ignoring().antMatchers(HttpMethod.GET,"/users/{id}");
		web.ignoring().antMatchers(HttpMethod.PUT,"/users/update");
		web.ignoring().antMatchers(HttpMethod.GET,"/threads/**");
		web.ignoring().antMatchers(HttpMethod.GET,"/categories");
		web.ignoring().antMatchers(HttpMethod.GET,"/files/**");
		web.ignoring().antMatchers(HttpMethod.PUT,"/users/forgot-password");
	}
}
