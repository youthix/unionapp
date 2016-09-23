package org.presentation.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.user.UserList;
@XmlRootElement(name = "reqparam")
public class RequestObj {
	
	private UserList UserListObj;
	
	private MeetingList MeetingListObj;
	
	private String bid;
	
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


	

}
