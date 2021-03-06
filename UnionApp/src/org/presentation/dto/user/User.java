package org.presentation.dto.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
	private String devicetype;
	private String newpwd;
	private String city;
	private String zipcode;
	private String acceptmeetingid;
	private String declinemeetingid;
	private String title;
	private String category;
	private String imageurl;

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

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getAcceptmeetingid() {
		return acceptmeetingid;
	}

	public void setAcceptmeetingid(String acceptmeetingid) {
		this.acceptmeetingid = acceptmeetingid;
	}

	public String getDeclinemeetingid() {
		return declinemeetingid;
	}

	public void setDeclinemeetingid(String declinemeetingid) {
		this.declinemeetingid = declinemeetingid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

}
