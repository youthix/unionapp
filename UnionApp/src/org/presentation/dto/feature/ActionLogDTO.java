package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "actionlogdto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ActionLogDTO {

	private String subject;

	private String actdate;

	private String acttime;

	private String detail;

	private String creator;

	private String action;

	private String actionid;

	private String module;
	
	private String status;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getActdate() {
		return actdate;
	}

	public void setActdate(String actdate) {
		this.actdate = actdate;
	}

	public String getActtime() {
		return acttime;
	}

	public void setActtime(String acttime) {
		this.acttime = acttime;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionid() {
		return actionid;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
