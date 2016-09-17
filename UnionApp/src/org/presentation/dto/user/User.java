package org.presentation.dto.user;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "user")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {

	private String usNa;
	private String pwd;
	private String fn;
	private String ln;
	private String joinDt;
	private String age;
	private String gen;
	private String add;
	private String conNu;
	private String emId;
	private String uId;
	private String status;
	private String role;
	private String loginstatus;
	private String deviceid;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public String getJoinDt() {
		return joinDt;
	}

	public void setJoinDt(String joinDt) {
		this.joinDt = joinDt;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getConNu() {
		return conNu;
	}

	public void setConNu(String conNu) {
		this.conNu = conNu;
	}

	public String getEmId() {
		return emId;
	}

	public void setEmId(String emId) {
		this.emId = emId;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsNa() {
		return usNa;
	}

	public void setUsNa(String usNa) {
		this.usNa = usNa;
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

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	
}
