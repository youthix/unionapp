package org.presentation.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.presentation.dto.feature.ActionLogList;
import org.presentation.dto.feature.ActivityList;
import org.presentation.dto.feature.AgreementList;
import org.presentation.dto.feature.AmrList;
import org.presentation.dto.feature.CategoryList;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.feature.NewsLetterList;
import org.presentation.dto.feature.PayrateList;
import org.presentation.dto.feature.SuggestionIdeaList;
import org.presentation.dto.feature.SummaryList;
import org.presentation.dto.feature.SurveyList;
import org.presentation.dto.user.UserList;

@XmlRootElement(name = "resparam")
// @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseObj {

	private UserList UserListObj;

	private MeetingList MeetingListObj;

	private ActivityList activityListObj;

	private NewsLetterList newsLetterListObj;

	private SummaryList summaryListObj;

	private SuggestionIdeaList suggestionIdeaListObj;

	private AgreementList agreementListObj;

	private AmrList amrListObj;

	private PayrateList payrateListObj;

	private CategoryList CategoryListObj;

	private SurveyList SurveyListObj;
	private ActionLogList ActionLogListObj;

	private String totalPage;

	ResStatus resStatus;

	public ResStatus getResStatus() {
		return resStatus;
	}

	public void setResStatus(ResStatus resStatus) {
		this.resStatus = resStatus;
	}

	public UserList getUserListObj() {
		return UserListObj;
	}

	public void setUserListObj(UserList userListObj) {
		UserListObj = userListObj;
	}

	public MeetingList getMeetingListObj() {
		return MeetingListObj;
	}

	public void setMeetingListObj(MeetingList meetingListObj) {
		MeetingListObj = meetingListObj;
	}

	public ActivityList getActivityListObj() {
		return activityListObj;
	}

	public void setActivityListObj(ActivityList activityListObj) {
		this.activityListObj = activityListObj;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public NewsLetterList getNewsLetterListObj() {
		return newsLetterListObj;
	}

	public void setNewsLetterListObj(NewsLetterList newsLetterListObj) {
		this.newsLetterListObj = newsLetterListObj;
	}

	public SuggestionIdeaList getSuggestionIdeaListObj() {
		return suggestionIdeaListObj;
	}

	public void setSuggestionIdeaListObj(SuggestionIdeaList suggestionIdeaListObj) {
		this.suggestionIdeaListObj = suggestionIdeaListObj;
	}

	public SummaryList getSummaryListObj() {
		return summaryListObj;
	}

	public void setSummaryListObj(SummaryList summaryListObj) {
		this.summaryListObj = summaryListObj;
	}

	public AgreementList getAgreementListObj() {
		return agreementListObj;
	}

	public void setAgreementListObj(AgreementList agreementListObj) {
		this.agreementListObj = agreementListObj;
	}

	public AmrList getAmrListObj() {
		return amrListObj;
	}

	public void setAmrListObj(AmrList amrListObj) {
		this.amrListObj = amrListObj;
	}

	public PayrateList getPayrateListObj() {
		return payrateListObj;
	}

	public void setPayrateListObj(PayrateList payrateListObj) {
		this.payrateListObj = payrateListObj;
	}

	public CategoryList getCategoryListObj() {
		return CategoryListObj;
	}

	public void setCategoryListObj(CategoryList categoryListObj) {
		CategoryListObj = categoryListObj;
	}

	public SurveyList getSurveyListObj() {
		return SurveyListObj;
	}

	public void setSurveyListObj(SurveyList surveyListObj) {
		SurveyListObj = surveyListObj;
	}

	public ActionLogList getActionLogListObj() {
		return ActionLogListObj;
	}

	public void setActionLogListObj(ActionLogList actionLogListObj) {
		ActionLogListObj = actionLogListObj;
	}

}
