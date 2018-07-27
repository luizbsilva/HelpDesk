package com.bisao.helpdesk.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bisao.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends CrudRepository<ChangeStatus, Long> {

	Iterable<ChangeStatus> findByTicketIdOrderByDateChangeStatusDesc(Long ticketId);
}
