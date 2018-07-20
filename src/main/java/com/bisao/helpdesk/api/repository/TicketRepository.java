package com.bisao.helpdesk.api.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bisao.helpdesk.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {

	Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String userId);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(String title, String status,
			String priority, Pageable pages);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignerUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);

	Page<Ticket> findByNumber(Integer number);

}
