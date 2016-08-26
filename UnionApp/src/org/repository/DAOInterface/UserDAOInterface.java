package org.repository.DAOInterface;

import java.util.List;

import javax.sql.DataSource;



import org.repository.entity.UserBO;

public interface UserDAOInterface {
	

	    
	    List<UserBO> doLogin(UserBO ubo,String dbName);
	    

}
