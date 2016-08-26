package org.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserDetail")
public class UserBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer  id;
	
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
	
	@Column(name = "emailid")
	private String emailid;
	
	@Column(name = "uniqueid")
	private String uId;
	
	@Column(name = "status")
	private String status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
