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
@Table(name = "actionlogdetail")
public class ActionLogBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "subject")
	private String subject;

	@Column(name = "actdate")
	private Date actdate;

	@Column(name = "detail")
	@Type(type = "text")
	private String detail;

	@Column(name = "creator")
	private String creator;

	@Column(name = "action")
	private String action;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actionid")
	private Integer actionid;

	@Column(name = "module")
	private String module;
	
	@Column(name = "status")
	private String status;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getActdate() {
		return actdate;
	}

	public void setActdate(Date actdate) {
		this.actdate = actdate;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getActionid() {
		return actionid;
	}

	public void setActionid(Integer actionid) {
		this.actionid = actionid;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
