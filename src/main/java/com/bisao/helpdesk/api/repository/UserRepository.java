package com.bisao.helpdesk.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bisao.helpdesk.api.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
}
