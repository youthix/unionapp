package org.repository.DAOImpl;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.criteria.FetchUserCriteria;
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

	public void update(UserBO userBO) {
		try {
			System.out.println("InDAOAddUser");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(userBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void updateOnCriteria(UserBO userBO, Criteria criteriaObj) {
		try {
			System.out.println("DoneDAOUpdateLoginStatus");
			String SQL = "";

			if (null != criteriaObj.getSetCriteria() && criteriaObj.getSetCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getUpdateUserCriteriaObj() != null) {

					if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("loginstatus")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.loginstatus='" + userBO.getLoginstatus()
								+ "' where usname = '" + userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("deviceid")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.deviceid='" + userBO.getDeviceid()
								+ "' and u.devicetype='" +userBO.getDeviceType() +"' where usname = '" + userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("status")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.status='" + userBO.getStatus()
								+ "' where usname = '" + userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("pwd")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.pwd='" + userBO.getPwd()
								+ "' where usname = '" + userBO.getUsname() + "'";
					}
				}
			}
			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("DoneDAOUpdateLoginStatus");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public ArrayList<UserBO> fetchUser(Criteria criteriaObj) {

		System.out.println("InDAOFetchUser");
		ArrayList<UserBO> userBOList = null;

		try {

			/*
			 * CriteriaBuilder cb = manager.getCriteriaBuilder();
			 * CriteriaQuery<UserBO> cq = cb.createQuery(UserBO.class);
			 * 
			 * Root<UserBO> c = cq.from(UserBO.class); cq.select(c);
			 * 
			 * 
			 * if (null != criteriaObj.getSetCriteria() &&
			 * criteriaObj.getSetCriteria().equalsIgnoreCase("True")) { if (null
			 * != criteriaObj.getEmailid() && criteriaObj.getEmailid() != "") {
			 * 
			 * cq.where(cb.equal(c.get("emailid"), criteriaObj.getEmailid()));
			 * 
			 * }
			 * 
			 * if (null != criteriaObj.getRole() && criteriaObj.getRole() != "")
			 * {
			 * 
			 * cq.where(cb.equal(c.get("role"), criteriaObj.getRole()));
			 * 
			 * }
			 * 
			 * if (null != criteriaObj.getStatus() && criteriaObj.getStatus() !=
			 * "") {
			 * 
			 * cq.where(cb.equal(c.get("status"), criteriaObj.getStatus()));
			 * 
			 * }
			 * 
			 * if (null != criteriaObj.getLoginstatus() &&
			 * criteriaObj.getLoginstatus() != "") {
			 * 
			 * cq.where(cb.equal(c.get("loginstatus"),
			 * criteriaObj.getLoginstatus()));
			 * 
			 * }
			 * 
			 * }
			 */

			if (null != criteriaObj.getSetCriteria() && criteriaObj.getSetCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchUserCriteriaObj() != null) {

					String SQL = "select u from " + UserBO.class.getName() + " u where "
							+ criteriaObj.getFetchUserCriteriaObj().getName() + " = '"
							+ criteriaObj.getFetchUserCriteriaObj().getValue() + "'";

					userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).getResultList();

				} 
			}
			else {
				String SQL = "select u from " + UserBO.class.getName() + " u";
				userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return userBOList;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
