package com.bisao.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.bisao.helpdesk.api.entity.User;

public interface UserService {

	User findByEmail(String email);

	User createOrUpdate(User user);

	User findById(long id);

	void delete(long id);

	Page<User> findAll(int page, int count);
}
