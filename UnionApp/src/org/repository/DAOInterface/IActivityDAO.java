package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.ActivityBO;

public interface IActivityDAO {

	public void createActivity(ActivityBO activityBO);
	
	public void update(ActivityBO activityBO);

	public void updateOnCriteria(ActivityBO activityBO, Criteria criteriaObj);
	
	public void deleteOnCriteria(ActivityBO activityBO, Criteria criteriaObj);

	public ArrayList<ActivityBO> fetchActivity(Criteria criteriaObj, String pageno);
	
	public Integer totalRecordCount(Criteria criteriaObj);
	
	public void deleteCron(String beforeLimit);

}
