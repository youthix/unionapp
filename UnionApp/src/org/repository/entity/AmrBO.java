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
@Table(name = "amrdetail")

public class AmrBO  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "amrdate")
	private Date amrdate;
	
	@Column(name = "detail")
	@Type(type="text")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "status")
	private String status;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "amrid")
	private Integer amrid;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getAmrdate() {
		return amrdate;
	}

	public void setAmrdate(Date amrdate) {
		this.amrdate = amrdate;
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

	public Integer getAmrid() {
		return amrid;
	}

	public void setAmrid(Integer amrid) {
		this.amrid = amrid;
	}
}
