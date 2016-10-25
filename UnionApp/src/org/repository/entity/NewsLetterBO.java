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
@Table(name = "newsletterdetail")

public class NewsLetterBO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "subject")
	private String subject;

	@Column(name = "nldate")
	private Date nldate;

	@Column(name = "detail")
	@Type(type = "text")
	private String detail;

	@Column(name = "creator")
	private String creator;

	@Column(name = "status")
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nlid")
	private Integer nlid;
	
	@Column(name = "attachmentstatus")
	private String attachmentstatus;	
	
	@Column(name = "docattachment")
	@Type(type = "text")
	private String docattachment;
	
	@Column(name = "imgattachment")
	@Type(type = "text")
	private String imgattachment;	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public Integer getNlid() {
		return nlid;
	}

	public void setNlid(Integer nlid) {
		this.nlid = nlid;
	}

	public Date getNldate() {
		return nldate;
	}

	public void setNldate(Date nldate) {
		this.nldate = nldate;
	}

	public String getAttachmentstatus() {
		return attachmentstatus;
	}

	public void setAttachmentstatus(String attachmentstatus) {
		this.attachmentstatus = attachmentstatus;
	}

	public String getDocattachment() {
		return docattachment;
	}

	public void setDocattachment(String docattachment) {
		this.docattachment = docattachment;
	}

	public String getImgattachment() {
		return imgattachment;
	}

	public void setImgattachment(String imgattachment) {
		this.imgattachment = imgattachment;
	}
	
	
}
