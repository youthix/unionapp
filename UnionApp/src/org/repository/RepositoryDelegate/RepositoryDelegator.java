package org.repository.RepositoryDelegate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.presentation.dto.RequestObj;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.criteria.FetchMeetingCriteria;
import org.presentation.dto.criteria.FetchUserCriteria;
import org.presentation.dto.criteria.UpdateMeetingCriteria;
import org.presentation.dto.criteria.UpdateUserCriteria;
import org.presentation.dto.feature.MeetingDTO;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IMeetingDAO;
import org.repository.DAOInterface.IUserDAO;
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

	public MeetingList fetchmeeting(Criteria criteriaObj) {
		System.out.println("InRDFetch");

		MeetingList meetingListObj = new MeetingList();
		ArrayList<MeetingDTO> meetingDTOList = new ArrayList<MeetingDTO>();

		ArrayList<MeetingBO> meetingBOList;
		
		ArrayList<UserBO> userBOList;
		
		int totalActUserCount = 0;

		meetingBOList = meetingdao.fetchMeeting(criteriaObj);
		
		
		//To get the count of total Active Users. This count would be used to determine no of users who have not responded to a meeting.
		
		FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

		fetchUserCriteriaObj.setName("status");
		fetchUserCriteriaObj.setValue("A");
		criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);
		
		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");
		userBOList = userdao.fetchUser(criteriaObj);	
		
		if(null!=userBOList){
			totalActUserCount = userBOList.size();
		}
		

		if (null != meetingBOList && meetingBOList.size() > 0) {

			Iterator<MeetingBO> litr = meetingBOList.iterator();

			while (litr.hasNext()) {
				MeetingDTO meetingDTOObj = new MeetingDTO();
				populateMeetingDTO(meetingDTOObj, litr.next());
				meetingDTOObj.setNoresponsecount(String.valueOf((totalActUserCount - (litr.next().getAcceptcount()+litr.next().getDeclinecount()))));
				meetingDTOList.add(meetingDTOObj);

			}

			meetingListObj.setMeetingdtoLs(meetingDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		return meetingListObj;
	}

	public ResponseObj acceptdeny(RequestObj reqparam) {
		System.out.println("InRDFetch");
		
		ResponseObj responseObj = new ResponseObj();

		MeetingList meetingListObj = reqparam.getMeetingListObj();

		UserList userListObj = reqparam.getUserListObj();
		
		ArrayList<MeetingDTO> meetingDTOList;

		ArrayList<User> userDTOList;

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

					meetingBOList = meetingdao.fetchMeeting(criteriaObj);
					meetingBOObj = meetingBOList.get(0);

					String acceptuserid = meetingBOObj.getAcceptid();
					String declineuserid = meetingBOObj.getDeclineid();
					int acceptcount = meetingBOObj.getAcceptcount();
					int declinecount = meetingBOObj.getDeclinecount();

					if (meetingDTOObj.getAcceptdenyind().equalsIgnoreCase("accept")) {

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
/*		responseObj.setMeetingListObj(meetingListObj);
		responseObj.setUserListObj(userListObj);*/
		return responseObj;
	}

	public UserList updatemeeting(UserList userListObj, Criteria criteriaObj) {
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
			
			meetingBOObj.setNoresponsecount(0);

			meetingBOObj.setDeclineid(meetingdtoObj.getDeclineid());

			meetingBOObj.setCreator(meetingdtoObj.getCreator());
			meetingBOObj.setDetail(meetingdtoObj.getDetail());
			meetingBOObj.setMeetdate(dateformatter.parse(meetingdtoObj.getMeetdate()));
			//meetingBOObj.setMeetingid(meetingdtoObj.getMeetingid());
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

}
