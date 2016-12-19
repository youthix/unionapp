package org.presentation.dto.feature;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activeuserdto")
public class ActiveUserDTO {
		
	private String usname;	
	private String activedate;	
	private String activetime;
	public String getUsname() {
		return usname;
	}
	public void setUsname(String usname) {
		this.usname = usname;
	}	
	public String getActivedate() {
		return activedate;
	}
	public void setActivedate(String activedate) {
		this.activedate = activedate;
	}
	public String getActivetime() {
		return activetime;
	}
	public void setActivetime(String activetime) {
		this.activetime = activetime;
	}
}
