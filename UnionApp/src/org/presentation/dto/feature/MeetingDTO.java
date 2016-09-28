package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meetingdto")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MeetingDTO {

	private String subject;
	
	private String meetdate;
	
	private String meettime;
	
	private String detail;
	
	private String creator;
	
	private String venue;
	
	private String status;
	
	private String acceptid;
	
	private String acceptcount;	
	
	private String declineid;	
	
	private String declinecount;	
	
	private String noresponsecount;
	

	private String meetingid;
	
	private String acceptdenyind;
	

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


	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getAcceptid() {
		return acceptid;
	}

	public void setAcceptid(String acceptid) {
		this.acceptid = acceptid;
	}

	public String getAcceptcount() {
		return acceptcount;
	}

	public void setAcceptcount(String acceptcount) {
		this.acceptcount = acceptcount;
	}

	public String getDeclineid() {
		return declineid;
	}

	public void setDeclineid(String declineid) {
		this.declineid = declineid;
	}

	public String getDeclinecount() {
		return declinecount;
	}

	public void setDeclinecount(String declinecount) {
		this.declinecount = declinecount;
	}

	public String getAcceptdenyind() {
		return acceptdenyind;
	}

	public void setAcceptdenyind(String acceptdenyind) {
		this.acceptdenyind = acceptdenyind;
	}

	public String getNoresponsecount() {
		return noresponsecount;
	}

	public void setNoresponsecount(String noresponsecount) {
		this.noresponsecount = noresponsecount;
	}

	

}
