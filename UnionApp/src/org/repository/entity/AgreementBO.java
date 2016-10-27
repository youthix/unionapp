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
@Table(name = "agreementdetail")
public class AgreementBO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "armdate")
	private Date armdate;
	
	@Column(name = "detail")
	@Type(type="text")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "status")
	private String status;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "armid")
	private Integer armid;

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

	public Date getArmdate() {
		return armdate;
	}

	public void setArmdate(Date armdate) {
		this.armdate = armdate;
	}

	public Integer getArmid() {
		return armid;
	}

	public void setArmid(Integer armid) {
		this.armid = armid;
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
