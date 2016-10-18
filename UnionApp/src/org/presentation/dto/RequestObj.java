package org.presentation.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.feature.ActivityList;
import org.presentation.dto.feature.AgreementList;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.feature.NewsLetterList;
import org.presentation.dto.feature.SuggestionIdeaList;
import org.presentation.dto.feature.SummaryList;
import org.presentation.dto.user.UserList;
@XmlRootElement(name = "reqparam")
public class RequestObj {
	
	private UserList UserListObj;
	
	private MeetingList MeetingListObj;
	
	private ActivityList ActivityListObj;
	
	private NewsLetterList NewsLetterListObj;
	
	private SummaryList SummaryListObj;
	
	private SuggestionIdeaList SuggestionIdeaListObj;
	
	private AgreementList AgreementDTOObj;
	
	private String bid;
	
	private String channel;
	
	private String pageno;
	
	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	private Criteria criteria;


	public UserList getUserListObj() {
		return UserListObj;
	}

	public void setUserListObj(UserList userListObj) {
		UserListObj = userListObj;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public MeetingList getMeetingListObj() {
		return MeetingListObj;
	}

	public void setMeetingListObj(MeetingList meetingListObj) {
		MeetingListObj = meetingListObj;
	}

	public ActivityList getActivityListObj() {
		return ActivityListObj;
	}

	public void setActivityListObj(ActivityList activityListObj) {
		ActivityListObj = activityListObj;
	}

	public NewsLetterList getNewsLetterListObj() {
		return NewsLetterListObj;
	}

	public void setNewsLetterListObj(NewsLetterList newsLetterListObj) {
		NewsLetterListObj = newsLetterListObj;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public SuggestionIdeaList getSuggestionIdeaListObj() {
		return SuggestionIdeaListObj;
	}

	public void setSuggestionIdeaListObj(SuggestionIdeaList suggestionIdeaListObj) {
		SuggestionIdeaListObj = suggestionIdeaListObj;
	}

	public SummaryList getSummaryListObj() {
		return SummaryListObj;
	}

	public void setSummaryListObj(SummaryList summaryListObj) {
		SummaryListObj = summaryListObj;
	}

	public AgreementList getAgreementDTOObj() {
		return AgreementDTOObj;
	}

	public void setAgreementDTOObj(AgreementList agreementDTOObj) {
		AgreementDTOObj = agreementDTOObj;
	}
}
