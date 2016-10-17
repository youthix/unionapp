package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "suggestionideadto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SuggestionIdeaDTO {

	private String subject;

	private String suggideadate;

	private String suggideatime;

	private String detail;

	private String creator;

	private String status;

	private String suggideaid;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSuggideadate() {
		return suggideadate;
	}

	public void setSuggideadate(String suggideadate) {
		this.suggideadate = suggideadate;
	}

	public String getSuggideatime() {
		return suggideatime;
	}

	public void setSuggideatime(String suggideatime) {
		this.suggideatime = suggideatime;
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

	public String getSuggideaid() {
		return suggideaid;
	}

	public void setSuggideaid(String suggideaid) {
		this.suggideaid = suggideaid;
	}



}
