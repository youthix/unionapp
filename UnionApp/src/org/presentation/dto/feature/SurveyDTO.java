package org.presentation.dto.feature;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "surveydto")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SurveyDTO {

	private String subject;

	private String createdate;

	private String createtime;

	private String enddate;

	private String endtime;

	private String detail;

	private String creator;

	private String status;

	private String acceptid;

	private String acceptcount;

	private String noresponsecount;

	private String surveyid;

	private List<QuestionDTO> questiondtoLs;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcceptid() {
		return acceptid;
	}

	public void setAcceptid(String acceptid) {
		this.acceptid = acceptid;
	}

	public String getAcceptcount() {
		return acceptcount;
	}

	public void setAcceptcount(String acceptcount) {
		this.acceptcount = acceptcount;
	}

	public String getNoresponsecount() {
		return noresponsecount;
	}

	public void setNoresponsecount(String noresponsecount) {
		this.noresponsecount = noresponsecount;
	}

	public String getSurveyid() {
		return surveyid;
	}

	public void setSurveyid(String surveyid) {
		this.surveyid = surveyid;
	}

	public List<QuestionDTO> getQuestiondtoLs() {
		if (null == this.questiondtoLs) {
			questiondtoLs = new ArrayList<QuestionDTO>();
		}
		return questiondtoLs;
	}

	public void setQuestiondtoLs(List<QuestionDTO> questiondtoLs) {
		this.questiondtoLs = questiondtoLs;
	}

}