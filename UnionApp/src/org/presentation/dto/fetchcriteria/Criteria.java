package org.presentation.dto.fetchcriteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "fetchcriteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Criteria {

	private String setCriteria;
	private String usname;
	private String fname;
	private String lname;
	private String gender;
	private String connumber;
	private String emailid;
	private String status;
	public String getSetCriteria() {
		return setCriteria;
	}
	public void setSetCriteria(String setCriteria) {
		this.setCriteria = setCriteria;
	}
	public String getUsname() {
		return usname;
	}
	public void setUsname(String usname) {
		this.usname = usname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getConnumber() {
		return connumber;
	}
	public void setConnumber(String connumber) {
		this.connumber = connumber;
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



}
