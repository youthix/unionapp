package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meetingdetail")
public class MeetingBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "meetdate")
	private Date meetdate;
	
	@Column(name = "meettime")
	private Date meettime;
	
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
	@Column(name = "meetingid")
	private String meetingid;

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


	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getMeetdate() {
		return meetdate;
	}

	public void setMeetdate(Date meetdate) {
		this.meetdate = meetdate;
	}

	public Date getMeettime() {
		return meettime;
	}

	public void setMeettime(Date meettime) {
		this.meettime = meettime;
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

	



}
