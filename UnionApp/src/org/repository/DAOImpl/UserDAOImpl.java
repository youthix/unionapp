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

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getUpdateUserCriteriaObj() != null) {

					if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("loginstatus")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.loginstatus='" + userBO.getLoginstatus()
								+ "' where usname = '" + userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("deviceid")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.deviceid='" + userBO.getDeviceid()
								+ "' , u.devicetype='" + userBO.getDevicetype() + "' where usname = '"
								+ userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("status")) {
						if (userBO.getStatus().equalsIgnoreCase("delete")) {
							SQL = "delete from " + UserBO.class.getName() + " where usname = '" + userBO.getUsname()
									+ "'";

						} else {
							SQL = "update " + UserBO.class.getName() + " u Set u.status='" + userBO.getStatus()
									+ "' where usname = '" + userBO.getUsname() + "'";
						}

					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("pwd")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.pwd='" + userBO.getPwd()
								+ "' where usname = '" + userBO.getUsname() + "'";
					} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("meeting")) {
						SQL = "update " + UserBO.class.getName() + " u Set u.acceptmeetingid='"
								+ userBO.getAcceptmeetingid() + "' , u.declinemeetingid='"
								+ userBO.getDeclinemeetingid() + "' where usname = '" + userBO.getUsname() + "'";
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

	public ArrayList<UserBO> fetchUser(Criteria criteriaObj, String pageno) {

		System.out.println("InDAOFetchUser");
		ArrayList<UserBO> userBOList = null;

		if (null == pageno || pageno == "") {

			pageno = "1";

		}

		int offsetno;

		int pageSize = 6;

		int pageNo = Integer.parseInt(pageno);

		if (null != pageno && pageno != "" && pageNo > 1) {

			offsetno = (pageNo - 1) * pageSize;
		} else {
			offsetno = 0;

		}

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchUserCriteriaObj() != null) {

					String SQL = "select u from " + UserBO.class.getName() + " u where "
							+ criteriaObj.getFetchUserCriteriaObj().getName() + " = '"
							+ criteriaObj.getFetchUserCriteriaObj().getValue() + "'";

					userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
							.setMaxResults(pageSize) // limit
							.getResultList();
					;

				}
			} else {
				String SQL = "select u from " + UserBO.class.getName() + " u";
				userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
				;
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return userBOList;
	}

	public ArrayList<UserBO> fetchAllUser(Criteria criteriaObj) {

		System.out.println("InDAOFetchUser");
		ArrayList<UserBO> userBOList = null;

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchUserCriteriaObj() != null) {

					String SQL = "select u from " + UserBO.class.getName() + " u where "
							+ criteriaObj.getFetchUserCriteriaObj().getName() + " = '"
							+ criteriaObj.getFetchUserCriteriaObj().getValue() + "'";

					userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).getResultList();
					;

				}
			} else {
				String SQL = "select u from " + UserBO.class.getName() + " u";
				userBOList = (ArrayList<UserBO>) manager.createQuery(SQL).getResultList();
				;
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
