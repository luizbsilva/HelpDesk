package com.bisao.helpdesk.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bisao.helpdesk.api.enums.StatusEnum;

@Entity
@Table(name = "changeStatus")
public class ChangeStatus implements Serializable {

	private static final long serialVersionUID = 3464307492229791329L;

	@Id
	@Column(name = "id_changeStatus", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_ticket", nullable = false)
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "id_userChange", nullable = false)
	private User userChange;

	@Column(name = "dateChangeStatus", nullable = false)
	private Date dateChangeStatus;

	@Column(name = "status", nullable = false)
	private StatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUserChange() {
		return userChange;
	}

	public void setUserChange(User userChange) {
		this.userChange = userChange;
	}

	public Date getDateChangeStatus() {
		return dateChangeStatus;
	}

	public void setDateChangeStatus(Date dateChangeStatus) {
		this.dateChangeStatus = dateChangeStatus;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
