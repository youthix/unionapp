package org.repository.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.presentation.dto.criteria.Criteria;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IMeetingDAO;
import org.repository.entity.MeetingBO;
import org.repository.entity.UserBO;
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
/*		try {
			
			String SQL = "";

			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getUpdateMeetingCriteriaObj() != null) {

					if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("deviceid")) {
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
		}*/
	}

	public ArrayList<MeetingBO> fetchMeeting(Criteria criteriaObj) {

		System.out.println("InDAOFetchUser");
		ArrayList<MeetingBO> meetingBOList = null;

		try {


			if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
				if (criteriaObj.getFetchMeetingCriteriaObj() != null) {

					String SQL = "select u from " + MeetingBO.class.getName() + " u where "
							+ criteriaObj.getFetchMeetingCriteriaObj().getName() + " = '"
							+ criteriaObj.getFetchMeetingCriteriaObj().getValue() + "'";

					meetingBOList = (ArrayList<MeetingBO>) manager.createQuery(SQL).getResultList();

				} 
			}
			else {
				String SQL = "select u from " + MeetingBO.class.getName() + " u";
				meetingBOList = (ArrayList<MeetingBO>) manager.createQuery(SQL).getResultList();
			}

			System.out.println("DoneDAOFetchUser");
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException("Error While Fetching : " + e.getMessage());
			throw serviceExceptionObj;
		}

		return meetingBOList;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
