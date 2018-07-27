package com.bisao.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bisao.helpdesk.api.entity.User;
import com.bisao.helpdesk.api.repository.UserRepository;
import com.bisao.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findById(long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		this.userRepository.delete(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		// Pageable pages = new PageRequest(page, count);
		return null;// this.userRepository.findAll(pages);
	}

}
