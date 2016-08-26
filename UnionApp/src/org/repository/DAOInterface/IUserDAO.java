package org.repository.DAOInterface;

import org.presentation.dto.user.User;
import org.repository.entity.UserBO;

public interface IUserDAO {
	
	public void addUser(UserBO userBO); 
	
	
	public void fetchUser(UserBO userBO); 
	
	public UserBO fetchUserByParam(User userdto);
	    

}
