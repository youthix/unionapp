package org.presentation.dto.feature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visitorinfodto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VisitorInfoDTO {

	private String date;

	private String count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	

}
