package org.repository.DAOInterface;

import java.util.ArrayList;

import org.presentation.dto.criteria.Criteria;
import org.repository.entity.UserBO;

public interface IUserDAO {

	public void addUser(UserBO userBO);

	//public UserBO fetchUserByParam(User userdto);
	
	public void update(UserBO userBO);
	
	public void updateOnCriteria(UserBO userBO,Criteria criteriaObj );
	
/*	public void updateLoginStatus(UserBO userBO);
	
	public void updateDeviceId(UserBO userBO);*/

	public ArrayList<UserBO> fetchUser(Criteria criteriaObj);

}
