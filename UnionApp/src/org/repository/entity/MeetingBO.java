package org.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	private String meetdate;
	
	@Column(name = "meettime")
	private String meettime;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "venue")
	private String venue;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "attendee")
	private String attendee;

	@Id
	@Column(name = "meetingid")
	private String meetingid;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMeetdate() {
		return meetdate;
	}

	public void setMeetdate(String meetdate) {
		this.meetdate = meetdate;
	}

	public String getMeettime() {
		return meettime;
	}

	public void setMeettime(String meettime) {
		this.meettime = meettime;
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

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
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
	
	


}
