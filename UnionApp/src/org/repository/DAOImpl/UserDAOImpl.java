package org.repository.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.presentation.dto.user.User;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IUserDAO;
import org.repository.entity.UserBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements IUserDAO {

	@PersistenceContext
	private EntityManager manager;

	public void addUser(UserBO userBO) {
		try {
			System.out.println("InDAOAddUser");
			manager.persist(userBO);
			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public UserBO fetchUserByParam(User userDTO) {

		UserBO userBOObj = null;
		try {

			System.out.println("InDAOloginUser");
			
/*			public List findWithName(String name) {
			    return em.createQuery(
			        "SELECT c FROM Customer c WHERE c.name LIKE :custName")
			        .setParameter("custName", name)
			        .getResultList();
			}*/
			 
			String SQL = "select u from " +  UserBO.class.getName() + " u where usname = '" + userDTO.getUsNa() + "'";
			//"select p from " + Payment.class.getName() + " p"
			userBOObj = (UserBO) manager.createQuery(SQL).getSingleResult();

			System.out.println("DoneDAOloginUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return userBOObj;
	}

	public void fetchUser(UserBO userBO) {
		try {
			System.out.println("InDAOAddUser");
			String SQL = "select * from unionapp.`userdetail` where connumber = '%" + userBO.getConNu() + "'";
			userBO = (UserBO) manager.createQuery(SQL).getSingleResult();

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
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
