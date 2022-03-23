package com.lawencon.linovhrcommunity.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dto.user.AuthorizationDtoDataRes;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	private JwtBuilderComponent jwtBuilderComponent;

	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent jwtBuilderComponent) {
		super(authenticationManager);
		this.jwtBuilderComponent = jwtBuilderComponent;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		String token = header.substring(7);
		String id = null;
		AuthorizationDtoDataRes authorizationDtoDataRes = new AuthorizationDtoDataRes();
		try {
			id = jwtBuilderComponent.getClaimId(token);
		} catch (ExpiredJwtException e) {
			authorizationDtoDataRes.setMessage("Expired Token");
			e.printStackTrace();
		} catch (ClaimJwtException e) {
			authorizationDtoDataRes.setMessage("Invalid Token");
			e.printStackTrace();
		} catch(SignatureException e) {
			authorizationDtoDataRes.setMessage("Invalid Token");
			e.printStackTrace();
		}
		if (authorizationDtoDataRes.getMessage() != null) {
			String authRes = new ObjectMapper().writeValueAsString(authorizationDtoDataRes);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(authRes);
			return;
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(id, null, null);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
	}
}
