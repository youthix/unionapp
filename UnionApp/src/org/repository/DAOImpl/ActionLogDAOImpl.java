package org.repository.DAOImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IActionLogDAO;
import org.repository.entity.ActionLogBO;
import org.repository.entity.ActiveUserBO;
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

	public ArrayList<ActionLogBO> fetchActionLogForNotification(String datetime) {

		System.out.println("InDAOFetchUser");
		ArrayList<ActionLogBO> actionLogBOList = null;

		try {
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

/*			String SQL = "select u from " + ActionLogBO.class.getName() + " u where actdate >='"
					+ dateformatter.parse(datetime) + "'";*/

			String SQL = "select u from " + ActionLogBO.class.getName() + " u where actdate >='"
					+ datetime + "' and status='online'";

			actionLogBOList = (ArrayList<ActionLogBO>) manager.createQuery(SQL).getResultList();

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return actionLogBOList;
	}

	public ArrayList<ActiveUserBO> fetchActiveUser(ActiveUserBO a) {

		int offsetno = 0;

		int pageSize = 15;

		System.out.println("InDAOFetchActiveUser");
		ArrayList<ActiveUserBO> activeUserBOList = null;

		try {
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String SQL = "select u from " + ActiveUserBO.class.getName() + " u where usname='" + a.getUsname()
					+ "' and activedate='" + dateformatter.format(a.getActivedate()) + "'";

			activeUserBOList = (ArrayList<ActiveUserBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
					.setMaxResults(pageSize) // limit
					.getResultList();

			System.out.println("DoneDAOFetchActiveUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return activeUserBOList;
	}

	public ArrayList<Object> fetchActiveUsersCount() {

		System.out.println("InDAOfetchActiveUsersCount");
		ArrayList<Object> activeUserCountList = null;
		try {

			String SQL = "SELECT count(*) as count,activedate as activedate FROM " + ActiveUserBO.class.getName() + " "
					+ "where activedate > SUBDATE(sysdate(), 15)" + "and activedate < ADDDATE(sysdate(), 1)"
					+ "group by activedate order by activedate desc";

			activeUserCountList = (ArrayList<Object>) manager.createQuery(SQL).getResultList();

			System.out.println("DoneDAOfetchActiveUsersCount");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return activeUserCountList;

	}

	public void addActiveUser(ActiveUserBO activeUserBO) {
		try {
			manager.persist(activeUserBO);

		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void updateActiveUser(ActiveUserBO activeUserBO) {
		try {
			manager.merge(activeUserBO);

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

	@Override
	public BigDecimal fetchDbSize(String dbName) {

		System.out.println("In fetchDbSize");
		BigDecimal size = null;

		try {

			size = (BigDecimal) manager.createNativeQuery("call fetchDbSize()").getSingleResult();

			System.out.println("Done fetchDbSize>>" + size);
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return size;
	}
}
