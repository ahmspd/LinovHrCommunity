package com.lawencon.linovhrcommunity.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	public Authentication getAuthentication();
}
