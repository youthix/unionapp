package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.MeetingBO;
import org.repository.entity.UserBO;

public interface IMeetingDAO {

	public void createMeeting(MeetingBO meetingBO);
	
	public void update(MeetingBO meetingBO);

	public void updateOnCriteria(MeetingBO meetingBO, Criteria criteriaObj);
	
	public void deleteOnCriteria(MeetingBO meetingBO, Criteria criteriaObj);

	public ArrayList<MeetingBO> fetchMeeting(Criteria criteriaObj, String pageno);
	
	public Integer totalRecordCount();

}
