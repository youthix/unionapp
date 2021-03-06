package org.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "userdetail")
public class UserBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "usname")
	private String usname;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "fname")
	private String fn;

	@Column(name = "lname")
	private String ln;

	@Column(name = "joindt")
	private String joindt;

	@Column(name = "age")
	private String age;

	@Column(name = "gender")
	private String gen;

	@Column(name = "address")
	private String add;

	@Column(name = "connumber")
	private String conNu;

	@Id
	@Column(name = "emailid")
	private String emailid;

	@Column(name = "status")
	private String status;

	@Column(name = "role")
	private String role;

	@Column(name = "loginstatus")
	private String loginstatus;

	@Column(name = "deviceid")
	private String deviceid;

	@Column(name = "devicetype")
	private String devicetype;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "acceptmeetingid")
	private String acceptmeetingid;

	@Column(name = "declinemeetingid")
	private String declinemeetingid;

	@Column(name = "acceptactivityid")
	private String acceptactivityid;

	@Column(name = "declineactivityid")
	private String declineactivityid;

	@Column(name = "imgattachment")
	@Type(type = "text")
	private String imgattachment;

	@Column(name = "category")
	private String category;

	@Column(name = "title")
	private String title;

	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

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

	public String getJoindt() {
		return joindt;
	}

	public void setJoindt(String joindt) {
		this.joindt = joindt;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getAcceptactivityid() {
		return acceptactivityid;
	}

	public void setAcceptactivityid(String acceptactivityid) {
		this.acceptactivityid = acceptactivityid;
	}

	public String getDeclineactivityid() {
		return declineactivityid;
	}

	public void setDeclineactivityid(String declineactivityid) {
		this.declineactivityid = declineactivityid;
	}

	public String getImgattachment() {
		return imgattachment;
	}

	public void setImgattachment(String imgattachment) {
		this.imgattachment = imgattachment;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
