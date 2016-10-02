package org.repository.RepositoryDelegate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.presentation.dto.RequestObj;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.criteria.FetchActivityCriteria;
import org.presentation.dto.criteria.FetchMeetingCriteria;
import org.presentation.dto.criteria.FetchUserCriteria;
import org.presentation.dto.criteria.UpdateActivityCriteria;
import org.presentation.dto.criteria.UpdateMeetingCriteria;
import org.presentation.dto.criteria.UpdateUserCriteria;
import org.presentation.dto.feature.ActivityDTO;
import org.presentation.dto.feature.ActivityList;
import org.presentation.dto.feature.MeetingDTO;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IActivityDAO;
import org.repository.DAOInterface.IMeetingDAO;
import org.repository.DAOInterface.IUserDAO;
import org.repository.entity.ActivityBO;
import org.repository.entity.MeetingBO;
import org.repository.entity.UserBO;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryDelegator {

	/*
	 * @Autowired ITestDAO dao;
	 */

	@Autowired
	IUserDAO userdao;

	@Autowired
	IMeetingDAO meetingdao;

	@Autowired
	IActivityDAO activitydao;

	public UserList register(UserList userListObj) {
		System.out.println("InRDRegister");

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();
		UserList userListObjResp = new UserList();

		if (userList.size() > 0) {
			Iterator<User> userListIterator = userList.iterator();

			while (userListIterator.hasNext()) {

				User userObj = userListIterator.next();

				UserBO userBOObj = new UserBO();

				populateCreateUserBO(userObj, userBOObj);
				userdao.addUser(userBOObj);
				populateUserDTO(userObj, userBOObj);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return userListObjResp;
	}

	public UserBO login(UserList userListObj) {
		System.out.println("InRDLogin");

		UserBO userBOObj = null;

		Criteria criteriaObj = new Criteria();
		criteriaObj.setCriteria("TRUE");

		ArrayList<UserBO> userBOList;

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {

			User userObj = userList.get(0);

			FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

			fetchUserCriteriaObj.setName("emailid");
			fetchUserCriteriaObj.setValue(userObj.getUsNa());
			criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			userBOList = userdao.fetchUser(criteriaObj);

			userBOObj = userBOList.get(0);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserDetails Empty. Check and Resend");
			throw serviceExceptionObj;
		}

		return userBOObj;
	}

	public UserList fetch(Criteria criteriaObj) {
		System.out.println("InRDFetch");

		UserList userListObj = new UserList();
		ArrayList<User> userDTOList = new ArrayList<User>();

		ArrayList<UserBO> userBOList;

		userBOList = userdao.fetchUser(criteriaObj);

		if (null != userBOList && userBOList.size() > 0) {

			Iterator<UserBO> litr = userBOList.iterator();

			while (litr.hasNext()) {
				User userDTOObj = new User();
				populateUserDTO(userDTOObj, litr.next());
				userDTOList.add(userDTOObj);

			}

			userListObj.setUl(userDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		return userListObj;
	}

	public UserList update(UserList userListObj, Criteria criteriaObj) {
		System.out.println("InRDUpdate");

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {
			Iterator<User> userListIterator = userList.iterator();

			while (userListIterator.hasNext()) {

				User userObj = userListIterator.next();

				UserBO userBOObj = new UserBO();
				userBOObj.setUsname(userObj.getUsNa());

				if (null != criteriaObj.getCriteria() && criteriaObj.getCriteria().equalsIgnoreCase("True")) {
					if (criteriaObj.getUpdateUserCriteriaObj() != null) {

						if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("loginstatus")) {
							userBOObj.setLoginstatus(userObj.getLoginstatus());
						} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("deviceid")) {
							userBOObj.setDeviceid(userObj.getDeviceid());
							userBOObj.setDevicetype(userObj.getDevicetype());
						} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("status")) {
							userBOObj.setStatus(userObj.getStatus());
						} else if (criteriaObj.getUpdateUserCriteriaObj().getName().equalsIgnoreCase("pwd")) {
							userBOObj.setPwd(userObj.getPwd());
						}
					}
				}

				userdao.updateOnCriteria(userBOObj, criteriaObj);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return userListObj;
	}

	public ResponseObj updateuserprofile(RequestObj reqparam) {
		System.out.println("InRDUpdate");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the user from the DB basis the id coming in the request
		 * Then update the fields of the UserBO fetched from DB with those
		 * received in the input
		 */
		UserList userListObj = reqparam.getUserListObj();

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		ArrayList<UserBO> userBOList;

		UserBO userBOObj = null;

		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");

		FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

		fetchUserCriteriaObj.setName("usname");

		if (userList.size() > 0) {

			User userdtoObj = userList.get(0);

			fetchUserCriteriaObj.setValue(userdtoObj.getUsNa());
			criteriaUserObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			userBOList = userdao.fetchUser(criteriaUserObj);

			if (null != userBOList && userBOList.size() > 0) {

				userBOObj = userBOList.get(0);

				// update the meetingBO fetched from DB

				userBOObj.setAdd(userdtoObj.getAdd());
				userBOObj.setAge(userdtoObj.getAge());
				userBOObj.setCity(userdtoObj.getCity());
				userBOObj.setConNu(userdtoObj.getConNu());
				userBOObj.setFn(userdtoObj.getFn());
				userBOObj.setGen(userdtoObj.getGen());
				userBOObj.setLn(userdtoObj.getLn());
				userBOObj.setZipcode(userdtoObj.getZipcode());

				// merge this UpdateBO back in DB
				userdao.update(userBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}

			populateUserDTO(userdtoObj, userBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("MeetingList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setUserListObj(userListObj);

		return responseObj;
	}

	public MeetingList createmeeting(MeetingList meetingListObj) {
		System.out.println("InRDRegister");

		ArrayList<MeetingDTO> meetingList = (ArrayList<MeetingDTO>) meetingListObj.getMeetingdtoLs();
		MeetingList meetingListObjResp = new MeetingList();

		if (meetingList.size() > 0) {
			Iterator<MeetingDTO> meetingListIterator = meetingList.iterator();

			while (meetingListIterator.hasNext()) {

				MeetingDTO meetingdtoObj = meetingListIterator.next();

				MeetingBO meetingBOObj = new MeetingBO();

				populateCreateMeetingBO(meetingdtoObj, meetingBOObj);
				meetingdao.createMeeting(meetingBOObj);
				populateMeetingDTO(meetingdtoObj, meetingBOObj);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return meetingListObjResp;
	}

	public ResponseObj fetchmeeting(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();

		MeetingList meetingListObj = new MeetingList();
		ArrayList<MeetingDTO> meetingDTOList = new ArrayList<MeetingDTO>();

		ArrayList<MeetingBO> meetingBOList;

		MeetingBO meetingBOObj;

		ArrayList<UserBO> userBOList;

		int totalActUserCount = 0;

		Criteria criteriaObj = reqparam.getCriteria();

		UserList userListObj = reqparam.getUserListObj();

		meetingBOList = meetingdao.fetchMeeting(criteriaObj, reqparam.getPageno());

		// To get the count of total Active Users. This count would be used to
		// determine no of users who have not responded to a meeting.

		FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

		fetchUserCriteriaObj.setName("status");
		fetchUserCriteriaObj.setValue("A");
		criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");
		userBOList = userdao.fetchUser(criteriaObj);

		if (null != userBOList) {
			totalActUserCount = userBOList.size();
		}

		if (null != meetingBOList && meetingBOList.size() > 0) {

			Iterator<MeetingBO> litr = meetingBOList.iterator();

			while (litr.hasNext()) {

				meetingBOObj = litr.next();
				MeetingDTO meetingDTOObj = new MeetingDTO();
				populateMeetingDTO(meetingDTOObj, meetingBOObj);

				// set the no of users who did not responded by subtracting the
				// accept + deny from the total no of users calculated above.
				meetingDTOObj.setNoresponsecount(String.valueOf(
						(totalActUserCount - (meetingBOObj.getAcceptcount() + meetingBOObj.getDeclinecount()))));

				// for a particular user who is requesting this fetch Meeting,
				// the accept or decline status of all the meetings needs to be
				// populated in the return obj
				String acceptids = meetingBOObj.getAcceptid();
				String declineids = meetingBOObj.getDeclineid();

				if (null != acceptids && acceptids.contains(userListObj.getUl().get(0).getUsNa())) {

					meetingDTOObj.setAcceptdenyind("accept");

				} else if (null != declineids && declineids.contains(userListObj.getUl().get(0).getUsNa())) {

					meetingDTOObj.setAcceptdenyind("deny");

				}
				meetingDTOList.add(meetingDTOObj);

			}

			meetingListObj.setMeetingdtoLs(meetingDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setMeetingListObj(meetingListObj);
		int totalrecordcount = meetingdao.totalRecordCount();

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public ResponseObj acceptdeny(RequestObj reqparam) {
		System.out.println("InRDFetch");

		ResponseObj responseObj = new ResponseObj();

		MeetingList meetingListObj = reqparam.getMeetingListObj();

		UserList userListObj = reqparam.getUserListObj();

		ArrayList<MeetingDTO> meetingDTOList;

		ArrayList<User> userDTOList;

		String pageno = "1";

		if (null != meetingListObj && null != userListObj && null != meetingListObj.getMeetingdtoLs()
				&& meetingListObj.getMeetingdtoLs().size() > 0 && null != userListObj.getUl()
				&& userListObj.getUl().size() > 0) {

			meetingDTOList = (ArrayList<MeetingDTO>) meetingListObj.getMeetingdtoLs();

			/*
			 * Fetch the user on basis of usname and then update the
			 * acceptmeetingid or declinemeetingid property
			 */
			userDTOList = (ArrayList<User>) userListObj.getUl();
			User userObj = userDTOList.get(0);

			Criteria criteriaObj = new Criteria();

			criteriaObj.setCriteria("TRUE");

			FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

			fetchUserCriteriaObj.setName("usname");

			fetchUserCriteriaObj.setValue(userObj.getUsNa());

			criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			ArrayList<UserBO> userBOList;

			userBOList = userdao.fetchUser(criteriaObj);

			if (null != userBOList && userBOList.size() > 0) {
				UserBO userBOObj = userBOList.get(0);

				String acceptmeetingid = userBOObj.getAcceptmeetingid();

				String declinemeetingid = userBOObj.getDeclinemeetingid();

				for (MeetingDTO meetingDTOObj : meetingDTOList) {

					/*
					 * fetch and update the meetingTable with the list of user
					 * who accepted or declined Also need to update the
					 * usertable with the meetings accepted or declined. This is
					 * stored in comman separated list hence creating the String
					 * below.
					 */
					ArrayList<MeetingBO> meetingBOList;
					MeetingBO meetingBOObj;

					FetchMeetingCriteria fetchMeetingCriteriaObj = new FetchMeetingCriteria();
					fetchMeetingCriteriaObj.setName("meetingid");
					fetchMeetingCriteriaObj.setValue(meetingDTOObj.getMeetingid());
					criteriaObj.setFetchMeetingCriteriaObj(fetchMeetingCriteriaObj);

					meetingBOList = meetingdao.fetchMeeting(criteriaObj, pageno);
					meetingBOObj = meetingBOList.get(0);

					String acceptuserid = meetingBOObj.getAcceptid();
					String declineuserid = meetingBOObj.getDeclineid();
					int acceptcount = meetingBOObj.getAcceptcount();
					int declinecount = meetingBOObj.getDeclinecount();
					if (meetingDTOObj.getAcceptdenyind().equalsIgnoreCase("delete")){
						meetingdao.deleteOnCriteria(meetingBOObj, criteriaObj);
					}
					else if (meetingDTOObj.getAcceptdenyind().equalsIgnoreCase("accept")) {

						// for user table
						if (acceptmeetingid == null || acceptmeetingid.equals("")) {

							acceptmeetingid = meetingDTOObj.getMeetingid();
						} else {

							acceptmeetingid = acceptmeetingid + "," + meetingDTOObj.getMeetingid();
						}

						// for meeting table

						if (acceptuserid == null || acceptuserid.equals("")) {

							acceptuserid = userBOObj.getUsname();
						} else {

							acceptuserid = acceptuserid + "," + userBOObj.getUsname();
						}
						acceptcount = acceptcount + 1;

					} else {

						// for user table

						if (declinemeetingid == null || declinemeetingid.equals("")) {

							declinemeetingid = meetingDTOObj.getMeetingid();
						} else {

							declinemeetingid = declinemeetingid + "," + meetingDTOObj.getMeetingid();
						}

						// for meeting table

						if (declineuserid == null || declineuserid.equals("")) {

							declineuserid = userBOObj.getUsname();
						} else {

							declineuserid = declineuserid + "," + userBOObj.getUsname();
						}
						declinecount = declinecount + 1;

					}

					// update the meeting obj

					criteriaObj = new Criteria();
					criteriaObj.setCriteria("TRUE");
					UpdateMeetingCriteria updateMeetingCriteriaObj = new UpdateMeetingCriteria();
					updateMeetingCriteriaObj.setName("acceptdecline");
					criteriaObj.setUpdateMeetingCriteriaObj(updateMeetingCriteriaObj);

					meetingBOObj.setAcceptcount(acceptcount);
					meetingBOObj.setAcceptid(acceptuserid);
					meetingBOObj.setDeclinecount(declinecount);
					meetingBOObj.setDeclineid(declineuserid);

					meetingdao.updateOnCriteria(meetingBOObj, criteriaObj);
					populateMeetingDTO(meetingDTOObj, meetingBOObj);

				}

				// update the UserObj with the meetingids accept or declined
				criteriaObj = new Criteria();
				criteriaObj.setCriteria("TRUE");
				UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
				updateUserCriteriaObj.setName("meeting");
				criteriaObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);

				userBOObj.setAcceptmeetingid(acceptmeetingid);
				userBOObj.setDeclinemeetingid(declinemeetingid);

				userdao.updateOnCriteria(userBOObj, criteriaObj);
				populateUserDTO(userObj, userBOObj);
			} else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
				throw serviceExceptionObj;
			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList or Meeting List is null");
			throw serviceExceptionObj;
		}
		/*
		 * responseObj.setMeetingListObj(meetingListObj);
		 * responseObj.setUserListObj(userListObj);
		 */
		return responseObj;
	}

	public ResponseObj updatemeeting(RequestObj reqparam) {
		System.out.println("InRDUpdate");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the meeting from the DB basis the id coming in the
		 * request Then update teh fields of the MeetingBo fetched from DB with
		 * those received in the input
		 */
		MeetingList meetingListObj = reqparam.getMeetingListObj();

		ArrayList<MeetingDTO> meetingList = (ArrayList<MeetingDTO>) meetingListObj.getMeetingdtoLs();

		ArrayList<MeetingBO> meetingBOList;

		MeetingBO meetingBOObj = null;

		Criteria criteriameetingObj = new Criteria();
		criteriameetingObj.setCriteria("TRUE");

		FetchMeetingCriteria fetchMeetingCriteriaObj = new FetchMeetingCriteria();

		fetchMeetingCriteriaObj.setName("meetingid");

		if (meetingList.size() > 0) {

			MeetingDTO meetingdtoObj = meetingList.get(0);

			fetchMeetingCriteriaObj.setValue(meetingdtoObj.getMeetingid());
			criteriameetingObj.setFetchMeetingCriteriaObj(fetchMeetingCriteriaObj);

			meetingBOList = meetingdao.fetchMeeting(criteriameetingObj, "1");

			if (null != meetingBOList && meetingBOList.size() > 0) {

				meetingBOObj = meetingBOList.get(0);

				// update the meetingBO fetched from DB

				meetingBOObj.setCreator(meetingdtoObj.getCreator());
				meetingBOObj.setDetail(meetingdtoObj.getDetail());
				meetingBOObj.setStatus(meetingdtoObj.getStatus());
				meetingBOObj.setSubject(meetingdtoObj.getSubject());
				meetingBOObj.setVenue(meetingdtoObj.getVenue());

				// merge this UpdateBO back in DB
				meetingdao.update(meetingBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}

			populateMeetingDTO(meetingdtoObj, meetingBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("MeetingList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setMeetingListObj(meetingListObj);

		return responseObj;
	}

	public ActivityList createactivity(ActivityList activityListObj) {
		System.out.println("InRDRegister");

		ArrayList<ActivityDTO> activityList = (ArrayList<ActivityDTO>) activityListObj.getActivitydtoLs();
		ActivityList activityListObjResp = new ActivityList();

		if (activityList.size() > 0) {
			Iterator<ActivityDTO> activityListIterator = activityList.iterator();

			while (activityListIterator.hasNext()) {

				ActivityDTO activitydtoObj = activityListIterator.next();

				ActivityBO activityBOObj = new ActivityBO();

				populateCreateActivityBO(activitydtoObj, activityBOObj);
				activitydao.createActivity(activityBOObj);
				populateActivityDTO(activitydtoObj, activityBOObj);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return activityListObjResp;
	}

	public ResponseObj fetchactivity(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();

		ActivityList activityListObj = new ActivityList();
		ArrayList<ActivityDTO> activityDTOList = new ArrayList<ActivityDTO>();

		ArrayList<ActivityBO> activityBOList;

		ActivityBO activityBOObj;

		ArrayList<UserBO> userBOList;

		int totalActUserCount = 0;

		Criteria criteriaObj = reqparam.getCriteria();

		UserList userListObj = reqparam.getUserListObj();

		activityBOList = activitydao.fetchActivity(criteriaObj, reqparam.getPageno());

		// To get the count of total Active Users. This count would be used to
		// determine no of users who have not responded to a Activity.

		FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

		fetchUserCriteriaObj.setName("status");
		fetchUserCriteriaObj.setValue("A");
		criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");
		userBOList = userdao.fetchUser(criteriaObj);

		if (null != userBOList) {
			totalActUserCount = userBOList.size();
		}

		if (null != activityBOList && activityBOList.size() > 0) {

			Iterator<ActivityBO> litr = activityBOList.iterator();

			while (litr.hasNext()) {

				activityBOObj = litr.next();
				ActivityDTO activityDTOObj = new ActivityDTO();
				populateActivityDTO(activityDTOObj, activityBOObj);

				// set the no of users who did not responded by subtracting the
				// accept + deny from the total no of users calculated above.
				activityDTOObj.setNoresponsecount(String.valueOf(
						(totalActUserCount - (activityBOObj.getAcceptcount() + activityBOObj.getDeclinecount()))));

				// for a particular user who is requesting this fetch Activity,
				// the accept or decline status of all the Activitys needs to be
				// populated in the return obj
				String acceptids = activityBOObj.getAcceptid();
				String declineids = activityBOObj.getDeclineid();

				if (null != acceptids && acceptids.contains(userListObj.getUl().get(0).getUsNa())) {

					activityDTOObj.setAcceptdenyind("accept");

				} else if (null != declineids && declineids.contains(userListObj.getUl().get(0).getUsNa())) {

					activityDTOObj.setAcceptdenyind("deny");

				}
				activityDTOList.add(activityDTOObj);

			}

			activityListObj.setActivitydtoLs(activityDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setActivityListObj(activityListObj);
		int totalrecordcount = activitydao.totalRecordCount();

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public ResponseObj acceptdenyactivity(RequestObj reqparam) {
		System.out.println("InRDFetch");

		ResponseObj responseObj = new ResponseObj();

		ActivityList activityListObj = reqparam.getActivityListObj();

		UserList userListObj = reqparam.getUserListObj();

		ArrayList<ActivityDTO> activityDTOList;

		ArrayList<User> userDTOList;

		String pageno = "1";

		if (null != activityListObj && null != userListObj && null != activityListObj.getActivitydtoLs()
				&& activityListObj.getActivitydtoLs().size() > 0 && null != userListObj.getUl()
				&& userListObj.getUl().size() > 0) {

			activityDTOList = (ArrayList<ActivityDTO>) activityListObj.getActivitydtoLs();

			/*
			 * Fetch the user on basis of usname and then update the
			 * acceptActivityid or declineActivityid property
			 */
			userDTOList = (ArrayList<User>) userListObj.getUl();
			User userObj = userDTOList.get(0);

			Criteria criteriaObj = new Criteria();

			criteriaObj.setCriteria("TRUE");

			FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

			fetchUserCriteriaObj.setName("usname");

			fetchUserCriteriaObj.setValue(userObj.getUsNa());

			criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			ArrayList<UserBO> userBOList;

			userBOList = userdao.fetchUser(criteriaObj);

			if (null != userBOList && userBOList.size() > 0) {
				UserBO userBOObj = userBOList.get(0);

				String acceptactivityid = userBOObj.getAcceptactivityid();

				String declineactivityid = userBOObj.getDeclineactivityid();

				for (ActivityDTO activityDTOObj : activityDTOList) {

					/*
					 * fetch and update the activityTable with the list of user
					 * who accepted or declined Also need to update the
					 * usertable with the activitys accepted or declined. This
					 * is stored in comman separated list hence creating the
					 * String below.
					 */
					ArrayList<ActivityBO> activityBOList;
					ActivityBO activityBOObj;

					FetchActivityCriteria fetchActivityCriteriaObj = new FetchActivityCriteria();
					fetchActivityCriteriaObj.setName("activityid");
					fetchActivityCriteriaObj.setValue(activityDTOObj.getActivityid());
					criteriaObj.setFetchActivityCriteriaObj(fetchActivityCriteriaObj);

					activityBOList = activitydao.fetchActivity(criteriaObj, pageno);
					activityBOObj = activityBOList.get(0);

					String acceptuserid = activityBOObj.getAcceptid();
					String declineuserid = activityBOObj.getDeclineid();
					int acceptcount = activityBOObj.getAcceptcount();
					int declinecount = activityBOObj.getDeclinecount();
					
					if (activityDTOObj.getAcceptdenyind().equalsIgnoreCase("delete")){
						activitydao.deleteOnCriteria(activityBOObj, criteriaObj);
					}
					else if (activityDTOObj.getAcceptdenyind().equalsIgnoreCase("accept")) {

						// for user table
						if (acceptactivityid == null || acceptactivityid.equals("")) {

							acceptactivityid = activityDTOObj.getActivityid();
						} else {

							acceptactivityid = acceptactivityid + "," + activityDTOObj.getActivityid();
						}

						// for Activity table

						if (acceptuserid == null || acceptuserid.equals("")) {

							acceptuserid = userBOObj.getUsname();
						} else {

							acceptuserid = acceptuserid + "," + userBOObj.getUsname();
						}
						acceptcount = acceptcount + 1;

					} else {

						// for user table

						if (declineactivityid == null || declineactivityid.equals("")) {

							declineactivityid = activityDTOObj.getActivityid();
						} else {

							declineactivityid = declineactivityid + "," + activityDTOObj.getActivityid();
						}

						// for Activity table

						if (declineuserid == null || declineuserid.equals("")) {

							declineuserid = userBOObj.getUsname();
						} else {

							declineuserid = declineuserid + "," + userBOObj.getUsname();
						}
						declinecount = declinecount + 1;

					}

					// update the Activity obj

					criteriaObj = new Criteria();
					criteriaObj.setCriteria("TRUE");
					UpdateActivityCriteria updateActivityCriteriaObj = new UpdateActivityCriteria();
					updateActivityCriteriaObj.setName("acceptdecline");
					criteriaObj.setUpdateActivityCriteriaObj(updateActivityCriteriaObj);

					activityBOObj.setAcceptcount(acceptcount);
					activityBOObj.setAcceptid(acceptuserid);
					activityBOObj.setDeclinecount(declinecount);
					activityBOObj.setDeclineid(declineuserid);

					activitydao.updateOnCriteria(activityBOObj, criteriaObj);
					populateActivityDTO(activityDTOObj, activityBOObj);

				}

				// update the UserObj with the activityids accept or declined
				criteriaObj = new Criteria();
				criteriaObj.setCriteria("TRUE");
				UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
				updateUserCriteriaObj.setName("activity");
				criteriaObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);

				userBOObj.setAcceptactivityid(acceptactivityid);
				userBOObj.setDeclineactivityid(declineactivityid);

				userdao.updateOnCriteria(userBOObj, criteriaObj);
				populateUserDTO(userObj, userBOObj);
			} else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
				throw serviceExceptionObj;
			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList or Activity List is null");
			throw serviceExceptionObj;
		}
		/*
		 * responseObj.setActivityListObj(activityListObj);
		 * responseObj.setUserListObj(userListObj);
		 */
		return responseObj;
	}

	public ResponseObj updateactivity(RequestObj reqparam) {
		System.out.println("InRDUpdate");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the activity from the DB basis the id coming in the
		 * request Then update teh fields of the activityBo fetched from DB with
		 * those received in the input
		 */
		ActivityList activityListObj = reqparam.getActivityListObj();

		ArrayList<ActivityDTO> activityList = (ArrayList<ActivityDTO>) activityListObj.getActivitydtoLs();

		ArrayList<ActivityBO> activityBOList;

		ActivityBO activityBOObj = null;

		Criteria criteriaactivityObj = new Criteria();
		criteriaactivityObj.setCriteria("TRUE");

		FetchActivityCriteria fetchActivityCriteriaObj = new FetchActivityCriteria();

		fetchActivityCriteriaObj.setName("activityid");

		if (activityList.size() > 0) {

			ActivityDTO activitydtoObj = activityList.get(0);

			fetchActivityCriteriaObj.setValue(activitydtoObj.getActivityid());
			criteriaactivityObj.setFetchActivityCriteriaObj(fetchActivityCriteriaObj);

			activityBOList = activitydao.fetchActivity(criteriaactivityObj, "1");

			if (null != activityBOList && activityBOList.size() > 0) {

				activityBOObj = activityBOList.get(0);

				// update the activityBO fetched from DB

				activityBOObj.setCreator(activitydtoObj.getCreator());
				activityBOObj.setDetail(activitydtoObj.getDetail());
				activityBOObj.setStatus(activitydtoObj.getStatus());
				activityBOObj.setSubject(activitydtoObj.getSubject());
				activityBOObj.setVenue(activitydtoObj.getVenue());

				// merge this UpdateBO back in DB
				activitydao.update(activityBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}

			populateActivityDTO(activitydtoObj, activityBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("ActivityList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setActivityListObj(activityListObj);

		return responseObj;
	}

	private void populateCreateUserBO(User userObj, UserBO userBOObj) {

		userBOObj.setUsname(userObj.getUsNa());
		userBOObj.setDeviceid(userObj.getDeviceid());
		userBOObj.setPwd(userObj.getPwd());
		userBOObj.setAdd(userObj.getAdd());
		userBOObj.setAge(userObj.getAge());
		userBOObj.setConNu(userObj.getConNu());
		userBOObj.setEmailid(userObj.getEmId());
		userBOObj.setFn(userObj.getFn());
		userBOObj.setGen(userObj.getGen());
		userBOObj.setJoindt(userObj.getJoinDt());
		userBOObj.setLn(userObj.getLn());
		userBOObj.setZipcode(userObj.getZipcode());
		userBOObj.setCity(userObj.getCity());
		if (null == userObj.getLoginstatus() || userObj.getLoginstatus().equals("")) {
			userBOObj.setLoginstatus("F");
		} else {
			userBOObj.setLoginstatus(userObj.getLoginstatus());
		}
		if (null == userObj.getStatus() || userObj.getStatus().equals("")) {
			userBOObj.setStatus("P");
		} else {
			userBOObj.setStatus(userObj.getStatus());
		}
		if (null == userObj.getRole() || userObj.getRole().equals("")) {
			userBOObj.setRole("N");
		} else {
			userBOObj.setRole(userObj.getRole());
		}

	}

	private void populateUserDTO(User userObj, UserBO userBOObj) {

		userObj.setAdd(userBOObj.getAdd());
		userObj.setAge(userBOObj.getAge());
		userObj.setConNu(userBOObj.getConNu());
		userObj.setEmId(userBOObj.getEmailid());
		userObj.setFn(userBOObj.getFn());
		userObj.setGen(userBOObj.getGen());
		userObj.setJoinDt(userBOObj.getJoindt());
		userObj.setLn(userBOObj.getLn());
		userObj.setPwd(userBOObj.getPwd());
		userObj.setRole(userBOObj.getRole());
		userObj.setStatus(userBOObj.getStatus());
		userObj.setUsNa(userBOObj.getUsname());
		userObj.setLoginstatus(userBOObj.getLoginstatus());
		userObj.setDeviceid(userBOObj.getDeviceid());
		userObj.setZipcode(userBOObj.getZipcode());
		userObj.setCity(userBOObj.getCity());
		userObj.setDevicetype(userBOObj.getDevicetype());

	}

	private void populateCreateMeetingBO(MeetingDTO meetingdtoObj, MeetingBO meetingBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm:ss");

		try {
			meetingBOObj.setAcceptid(meetingdtoObj.getAcceptid());

			if (null != meetingdtoObj.getAcceptcount() && !meetingdtoObj.getAcceptcount().equals("")) {
				meetingBOObj.setAcceptcount(Integer.parseInt(meetingdtoObj.getAcceptcount()));
			} else {
				meetingBOObj.setAcceptcount(0);
			}
			if (null != meetingdtoObj.getDeclinecount() && !meetingdtoObj.getDeclinecount().equals("")) {
				meetingBOObj.setDeclinecount(Integer.parseInt(meetingdtoObj.getDeclinecount()));
			} else {
				meetingBOObj.setDeclinecount(0);
			}

			// meetingBOObj.setNoresponsecount(0);

			meetingBOObj.setDeclineid(meetingdtoObj.getDeclineid());

			meetingBOObj.setCreator(meetingdtoObj.getCreator());
			meetingBOObj.setDetail(meetingdtoObj.getDetail());
			meetingBOObj.setMeetdate(dateformatter.parse(meetingdtoObj.getMeetdate()));
			// meetingBOObj.setMeetingid(meetingdtoObj.getMeetingid());
			meetingBOObj.setMeettime(timeformatter.parse(meetingdtoObj.getMeettime()));
			meetingBOObj.setStatus(meetingdtoObj.getStatus());
			meetingBOObj.setSubject(meetingdtoObj.getSubject());
			meetingBOObj.setVenue(meetingdtoObj.getVenue());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateMeetingDTO(MeetingDTO meetingdtoObj, MeetingBO meetingBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm:ss");

		meetingdtoObj.setAcceptcount(String.valueOf(meetingBOObj.getAcceptcount()));
		meetingdtoObj.setDeclinecount(String.valueOf(meetingBOObj.getDeclinecount()));
		meetingdtoObj.setCreator(meetingBOObj.getCreator());
		meetingdtoObj.setDetail(meetingBOObj.getDetail());
		meetingdtoObj.setMeetdate(dateformatter.format(meetingBOObj.getMeetdate()));
		meetingdtoObj.setMeetingid(meetingBOObj.getMeetingid().toString());
		meetingdtoObj.setMeettime(timeformatter.format(meetingBOObj.getMeettime()));
		meetingdtoObj.setStatus(meetingBOObj.getStatus());
		meetingdtoObj.setSubject(meetingBOObj.getSubject());
		meetingdtoObj.setVenue(meetingBOObj.getVenue());

	}

	private void populateCreateActivityBO(ActivityDTO activitydtoObj, ActivityBO activityBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm:ss");

		try {
			activityBOObj.setAcceptid(activitydtoObj.getAcceptid());

			if (null != activitydtoObj.getAcceptcount() && !activitydtoObj.getAcceptcount().equals("")) {
				activityBOObj.setAcceptcount(Integer.parseInt(activitydtoObj.getAcceptcount()));
			} else {
				activityBOObj.setAcceptcount(0);
			}
			if (null != activitydtoObj.getDeclinecount() && !activitydtoObj.getDeclinecount().equals("")) {
				activityBOObj.setDeclinecount(Integer.parseInt(activitydtoObj.getDeclinecount()));
			} else {
				activityBOObj.setDeclinecount(0);
			}

			// activityBOObj.setNoresponsecount(0);

			activityBOObj.setDeclineid(activitydtoObj.getDeclineid());

			activityBOObj.setCreator(activitydtoObj.getCreator());
			activityBOObj.setDetail(activitydtoObj.getDetail());
			activityBOObj.setActdate(dateformatter.parse(activitydtoObj.getActdate()));
			// activityBOObj.setactivityid(activitydtoObj.getactivityid());
			activityBOObj.setStatus(activitydtoObj.getStatus());
			activityBOObj.setSubject(activitydtoObj.getSubject());
			activityBOObj.setVenue(activitydtoObj.getVenue());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateActivityDTO(ActivityDTO activitydtoObj, ActivityBO activityBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm:ss");

		activitydtoObj.setAcceptcount(String.valueOf(activityBOObj.getAcceptcount()));
		activitydtoObj.setDeclinecount(String.valueOf(activityBOObj.getDeclinecount()));
		activitydtoObj.setCreator(activityBOObj.getCreator());
		activitydtoObj.setDetail(activityBOObj.getDetail());
		activitydtoObj.setActdate(dateformatter.format(activityBOObj.getActdate()));
		activitydtoObj.setActivityid(activityBOObj.getActivityid().toString());
		activitydtoObj.setStatus(activityBOObj.getStatus());
		activitydtoObj.setSubject(activityBOObj.getSubject());
		activitydtoObj.setVenue(activityBOObj.getVenue());

	}

	private int getTotalPageCount(int totalrecordcount) {
		int pagesize = 5;

		int totalPage = 0;
		
		if(totalrecordcount > 0){
			
			if (totalrecordcount % pagesize == 0) {

				totalPage = totalrecordcount / pagesize;

			} else {
				totalPage = totalrecordcount / pagesize + 1;
			}
			
		}


		return totalPage;
	}

}
