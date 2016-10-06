package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IActivityDAO;
import org.repository.entity.ActivityBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ActivityDAOImpl implements IActivityDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createActivity(ActivityBO activityBO) {
		try {
			System.out.println("InDAOCreateMeet");
			manager.persist(activityBO);
			System.out.println("DoneDAOCreateMeet");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(ActivityBO activityBO) {
		try {
			System.out.println("InDAOAddUser");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(activityBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void updateOnCriteria(ActivityBO activityBO, Criteria criteriaObj) {
		try {

			String SQL = "";

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getUpdateActivityCriteriaObj() != null) {

					if (criteriaObj.getUpdateActivityCriteriaObj().getName().equalsIgnoreCase("acceptdecline")) {
						SQL = "update " + ActivityBO.class.getName() + " m Set m.acceptid='" + activityBO.getAcceptid()
								+ "' , m.acceptcount='" + activityBO.getAcceptcount() + "' , m.declineid='"
								+ activityBO.getDeclineid() + "' , m.declinecount='" + activityBO.getDeclinecount()
								+ "' where activityid = '" + activityBO.getActivityid() + "'";
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
	
	public void deleteCron(String beforeLimit) {
		try {

			String SQL = "delete from " + ActivityBO.class.getName() + " where DATEDIFF(sysdate(),actdate) > "+beforeLimit;					
			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done Activity deleteCron !!");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}
	
	public ArrayList<ActivityBO> fetchActivity(Criteria criteriaObj, String pageno) {

		System.out.println("InDAOFetchUser");
		ArrayList<ActivityBO> activityBOList = null;

		/* int pageno = pageno.v */

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
				if (criteriaObj.getFetchActivityCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					int pageListSize;
					if (null != criteriaObj.getFetchActivityCriteriaObj().getValue()
							&& criteriaObj.getFetchActivityCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchActivityCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchActivityCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchActivityCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + ActivityBO.class.getName() + " m where "
								+ criteriaObj.getFetchActivityCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.actdate asc";

						activityBOList = (ArrayList<ActivityBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + ActivityBO.class.getName() + " m where status not in ('delete') order by m.actdate asc ";
				activityBOList = (ArrayList<ActivityBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return activityBOList;
	}
	public Integer totalRecordCount() {
		
		int count = 0;
		String SQL = "select a from " + ActivityBO.class.getName() + "  a where status not in ('delete') ";
		
		 if(null!= manager.createQuery(SQL).getResultList())	
		 {
			  count = manager.createQuery(SQL).getResultList().size();
		 }
		
		return count ;
		
	}
	

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void deleteOnCriteria(ActivityBO activityBO, Criteria criteriaObj) {
		try {

			String SQL  = "delete from " + ActivityBO.class.getName() + " where activityid = '" + activityBO.getActivityid() + "'";
					
			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done deleteOnCriteria");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}
}
