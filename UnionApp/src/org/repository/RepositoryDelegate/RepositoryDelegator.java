package org.repository.RepositoryDelegate;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.common.UnionAppConstants;
import org.presentation.dto.RequestObj;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.criteria.FetchActivityCriteria;
import org.presentation.dto.criteria.FetchAgreementCriteria;
import org.presentation.dto.criteria.FetchAmrCriteria;
import org.presentation.dto.criteria.FetchMeetingCriteria;
import org.presentation.dto.criteria.FetchNewsLetterCriteria;
import org.presentation.dto.criteria.FetchPayrateCriteria;
import org.presentation.dto.criteria.FetchSuggestionIdeaCriteria;
import org.presentation.dto.criteria.FetchSummaryCriteria;
import org.presentation.dto.criteria.FetchSurveyCriteria;
import org.presentation.dto.criteria.FetchUserCriteria;
import org.presentation.dto.criteria.UpdateActivityCriteria;
import org.presentation.dto.criteria.UpdateMeetingCriteria;
import org.presentation.dto.criteria.UpdateUserCriteria;
import org.presentation.dto.feature.ActionLogDTO;
import org.presentation.dto.feature.ActionLogList;
import org.presentation.dto.feature.ActiveUserDTO;
import org.presentation.dto.feature.ActiveUserList;
import org.presentation.dto.feature.ActivityDTO;
import org.presentation.dto.feature.ActivityList;
import org.presentation.dto.feature.AgreementDTO;
import org.presentation.dto.feature.AgreementList;
import org.presentation.dto.feature.AmrDTO;
import org.presentation.dto.feature.AmrList;
import org.presentation.dto.feature.AttachmentDTO;
import org.presentation.dto.feature.AttachmentList;
import org.presentation.dto.feature.CategoryDTO;
import org.presentation.dto.feature.CategoryList;
import org.presentation.dto.feature.MeetingDTO;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.feature.NewsLetterDTO;
import org.presentation.dto.feature.NewsLetterList;
import org.presentation.dto.feature.OptionDTO;
import org.presentation.dto.feature.PayrateDTO;
import org.presentation.dto.feature.PayrateList;
import org.presentation.dto.feature.QuestionDTO;
import org.presentation.dto.feature.SuggestionIdeaDTO;
import org.presentation.dto.feature.SuggestionIdeaList;
import org.presentation.dto.feature.SummaryDTO;
import org.presentation.dto.feature.SummaryList;
import org.presentation.dto.feature.SurveyDTO;
import org.presentation.dto.feature.SurveyList;
import org.presentation.dto.feature.VisitorInfoDTO;
import org.presentation.dto.feature.VisitorInfoList;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.DAOInterface.IActionLogDAO;
import org.repository.DAOInterface.IActivityDAO;
import org.repository.DAOInterface.IAgreementDAO;
import org.repository.DAOInterface.IAmrDAO;
import org.repository.DAOInterface.ICategoryDAO;
import org.repository.DAOInterface.IMeetingDAO;
import org.repository.DAOInterface.INewsLetterDAO;
import org.repository.DAOInterface.IPayrateDAO;
import org.repository.DAOInterface.ISuggestionIdeaDAO;
import org.repository.DAOInterface.ISummaryDAO;
import org.repository.DAOInterface.ISurveyDAO;
import org.repository.DAOInterface.IUserDAO;
import org.repository.entity.ActionLogBO;
import org.repository.entity.ActiveUserBO;
import org.repository.entity.ActivityBO;
import org.repository.entity.AgreementBO;
import org.repository.entity.AmrBO;
import org.repository.entity.CategoryBO;
import org.repository.entity.MeetingBO;
import org.repository.entity.NewsLetterBO;
import org.repository.entity.PayrateBO;
import org.repository.entity.SuggestionIdeaBO;
import org.repository.entity.SummaryBO;
import org.repository.entity.SurveyBO;
import org.repository.entity.UserBO;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

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

	@Autowired
	INewsLetterDAO newsletterdao;

	@Autowired
	ISummaryDAO summarydao;

	@Autowired
	ISuggestionIdeaDAO suggestionIdeadao;

	@Autowired
	IAgreementDAO agreementdao;

	@Autowired
	IAmrDAO amrdao;

	@Autowired
	IPayrateDAO payratedao;

	@Autowired
	ICategoryDAO categorydao;

	@Autowired
	ISurveyDAO surveydao;

	@Autowired
	IActionLogDAO actionlogdao;

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

	public ResponseObj login(UserList userListObj) {
		System.out.println("InRDLogin");

		ResponseObj responseObj = new ResponseObj();

		UserBO userBOObj = null;
		String pageNo = "1";

		ArrayList<UserBO> userBOList;

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {

			Criteria criteriaObj = new Criteria();
			criteriaObj.setCriteria("TRUE");

			User userObj = userList.get(0);

			FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

			fetchUserCriteriaObj.setName("emailid");
			fetchUserCriteriaObj.setValue(userObj.getUsNa());
			criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			userBOList = userdao.fetchUser(criteriaObj, pageNo);

			userBOObj = userBOList.get(0);

			if ((null != userBOObj) && ((userBOObj.getUsname().equalsIgnoreCase(userObj.getUsNa()))
					&& (userBOObj.getPwd().equalsIgnoreCase(userObj.getPwd())))) {
				if (userBOObj.getStatus().equalsIgnoreCase("B")) {
					ServiceException serviceExceptionObj = new ServiceException("User is Blocked");
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("P")) {
					ServiceException serviceExceptionObj = new ServiceException("User is Pending for Approval");
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("A")) {

					// Update the Login status
					userListObj.getUl().get(0).setLoginstatus("T");
					Criteria criteriaUpdateObj = new Criteria();
					criteriaUpdateObj.setCriteria("TRUE");
					UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
					updateUserCriteriaObj.setName("loginstatus");
					criteriaUpdateObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);
					update(userListObj, criteriaUpdateObj);
					populateUserDTO(userObj, userBOObj);
				}

			} else {
				ServiceException serviceExceptionObj = new ServiceException(
						"Credentials Incorrect. No matching Object Found");
				throw serviceExceptionObj;
			}
			responseObj.setUserListObj(userListObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserDetails Empty. Check and Resend");
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetch(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		Criteria criteriaObj = reqparam.getCriteria();

		System.out.println("InRDFetch");

		UserList userListObj = new UserList();
		ArrayList<User> userDTOList = new ArrayList<User>();

		ArrayList<UserBO> userBOList;

		userBOList = userdao.fetchUser(criteriaObj, reqparam.getPageno());

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

		responseObj.setUserListObj(userListObj);
		userBOList = userdao.fetchAllUser(criteriaObj);
		int totalrecordcount = 0;
		if (null != userBOList) {

			totalrecordcount = userBOList.size();
		}

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public ResponseObj fetchAllUser(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		Criteria criteriaObj = reqparam.getCriteria();

		System.out.println("InRDFetch");

		UserList userListObj = new UserList();
		ArrayList<User> userDTOList = new ArrayList<User>();

		ArrayList<UserBO> userBOList;

		userBOList = userdao.fetchAllUser(criteriaObj);

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

		responseObj.setUserListObj(userListObj);
		return responseObj;
	}

	public UserBO fetchUserBO(UserList userListObj) {
		System.out.println("InRDLogin");

		ResponseObj responseObj = new ResponseObj();

		UserBO userBOObj = null;
		String pageNo = "1";

		ArrayList<UserBO> userBOList;

		ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

		if (userList.size() > 0) {

			Criteria criteriaObj = new Criteria();
			criteriaObj.setCriteria("TRUE");

			User userObj = userList.get(0);

			FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

			fetchUserCriteriaObj.setName("emailid");
			fetchUserCriteriaObj.setValue(userObj.getUsNa());
			criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

			userBOList = userdao.fetchUser(criteriaObj, pageNo);

			userBOObj = userBOList.get(0);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserDetails Empty. Check and Resend");
			throw serviceExceptionObj;
		}

		return userBOObj;
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

		String pageNo = "1";

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

			userBOList = userdao.fetchUser(criteriaUserObj, pageNo);

			if (null != userBOList && userBOList.size() > 0) {

				userBOObj = userBOList.get(0);

				// update the meetingBO fetched from DB
				if (null != userdtoObj.getAdd()) {
					userBOObj.setAdd(userdtoObj.getAdd());

				}

				if (null != userdtoObj.getAge()) {

					userBOObj.setAge(userdtoObj.getAge());

				}

				if (null != userdtoObj.getCity()) {
					userBOObj.setCity(userdtoObj.getCity());
				}

				if (null != userdtoObj.getConNu()) {
					userBOObj.setConNu(userdtoObj.getConNu());
				}

				if (null != userdtoObj.getFn()) {
					userBOObj.setFn(userdtoObj.getFn());
				}

				if (null != userdtoObj.getGen()) {
					userBOObj.setGen(userdtoObj.getGen());
				}

				if (null != userdtoObj.getLn()) {
					userBOObj.setLn(userdtoObj.getLn());
				}
				if (null != userdtoObj.getZipcode()) {
					userBOObj.setZipcode(userdtoObj.getZipcode());
				}

				if (null != userdtoObj.getCategory()) {
					userBOObj.setCategory(userdtoObj.getCategory());
				}
				if (null != userdtoObj.getTitle()) {
					userBOObj.setTitle(userdtoObj.getTitle());
				}
				if (null != userdtoObj.getRole()) {
					userBOObj.setRole(userdtoObj.getRole());
				}
				if (null != userdtoObj.getStatus()) {
					userBOObj.setStatus(userdtoObj.getStatus());
				}

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

				createActionLog(meetingdtoObj.getMeetdate(), meetingdtoObj.getMeettime(), UnionAppConstants.create,
						meetingdtoObj.getCreator(), meetingdtoObj.getDetail(), UnionAppConstants.meeting,
						meetingdtoObj.getSubject());

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
		// criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");
		criteriaUserObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);
		userBOList = userdao.fetchAllUser(criteriaObj);

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
		int totalrecordcount = meetingdao.totalRecordCount(criteriaObj);

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

			userBOList = userdao.fetchUser(criteriaObj, pageno);

			if (null != userBOList && userBOList.size() > 0) {
				UserBO userBOObj = userBOList.get(0);

				for (MeetingDTO meetingDTOObj : meetingDTOList) {

					/*
					 * fetch and update the meetingTable with the list of user
					 * who accepted or declined. This is stored in comman
					 * separated list hence creating the String below.
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
					if (meetingDTOObj.getAcceptdenyind().equalsIgnoreCase("delete")) {
						meetingdao.deleteOnCriteria(meetingBOObj, criteriaObj);
					} else if (meetingDTOObj.getAcceptdenyind().equalsIgnoreCase("accept")) {

						// for meeting table

						if (acceptuserid == null || acceptuserid.equals("")) {

							acceptuserid = userBOObj.getUsname();
						} else {

							acceptuserid = acceptuserid + "," + userBOObj.getUsname();
						}
						acceptcount = acceptcount + 1;

					} else {

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

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		Criteria criteriameetingObj = new Criteria();
		criteriameetingObj.setCriteria("TRUE");

		FetchMeetingCriteria fetchMeetingCriteriaObj = new FetchMeetingCriteria();

		fetchMeetingCriteriaObj.setName("meetingid");

		if (meetingList.size() > 0) {

			MeetingDTO meetingdtoObj = meetingList.get(0);

			fetchMeetingCriteriaObj.setValue(meetingdtoObj.getMeetingid());
			criteriameetingObj.setFetchMeetingCriteriaObj(fetchMeetingCriteriaObj);
			meetingBOList = meetingdao.fetchMeeting(criteriameetingObj, "1");

			try {
				if (null != meetingBOList && meetingBOList.size() > 0) {

					meetingBOObj = meetingBOList.get(0);

					// update the meetingBO fetched from DB
					if (meetingdtoObj.getStatus().equalsIgnoreCase("Delete")) {
						meetingdao.deleteOnCriteria(meetingBOObj, null);
					} else {
						meetingBOObj.setCreator(meetingdtoObj.getCreator());
						meetingBOObj.setDetail(meetingdtoObj.getDetail());
						meetingBOObj.setStatus(meetingdtoObj.getStatus());
						meetingBOObj.setSubject(meetingdtoObj.getSubject());
						meetingBOObj.setVenue(meetingdtoObj.getVenue());
						meetingBOObj.setMeetdate(
								dateformatter.parse(meetingdtoObj.getMeetdate() + " " + meetingdtoObj.getMeettime()));
						// meetingBOObj.setMeettime(timeformatter.parse(meetingdtoObj.getMeettime()));

						// merge this UpdateBO back in DB
						meetingdao.update(meetingBOObj);
						createActionLog(meetingdtoObj.getMeetdate(), meetingdtoObj.getMeettime(),
								UnionAppConstants.update, meetingdtoObj.getCreator(), meetingdtoObj.getDetail(),
								UnionAppConstants.meeting, meetingdtoObj.getSubject());
					}

				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
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

				createActionLog(activitydtoObj.getActdate(), activitydtoObj.getActtime(), UnionAppConstants.create,
						activitydtoObj.getCreator(), activitydtoObj.getDetail(), UnionAppConstants.activity,
						activitydtoObj.getSubject());
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
		// criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

		Criteria criteriaUserObj = new Criteria();
		criteriaUserObj.setCriteria("TRUE");
		criteriaUserObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);
		userBOList = userdao.fetchAllUser(criteriaObj);

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
		int totalrecordcount = activitydao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
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

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		Criteria criteriaactivityObj = new Criteria();
		criteriaactivityObj.setCriteria("TRUE");

		FetchActivityCriteria fetchActivityCriteriaObj = new FetchActivityCriteria();

		fetchActivityCriteriaObj.setName("activityid");

		if (activityList.size() > 0) {

			ActivityDTO activitydtoObj = activityList.get(0);

			fetchActivityCriteriaObj.setValue(activitydtoObj.getActivityid());
			criteriaactivityObj.setFetchActivityCriteriaObj(fetchActivityCriteriaObj);

			activityBOList = activitydao.fetchActivity(criteriaactivityObj, "1");

			try {
				if (null != activityBOList && activityBOList.size() > 0) {

					activityBOObj = activityBOList.get(0);
					if (activitydtoObj.getStatus().equalsIgnoreCase("Delete")) {
						activitydao.deleteOnCriteria(activityBOObj, null);
					} else {

						// update the activityBO fetched from DB

						activityBOObj.setCreator(activitydtoObj.getCreator());
						activityBOObj.setDetail(activitydtoObj.getDetail());
						activityBOObj.setStatus(activitydtoObj.getStatus());
						activityBOObj.setSubject(activitydtoObj.getSubject());
						activityBOObj.setVenue(activitydtoObj.getVenue());
						activityBOObj.setActdate(
								dateformatter.parse(activitydtoObj.getActdate() + " " + activitydtoObj.getActtime()));
						// activityBOObj.setActtime(timeformatter.parse(activitydtoObj.getActtime()));

						// merge this UpdateBO back in DB
						activitydao.update(activityBOObj);
						createActionLog(activitydtoObj.getActdate(), activitydtoObj.getActtime(),
								UnionAppConstants.update, activitydtoObj.getCreator(), activitydtoObj.getDetail(),
								UnionAppConstants.activity, activitydtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
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

			userBOList = userdao.fetchUser(criteriaObj, pageno);

			if (null != userBOList && userBOList.size() > 0) {
				UserBO userBOObj = userBOList.get(0);

				for (ActivityDTO activityDTOObj : activityDTOList) {

					/*
					 * fetch and update the activityTable with the list of user
					 * who accepted or declined.
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

					if (activityDTOObj.getAcceptdenyind().equalsIgnoreCase("delete")) {
						activitydao.deleteOnCriteria(activityBOObj, criteriaObj);
					} else if (activityDTOObj.getAcceptdenyind().equalsIgnoreCase("accept")) {

						// for Activity table

						if (acceptuserid == null || acceptuserid.equals("")) {

							acceptuserid = userBOObj.getUsname();
						} else {

							acceptuserid = acceptuserid + "," + userBOObj.getUsname();
						}
						acceptcount = acceptcount + 1;

					} else {

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

		return responseObj;
	}

	public NewsLetterList createNewsLetter(NewsLetterList newsLetterListObj) {
		System.out.println("In createNewsLetter");

		ArrayList<NewsLetterDTO> NewsLetterList = (ArrayList<NewsLetterDTO>) newsLetterListObj.getNewsletterdtoLs();
		NewsLetterList NewsLetterListObjResp = new NewsLetterList();

		if (NewsLetterList.size() > 0) {
			Iterator<NewsLetterDTO> NewsLetterListIterator = NewsLetterList.iterator();

			while (NewsLetterListIterator.hasNext()) {

				NewsLetterDTO NewsLetterdtoObj = NewsLetterListIterator.next();

				NewsLetterBO NewsLetterBOObj = new NewsLetterBO();

				populateCreateNewsLetterBO(NewsLetterdtoObj, NewsLetterBOObj);
				newsletterdao.createNewsLetter(NewsLetterBOObj);
				populateNewsLetterDTO(NewsLetterdtoObj, NewsLetterBOObj);

				createActionLog(NewsLetterdtoObj.getNldate(), NewsLetterdtoObj.getNltime(), UnionAppConstants.create,
						NewsLetterdtoObj.getCreator(), NewsLetterdtoObj.getDetail(), UnionAppConstants.newsletter,
						NewsLetterdtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return NewsLetterListObjResp;
	}

	public ResponseObj fetchNewsLetter(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();
		String channel = reqparam.getChannel();
		NewsLetterList NewsLetterListObj = new NewsLetterList();
		ArrayList<NewsLetterDTO> NewsLetterDTOList = new ArrayList<NewsLetterDTO>();

		ArrayList<NewsLetterBO> newsLetterBOList;

		NewsLetterBO NewsLetterBOObj;

		Criteria criteriaObj = reqparam.getCriteria();

		newsLetterBOList = newsletterdao.fetchNewsLetter(criteriaObj, reqparam.getPageno());

		if (null != newsLetterBOList && newsLetterBOList.size() > 0) {

			Iterator<NewsLetterBO> litr = newsLetterBOList.iterator();

			while (litr.hasNext()) {

				NewsLetterBOObj = litr.next();
				NewsLetterDTO NewsLetterDTOObj = new NewsLetterDTO();
				populateNewsLetterDTO(NewsLetterDTOObj, NewsLetterBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					NewsLetterDTOObj.setDetail("");
				}
				NewsLetterDTOList.add(NewsLetterDTOObj);
			}

			NewsLetterListObj.setNewsletterdtoLs(NewsLetterDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setNewsLetterListObj(NewsLetterListObj);
		int totalrecordcount = newsletterdao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public String fetchNewsLetterById(String id) {
		System.out.println("In fetchNewsLetterById");
		String responseObj = "";

		ArrayList<NewsLetterBO> newsLetterBOList = newsletterdao.fetchNewsLetterById(id);

		if (null != newsLetterBOList && newsLetterBOList.size() > 0) {

			Iterator<NewsLetterBO> litr = newsLetterBOList.iterator();

			while (litr.hasNext()) {

				NewsLetterBO newsLetterBOObj = litr.next();
				responseObj = newsLetterBOObj.getDetail();
			}
		}
		return responseObj;
	}

	public ResponseObj updateNewsLetter(RequestObj reqparam) {
		System.out.println("In updateNewsLetter");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the NewsLetter from the DB basis the id coming in the
		 * request Then update teh fields of the NewsLetterBo fetched from DB
		 * with those received in the input
		 */
		NewsLetterList newsLetterListObj = reqparam.getNewsLetterListObj();

		ArrayList<NewsLetterDTO> NewsLetterList = (ArrayList<NewsLetterDTO>) newsLetterListObj.getNewsletterdtoLs();

		ArrayList<NewsLetterBO> NewsLetterBOList;

		NewsLetterBO NewsLetterBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Criteria criteriaNewsLetterObj = new Criteria();
		criteriaNewsLetterObj.setCriteria("TRUE");

		FetchNewsLetterCriteria fetchNewsLetterCriteriaObj = new FetchNewsLetterCriteria();

		fetchNewsLetterCriteriaObj.setName("nlid");

		if (NewsLetterList.size() > 0) {

			NewsLetterDTO NewsLetterdtoObj = NewsLetterList.get(0);

			fetchNewsLetterCriteriaObj.setValue(NewsLetterdtoObj.getNlid());
			criteriaNewsLetterObj.setFetchNewsLetterCriteriaObj(fetchNewsLetterCriteriaObj);

			NewsLetterBOList = newsletterdao.fetchNewsLetter(criteriaNewsLetterObj, "1");

			try {
				if (null != NewsLetterBOList && NewsLetterBOList.size() > 0) {

					NewsLetterBOObj = NewsLetterBOList.get(0);
					if (NewsLetterdtoObj.getStatus().equalsIgnoreCase("Delete")) {
						newsletterdao.deleteOnCriteria(NewsLetterBOObj, null);
					} else {

						// update the NewsLetterBO fetched from DB

						NewsLetterBOObj.setCreator(NewsLetterdtoObj.getCreator());
						NewsLetterBOObj.setDetail(NewsLetterdtoObj.getDetail());
						NewsLetterBOObj.setStatus(NewsLetterdtoObj.getStatus());
						NewsLetterBOObj.setSubject(NewsLetterdtoObj.getSubject());
						NewsLetterBOObj.setNldate(
								dateformatter.parse(NewsLetterdtoObj.getNldate() + " " + NewsLetterdtoObj.getNltime()));

						// merge this UpdateBO back in DB
						newsletterdao.update(NewsLetterBOObj);

						createActionLog(NewsLetterdtoObj.getNldate(), NewsLetterdtoObj.getNltime(),
								UnionAppConstants.update, NewsLetterdtoObj.getCreator(), NewsLetterdtoObj.getDetail(),
								UnionAppConstants.newsletter, NewsLetterdtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populateNewsLetterDTO(NewsLetterdtoObj, NewsLetterBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("NewsLetterList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setNewsLetterListObj(newsLetterListObj);

		return responseObj;
	}

	public PayrateList createPayrate(PayrateList PayrateListObj) {
		System.out.println("In createPayrate");

		ArrayList<PayrateDTO> PayrateList = (ArrayList<PayrateDTO>) PayrateListObj.getPayratedtoLs();
		PayrateList PayrateListObjResp = new PayrateList();

		if (PayrateList.size() > 0) {
			Iterator<PayrateDTO> PayrateListIterator = PayrateList.iterator();

			while (PayrateListIterator.hasNext()) {

				PayrateDTO PayratedtoObj = PayrateListIterator.next();

				PayrateBO PayrateBOObj = new PayrateBO();

				populateCreatePayrateBO(PayratedtoObj, PayrateBOObj);
				payratedao.createPayrate(PayrateBOObj);
				populatePayrateDTO(PayratedtoObj, PayrateBOObj);

				createActionLog(PayratedtoObj.getPaydate(), PayratedtoObj.getPaytime(), UnionAppConstants.create,
						PayratedtoObj.getCreator(), PayratedtoObj.getDetail(), UnionAppConstants.payrate,
						PayratedtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return PayrateListObjResp;
	}

	public ResponseObj fetchPayrate(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();
		String channel = reqparam.getChannel();
		PayrateList PayrateListObj = new PayrateList();
		ArrayList<PayrateDTO> PayrateDTOList = new ArrayList<PayrateDTO>();

		ArrayList<PayrateBO> PayrateBOList;

		PayrateBO PayrateBOObj;

		Criteria criteriaObj = reqparam.getCriteria();

		PayrateBOList = payratedao.fetchPayrate(criteriaObj, reqparam.getPageno());

		if (null != PayrateBOList && PayrateBOList.size() > 0) {

			Iterator<PayrateBO> litr = PayrateBOList.iterator();

			while (litr.hasNext()) {

				PayrateBOObj = litr.next();
				PayrateDTO PayrateDTOObj = new PayrateDTO();
				populatePayrateDTO(PayrateDTOObj, PayrateBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					PayrateDTOObj.setDetail("");
				}
				PayrateDTOList.add(PayrateDTOObj);
			}

			PayrateListObj.setPayratedtoLs(PayrateDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setPayrateListObj(PayrateListObj);
		int totalrecordcount = payratedao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public String fetchPayrateById(String id) {
		System.out.println("In fetchPayrateById");
		String responseObj = "";

		ArrayList<PayrateBO> PayrateBOList = payratedao.fetchPayrateById(id);

		if (null != PayrateBOList && PayrateBOList.size() > 0) {

			Iterator<PayrateBO> litr = PayrateBOList.iterator();

			while (litr.hasNext()) {

				PayrateBO PayrateBOObj = litr.next();
				responseObj = PayrateBOObj.getDetail();
			}
		}
		return responseObj;
	}

	public ResponseObj updatePayrate(RequestObj reqparam) {
		System.out.println("In updatePayrate");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the Payrate from the DB basis the id coming in the
		 * request Then update teh fields of the PayrateBo fetched from DB with
		 * those received in the input
		 */
		PayrateList PayrateListObj = reqparam.getPayrateListObj();

		ArrayList<PayrateDTO> PayrateList = (ArrayList<PayrateDTO>) PayrateListObj.getPayratedtoLs();

		ArrayList<PayrateBO> PayrateBOList;

		PayrateBO PayrateBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Criteria criteriaPayrateObj = new Criteria();
		criteriaPayrateObj.setCriteria("TRUE");

		FetchPayrateCriteria fetchPayrateCriteriaObj = new FetchPayrateCriteria();

		fetchPayrateCriteriaObj.setName("payid");

		if (PayrateList.size() > 0) {

			PayrateDTO PayratedtoObj = PayrateList.get(0);

			fetchPayrateCriteriaObj.setValue(PayratedtoObj.getPayid());
			criteriaPayrateObj.setFetchPayrateCriteriaObj(fetchPayrateCriteriaObj);

			PayrateBOList = payratedao.fetchPayrate(criteriaPayrateObj, "1");

			try {
				if (null != PayrateBOList && PayrateBOList.size() > 0) {

					PayrateBOObj = PayrateBOList.get(0);
					if (PayratedtoObj.getStatus().equalsIgnoreCase("Delete")) {
						payratedao.deleteOnCriteria(PayrateBOObj, null);
					} else {

						// update the PayrateBO fetched from DB

						PayrateBOObj.setCreator(PayratedtoObj.getCreator());
						PayrateBOObj.setDetail(PayratedtoObj.getDetail());
						PayrateBOObj.setStatus(PayratedtoObj.getStatus());
						PayrateBOObj.setSubject(PayratedtoObj.getSubject());
						PayrateBOObj.setPaydate(
								dateformatter.parse(PayratedtoObj.getPaydate() + " " + PayratedtoObj.getPaytime()));

						// merge this UpdateBO back in DB
						payratedao.update(PayrateBOObj);

						createActionLog(PayratedtoObj.getPaydate(), PayratedtoObj.getPaytime(),
								UnionAppConstants.update, PayratedtoObj.getCreator(), PayratedtoObj.getDetail(),
								UnionAppConstants.payrate, PayratedtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populatePayrateDTO(PayratedtoObj, PayrateBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("PayrateList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setPayrateListObj(PayrateListObj);

		return responseObj;
	}

	public AmrList createAmr(AmrList AmrListObj) {
		System.out.println("In createAmr");

		ArrayList<AmrDTO> AmrList = (ArrayList<AmrDTO>) AmrListObj.getAmrdtoLs();
		AmrList AmrListObjResp = new AmrList();

		if (AmrList.size() > 0) {
			Iterator<AmrDTO> AmrListIterator = AmrList.iterator();

			while (AmrListIterator.hasNext()) {

				AmrDTO AmrdtoObj = AmrListIterator.next();

				AmrBO AmrBOObj = new AmrBO();

				populateCreateAmrBO(AmrdtoObj, AmrBOObj);
				amrdao.createAmr(AmrBOObj);
				populateAmrDTO(AmrdtoObj, AmrBOObj);

				createActionLog(AmrdtoObj.getAmrdate(), AmrdtoObj.getAmrtime(), UnionAppConstants.create,
						AmrdtoObj.getCreator(), AmrdtoObj.getDetail(), UnionAppConstants.amr, AmrdtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return AmrListObjResp;
	}

	public ResponseObj fetchAmr(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();
		String channel = reqparam.getChannel();
		AmrList AmrListObj = new AmrList();
		ArrayList<AmrDTO> AmrDTOList = new ArrayList<AmrDTO>();

		ArrayList<AmrBO> AmrBOList;

		AmrBO AmrBOObj;

		Criteria criteriaObj = reqparam.getCriteria();

		AmrBOList = amrdao.fetchAmr(criteriaObj, reqparam.getPageno());

		if (null != AmrBOList && AmrBOList.size() > 0) {

			Iterator<AmrBO> litr = AmrBOList.iterator();

			while (litr.hasNext()) {

				AmrBOObj = litr.next();
				AmrDTO AmrDTOObj = new AmrDTO();
				populateAmrDTO(AmrDTOObj, AmrBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					AmrDTOObj.setDetail("");
				}
				AmrDTOList.add(AmrDTOObj);
			}

			AmrListObj.setAmrdtoLs(AmrDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setAmrListObj(AmrListObj);
		int totalrecordcount = amrdao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public String fetchAmrById(String id) {
		System.out.println("In fetchAmrById");
		String responseObj = "";

		ArrayList<AmrBO> AmrBOList = amrdao.fetchAmrById(id);

		if (null != AmrBOList && AmrBOList.size() > 0) {

			Iterator<AmrBO> litr = AmrBOList.iterator();

			while (litr.hasNext()) {

				AmrBO AmrBOObj = litr.next();
				responseObj = AmrBOObj.getDetail();
			}
		}
		return responseObj;
	}

	public ResponseObj updateAmr(RequestObj reqparam) {
		System.out.println("In updateAmr");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the Amr from the DB basis the id coming in the request
		 * Then update teh fields of the AmrBo fetched from DB with those
		 * received in the input
		 */
		AmrList AmrListObj = reqparam.getAmrListObj();

		ArrayList<AmrDTO> AmrList = (ArrayList<AmrDTO>) AmrListObj.getAmrdtoLs();

		ArrayList<AmrBO> AmrBOList;

		AmrBO AmrBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Criteria criteriaAmrObj = new Criteria();
		criteriaAmrObj.setCriteria("TRUE");

		FetchAmrCriteria fetchAmrCriteriaObj = new FetchAmrCriteria();

		fetchAmrCriteriaObj.setName("amrid");

		if (AmrList.size() > 0) {

			AmrDTO AmrdtoObj = AmrList.get(0);

			fetchAmrCriteriaObj.setValue(AmrdtoObj.getAmrid());
			criteriaAmrObj.setFetchAmrCriteriaObj(fetchAmrCriteriaObj);

			AmrBOList = amrdao.fetchAmr(criteriaAmrObj, "1");

			try {
				if (null != AmrBOList && AmrBOList.size() > 0) {

					AmrBOObj = AmrBOList.get(0);
					if (AmrdtoObj.getStatus().equalsIgnoreCase("Delete")) {
						amrdao.deleteOnCriteria(AmrBOObj, null);
					} else {

						// update the AmrBO fetched from DB

						AmrBOObj.setCreator(AmrdtoObj.getCreator());
						AmrBOObj.setDetail(AmrdtoObj.getDetail());
						AmrBOObj.setStatus(AmrdtoObj.getStatus());
						AmrBOObj.setSubject(AmrdtoObj.getSubject());
						AmrBOObj.setAmrdate(dateformatter.parse(AmrdtoObj.getAmrdate() + " " + AmrdtoObj.getAmrtime()));

						// merge this UpdateBO back in DB
						amrdao.update(AmrBOObj);

						createActionLog(AmrdtoObj.getAmrdate(), AmrdtoObj.getAmrtime(), UnionAppConstants.update,
								AmrdtoObj.getCreator(), AmrdtoObj.getDetail(), UnionAppConstants.amr,
								AmrdtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populateAmrDTO(AmrdtoObj, AmrBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("AmrList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setAmrListObj(AmrListObj);

		return responseObj;
	}

	public AgreementList createAgreement(AgreementList AgreementListObj) {
		System.out.println("In createAgreement");

		ArrayList<AgreementDTO> AgreementList = (ArrayList<AgreementDTO>) AgreementListObj.getAgreementdtoLs();
		AgreementList AgreementListObjResp = new AgreementList();

		if (AgreementList.size() > 0) {
			Iterator<AgreementDTO> AgreementListIterator = AgreementList.iterator();

			while (AgreementListIterator.hasNext()) {

				AgreementDTO AgreementdtoObj = AgreementListIterator.next();

				AgreementBO AgreementBOObj = new AgreementBO();

				populateCreateAgreementBO(AgreementdtoObj, AgreementBOObj);
				agreementdao.createAgreement(AgreementBOObj);
				populateAgreementDTO(AgreementdtoObj, AgreementBOObj);

				createActionLog(AgreementdtoObj.getArmdate(), AgreementdtoObj.getArmtime(), UnionAppConstants.create,
						AgreementdtoObj.getCreator(), AgreementdtoObj.getDetail(), UnionAppConstants.agreement,
						AgreementdtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return AgreementListObjResp;
	}

	public ResponseObj fetchAgreement(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();
		String channel = reqparam.getChannel();
		AgreementList AgreementListObj = new AgreementList();
		ArrayList<AgreementDTO> AgreementDTOList = new ArrayList<AgreementDTO>();

		ArrayList<AgreementBO> AgreementBOList;

		AgreementBO AgreementBOObj;

		Criteria criteriaObj = reqparam.getCriteria();

		AgreementBOList = agreementdao.fetchAgreement(criteriaObj, reqparam.getPageno());

		if (null != AgreementBOList && AgreementBOList.size() > 0) {

			Iterator<AgreementBO> litr = AgreementBOList.iterator();

			while (litr.hasNext()) {

				AgreementBOObj = litr.next();
				AgreementDTO AgreementDTOObj = new AgreementDTO();
				populateAgreementDTO(AgreementDTOObj, AgreementBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					AgreementDTOObj.setDetail("");
				}
				AgreementDTOList.add(AgreementDTOObj);
			}

			AgreementListObj.setAgreementdtoLs(AgreementDTOList);
			// setAttachments(AgreementListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setAgreementListObj(AgreementListObj);
		int totalrecordcount = agreementdao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	/*
	 * private void setAttachments(AgreementList agreementListObj) {
	 * AgreementDTO agrm = agreementListObj.getAgreementdtoLs().get(0);
	 * AttachmentList al = new AttachmentList(); al.getAttachmentdtoLs().add(
	 * new AttachmentDTO("Docx File",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.docx", "doc"));
	 * al.getAttachmentdtoLs().add( new AttachmentDTO("PDF File",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.pdf", "doc"));
	 * al.getAttachmentdtoLs().add(new AttachmentDTO("Image File PNG",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.PNG", "image"));
	 * al.getAttachmentdtoLs().add(new AttachmentDTO("Image File JPEG",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.jpeg", "image"));
	 * al.getAttachmentdtoLs().add(new AttachmentDTO("Image File GIF",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.gif", "image"));
	 * al.getAttachmentdtoLs().add(new AttachmentDTO("Image File JPG",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.jpg", "image"));
	 * al.getAttachmentdtoLs().add( new AttachmentDTO("Excel File",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.xlsx", "doc"));
	 * al.getAttachmentdtoLs().add( new AttachmentDTO("Text File",
	 * "http://codeplay-dev6.cloud.cms500.com/attachments/demo.txt", "doc"));
	 * al.setListSize(8); for (int i = 1; i <
	 * agreementListObj.getAgreementdtoLs().size(); i++) {
	 * agreementListObj.getAgreementdtoLs().get(i).getAttachmentlist().
	 * setListSize(0); }
	 * 
	 * agrm.setAttachmentlist(al); }
	 */
	public String fetchAgreementById(String id) {
		System.out.println("In fetchAgreementById");
		String responseObj = "";

		ArrayList<AgreementBO> AgreementBOList = agreementdao.fetchAgreementById(id);

		if (null != AgreementBOList && AgreementBOList.size() > 0) {

			Iterator<AgreementBO> litr = AgreementBOList.iterator();

			while (litr.hasNext()) {

				AgreementBO AgreementBOObj = litr.next();
				responseObj = AgreementBOObj.getDetail();
			}
		}
		return responseObj;
	}

	public ResponseObj updateAgreement(RequestObj reqparam) {
		System.out.println("In updateAgreement");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the Agreement from the DB basis the id coming in the
		 * request Then update teh fields of the AgreementBo fetched from DB
		 * with those received in the input
		 */
		AgreementList AgreementListObj = reqparam.getAgreementListObj();

		ArrayList<AgreementDTO> AgreementList = (ArrayList<AgreementDTO>) AgreementListObj.getAgreementdtoLs();

		ArrayList<AgreementBO> AgreementBOList;

		AgreementBO AgreementBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Criteria criteriaAgreementObj = new Criteria();
		criteriaAgreementObj.setCriteria("TRUE");

		FetchAgreementCriteria fetchAgreementCriteriaObj = new FetchAgreementCriteria();

		fetchAgreementCriteriaObj.setName("armid");

		if (AgreementList.size() > 0) {

			AgreementDTO AgreementdtoObj = AgreementList.get(0);

			fetchAgreementCriteriaObj.setValue(AgreementdtoObj.getArmid());
			criteriaAgreementObj.setFetchAgreementCriteriaObj(fetchAgreementCriteriaObj);

			AgreementBOList = agreementdao.fetchAgreement(criteriaAgreementObj, "1");

			try {
				if (null != AgreementBOList && AgreementBOList.size() > 0) {

					AgreementBOObj = AgreementBOList.get(0);
					if (AgreementdtoObj.getStatus().equalsIgnoreCase("Delete")) {
						agreementdao.deleteOnCriteria(AgreementBOObj, null);
					} else {

						// update the AgreementBO fetched from DB

						AgreementBOObj.setCreator(AgreementdtoObj.getCreator());
						AgreementBOObj.setDetail(AgreementdtoObj.getDetail());
						AgreementBOObj.setStatus(AgreementdtoObj.getStatus());
						AgreementBOObj.setSubject(AgreementdtoObj.getSubject());
						AgreementBOObj.setArmdate(
								dateformatter.parse(AgreementdtoObj.getArmdate() + " " + AgreementdtoObj.getArmtime()));

						// merge this UpdateBO back in DB
						agreementdao.update(AgreementBOObj);

						createActionLog(AgreementdtoObj.getArmdate(), AgreementdtoObj.getArmtime(),
								UnionAppConstants.update, AgreementdtoObj.getCreator(), AgreementdtoObj.getDetail(),
								UnionAppConstants.agreement, AgreementdtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populateAgreementDTO(AgreementdtoObj, AgreementBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("AgreementList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setAgreementListObj(AgreementListObj);

		return responseObj;
	}

	public SummaryList createSummary(SummaryList SummaryListObj) {
		System.out.println("In createSummary");

		ArrayList<SummaryDTO> SummaryList = (ArrayList<SummaryDTO>) SummaryListObj.getSummarydtoLs();
		SummaryList SummaryListObjResp = new SummaryList();

		if (SummaryList.size() > 0) {
			Iterator<SummaryDTO> SummaryListIterator = SummaryList.iterator();

			while (SummaryListIterator.hasNext()) {

				SummaryDTO SummarydtoObj = SummaryListIterator.next();

				SummaryBO SummaryBOObj = new SummaryBO();

				populateCreateSummaryBO(SummarydtoObj, SummaryBOObj);
				summarydao.createSummary(SummaryBOObj);
				populateSummaryDTO(SummarydtoObj, SummaryBOObj);

				createActionLog(SummarydtoObj.getSumdate(), SummarydtoObj.getSumtime(), UnionAppConstants.create,
						SummarydtoObj.getCreator(), SummarydtoObj.getDetail(), UnionAppConstants.summary,
						SummarydtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return SummaryListObjResp;
	}

	public ResponseObj fetchSummary(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();
		String channel = reqparam.getChannel();
		SummaryList SummaryListObj = new SummaryList();
		ArrayList<SummaryDTO> SummaryDTOList = new ArrayList<SummaryDTO>();

		ArrayList<SummaryBO> SummaryBOList;

		SummaryBO SummaryBOObj;

		Criteria criteriaObj = reqparam.getCriteria();

		SummaryBOList = summarydao.fetchSummary(criteriaObj, reqparam.getPageno());

		if (null != SummaryBOList && SummaryBOList.size() > 0) {

			Iterator<SummaryBO> litr = SummaryBOList.iterator();

			while (litr.hasNext()) {

				SummaryBOObj = litr.next();
				SummaryDTO SummaryDTOObj = new SummaryDTO();
				populateSummaryDTO(SummaryDTOObj, SummaryBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					SummaryDTOObj.setDetail("");
				}
				SummaryDTOList.add(SummaryDTOObj);
			}

			SummaryListObj.setSummarydtoLs(SummaryDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}
		responseObj.setSummaryListObj(SummaryListObj);
		int totalrecordcount = summarydao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public String fetchSummaryById(String id) {
		System.out.println("In fetchSummaryById");
		String responseObj = "";

		ArrayList<SummaryBO> SummaryBOList = summarydao.fetchSummaryById(id);

		if (null != SummaryBOList && SummaryBOList.size() > 0) {

			Iterator<SummaryBO> litr = SummaryBOList.iterator();

			while (litr.hasNext()) {

				SummaryBO SummaryBOObj = litr.next();
				responseObj = SummaryBOObj.getDetail();
			}
		}
		return responseObj;
	}

	public ResponseObj updateSummary(RequestObj reqparam) {
		System.out.println("In updateSummary");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the Summary from the DB basis the id coming in the
		 * request Then update teh fields of the SummaryBo fetched from DB with
		 * those received in the input
		 */
		SummaryList SummaryListObj = reqparam.getSummaryListObj();

		ArrayList<SummaryDTO> SummaryList = (ArrayList<SummaryDTO>) SummaryListObj.getSummarydtoLs();

		ArrayList<SummaryBO> SummaryBOList;

		SummaryBO SummaryBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Criteria criteriaSummaryObj = new Criteria();
		criteriaSummaryObj.setCriteria("TRUE");

		FetchSummaryCriteria fetchSummaryCriteriaObj = new FetchSummaryCriteria();

		fetchSummaryCriteriaObj.setName("sumid");

		if (SummaryList.size() > 0) {

			SummaryDTO SummarydtoObj = SummaryList.get(0);

			fetchSummaryCriteriaObj.setValue(SummarydtoObj.getSumid());
			criteriaSummaryObj.setFetchSummaryCriteriaObj(fetchSummaryCriteriaObj);

			SummaryBOList = summarydao.fetchSummary(criteriaSummaryObj, "1");

			try {
				if (null != SummaryBOList && SummaryBOList.size() > 0) {

					SummaryBOObj = SummaryBOList.get(0);
					if (SummarydtoObj.getStatus().equalsIgnoreCase("Delete")) {
						summarydao.deleteOnCriteria(SummaryBOObj, null);
					} else {

						// update the SummaryBO fetched from DB

						SummaryBOObj.setCreator(SummarydtoObj.getCreator());
						SummaryBOObj.setDetail(SummarydtoObj.getDetail());
						SummaryBOObj.setStatus(SummarydtoObj.getStatus());
						SummaryBOObj.setSubject(SummarydtoObj.getSubject());
						SummaryBOObj.setSumdate(
								dateformatter.parse(SummarydtoObj.getSumdate() + " " + SummarydtoObj.getSumtime()));

						// merge this UpdateBO back in DB
						summarydao.update(SummaryBOObj);

						createActionLog(SummarydtoObj.getSumdate(), SummarydtoObj.getSumtime(),
								UnionAppConstants.update, SummarydtoObj.getCreator(), SummarydtoObj.getDetail(),
								UnionAppConstants.summary, SummarydtoObj.getSubject());
					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populateSummaryDTO(SummarydtoObj, SummaryBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("SummaryList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setSummaryListObj(SummaryListObj);

		return responseObj;
	}

	public SuggestionIdeaList createsuggestionidea(SuggestionIdeaList suggestionIdeaListObj) {
		System.out.println("InRDRegister");

		ArrayList<SuggestionIdeaDTO> suggestionIdeaList = (ArrayList<SuggestionIdeaDTO>) suggestionIdeaListObj
				.getSuggestionideadtoLs();
		SuggestionIdeaList suggestionIdeaListObjResp = new SuggestionIdeaList();

		if (suggestionIdeaList.size() > 0) {
			Iterator<SuggestionIdeaDTO> suggestionIdeaListIterator = suggestionIdeaList.iterator();

			while (suggestionIdeaListIterator.hasNext()) {

				SuggestionIdeaDTO suggestionIdeadtoObj = suggestionIdeaListIterator.next();

				SuggestionIdeaBO suggestionIdeaBOObj = new SuggestionIdeaBO();

				populateCreateSuggestionIdeaBO(suggestionIdeadtoObj, suggestionIdeaBOObj);
				suggestionIdeadao.createSuggestionIdea(suggestionIdeaBOObj);
				populateSuggestionIdeaDTO(suggestionIdeadtoObj, suggestionIdeaBOObj);

				createActionLog(suggestionIdeadtoObj.getSuggideadate(), suggestionIdeadtoObj.getSuggideatime(),
						UnionAppConstants.create, suggestionIdeadtoObj.getCreator(), suggestionIdeadtoObj.getDetail(),
						UnionAppConstants.suggestionidea, suggestionIdeadtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("UserList is NULL");
			throw serviceExceptionObj;
		}

		return suggestionIdeaListObjResp;
	}

	public String fetchsuggestionideaById(String id) {
		System.out.println("In fetchsuggestionideaById");
		String responseObj = "";

		ArrayList<SuggestionIdeaBO> suggestionIdeaBOList = suggestionIdeadao.fetchSuggestionIdeaById(id);

		if (null != suggestionIdeaBOList && suggestionIdeaBOList.size() > 0) {

			Iterator<SuggestionIdeaBO> litr = suggestionIdeaBOList.iterator();

			while (litr.hasNext()) {

				SuggestionIdeaBO suggestionIdeaBOObj = litr.next();
				responseObj = suggestionIdeaBOObj.getDetail();
				responseObj = responseObj + "<br><br><b>Suggested By : " + suggestionIdeaBOObj.getCreator() + "</b>";

			}
		}
		return responseObj;
	}

	public ResponseObj fetchsuggestionidea(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();

		SuggestionIdeaList suggestionIdeaListObj = new SuggestionIdeaList();
		ArrayList<SuggestionIdeaDTO> suggestionIdeaDTOList = new ArrayList<SuggestionIdeaDTO>();

		ArrayList<SuggestionIdeaBO> suggestionIdeaBOList;

		SuggestionIdeaBO suggestionIdeaBOObj;

		String channel = reqparam.getChannel();

		Criteria criteriaObj = reqparam.getCriteria();

		suggestionIdeaBOList = suggestionIdeadao.fetchSuggestionIdea(criteriaObj, reqparam.getPageno());

		if (null != suggestionIdeaBOList && suggestionIdeaBOList.size() > 0) {

			Iterator<SuggestionIdeaBO> litr = suggestionIdeaBOList.iterator();

			while (litr.hasNext()) {

				suggestionIdeaBOObj = litr.next();
				SuggestionIdeaDTO suggestionIdeaDTOObj = new SuggestionIdeaDTO();
				populateSuggestionIdeaDTO(suggestionIdeaDTOObj, suggestionIdeaBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					suggestionIdeaDTOObj.setDetail("");
				}

				suggestionIdeaDTOList.add(suggestionIdeaDTOObj);

			}

			suggestionIdeaListObj.setSuggestionideadtoLs(suggestionIdeaDTOList);
		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		responseObj.setSuggestionIdeaListObj(suggestionIdeaListObj);
		int totalrecordcount = suggestionIdeadao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public ResponseObj updatesuggestionidea(RequestObj reqparam) {
		System.out.println("InRDUpdate");

		ResponseObj responseObj = new ResponseObj();

		/*
		 * First fetch the suggestionIdea from the DB basis the id coming in the
		 * request Then update teh fields of the suggestionIdeaBO fetched from
		 * DB with those received in the input
		 */
		SuggestionIdeaList suggestionIdeaListObj = reqparam.getSuggestionIdeaListObj();

		ArrayList<SuggestionIdeaDTO> suggestionIdeaList = (ArrayList<SuggestionIdeaDTO>) suggestionIdeaListObj
				.getSuggestionideadtoLs();

		ArrayList<SuggestionIdeaBO> suggestionIdeaBOList;

		SuggestionIdeaBO suggestionIdeaBOObj = null;

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		Criteria criteriaSuggestionIdeaObj = new Criteria();
		criteriaSuggestionIdeaObj.setCriteria("TRUE");

		FetchSuggestionIdeaCriteria fetchSuggestionIdeaCriteriaObj = new FetchSuggestionIdeaCriteria();

		fetchSuggestionIdeaCriteriaObj.setName("suggestionideaid");

		if (suggestionIdeaList.size() > 0) {

			SuggestionIdeaDTO suggestionIdeadtoObj = suggestionIdeaList.get(0);

			fetchSuggestionIdeaCriteriaObj.setValue(suggestionIdeadtoObj.getSuggideaid());
			criteriaSuggestionIdeaObj.setFetchSuggestionIdeaCriteriaObj(fetchSuggestionIdeaCriteriaObj);

			suggestionIdeaBOList = suggestionIdeadao.fetchSuggestionIdea(criteriaSuggestionIdeaObj, "1");

			try {
				if (null != suggestionIdeaBOList && suggestionIdeaBOList.size() > 0) {

					suggestionIdeaBOObj = suggestionIdeaBOList.get(0);
					if (suggestionIdeadtoObj.getStatus().equalsIgnoreCase("Delete")) {
						suggestionIdeadao.deleteOnCriteria(suggestionIdeaBOObj, null);
					} else {

						// update the activityBO fetched from DB

						suggestionIdeaBOObj.setCreator(suggestionIdeadtoObj.getCreator());
						suggestionIdeaBOObj.setDetail(suggestionIdeadtoObj.getDetail());
						suggestionIdeaBOObj.setStatus(suggestionIdeadtoObj.getStatus());
						suggestionIdeaBOObj.setSubject(suggestionIdeadtoObj.getSubject());
						suggestionIdeaBOObj.setSuggideadate(dateformatter.parse(
								suggestionIdeadtoObj.getSuggideadate() + " " + suggestionIdeadtoObj.getSuggideatime()));
						// merge this UpdateBO back in DB
						suggestionIdeadao.update(suggestionIdeaBOObj);

						createActionLog(suggestionIdeadtoObj.getSuggideadate(), suggestionIdeadtoObj.getSuggideatime(),
								UnionAppConstants.update, suggestionIdeadtoObj.getCreator(),
								suggestionIdeadtoObj.getDetail(), UnionAppConstants.suggestionidea,
								suggestionIdeadtoObj.getSubject());

					}
				}

				else {
					ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
					throw serviceExceptionObj;
				}
			} catch (ParseException e) {
				ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
				throw serviceExceptionObj;
			}

			populateSuggestionIdeaDTO(suggestionIdeadtoObj, suggestionIdeaBOObj);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("ActivityList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setSuggestionIdeaListObj(suggestionIdeaListObj);

		return responseObj;
	}

	public ResponseObj updateAttachmentDetail(String featureType, String featureId, String fileName,
			String attachmentType, String status) {

		ResponseObj responseObj = new ResponseObj();

		if (featureType.equalsIgnoreCase("newsletter")) {

			updateNLAttachmentDet(featureId, fileName, attachmentType, status);

		} else if (featureType.equalsIgnoreCase("agreement")) {
			updateAgreementAttachmentDet(featureId, fileName, attachmentType, status);

		} else if (featureType.equalsIgnoreCase("payrate")) {
			updatePayrateAttachmentDet(featureId, fileName, attachmentType, status);
		} else if (featureType.equalsIgnoreCase("profile")) {
			updateProfileAttachmentDet(featureId, fileName, attachmentType, status);

		}
		return responseObj;
	}

	public CategoryList addcategory(CategoryList categoryListObj) {
		System.out.println("InRDRegister");

		ArrayList<CategoryDTO> categoryDTOList = (ArrayList<CategoryDTO>) categoryListObj.getCategorydtoLs();
		CategoryList categoryListObjResp = new CategoryList();

		if (categoryDTOList.size() > 0) {
			Iterator<CategoryDTO> categoryDTOListIterator = categoryDTOList.iterator();

			while (categoryDTOListIterator.hasNext()) {

				CategoryDTO categoryDTOObj = categoryDTOListIterator.next();

				CategoryBO categoryBOObj = new CategoryBO();

				populateCreateCatBO(categoryDTOObj, categoryBOObj);
				categorydao.addCategory(categoryBOObj);
				populateCategoryDTO(categoryDTOObj, categoryBOObj);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("CategoryList is NULL");
			throw serviceExceptionObj;
		}

		return categoryListObjResp;
	}

	public CategoryList fetchcategory(Criteria criteriaObj) {
		System.out.println("InRDFetch");

		CategoryList categoryListObjResp = new CategoryList();
		ArrayList<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();

		ArrayList<CategoryBO> categoryBOList;

		categoryBOList = categorydao.fetchCategory(criteriaObj);

		if (null != categoryBOList && categoryBOList.size() > 0) {

			Iterator<CategoryBO> litr = categoryBOList.iterator();

			while (litr.hasNext()) {
				CategoryDTO categoryDTOObj = new CategoryDTO();
				populateCategoryDTO(categoryDTOObj, litr.next());
				categoryDTOList.add(categoryDTOObj);

			}

			categoryListObjResp.setCategorydtoLs(categoryDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		return categoryListObjResp;
	}

	public ResponseObj createSurvey(SurveyList surveyListObj) {
		System.out.println("InRDRegister");

		ResponseObj responseObj = new ResponseObj();
		ArrayList<SurveyDTO> surveyList = (ArrayList<SurveyDTO>) surveyListObj.getSurveydtoLs();

		if (surveyList.size() > 0) {
			Iterator<SurveyDTO> surveyListIterator = surveyList.iterator();

			while (surveyListIterator.hasNext()) {

				SurveyDTO surveydtoObj = surveyListIterator.next();

				SurveyBO surveyBOObj = new SurveyBO();

				populateCreateSurveyBO(surveydtoObj, surveyBOObj);
				surveydao.createSurvey(surveyBOObj);
				populateSurveyDTO(surveydtoObj, surveyBOObj);
				surveydtoObj.setQuestiondtoLs(null);

				createActionLog(null, null, UnionAppConstants.create, surveydtoObj.getCreator(),
						surveydtoObj.getDetail(), UnionAppConstants.survey, surveydtoObj.getSubject());

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("SurveyList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setSurveyListObj(surveyListObj);
		return responseObj;
	}

	public ResponseObj fetchSurveyById(String surveyid, String userid) {
		System.out.println("InRDRegister");

		ResponseObj responseObj = new ResponseObj();
		SurveyList surveyListObj = new SurveyList();
		ArrayList<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();

		if (null != surveyid && surveyid != "") {

			ArrayList<SurveyBO> surveyBOObj = new ArrayList<SurveyBO>();
			SurveyDTO surveydtoObj = new SurveyDTO();

			surveyBOObj = surveydao.fetchSurveyById(surveyid);

			if (surveyBOObj != null && surveyBOObj.size() > 0) {

				surveydtoObj = populateSurveyDTO(surveydtoObj, surveyBOObj.get(0));
				if (surveydtoObj != null && surveydtoObj.getResponseid() != null
						&& surveydtoObj.getResponseid().contains(userid)) {
					surveydtoObj.setUserresponsestatus("true");

				}

				surveyList.add(surveydtoObj);
				surveyListObj.setSurveydtoLs(surveyList);

			}

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("ID is NULL or Empty");
			throw serviceExceptionObj;
		}

		responseObj.setSurveyListObj(surveyListObj);
		return responseObj;
	}

	public ResponseObj fetchSurvey(RequestObj reqparam) {
		System.out.println("InRDFetch");
		ResponseObj responseObj = new ResponseObj();

		SurveyList surveyListObj = new SurveyList();
		ArrayList<SurveyDTO> surveyDTOList = new ArrayList<SurveyDTO>();

		ArrayList<SurveyBO> surveyBOList;

		SurveyBO surveyBOObj;

		String channel = reqparam.getChannel();

		Criteria criteriaObj = reqparam.getCriteria();

		surveyBOList = surveydao.fetchSurvey(criteriaObj, reqparam.getPageno());

		if (null != surveyBOList && surveyBOList.size() > 0) {

			Iterator<SurveyBO> litr = surveyBOList.iterator();

			while (litr.hasNext()) {

				surveyBOObj = litr.next();
				SurveyDTO surveyDTOObj = new SurveyDTO();
				surveyDTOObj = populateSurveyDTO(surveyDTOObj, surveyBOObj);
				if (null != channel && "app".equalsIgnoreCase(channel)) {
					surveyDTOObj.setQuestiondtoLs(null);
				}

				surveyDTOList.add(surveyDTOObj);

			}

			surveyListObj.setSurveydtoLs(surveyDTOList);
		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		responseObj.setSurveyListObj(surveyListObj);
		int totalrecordcount = surveydao.totalRecordCount(criteriaObj);

		int totalPage = getTotalPageCount(totalrecordcount);

		responseObj.setTotalPage(String.valueOf(totalPage));
		return responseObj;
	}

	public ResponseObj updateSurvey(RequestObj reqparam) {
		System.out.println("InRDUpdate");

		ResponseObj responseObj = new ResponseObj();

		String channel = reqparam.getChannel();

		Gson gson = new Gson();

		SurveyList surveyListObj = reqparam.getSurveyListObj();

		ArrayList<SurveyDTO> surveyList = (ArrayList<SurveyDTO>) surveyListObj.getSurveydtoLs();

		ArrayList<SurveyDTO> surveyListResp = new ArrayList<SurveyDTO>();

		SurveyList surveyListObjResp = new SurveyList();

		ArrayList<SurveyBO> surveyBOList;

		SurveyBO surveyBOObj = null;

		Criteria criteriaSurveyObj = new Criteria();
		criteriaSurveyObj.setCriteria("TRUE");

		FetchSurveyCriteria fetchSurveyCriteriaObj = new FetchSurveyCriteria();

		fetchSurveyCriteriaObj.setName("surveyid");

		if (surveyList.size() > 0) {

			SurveyDTO surveydtoObj = surveyList.get(0);

			fetchSurveyCriteriaObj.setValue(surveydtoObj.getSurveyid());
			criteriaSurveyObj.setFetchSurveyCriteriaObj(fetchSurveyCriteriaObj);

			surveyBOList = surveydao.fetchSurvey(criteriaSurveyObj, "1");

			if (null != surveyBOList && surveyBOList.size() > 0) {

				surveyBOObj = surveyBOList.get(0);
				if (surveydtoObj.getStatus() != null && surveydtoObj.getStatus().equalsIgnoreCase("Delete")) {
					surveydao.deleteOnCriteria(surveyBOObj, null);
				} else if (null != channel && "app".equalsIgnoreCase(channel)) {

					String surveyBOJson = surveyBOObj.getSurveyjson();
					// String json = gson.toJson(surveyDTOObj1);
					// System.out.println(json);
					SurveyDTO surveyBO_DTOObj = new SurveyDTO();
					surveyBO_DTOObj = gson.fromJson(surveyBOJson, SurveyDTO.class);

					// Updating the SurveyResponsecount and the Response
					// userIds.
					if (surveydtoObj.getUserresponsestatus() != null
							&& surveydtoObj.getUserresponsestatus().equalsIgnoreCase("true")) {
						surveyBO_DTOObj.setUserresponsestatus("true");
						String oldresponsecount = surveyBO_DTOObj.getResponsecount();
						if (oldresponsecount == null || oldresponsecount.equalsIgnoreCase("")) {

							oldresponsecount = "0";
						}
						String oldresponseid = surveyBO_DTOObj.getResponseid();
						surveyBO_DTOObj.setResponsecount(String.valueOf(Integer.parseInt(oldresponsecount) + 1));
						if (oldresponseid == null || oldresponseid.equals("")) {

							surveyBO_DTOObj.setResponseid(surveydtoObj.getResponseid());

						} else {
							surveyBO_DTOObj.setResponseid(oldresponseid + "~~~" + surveydtoObj.getResponseid());
						}

					}

					// update the total ActiveUserCount:

					Criteria criteriaObj = new Criteria();
					criteriaObj.setCriteria("TRUE");

					FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

					fetchUserCriteriaObj.setName("status");
					fetchUserCriteriaObj.setValue("A");
					criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

					ArrayList<UserBO> userBOList = userdao.fetchAllUser(criteriaObj);

					if (null != userBOList) {
						int totalActUserCount = userBOList.size();
						surveyBO_DTOObj.setTotalusercount(String.valueOf(totalActUserCount));
					}

					// Update the Questions Option Count
					ArrayList<QuestionDTO> questiondtoLs = (ArrayList<QuestionDTO>) surveydtoObj.getQuestiondtoLs();
					ArrayList<QuestionDTO> questiondtoLsBODTO = (ArrayList<QuestionDTO>) surveyBO_DTOObj
							.getQuestiondtoLs();

					Iterator<QuestionDTO> litrquesDTO = questiondtoLs.iterator();
					Iterator<QuestionDTO> litrquesBODTO = questiondtoLsBODTO.iterator();

					while (litrquesDTO.hasNext() && litrquesBODTO.hasNext()) {

						QuestionDTO surveyQuDTOObj = litrquesDTO.next();
						QuestionDTO surveyQuBODTOObj = litrquesBODTO.next();

						ArrayList<OptionDTO> optiondtoLs = (ArrayList<OptionDTO>) surveyQuDTOObj.getOptiondtoLs();
						ArrayList<OptionDTO> optiondtoLsBODTO = (ArrayList<OptionDTO>) surveyQuBODTOObj
								.getOptiondtoLs();

						Iterator<OptionDTO> litrOptionDTO = optiondtoLs.iterator();
						Iterator<OptionDTO> litrOptionBODTO = optiondtoLsBODTO.iterator();
						while (litrOptionDTO.hasNext() && litrOptionBODTO.hasNext()) {

							OptionDTO optionDTOObj = litrOptionDTO.next();
							OptionDTO optionBODTOObj = litrOptionBODTO.next();

							if (optionDTOObj.getResponsecount() != null
									&& optionDTOObj.getResponsecount().equalsIgnoreCase("true")) {
								String oldresponsecount = optionBODTOObj.getResponsecount();
								if (oldresponsecount == null || oldresponsecount.equalsIgnoreCase("")) {

									oldresponsecount = "0";
								}
								optionBODTOObj.setResponsecount(String.valueOf(Integer.parseInt(oldresponsecount) + 1));
							}

						}

					}
					String json = gson.toJson(surveyBO_DTOObj);
					surveyBOObj.setSurveyjson(json);

				} else {
					populateCreateSurveyBO(surveydtoObj, surveyBOObj);
				}

				// merge this UpdateBO back in DB
				surveydao.update(surveyBOObj);

				createActionLog(null, null, UnionAppConstants.update, surveydtoObj.getCreator(),
						surveydtoObj.getDetail(), UnionAppConstants.survey, surveydtoObj.getSubject());
			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}

			surveydtoObj = populateSurveyDTO(surveydtoObj, surveyBOObj);
			surveyListResp.add(surveydtoObj);
			surveyListObjResp.setSurveydtoLs(surveyListResp);

		}

		else {
			ServiceException serviceExceptionObj = new ServiceException("ActivityList is NULL");
			throw serviceExceptionObj;
		}

		responseObj.setSurveyListObj(surveyListObjResp);

		return responseObj;
	}

	public BigDecimal fetchUsedSpace(){
		BigDecimal dbSize=actionlogdao.fetchDbSize(UnionAppConstants.dbName);
		dbSize=(null==dbSize)?new BigDecimal(0):dbSize;
		String path = UnionAppConstants.serverAbsPath;
		FileSystem fs = FileSystems.getDefault();
		Path path1 = fs.getPath(path);			
		long usedFileSystemSize=RepositoryDelegator.size(path1)/1024/1024;
		System.out.println("used file ystem length>>>"+usedFileSystemSize);
		return dbSize.add(new BigDecimal(usedFileSystemSize));
	}
	
	public static long size(Path path) {

	    final AtomicLong size = new AtomicLong(0);

	    try {
	        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
	            @Override
	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

	                size.addAndGet(attrs.size());
	                return FileVisitResult.CONTINUE;
	            }

	            @Override
	            public FileVisitResult visitFileFailed(Path file, IOException exc) {

	                System.out.println("skipped: " + file + " (" + exc + ")");
	                // Skip folders that can't be traversed
	                return FileVisitResult.CONTINUE;
	            }

	            @Override
	            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

	                if (exc != null)
	                    System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
	                // Ignore errors traversing a folder
	                return FileVisitResult.CONTINUE;
	            }
	        });
	    } catch (IOException e) {
	        throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
	    }

	    return size.get();
	}
	
	public ResponseObj fetchActionLog(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		ActionLogList actionLogListObj = new ActionLogList();
		ArrayList<ActionLogDTO> actionLogDTOList = new ArrayList<ActionLogDTO>();

		ArrayList<ActionLogBO> actionLogBOList;

		actionLogBOList = actionlogdao.fetchActionLog();		

		if (null != actionLogBOList && actionLogBOList.size() > 0) {

			Iterator<ActionLogBO> litr = actionLogBOList.iterator();

			while (litr.hasNext()) {
				ActionLogDTO actionLogDTOObj = new ActionLogDTO();
				populateActionLogDTO(actionLogDTOObj, litr.next());
				actionLogDTOList.add(actionLogDTOObj);

			}

			actionLogListObj.setActionlogdtoLs(actionLogDTOList);

		} else {
			ServiceException serviceExceptionObj = new ServiceException("No Matching Object Found");
			throw serviceExceptionObj;
		}

		responseObj.setActionLogListObj(actionLogListObj);

		return responseObj;
	}

	public void createActionLog(String date, String time, String action, String creator, String detail, String module,
			String subject) {

		ActionLogDTO actionlogdtoObj = new ActionLogDTO();
		actionlogdtoObj.setActdate(date);
		actionlogdtoObj.setAction(action);
		actionlogdtoObj.setActtime(time);
		actionlogdtoObj.setCreator(creator);
		actionlogdtoObj.setDetail(detail);
		actionlogdtoObj.setDetail(detail);
		actionlogdtoObj.setModule(module);
		actionlogdtoObj.setSubject(subject);

		ActionLogBO actionLogBOObj = new ActionLogBO();

		populateActionLogBO(actionlogdtoObj, actionLogBOObj);
		actionlogdao.addActionLog(actionLogBOObj);

	}

	private void updateNLAttachmentDet(String featureId, String fileName, String attachmentType, String status) {
		ArrayList<NewsLetterBO> newsLetterBOList;

		NewsLetterBO newsLetterBOObj = null;
		Criteria criteriaNewsLetterObj = new Criteria();
		criteriaNewsLetterObj.setCriteria("TRUE");

		FetchNewsLetterCriteria fetchNewsLetterCriteriaObj = new FetchNewsLetterCriteria();

		fetchNewsLetterCriteriaObj.setName("nlid");

		fetchNewsLetterCriteriaObj.setValue(featureId);
		criteriaNewsLetterObj.setFetchNewsLetterCriteriaObj(fetchNewsLetterCriteriaObj);

		newsLetterBOList = newsletterdao.fetchNewsLetter(criteriaNewsLetterObj, "1");

		try {
			if (null != newsLetterBOList && newsLetterBOList.size() > 0) {

				newsLetterBOObj = newsLetterBOList.get(0);

				// update the NewsLetterBO fetched from DB

				if (status.equalsIgnoreCase("delete")) {
					deleteNewsLetterAttachment(fileName, attachmentType, newsLetterBOObj);

				}

				else {

					addNewsLetterAttachment(fileName, attachmentType, newsLetterBOObj);

				}

				// merge this UpdateBO back in DB
				newsletterdao.update(newsLetterBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}
	}

	private void addNewsLetterAttachment(String fileName, String attachmentType, NewsLetterBO newsLetterBOObj) {
		newsLetterBOObj.setAttachmentstatus("true");

		if (attachmentType.equalsIgnoreCase("document")) {

			if (newsLetterBOObj.getDocattachment() != null && newsLetterBOObj.getDocattachment() != "") {
				String docattachment = newsLetterBOObj.getDocattachment();
				newsLetterBOObj.setDocattachment(docattachment + "," + fileName);
			} else {

				newsLetterBOObj.setDocattachment(fileName);
			}

		}

		else if (attachmentType.equalsIgnoreCase("image")) {

			if (newsLetterBOObj.getImgattachment() != null && newsLetterBOObj.getImgattachment() != "") {
				String imgattachment = newsLetterBOObj.getImgattachment();
				newsLetterBOObj.setImgattachment(imgattachment + "," + fileName);
			} else {

				newsLetterBOObj.setImgattachment(fileName);
			}

		}
	}

	private void deleteNewsLetterAttachment(String fileName, String attachmentType, NewsLetterBO newsLetterBOObj) {
		String imageAttachmentNew = "";
		String docAttachmentNew = "";

		if (null != newsLetterBOObj.getAttachmentstatus()
				&& !"false".equalsIgnoreCase(newsLetterBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(newsLetterBOObj.getAttachmentstatus())) {
			if (attachmentType.equalsIgnoreCase("image")) {

				if (null != newsLetterBOObj.getImgattachment()) {

					String[] imgAttachments = newsLetterBOObj.getImgattachment().split(",");
					for (String img : imgAttachments) {
						if (!img.contains(fileName)) {
							imageAttachmentNew = imageAttachmentNew + "," + img;
						}

					}
					newsLetterBOObj.setImgattachment(imageAttachmentNew);

				}

			} else if (attachmentType.equalsIgnoreCase("document")) {

				if (null != newsLetterBOObj.getDocattachment()) {

					String[] docAttachments = newsLetterBOObj.getDocattachment().split(",");
					for (String doc : docAttachments) {
						if (!doc.contains(fileName)) {
							docAttachmentNew = docAttachmentNew + "," + doc;
						}

					}
					newsLetterBOObj.setDocattachment(docAttachmentNew + "," + fileName);
				}

			}

		}
		if (docAttachmentNew.equals("") && imageAttachmentNew.equals("")) {
			newsLetterBOObj.setAttachmentstatus("false");

		}
	}

	private void updateAgreementAttachmentDet(String featureId, String fileName, String attachmentType, String status) {
		ArrayList<AgreementBO> AgreementBOList;

		AgreementBO agreementBOObj = null;
		Criteria criteriaAgreementObj = new Criteria();
		criteriaAgreementObj.setCriteria("TRUE");

		FetchAgreementCriteria fetchAgreementCriteriaObj = new FetchAgreementCriteria();

		fetchAgreementCriteriaObj.setName("armid");

		fetchAgreementCriteriaObj.setValue(featureId);
		criteriaAgreementObj.setFetchAgreementCriteriaObj(fetchAgreementCriteriaObj);

		AgreementBOList = agreementdao.fetchAgreement(criteriaAgreementObj, "1");

		try {
			if (null != AgreementBOList && AgreementBOList.size() > 0) {

				agreementBOObj = AgreementBOList.get(0);

				// update the AgreementBO fetched from DB

				if (status.equalsIgnoreCase("delete")) {
					deleteAgreementAttachment(fileName, attachmentType, agreementBOObj);

				}

				else {

					addAgreementAttachment(fileName, attachmentType, agreementBOObj);

				}
				// merge this UpdateBO back in DB
				agreementdao.update(agreementBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}
	}

	private void addAgreementAttachment(String fileName, String attachmentType, AgreementBO agreementBOObj) {
		agreementBOObj.setAttachmentstatus("true");

		if (attachmentType.equalsIgnoreCase("document")) {

			if (agreementBOObj.getDocattachment() != null && agreementBOObj.getDocattachment() != "") {
				String docattachment = agreementBOObj.getDocattachment();
				agreementBOObj.setDocattachment(docattachment + "," + fileName);
			} else {

				agreementBOObj.setDocattachment(fileName);
			}

		}

		else if (attachmentType.equalsIgnoreCase("image")) {

			if (agreementBOObj.getImgattachment() != null && agreementBOObj.getImgattachment() != "") {
				String imgattachment = agreementBOObj.getImgattachment();
				agreementBOObj.setImgattachment(imgattachment + "," + fileName);
			} else {

				agreementBOObj.setImgattachment(fileName);
			}

		}
	}

	private void deleteAgreementAttachment(String fileName, String attachmentType, AgreementBO agreementBOObj) {
		String imageAttachmentNew = "";
		String docAttachmentNew = "";

		if (null != agreementBOObj.getAttachmentstatus()
				&& !"false".equalsIgnoreCase(agreementBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(agreementBOObj.getAttachmentstatus())) {
			if (attachmentType.equalsIgnoreCase("document")) {

				if (null != agreementBOObj.getImgattachment()) {

					String[] imgAttachments = agreementBOObj.getImgattachment().split(",");
					for (String img : imgAttachments) {
						if (!img.contains(fileName)) {
							imageAttachmentNew = imageAttachmentNew + "," + img;
						}

					}
					agreementBOObj.setImgattachment(imageAttachmentNew);

				}

			} else if (attachmentType.equalsIgnoreCase("image")) {

				if (null != agreementBOObj.getDocattachment()) {

					String[] docAttachments = agreementBOObj.getDocattachment().split(",");
					for (String doc : docAttachments) {
						if (!doc.contains(fileName)) {
							docAttachmentNew = docAttachmentNew + "," + doc;
						}

					}
					agreementBOObj.setDocattachment(docAttachmentNew + "," + fileName);
				}

			}

		}
		if (docAttachmentNew.equals("") && imageAttachmentNew.equals("")) {
			agreementBOObj.setAttachmentstatus("false");

		}
	}

	private void updatePayrateAttachmentDet(String featureId, String fileName, String attachmentType, String status) {
		ArrayList<PayrateBO> PayrateBOList;

		PayrateBO payrateBOObj = null;
		Criteria criteriaPayrateObj = new Criteria();
		criteriaPayrateObj.setCriteria("TRUE");

		FetchPayrateCriteria fetchPayrateCriteriaObj = new FetchPayrateCriteria();

		fetchPayrateCriteriaObj.setName("payid");

		fetchPayrateCriteriaObj.setValue(featureId);
		criteriaPayrateObj.setFetchPayrateCriteriaObj(fetchPayrateCriteriaObj);

		PayrateBOList = payratedao.fetchPayrate(criteriaPayrateObj, "1");

		try {
			if (null != PayrateBOList && PayrateBOList.size() > 0) {

				payrateBOObj = PayrateBOList.get(0);

				// update the PayrateBO fetched from DB

				if (status.equalsIgnoreCase("delete")) {
					deletePayrateAttachment(fileName, attachmentType, payrateBOObj);

				}

				else {

					addPayrateAttachment(fileName, attachmentType, payrateBOObj);

				}
				// merge this UpdateBO back in DB
				payratedao.update(payrateBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}
	}

	private void addPayrateAttachment(String fileName, String attachmentType, PayrateBO payrateBOObj) {
		payrateBOObj.setAttachmentstatus("true");

		if (attachmentType.equalsIgnoreCase("document")) {

			if (payrateBOObj.getDocattachment() != null && payrateBOObj.getDocattachment() != "") {
				String docattachment = payrateBOObj.getDocattachment();
				payrateBOObj.setDocattachment(docattachment + "," + fileName);
			} else {

				payrateBOObj.setDocattachment(fileName);
			}

		}

		else if (attachmentType.equalsIgnoreCase("image")) {

			if (payrateBOObj.getImgattachment() != null && payrateBOObj.getImgattachment() != "") {
				String imgattachment = payrateBOObj.getImgattachment();
				payrateBOObj.setImgattachment(imgattachment + "," + fileName);
			} else {

				payrateBOObj.setImgattachment(fileName);
			}

		}
	}

	private void deletePayrateAttachment(String fileName, String attachmentType, PayrateBO payrateBOObj) {
		String imageAttachmentNew = "";
		String docAttachmentNew = "";

		if (null != payrateBOObj.getAttachmentstatus() && !"false".equalsIgnoreCase(payrateBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(payrateBOObj.getAttachmentstatus())) {
			if (attachmentType.equalsIgnoreCase("document")) {

				if (null != payrateBOObj.getImgattachment()) {

					String[] imgAttachments = payrateBOObj.getImgattachment().split(",");
					for (String img : imgAttachments) {
						if (!img.contains(fileName)) {
							imageAttachmentNew = imageAttachmentNew + "," + img;
						}

					}
					payrateBOObj.setImgattachment(imageAttachmentNew);

				}

			} else if (attachmentType.equalsIgnoreCase("image")) {

				if (null != payrateBOObj.getDocattachment()) {

					String[] docAttachments = payrateBOObj.getDocattachment().split(",");
					for (String doc : docAttachments) {
						if (!doc.contains(fileName)) {
							docAttachmentNew = docAttachmentNew + "," + doc;
						}

					}
					payrateBOObj.setDocattachment(docAttachmentNew + "," + fileName);
				}

			}

		}
		if (docAttachmentNew.equals("") && imageAttachmentNew.equals("")) {
			payrateBOObj.setAttachmentstatus("false");

		}
	}

	private void updateProfileAttachmentDet(String featureId, String fileName, String attachmentType, String status) {
		ArrayList<UserBO> UserBOList;
		String pageno = "1";

		UserBO UserBOObj = null;
		Criteria criteriaObj = new Criteria();
		criteriaObj.setCriteria("TRUE");

		FetchUserCriteria fetchUserCriteriaObj = new FetchUserCriteria();

		fetchUserCriteriaObj.setName("emailid");
		fetchUserCriteriaObj.setValue(featureId);
		criteriaObj.setFetchUserCriteriaObj(fetchUserCriteriaObj);

		UserBOList = userdao.fetchUser(criteriaObj, pageno);

		try {
			if (null != UserBOList && UserBOList.size() > 0) {

				UserBOObj = UserBOList.get(0);

				/*
				 * if (UserBOObj.getImgattachment() != null &&
				 * UserBOObj.getImgattachment() != "") { String imgattachment =
				 * UserBOObj.getImgattachment();
				 * UserBOObj.setImgattachment(imgattachment + "," + fileName); }
				 * else {
				 */

				UserBOObj.setImgattachment(fileName);
				// }

				// merge this UpdateBO back in DB
				userdao.update(UserBOObj);

			}

			else {
				ServiceException serviceExceptionObj = new ServiceException("No Matching Obj Found");
				throw serviceExceptionObj;
			}
		} catch (Exception e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}
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
		userObj.setCategory(userBOObj.getCategory());
		userObj.setTitle(userBOObj.getTitle());
		userObj.setImageurl(splitUrl(userBOObj.getImgattachment()));

	}

	private void populateCreateMeetingBO(MeetingDTO meetingdtoObj, MeetingBO meetingBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

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
			meetingBOObj
					.setMeetdate(dateformatter.parse(meetingdtoObj.getMeetdate() + " " + meetingdtoObj.getMeettime()));
			// meetingBOObj.setMeetingid(meetingdtoObj.getMeetingid());
			// meetingBOObj.setMeettime(timeformatter.parse(meetingdtoObj.getMeettime()));
			meetingBOObj.setStatus(meetingdtoObj.getStatus());
			meetingBOObj.setSubject(meetingdtoObj.getSubject());
			meetingBOObj.setVenue(meetingdtoObj.getVenue());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateMeetingDTO(MeetingDTO meetingdtoObj, MeetingBO meetingBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		meetingdtoObj.setAcceptcount(String.valueOf(meetingBOObj.getAcceptcount()));
		meetingdtoObj.setDeclinecount(String.valueOf(meetingBOObj.getDeclinecount()));
		meetingdtoObj.setCreator(meetingBOObj.getCreator());
		meetingdtoObj.setDetail(meetingBOObj.getDetail());
		meetingdtoObj.setMeetdate(dateformatter.format(meetingBOObj.getMeetdate()));
		meetingdtoObj.setMeetingid(meetingBOObj.getMeetingid().toString());
		meetingdtoObj.setMeettime(timeformatter.format(meetingBOObj.getMeetdate()));
		meetingdtoObj.setStatus(meetingBOObj.getStatus());
		meetingdtoObj.setSubject(meetingBOObj.getSubject());
		meetingdtoObj.setVenue(meetingBOObj.getVenue());

	}

	private void populateCreateActivityBO(ActivityDTO activitydtoObj, ActivityBO activityBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

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
			activityBOObj
					.setActdate(dateformatter.parse(activitydtoObj.getActdate() + " " + activitydtoObj.getActtime()));
			// activityBOObj.setactivityid(activitydtoObj.getactivityid());
			// activityBOObj.setActtime(timeformatter.parse(activitydtoObj.getActtime()));
			activityBOObj.setStatus(activitydtoObj.getStatus());
			activityBOObj.setSubject(activitydtoObj.getSubject());
			activityBOObj.setVenue(activitydtoObj.getVenue());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateActivityDTO(ActivityDTO activitydtoObj, ActivityBO activityBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		activitydtoObj.setAcceptcount(String.valueOf(activityBOObj.getAcceptcount()));
		activitydtoObj.setDeclinecount(String.valueOf(activityBOObj.getDeclinecount()));
		activitydtoObj.setCreator(activityBOObj.getCreator());
		activitydtoObj.setDetail(activityBOObj.getDetail());
		activitydtoObj.setActdate(dateformatter.format(activityBOObj.getActdate()));
		activitydtoObj.setActtime(timeformatter.format(activityBOObj.getActdate()));
		activitydtoObj.setActivityid(activityBOObj.getActivityid().toString());
		activitydtoObj.setStatus(activityBOObj.getStatus());
		activitydtoObj.setSubject(activityBOObj.getSubject());
		activitydtoObj.setVenue(activityBOObj.getVenue());

	}

	private void populateCreateNewsLetterBO(NewsLetterDTO newsLetterdtoObj, NewsLetterBO newsLetterBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			newsLetterBOObj.setCreator(newsLetterdtoObj.getCreator());
			newsLetterBOObj.setDetail(newsLetterdtoObj.getDetail());
			newsLetterBOObj
					.setNldate(dateformatter.parse(newsLetterdtoObj.getNldate() + " " + newsLetterdtoObj.getNltime()));
			newsLetterBOObj.setStatus(newsLetterdtoObj.getStatus());
			newsLetterBOObj.setSubject(newsLetterdtoObj.getSubject());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateNewsLetterDTO(NewsLetterDTO newsLetterdtoObj, NewsLetterBO newsLetterBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		String status = "";

		newsLetterdtoObj.setCreator(newsLetterBOObj.getCreator());
		newsLetterdtoObj.setDetail(newsLetterBOObj.getDetail());
		newsLetterdtoObj.setNldate(dateformatter.format(newsLetterBOObj.getNldate()));
		newsLetterdtoObj.setNltime(timeformatter.format(newsLetterBOObj.getNldate()));
		newsLetterdtoObj.setNlid(newsLetterBOObj.getNlid().toString());
		if (null != newsLetterBOObj.getStatus() && !"".equalsIgnoreCase(newsLetterBOObj.getStatus()))
			status = newsLetterBOObj.getStatus().toLowerCase();
		newsLetterdtoObj.setStatus(status);
		newsLetterdtoObj.setSubject(newsLetterBOObj.getSubject());
		populateAttachments(newsLetterdtoObj, newsLetterBOObj);
	}

	private void populateCreateAmrBO(AmrDTO AmrdtoObj, AmrBO AmrBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			AmrBOObj.setCreator(AmrdtoObj.getCreator());
			AmrBOObj.setDetail(AmrdtoObj.getDetail());
			AmrBOObj.setAmrdate(dateformatter.parse(AmrdtoObj.getAmrdate() + " " + AmrdtoObj.getAmrtime()));
			AmrBOObj.setStatus(AmrdtoObj.getStatus());
			AmrBOObj.setSubject(AmrdtoObj.getSubject());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateAmrDTO(AmrDTO AmrdtoObj, AmrBO AmrBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		String status = "";

		AmrdtoObj.setCreator(AmrBOObj.getCreator());
		AmrdtoObj.setDetail(AmrBOObj.getDetail());
		AmrdtoObj.setAmrdate(dateformatter.format(AmrBOObj.getAmrdate()));
		AmrdtoObj.setAmrtime(timeformatter.format(AmrBOObj.getAmrdate()));
		AmrdtoObj.setAmrid(AmrBOObj.getAmrid().toString());
		if (null != AmrBOObj.getStatus() && !"".equalsIgnoreCase(AmrBOObj.getStatus()))
			status = AmrBOObj.getStatus().toLowerCase();
		AmrdtoObj.setStatus(status);
		AmrdtoObj.setSubject(AmrBOObj.getSubject());

	}

	private void populateCreatePayrateBO(PayrateDTO PayratedtoObj, PayrateBO PayrateBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			PayrateBOObj.setCreator(PayratedtoObj.getCreator());
			PayrateBOObj.setDetail(PayratedtoObj.getDetail());
			PayrateBOObj.setPaydate(dateformatter.parse(PayratedtoObj.getPaydate() + " " + PayratedtoObj.getPaytime()));
			PayrateBOObj.setStatus(PayratedtoObj.getStatus());
			PayrateBOObj.setSubject(PayratedtoObj.getSubject());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populatePayrateDTO(PayrateDTO PayratedtoObj, PayrateBO PayrateBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		String status = "";

		PayratedtoObj.setCreator(PayrateBOObj.getCreator());
		PayratedtoObj.setDetail(PayrateBOObj.getDetail());
		PayratedtoObj.setPaydate(dateformatter.format(PayrateBOObj.getPaydate()));
		PayratedtoObj.setPaytime(timeformatter.format(PayrateBOObj.getPaydate()));
		PayratedtoObj.setPayid(PayrateBOObj.getPayid().toString());
		if (null != PayrateBOObj.getStatus() && !"".equalsIgnoreCase(PayrateBOObj.getStatus()))
			status = PayrateBOObj.getStatus().toLowerCase();
		PayratedtoObj.setStatus(status);
		PayratedtoObj.setSubject(PayrateBOObj.getSubject());
		populateAttachments(PayratedtoObj, PayrateBOObj);
	}

	private void populateCreateAgreementBO(AgreementDTO agreementdtoObj, AgreementBO agreementBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			agreementBOObj.setCreator(agreementdtoObj.getCreator());
			agreementBOObj.setDetail(agreementdtoObj.getDetail());
			agreementBOObj
					.setArmdate(dateformatter.parse(agreementdtoObj.getArmdate() + " " + agreementdtoObj.getArmtime()));
			agreementBOObj.setStatus(agreementdtoObj.getStatus());
			agreementBOObj.setSubject(agreementdtoObj.getSubject());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateAgreementDTO(AgreementDTO agreementdtoObj, AgreementBO agreementBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		String status = "";

		agreementdtoObj.setCreator(agreementBOObj.getCreator());
		agreementdtoObj.setDetail(agreementBOObj.getDetail());
		agreementdtoObj.setArmdate(dateformatter.format(agreementBOObj.getArmdate()));
		agreementdtoObj.setArmtime(timeformatter.format(agreementBOObj.getArmdate()));
		agreementdtoObj.setArmid(agreementBOObj.getArmid().toString());
		if (null != agreementBOObj.getStatus() && !"".equalsIgnoreCase(agreementBOObj.getStatus()))
			status = agreementBOObj.getStatus().toLowerCase();
		agreementdtoObj.setStatus(status);
		agreementdtoObj.setSubject(agreementBOObj.getSubject());
		populateAttachments(agreementdtoObj, agreementBOObj);

	}

	private String[] splitTitle(String url) {
		String[] sarr = { "", "" };
		String[] sarrDummy = { url, "Attachment" };
		sarr = url.split("~~~");

		if (sarr.length < 2)
			return sarrDummy;
		else {
			String urlV = sarr[0];
			urlV = urlV.replace(UnionAppConstants.serverAbsPath, UnionAppConstants.serverUriPath);
			sarr[0] = urlV;
		}
		return sarr;
	}

	private String splitUrl(String url) {
		if (null == url)
			url = "";
		String[] sarr = { "", "" };
		String[] sarrDummy = { url, "Attachment" };
		sarr = url.split("~~~");

		if (sarr.length < 2)
			return sarrDummy[0];
		else {
			String urlV = sarr[0];
			urlV = urlV.replaceAll(UnionAppConstants.serverAbsPath, UnionAppConstants.serverUriPath);
			sarr[0] = urlV;
		}
		return sarr[0];
	}

	private void populateAttachments(AgreementDTO agreementdtoObj, AgreementBO agreementBOObj) {
		AttachmentList al = new AttachmentList();
		int counter = 0;
		if (null != agreementBOObj.getAttachmentstatus()
				&& !"false".equalsIgnoreCase(agreementBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(agreementBOObj.getAttachmentstatus())) {
			if (null != agreementBOObj.getImgattachment()) {
				String[] imgAttachments = agreementBOObj.getImgattachment().split(",");
				for (String img : imgAttachments) {
					String s[] = splitTitle(img);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "image"));
					counter++;
				}

			}

			if (null != agreementBOObj.getDocattachment()) {
				String[] docAttachments = agreementBOObj.getDocattachment().split(",");
				for (String doc : docAttachments) {
					String s[] = splitTitle(doc);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "doc"));
					counter++;
				}
			}

		}
		al.setListSize(counter);
		agreementdtoObj.setAttachmentlist(al);
	}

	private void populateAttachments(NewsLetterDTO NewsLetterdtoObj, NewsLetterBO NewsLetterBOObj) {
		AttachmentList al = new AttachmentList();
		int counter = 0;
		if (null != NewsLetterBOObj.getAttachmentstatus()
				&& !"false".equalsIgnoreCase(NewsLetterBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(NewsLetterBOObj.getAttachmentstatus())) {
			if (null != NewsLetterBOObj.getImgattachment()) {

				String[] imgAttachments = NewsLetterBOObj.getImgattachment().split(",");
				for (String img : imgAttachments) {
					String s[] = splitTitle(img);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "image"));
					counter++;
				}

			}

			if (null != NewsLetterBOObj.getDocattachment()) {

				String[] docAttachments = NewsLetterBOObj.getDocattachment().split(",");
				for (String doc : docAttachments) {
					String s[] = splitTitle(doc);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "doc"));
					counter++;
				}

			}

		}
		al.setListSize(counter);
		NewsLetterdtoObj.setAttachmentlist(al);
	}

	private void populateAttachments(PayrateDTO PayratedtoObj, PayrateBO PayrateBOObj) {
		AttachmentList al = new AttachmentList();
		int counter = 0;
		if (null != PayrateBOObj.getAttachmentstatus() && !"false".equalsIgnoreCase(PayrateBOObj.getAttachmentstatus())
				&& !"".equalsIgnoreCase(PayrateBOObj.getAttachmentstatus())) {

			if (null != PayrateBOObj.getImgattachment()) {
				String[] imgAttachments = PayrateBOObj.getImgattachment().split(",");
				for (String img : imgAttachments) {
					String s[] = splitTitle(img);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "image"));
					counter++;
				}
			}
			if (null != PayrateBOObj.getDocattachment()) {

				String[] docAttachments = PayrateBOObj.getDocattachment().split(",");
				for (String doc : docAttachments) {
					String s[] = splitTitle(doc);
					al.getAttachmentdtoLs().add(new AttachmentDTO(s[1], s[0], "doc"));
					counter++;
				}

			}

		}
		al.setListSize(counter);
		PayratedtoObj.setAttachmentlist(al);
	}

	private void populateCreateSummaryBO(SummaryDTO SummarydtoObj, SummaryBO SummaryBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {
			SummaryBOObj.setCreator(SummarydtoObj.getCreator());
			SummaryBOObj.setDetail(SummarydtoObj.getDetail());
			SummaryBOObj.setSumdate(dateformatter.parse(SummarydtoObj.getSumdate() + " " + SummarydtoObj.getSumtime()));
			SummaryBOObj.setStatus(SummarydtoObj.getStatus());
			SummaryBOObj.setSubject(SummarydtoObj.getSubject());
		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateSummaryDTO(SummaryDTO SummarydtoObj, SummaryBO SummaryBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
		String status = "";

		SummarydtoObj.setCreator(SummaryBOObj.getCreator());
		SummarydtoObj.setDetail(SummaryBOObj.getDetail());
		SummarydtoObj.setSumdate(dateformatter.format(SummaryBOObj.getSumdate()));
		SummarydtoObj.setSumtime(timeformatter.format(SummaryBOObj.getSumdate()));
		SummarydtoObj.setSumid(SummaryBOObj.getSumid().toString());
		if (null != SummaryBOObj.getStatus() && !"".equalsIgnoreCase(SummaryBOObj.getStatus()))
			status = SummaryBOObj.getStatus().toLowerCase();
		SummarydtoObj.setStatus(status);
		SummarydtoObj.setSubject(SummaryBOObj.getSubject());

	}

	private void populateCreateSuggestionIdeaBO(SuggestionIdeaDTO suggestionIdeadtoObj,
			SuggestionIdeaBO suggestionIdeaBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		try {

			suggestionIdeaBOObj.setCreator(suggestionIdeadtoObj.getCreator());
			suggestionIdeaBOObj.setDetail(suggestionIdeadtoObj.getDetail());
			suggestionIdeaBOObj.setSuggideadate(dateformatter
					.parse(suggestionIdeadtoObj.getSuggideadate() + " " + suggestionIdeadtoObj.getSuggideatime()));

			suggestionIdeaBOObj.setStatus(suggestionIdeadtoObj.getStatus());
			suggestionIdeaBOObj.setSubject(suggestionIdeadtoObj.getSubject());

		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateSuggestionIdeaDTO(SuggestionIdeaDTO suggestionIdeadtoObj,
			SuggestionIdeaBO suggestionIdeaBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		suggestionIdeadtoObj.setCreator(suggestionIdeaBOObj.getCreator());
		suggestionIdeadtoObj.setDetail(suggestionIdeaBOObj.getDetail());
		suggestionIdeadtoObj.setSuggideadate(dateformatter.format(suggestionIdeaBOObj.getSuggideadate()));
		suggestionIdeadtoObj.setSuggideatime(timeformatter.format(suggestionIdeaBOObj.getSuggideadate()));
		suggestionIdeadtoObj.setSuggideaid(suggestionIdeaBOObj.getSuggideaid().toString());
		suggestionIdeadtoObj.setStatus(suggestionIdeaBOObj.getStatus());
		suggestionIdeadtoObj.setSubject(suggestionIdeaBOObj.getSubject());

	}

	private void populateCreateCatBO(CategoryDTO catDTOObj, CategoryBO catBOObj) {

		catBOObj.setCatname(catDTOObj.getCatname());
	}

	private void populateCategoryDTO(CategoryDTO catDTOObj, CategoryBO catBOObj) {

		catDTOObj.setCatid(catBOObj.getCatid().toString());
		catDTOObj.setCatname(catBOObj.getCatname());

	}

	private void populateCreateSurveyBO(SurveyDTO surveydtoObj, SurveyBO surveyBOObj) {

		Gson gson = new Gson();
		if (null == surveydtoObj.getResponsecount() || surveydtoObj.getResponsecount().equalsIgnoreCase("") ) {
			surveydtoObj.setResponsecount("0");
		}
		if (null == surveydtoObj.getTotalusercount() || surveydtoObj.getTotalusercount().equalsIgnoreCase("")) {
			surveydtoObj.setTotalusercount("0");
		}
		if (null == surveydtoObj.getUserresponsestatus() || surveydtoObj.getUserresponsestatus().equalsIgnoreCase("")) {
			surveydtoObj.setUserresponsestatus("false");
		}

		if (null == surveydtoObj.getStatus() || surveydtoObj.getStatus().equalsIgnoreCase("")) {
			surveydtoObj.setStatus("false");
		}

		String json = gson.toJson(surveydtoObj);
		System.out.println(json);
		surveyBOObj.setSurveyjson(json);

		surveyBOObj.setStatus(surveydtoObj.getStatus());

	}

	private SurveyDTO populateSurveyDTO(SurveyDTO surveydtoObj, SurveyBO surveyBOObj) {

		String surveyJSON = surveyBOObj.getSurveyjson();
		Integer surveyid = surveyBOObj.getSurveyid();
		Gson gson = new Gson();
		surveydtoObj = gson.fromJson(surveyJSON, SurveyDTO.class);
		surveydtoObj.setSurveyid(surveyid.toString());
		return surveydtoObj;

	}

	private void populateActionLogBO(ActionLogDTO actionlogdtoObj, ActionLogBO actionlogBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		try {

			actionlogBOObj.setAction(actionlogdtoObj.getAction());
			actionlogBOObj.setCreator(actionlogdtoObj.getCreator());
			actionlogBOObj.setDetail(actionlogdtoObj.getDetail());
			actionlogBOObj.setModule(actionlogdtoObj.getModule());
			actionlogBOObj.setSubject(actionlogdtoObj.getSubject());
			if (null != actionlogdtoObj.getActdate() && null != actionlogdtoObj.getActtime()) {
				actionlogBOObj.setActdate(
						dateformatter.parse(actionlogdtoObj.getActdate() + " " + actionlogdtoObj.getActtime()));
			}

		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	
	private void populateActiveUserBO(ActiveUserDTO activeUserDTOObj, ActiveUserBO activeUserBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		try {

			activeUserBOObj.setUsname(activeUserDTOObj.getUsname());
			
			if (null != activeUserDTOObj.getActivedate() && null != activeUserDTOObj.getActivetime()) {
				activeUserBOObj.setActivedate(
						dateformatter.parse(activeUserDTOObj.getActivedate() + " 00:00:00"));
				activeUserBOObj.setActivedatetime(
						dateformatter.parse(activeUserDTOObj.getActivedate() + " " + activeUserDTOObj.getActivetime()));
			}

		} catch (ParseException e) {
			ServiceException serviceExceptionObj = new ServiceException(e.getMessage());
			throw serviceExceptionObj;
		}

	}

	private void populateActionLogDTO(ActionLogDTO actionlogdtoObj, ActionLogBO actionlogBOObj) {

		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");

		if (null != actionlogBOObj.getActdate()) {
			actionlogdtoObj.setActdate(dateformatter.format(actionlogBOObj.getActdate()));
		}

		actionlogdtoObj.setAction(actionlogBOObj.getAction());
		/* actionlogdtoObj.setActionid(actionlogBOObj.getActionid()); */
		if (null != actionlogBOObj.getActdate()) {

			actionlogdtoObj.setActtime(timeformatter.format(actionlogBOObj.getActdate()));
		}

		actionlogdtoObj.setCreator(actionlogBOObj.getCreator());
		actionlogdtoObj.setDetail(actionlogBOObj.getDetail());
		actionlogdtoObj.setModule(actionlogBOObj.getModule());
		actionlogdtoObj.setSubject(actionlogBOObj.getSubject());

	}
	
	public void setActiveUser(ActiveUserList activeUserListObj){
		ActiveUserBO aub = new ActiveUserBO();
		populateActiveUserBO(activeUserListObj.getActiveuserdtoLs().get(0), aub);
		ArrayList<ActiveUserBO> aubList= actionlogdao.fetchActiveUser(aub);
		if(aubList.size()>0){
			aub.setAudid(aubList.get(0).getAudid());
			actionlogdao.updateActiveUser(aub);
		}
		else{
			actionlogdao.addActiveUser(aub);
		}
	}
	
	public VisitorInfoList fetchVisitorInfo() {

		VisitorInfoList visitorInfoListObj = new VisitorInfoList();

		List<VisitorInfoDTO> visitorinfodtoLs = new ArrayList<VisitorInfoDTO>();
		
		ArrayList<Object> activeUsersCountList=actionlogdao.fetchActiveUsersCount();
		
		for(int i=0;i<7;i++){
			VisitorInfoDTO visitorInfoDTOObj1 = new VisitorInfoDTO();
			Object[] oArr=(Object[])activeUsersCountList.get(i);
			if(null != oArr){
			  visitorInfoDTOObj1.setCount(((Long)(oArr[0])).toString());
			  Date d=(Date)(oArr[1]);
			  SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
			  visitorInfoDTOObj1.setDate(dateformatter.format(d));
			  visitorinfodtoLs.add(visitorInfoDTOObj1);
			}
		}
		visitorInfoListObj.setVisitorinfodtoLs(visitorinfodtoLs);

		return visitorInfoListObj;
	}



	private int getTotalPageCount(int totalrecordcount) {
		int pagesize = 6;

		int totalPage = 0;

		if (totalrecordcount > 0) {

			if (totalrecordcount % pagesize == 0) {

				totalPage = totalrecordcount / pagesize;

			} else {
				totalPage = totalrecordcount / pagesize + 1;
			}

		}

		return totalPage;
	}

	public void deleteMeetingCron(String beforeLimit) {
		meetingdao.deleteCron(beforeLimit);
	}

	public void deleteActivityCron(String beforeLimit) {
		activitydao.deleteCron(beforeLimit);
	}

}
