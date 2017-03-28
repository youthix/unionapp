package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categorydto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CategoryDTO {

	private String catid;

	private String catname;
	
	private String cattype;
	
	private String usercount;
	
	private String action;

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public String getCattype() {
		return cattype;
	}

	public void setCattype(String cattype) {
		this.cattype = cattype;
	}

	public String getUsercount() {
		return usercount;
	}

	public void setUsercount(String usercount) {
		this.usercount = usercount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}



}
