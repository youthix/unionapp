package org.presentation.dto.criteria;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "criteria")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Criteria {

	private String criteria;

	private FetchUserCriteria fetchUserCriteriaObj;

	private UpdateUserCriteria UpdateUserCriteriaObj;

	private FetchMeetingCriteria fetchMeetingCriteriaObj;

	private UpdateMeetingCriteria UpdateMeetingCriteriaObj;

	private FetchActivityCriteria fetchActivityCriteriaObj;

	private UpdateActivityCriteria UpdateActivityCriteriaObj;

	private FetchNewsLetterCriteria fetchNewsLetterCriteriaObj;

	private FetchSuggestionIdeaCriteria fetchSuggestionIdeaCriteriaObj;

	private UpdateSuggestionIdeaCriteria UpdateSuggestionIdeaCriteriaObj;

	private FetchSummaryCriteria fetchSummaryCriteriaObj;

	private FetchAgreementCriteria fetchAgreementCriteriaObj;

	private FetchAmrCriteria fetchAmrCriteriaObj;

	private FetchPayrateCriteria fetchPayrateCriteriaObj;

	private FetchSurveyCriteria fetchSurveyCriteriaObj;

	private CategoryCriteria categoryCriteriaObj;

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

	public FetchNewsLetterCriteria getFetchNewsLetterCriteriaObj() {
		return fetchNewsLetterCriteriaObj;
	}

	public void setFetchNewsLetterCriteriaObj(FetchNewsLetterCriteria fetchNewsLetterCriteriaObj) {
		this.fetchNewsLetterCriteriaObj = fetchNewsLetterCriteriaObj;
	}

	public FetchSuggestionIdeaCriteria getFetchSuggestionIdeaCriteriaObj() {
		return fetchSuggestionIdeaCriteriaObj;
	}

	public void setFetchSuggestionIdeaCriteriaObj(FetchSuggestionIdeaCriteria fetchSuggestionIdeaCriteriaObj) {
		this.fetchSuggestionIdeaCriteriaObj = fetchSuggestionIdeaCriteriaObj;
	}

	public UpdateSuggestionIdeaCriteria getUpdateSuggestionIdeaCriteriaObj() {
		return UpdateSuggestionIdeaCriteriaObj;
	}

	public void setUpdateSuggestionIdeaCriteriaObj(UpdateSuggestionIdeaCriteria updateSuggestionIdeaCriteriaObj) {
		UpdateSuggestionIdeaCriteriaObj = updateSuggestionIdeaCriteriaObj;
	}

	public FetchSummaryCriteria getFetchSummaryCriteriaObj() {
		return fetchSummaryCriteriaObj;
	}

	public void setFetchSummaryCriteriaObj(FetchSummaryCriteria fetchSummaryCriteriaObj) {
		this.fetchSummaryCriteriaObj = fetchSummaryCriteriaObj;
	}

	public FetchAgreementCriteria getFetchAgreementCriteriaObj() {
		return fetchAgreementCriteriaObj;
	}

	public void setFetchAgreementCriteriaObj(FetchAgreementCriteria fetchAgreementCriteriaObj) {
		this.fetchAgreementCriteriaObj = fetchAgreementCriteriaObj;
	}

	public FetchAmrCriteria getFetchAmrCriteriaObj() {
		return fetchAmrCriteriaObj;
	}

	public void setFetchAmrCriteriaObj(FetchAmrCriteria fetchAmrCriteriaObj) {
		this.fetchAmrCriteriaObj = fetchAmrCriteriaObj;
	}

	public FetchPayrateCriteria getFetchPayrateCriteriaObj() {
		return fetchPayrateCriteriaObj;
	}

	public void setFetchPayrateCriteriaObj(FetchPayrateCriteria fetchPayrateCriteriaObj) {
		this.fetchPayrateCriteriaObj = fetchPayrateCriteriaObj;
	}

	public FetchSurveyCriteria getFetchSurveyCriteriaObj() {
		return fetchSurveyCriteriaObj;
	}

	public void setFetchSurveyCriteriaObj(FetchSurveyCriteria fetchSurveyCriteriaObj) {
		this.fetchSurveyCriteriaObj = fetchSurveyCriteriaObj;
	}

	public CategoryCriteria getCategoryCriteriaObj() {
		return categoryCriteriaObj;
	}

	public void setCategoryCriteriaObj(CategoryCriteria categoryCriteriaObj) {
		this.categoryCriteriaObj = categoryCriteriaObj;
	}

}
