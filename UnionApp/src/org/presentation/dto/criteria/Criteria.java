package org.presentation.dto.criteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "criteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Criteria {

	private String setCriteria;
	private String emailid;
	private String status;
	private String role;
	private String loginstatus;
	private String updatefield;

	public String getSetCriteria() {
		return setCriteria;
	}

	public void setSetCriteria(String setCriteria) {
		this.setCriteria = setCriteria;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	public String getUpdatefield() {
		return updatefield;
	}

	public void setUpdatefield(String updatefield) {
		this.updatefield = updatefield;
	}
	

}
