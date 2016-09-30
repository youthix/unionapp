package org.presentation.dto.criteria;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "criteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Criteria {

	private String criteria;
	
	private FetchUserCriteria fetchUserCriteriaObj ;
	
	private UpdateUserCriteria UpdateUserCriteriaObj ;
	
	private FetchMeetingCriteria fetchMeetingCriteriaObj ;
	
	private UpdateMeetingCriteria UpdateMeetingCriteriaObj ;
	
	private FetchActivityCriteria fetchActivityCriteriaObj ;
	
	private UpdateActivityCriteria UpdateActivityCriteriaObj ;


	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
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

	public FetchMeetingCriteria getFetchMeetingCriteriaObj() {
		return fetchMeetingCriteriaObj;
	}

	public void setFetchMeetingCriteriaObj(FetchMeetingCriteria fetchMeetingCriteriaObj) {
		this.fetchMeetingCriteriaObj = fetchMeetingCriteriaObj;
	}

	public UpdateMeetingCriteria getUpdateMeetingCriteriaObj() {
		return UpdateMeetingCriteriaObj;
	}

	public void setUpdateMeetingCriteriaObj(UpdateMeetingCriteria updateMeetingCriteriaObj) {
		UpdateMeetingCriteriaObj = updateMeetingCriteriaObj;
	}

	public FetchActivityCriteria getFetchActivityCriteriaObj() {
		return fetchActivityCriteriaObj;
	}

	public void setFetchActivityCriteriaObj(FetchActivityCriteria fetchActivityCriteriaObj) {
		this.fetchActivityCriteriaObj = fetchActivityCriteriaObj;
	}

	public UpdateActivityCriteria getUpdateActivityCriteriaObj() {
		return UpdateActivityCriteriaObj;
	}

	public void setUpdateActivityCriteriaObj(UpdateActivityCriteria updateActivityCriteriaObj) {
		UpdateActivityCriteriaObj = updateActivityCriteriaObj;
	}



}
