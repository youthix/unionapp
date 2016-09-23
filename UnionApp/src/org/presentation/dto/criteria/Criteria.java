package org.presentation.dto.criteria;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "criteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Criteria {

	private String setCriteria;
	
	private FetchUserCriteria fetchUserCriteriaObj ;
	
	private UpdateUserCriteria UpdateUserCriteriaObj ;

	public String getSetCriteria() {
		return setCriteria;
	}

	public void setSetCriteria(String setCriteria) {
		this.setCriteria = setCriteria;
	}


	public FetchUserCriteria getFetchUserCriteriaObj() {
		return fetchUserCriteriaObj;
	}

	public void setFetchUserCriteriaObj(FetchUserCriteria fetchUserCriteriaObj) {
		this.fetchUserCriteriaObj = fetchUserCriteriaObj;
	}

	public UpdateUserCriteria getUpdateUserCriteriaObj() {
		return UpdateUserCriteriaObj;
	}

	public void setUpdateUserCriteriaObj(UpdateUserCriteria updateUserCriteriaObj) {
		UpdateUserCriteriaObj = updateUserCriteriaObj;
	}



}
