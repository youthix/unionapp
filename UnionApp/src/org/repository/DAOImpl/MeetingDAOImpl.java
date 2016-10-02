package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IMeetingDAO;
import org.repository.entity.ActivityBO;
import org.repository.entity.MeetingBO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MeetingDAOImpl implements IMeetingDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createMeeting(MeetingBO meetingBO) {
		try {
			System.out.println("InDAOCreateMeet");
			manager.persist(meetingBO);
			System.out.println("DoneDAOCreateMeet");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void update(MeetingBO meetingBO) {
		try {
			System.out.println("InDAOAddUser");

			/*
			 * String SQL = "update " + UserBO.class.getName() +
			 * " u Set u.status='"+ userBO.getStatus()+"' where usname = '" +
			 * userBO.getUsname() + "'"; Query query = manager.createQuery(SQL);
			 * query.executeUpdate();
			 */

			manager.merge(meetingBO);

			System.out.println("DoneDAOAddUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public void updateOnCriteria(MeetingBO meetingBO, Criteria criteriaObj) {
		try {

			String SQL = "";

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getUpdateMeetingCriteriaObj() != null) {

					if (criteriaObj.getUpdateMeetingCriteriaObj().getName().equalsIgnoreCase("acceptdecline")) {
						SQL = "update " + MeetingBO.class.getName() + " m Set m.acceptid='" + meetingBO.getAcceptid()
								+ "' , m.acceptcount='" + meetingBO.getAcceptcount() + "' , m.declineid='"
								+ meetingBO.getDeclineid() + "' , m.declinecount='" + meetingBO.getDeclinecount()
								+ "' where meetingid = '" + meetingBO.getMeetingid() + "'";
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
	
	public void deleteOnCriteria(MeetingBO meetingBO, Criteria criteriaObj) {
		try {

			String SQL  = "delete from " + MeetingBO.class.getName() + " where meetingid = '" + meetingBO.getMeetingid() + "'";					
				
			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done deleteOnCriteria");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}
	
	public void deleteCron(String beforeLimit) {
		try {

			String SQL = "delete from " + MeetingBO.class.getName() + " where DATEDIFF(sysdate(),meetdate) > "+beforeLimit;					
			Query query = manager.createQuery(SQL);
			query.executeUpdate();

			System.out.println("Done Meeting deleteCron");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Persisiting : " + e.getMessage());
			throw serviceExceptionObj;
		}
	}

	public ArrayList<MeetingBO> fetchMeeting(Criteria criteriaObj, String pageno) {

		System.out.println("InDAOFetchUser");
		ArrayList<MeetingBO> meetingBOList = null;

		/* int pageno = pageno.v */

		int offsetno;

		int pageSize = 5;

		int pageNo = Integer.parseInt(pageno);

		if (null != pageno && pageno != "" && pageNo > 1) {

			offsetno = (pageNo - 1) * pageSize;
		} else {
			offsetno = 0;

		}

		try {

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchMeetingCriteriaObj() != null) {

					String searchCriteria = "";
					int index = -1;
					int pageListSize;
					if (null != criteriaObj.getFetchMeetingCriteriaObj().getValue()
							&& criteriaObj.getFetchMeetingCriteriaObj().getValue() != "") {

						if (criteriaObj.getFetchMeetingCriteriaObj().getValue().contains(",")) {

							String[] idList = criteriaObj.getFetchMeetingCriteriaObj().getValue().split(",");

							for (String id : idList) {
								searchCriteria = searchCriteria + "'" + id + "',";
							}

							index = searchCriteria.lastIndexOf(",");
							if (index != -1) {
								searchCriteria = searchCriteria.substring(0, index);
							}

						} else {
							searchCriteria = "'" + criteriaObj.getFetchMeetingCriteriaObj().getValue() + "'";
						}

						String SQL = "select m from " + MeetingBO.class.getName() + " m where "
								+ criteriaObj.getFetchMeetingCriteriaObj().getName() + " in (" + searchCriteria
								+ ") order by m.meetingid";

						meetingBOList = (ArrayList<MeetingBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
								.setMaxResults(pageSize) // limit
								.getResultList();
						;

					}

				} else {

					ServiceException serviceExceptionObj = new ServiceException("Criteria Object is empty or null ");
					throw serviceExceptionObj;
				}
			} else {
				String SQL = "select m from " + MeetingBO.class.getName() + " m where status not in ('delete') order by m.meetingid ";
				meetingBOList = (ArrayList<MeetingBO>) manager.createQuery(SQL).setFirstResult(offsetno) // offset
						.setMaxResults(pageSize) // limit
						.getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return meetingBOList;
	}
	
	public Integer totalRecordCount() {
		
		int count = 0 ;
		String SQL = "select COUNT(*) from " + MeetingBO.class.getName() + "  where status not in ('delete') ";
		
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
}
