package com.testi.TodoApp.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "todo_id") private Long id;
	@Column(name = "description") private String description;
	@Column(name = "is_done") private boolean isDone;
	@OneToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "priority_id") private Priority priority;
	@Column(name = "deadline") private Date deadline;
	@Column(name = "created_date", updatable = false) private Timestamp createdDate;
	
	public Todo() {
		
	}
	
	public Todo(String description, boolean isDone, Priority priority, Date deadline, Timestamp createdDate) {
		this.description = description;
		this.isDone = isDone;
		this.priority = priority;
		this.deadline = deadline;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
}
