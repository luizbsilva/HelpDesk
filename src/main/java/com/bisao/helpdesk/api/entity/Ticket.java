package com.bisao.helpdesk.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bisao.helpdesk.api.enums.PriorityEnum;
import com.bisao.helpdesk.api.enums.StatusEnum;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable{

	private static final long serialVersionUID = -6651447324481737880L;

	@Id
	@Column(name = "id_ticket", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;


	@Column(name = "date_ticket", nullable = false)
	private Date date;


	@Column(name = "title", nullable = false)
	private String title;


	@Column(name = "number", nullable = false)
	private Integer number;


	@Column(name = "status", nullable = false)
	private StatusEnum status;


	@Column(name = "priority", nullable = false)
	private PriorityEnum priority;

	@ManyToOne
	@JoinColumn(name = "id_assignedUser", nullable = false)
	private User assignedUser;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "image", nullable = true)
	private String image;

	@Transient
	private List<ChangeStatus> changes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	}
}
