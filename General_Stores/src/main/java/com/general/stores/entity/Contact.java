package com.general.stores.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message = "Name can't be blank.")
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull(message = "Email can't be blank.")
	@Size(max = 50)
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull(message = "Subject can't be blank.")
	@Size(max = 50)
	@Column(name = "subject", nullable = false)
	private String subject;
	
	@NotNull(message = "Message Name can't be blank.")
	@Size(max = 1000)
	@Column(name = "message", nullable = false)
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contact_date", nullable = false)
	private Date contactDate;

	public Contact() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", message="
				+ message + ", contactDate=" + contactDate + "]";
	}


}
