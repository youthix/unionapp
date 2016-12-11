package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IActionLogDAO;
import org.repository.entity.ActionLogBO;
import org.repository.entity.MeetingBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ActionLogDAOImpl implements IActionLogDAO {

	@PersistenceContext
	private EntityManager manager;

	public void addActionLog(ActionLogBO actioinLogBO) {
		try {
			manager.persist(actioinLogBO);

		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public ArrayList<ActionLogBO> fetchActionLog() {

		int offsetno = 0;

		int pageSize = 15;

		System.out.println("InDAOFetchUser");
		ArrayList<ActionLogBO> actionLogBOList = null;

		try {

			String SQL = "select u from " + ActionLogBO.class.getName() + " u order by u.actdate desc";

			actionLogBOList = (ArrayList<ActionLogBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
					.setMaxResults(pageSize) // limit
					.getResultList();

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return actionLogBOList;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
