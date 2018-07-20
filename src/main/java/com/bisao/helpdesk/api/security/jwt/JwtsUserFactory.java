package com.bisao.helpdesk.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bisao.helpdesk.api.entity.User;
import com.bisao.helpdesk.api.enums.ProfileEnum;

public class JwtsUserFactory {
	private JwtsUserFactory() {
	}
	
	public static JwtsUser create(User user) {
		return new JwtsUser(user.getId(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getProfile()));
		
	}
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum) {
		List<GrantedAuthority> authorities = 	new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}
}
