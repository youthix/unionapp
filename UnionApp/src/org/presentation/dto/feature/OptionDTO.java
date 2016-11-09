package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "optiondto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OptionDTO {

	private String detail;

	private String respondentid;

	private String respondentcount;

	private String optionid;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRespondentid() {
		return respondentid;
	}

	public void setRespondentid(String respondentid) {
		this.respondentid = respondentid;
	}

	public String getRespondentcount() {
		return respondentcount;
	}

	public void setRespondentcount(String respondentcount) {
		this.respondentcount = respondentcount;
	}

	public String getOptionid() {
		return optionid;
	}

	public void setOptionid(String optionid) {
		this.optionid = optionid;
	}

}
