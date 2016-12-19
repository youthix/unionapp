package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activeusersdetail")
public class ActiveUserBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "audid")
	private Integer audid;
	
	@Column(name = "usname")
	private String usname;
	
	@Column(name = "activedate")
	private Date activedate;
	
	@Column(name = "activedatetime")
	private String activedatetime;

	public Integer getAudid() {
		return audid;
	}

	public void setAudid(Integer audid) {
		this.audid = audid;
	}

	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public Date getActivedate() {
		return activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	public String getActivedatetime() {
		return activedatetime;
	}

	public void setActivedatetime(String activedatetime) {
		this.activedatetime = activedatetime;
	}
	
}
