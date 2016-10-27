package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categorydto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CategoryDTO {

	private String catid;

	private String catname;

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



}
