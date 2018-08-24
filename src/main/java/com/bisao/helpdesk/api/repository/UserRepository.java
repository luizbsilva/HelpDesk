package com.bisao.helpdesk.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bisao.helpdesk.api.entity.User;

@RepositoryRestResource(collectionResourceRel = "cliente", path = "clientes")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(@Param("email") String email);
}
