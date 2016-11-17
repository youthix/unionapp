package org.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "surveydetail")

public class SurveyBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "surveyjson")
	@Type(type = "text")
	private String surveyjson;

	@Column(name = "status")
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "surveyid")
	private Integer surveyid;

	public String getSurveyjson() {
		return surveyjson;
	}

	public void setSurveyjson(String surveyjson) {
		this.surveyjson = surveyjson;
	}



	public Integer getSurveyid() {
		return surveyid;
	}

	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
