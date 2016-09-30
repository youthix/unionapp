package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activitydetail")
public class ActivityBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "actdate")
	private Date actdate;
	
	@Column(name = "acttime")
	private Date acttime;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "venue")
	private String venue;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "acceptid")
	private String acceptid;
	
	@Column(name = "acceptcount")
	private Integer acceptcount;
	
	@Column(name = "declineid")
	private String declineid;	
	
	@Column(name = "declinecount")
	private Integer declinecount;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activityid")
	private Integer activityid;

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

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getAcceptid() {
		return acceptid;
	}

	public void setAcceptid(String acceptid) {
		this.acceptid = acceptid;
	}


	public String getDeclineid() {
		return declineid;
	}

	public void setDeclineid(String declineid) {
		this.declineid = declineid;
	}

	public Integer getAcceptcount() {
		return acceptcount;
	}

	public void setAcceptcount(Integer acceptcount) {
		this.acceptcount = acceptcount;
	}

	public Integer getDeclinecount() {
		return declinecount;
	}

	public void setDeclinecount(Integer declinecount) {
		this.declinecount = declinecount;
	}


	public Date getActdate() {
		return actdate;
	}

	public void setActdate(Date actdate) {
		this.actdate = actdate;
	}

	public Date getActtime() {
		return acttime;
	}

	public void setActtime(Date acttime) {
		this.acttime = acttime;
	}

	public Integer getActivityid() {
		return activityid;
	}

	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}




}
