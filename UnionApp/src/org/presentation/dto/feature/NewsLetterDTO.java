package org.presentation.dto.feature;

public class NewsLetterDTO {
	
	private String subject;
	private String nldate;
	private String nltime;
	private String detail;
	private String creator;
	private String status;
	private String nlid;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNldate() {
		return nldate;
	}
	public void setNldate(String nldate) {
		this.nldate = nldate;
	}
	public String getNltime() {
		return nltime;
	}
	public void setNltime(String nltime) {
		this.nltime = nltime;
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
	public String getNlid() {
		return nlid;
	}
	public void setNlid(String nlid) {
		this.nlid = nlid;
	}
}
