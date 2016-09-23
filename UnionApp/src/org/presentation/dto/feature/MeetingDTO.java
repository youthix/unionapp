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
	
	private String attendee;

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


}
