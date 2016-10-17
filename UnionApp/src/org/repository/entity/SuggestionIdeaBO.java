package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "suggestionideadetail")
public class SuggestionIdeaBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "suggideadate")
	private Date suggideadate;
	
	@Column(name = "detail")
	@Type(type="text")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "status")
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "suggideaid")
	private Integer suggideaid;

	public String getSubject() {
		return subject;
	}

	public Date getSuggideadate() {
		return suggideadate;
	}

	public void setSuggideadate(Date suggideadate) {
		this.suggideadate = suggideadate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSuggideaid() {
		return suggideaid;
	}

	public void setSuggideaid(Integer suggideaid) {
		this.suggideaid = suggideaid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}



}
