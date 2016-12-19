package org.presentation.dto.feature;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activeuserdto")
public class ActiveUserDTO {
		
	private String usname;	
	private Date activedate;	
	private String activetime;
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
	public String getActivetime() {
		return activetime;
	}
	public void setActivetime(String activetime) {
		this.activetime = activetime;
	}
}
