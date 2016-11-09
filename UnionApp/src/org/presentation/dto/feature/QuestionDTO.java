package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questiondto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QuestionDTO {

	private String subject;

	private String detail;

	private String questionid;

	private List<OptionDTO> optiondtoLs;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public List<OptionDTO> getOptiondtoLs() {

		if (null == this.optiondtoLs) {
			optiondtoLs = new ArrayList<OptionDTO>();
		}
		return optiondtoLs;
	}

	public void setOptiondtoLs(List<OptionDTO> optiondtoLs) {
		this.optiondtoLs = optiondtoLs;
	}

}
