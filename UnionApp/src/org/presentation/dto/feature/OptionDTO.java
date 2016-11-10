package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "optiondto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OptionDTO {

	private String detail;

	private String responseid;

	private String responsecount;

	private String optionid;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOptionid() {
		return optionid;
	}

	public void setOptionid(String optionid) {
		this.optionid = optionid;
	}

	public String getResponseid() {
		return responseid;
	}

	public void setResponseid(String responseid) {
		this.responseid = responseid;
	}

	public String getResponsecount() {
		return responsecount;
	}

	public void setResponsecount(String responsecount) {
		this.responsecount = responsecount;
	}

}
