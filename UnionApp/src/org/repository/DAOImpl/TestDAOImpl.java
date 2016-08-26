package org.repository.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.presentation.util.ServiceException;
import org.repository.DAOInterface.ITestDAO;
import org.repository.entity.UserBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository 
@Transactional
public class TestDAOImpl implements ITestDAO{
	
	@PersistenceContext    
	private EntityManager manager;
	    
	public void addUser(UserBO userBO)
	{
		try {
			System.out.println("InDAOAddUser");
			manager.persist(userBO);
			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage() );
			throw serviceExceptionObj;
		}
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	    

}
