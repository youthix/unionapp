package org.service.delegateService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.common.UnionAppConstants;
import org.common.UnionAppMsgConstants;
import org.presentation.dto.RequestObj;
import org.presentation.dto.ResStatus;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.criteria.Criteria;
import org.presentation.dto.criteria.UpdateUserCriteria;
import org.presentation.dto.feature.ActiveUserList;
import org.presentation.dto.feature.ActivityList;
import org.presentation.dto.feature.AgreementList;
import org.presentation.dto.feature.AmrList;
import org.presentation.dto.feature.CategoryList;
import org.presentation.dto.feature.MeetingList;
import org.presentation.dto.feature.NewsLetterList;
import org.presentation.dto.feature.PayrateList;
import org.presentation.dto.feature.SpaceInfoDTO;
import org.presentation.dto.feature.SuggestionIdeaList;
import org.presentation.dto.feature.SummaryList;
import org.presentation.dto.feature.SurveyList;
import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.presentation.util.ServiceException;
import org.repository.RepositoryDelegate.RepositoryDelegator;
import org.repository.entity.UserBO;
import org.service.utilityService.HttpClientUtil;
import org.service.utilityService.Mailer;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceDelegator {

	@Autowired
	private RepositoryDelegator repositoryDelegator;
	@Autowired
	private HttpClientUtil httpClientUtil;
	@Autowired
	private Mailer mailer;
	UserList res;

	ResStatus resStatus;

	/*
	 * @Autowired ITestDAO dao;
	 */

	public ResponseObj login(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();
		String channel = reqparam.getChannel();

		/*
		 * else if (userBOObj.getLoginstatus().equalsIgnoreCase("T")) {
		 * ServiceException serviceExceptionObj = new ServiceException(
		 * "User is Already Logged In"); throw serviceExceptionObj; }
		 */

		if (null != userListObj) {

			responseObj = repositoryDelegator.login(userListObj,channel);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj logout(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			// Update the Login status
			userListObj.getUl().get(0).setLoginstatus("F");
			Criteria criteriaObj = new Criteria();
			criteriaObj.setCriteria("TRUE");
			UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
			updateUserCriteriaObj.setName("loginstatus");
			criteriaObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);
			repositoryDelegator.update(userListObj, criteriaObj);

			setResponse(responseObj);
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj register(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			repositoryDelegator.register(userListObj);
			responseObj.setUserListObj(userListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetch(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria()) {

			responseObj = repositoryDelegator.fetch(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchAllUser(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria()) {

			responseObj = repositoryDelegator.fetchAllUser(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj update(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			repositoryDelegator.update(userListObj, reqparam.getCriteria());
			if(userListObj.getUl()!=null 
					&& userListObj.getUl().get(0).getStatus().equalsIgnoreCase(UnionAppConstants.notapproved)){
				mailer.denialMail(userListObj);
			}
			responseObj.setUserListObj(userListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateuserprofile(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();
		String status=userListObj.getUl().get(0).getStatus();
		if (null != userListObj) {

			responseObj = repositoryDelegator.updateuserprofile(reqparam);
			if(status.equalsIgnoreCase(UnionAppConstants.approved)){
				mailer.approvalMail(userListObj);
			}
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updatepwd(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			// UserBO userBOObj = repositoryDelegator.login(userListObj);

			UserBO userBOObj = repositoryDelegator.fetchUserBO(userListObj);

			ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

			User userObj = userList.get(0);

			if ((null != userBOObj) && ((userBOObj.getUsname().equalsIgnoreCase(userObj.getUsNa()))
					&& (userBOObj.getPwd().equalsIgnoreCase(userObj.getPwd())))) {
				if (userBOObj.getStatus().equalsIgnoreCase("B")) {
					ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.USER_BLKD);
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("P")) {
					ServiceException serviceExceptionObj = new ServiceException(
							UnionAppMsgConstants.USER_PENDINGAPPROVAL);
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("A")) {

					// Update the Pwd
					String newPwd = userListObj.getUl().get(0).getNewpwd();
					userListObj.getUl().get(0).setPwd(newPwd);
					Criteria criteriaObj = new Criteria();
					criteriaObj.setCriteria("TRUE");
					UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
					updateUserCriteriaObj.setName("pwd");
					criteriaObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);
					repositoryDelegator.update(userListObj, criteriaObj);
					setResponse(responseObj);
				}

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.IN_CREDENTIAL);
				throw serviceExceptionObj;
			}
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj resetpwd(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			UserBO userBOObj = repositoryDelegator.fetchUserBO(userListObj);

			ArrayList<User> userList = (ArrayList<User>) userListObj.getUl();

			User userObj = userList.get(0);

			if ((null != userBOObj) && ((userBOObj.getUsname().equalsIgnoreCase(userObj.getUsNa())))) {
				if (userBOObj.getStatus().equalsIgnoreCase("B")) {
					ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.USER_BLKD);
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("P")) {
					ServiceException serviceExceptionObj = new ServiceException(
							UnionAppMsgConstants.USER_PENDINGAPPROVAL);
					throw serviceExceptionObj;
				} else if (userBOObj.getStatus().equalsIgnoreCase("A")) {
					// Reset the Pwd
					String newPwd = generatepwd();
					userListObj.getUl().get(0).setPwd(newPwd);
					// userListObj.getUl().get(0).setNewpwd(newPwd);
					Criteria criteriaObj = new Criteria();
					criteriaObj.setCriteria("TRUE");
					UpdateUserCriteria updateUserCriteriaObj = new UpdateUserCriteria();
					updateUserCriteriaObj.setName("pwd");
					criteriaObj.setUpdateUserCriteriaObj(updateUserCriteriaObj);
					repositoryDelegator.update(userListObj, criteriaObj);

					mailer.forgotPasswordMail(userListObj, newPwd);

					ResStatus resStatus = new ResStatus();
					resStatus.setCode("00");
					resStatus.setMsg(UnionAppMsgConstants.RESETPWD_SUCCESS);
					responseObj.setResStatus(resStatus);

					responseObj.setUserListObj(userListObj);
				}

			} else {
				ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.IN_CREDENTIAL);
				throw serviceExceptionObj;
			}
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createmeeting(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		MeetingList meetingListObj = reqparam.getMeetingListObj();

		if (null != meetingListObj) {

			repositoryDelegator.createmeeting(meetingListObj);
			responseObj.setMeetingListObj(meetingListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchmeeting(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchmeeting(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updatemeeting(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		MeetingList meetingListObj = reqparam.getMeetingListObj();

		if (null != meetingListObj) {

			responseObj = repositoryDelegator.updatemeeting(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj acceptdenymeeting(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		responseObj = repositoryDelegator.acceptdeny(reqparam);
		setResponse(responseObj);

		return responseObj;
	}

	public ResponseObj createactivity(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		ActivityList activityListObj = reqparam.getActivityListObj();

		if (null != activityListObj) {

			repositoryDelegator.createactivity(activityListObj);
			responseObj.setActivityListObj(activityListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchactivity(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchactivity(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateactivity(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		ActivityList activityListObj = reqparam.getActivityListObj();

		if (null != activityListObj) {

			responseObj = repositoryDelegator.updateactivity(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj acceptdenyactivity(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		responseObj = repositoryDelegator.acceptdenyactivity(reqparam);
		setResponse(responseObj);

		return responseObj;
	}

	public ResponseObj createNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		NewsLetterList newsLetterListObj = reqparam.getNewsLetterListObj();

		if (null != newsLetterListObj) {

			repositoryDelegator.createNewsLetter(newsLetterListObj);
			responseObj.setNewsLetterListObj(newsLetterListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchNewsLetter(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchNewsLetterById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchNewsLetterById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateNewsLetter(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		NewsLetterList newsLetterListObj = reqparam.getNewsLetterListObj();

		if (null != newsLetterListObj) {

			responseObj = repositoryDelegator.updateNewsLetter(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createPayrate(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		PayrateList PayrateListObj = reqparam.getPayrateListObj();

		if (null != PayrateListObj) {

			repositoryDelegator.createPayrate(PayrateListObj);
			responseObj.setPayrateListObj(PayrateListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchPayrate(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchPayrate(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchPayrateById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchPayrateById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updatePayrate(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		PayrateList PayrateListObj = reqparam.getPayrateListObj();

		if (null != PayrateListObj) {

			responseObj = repositoryDelegator.updatePayrate(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createAmr(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		AmrList AmrListObj = reqparam.getAmrListObj();

		if (null != AmrListObj) {

			repositoryDelegator.createAmr(AmrListObj);
			responseObj.setAmrListObj(AmrListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchAmr(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchAmr(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchAmrById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchAmrById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateAmr(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		AmrList AmrListObj = reqparam.getAmrListObj();

		if (null != AmrListObj) {

			responseObj = repositoryDelegator.updateAmr(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createAgreement(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		AgreementList AgreementListObj = reqparam.getAgreementListObj();

		if (null != AgreementListObj) {

			repositoryDelegator.createAgreement(AgreementListObj);
			responseObj.setAgreementListObj(AgreementListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchAgreement(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchAgreement(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchAgreementById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchAgreementById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateAgreement(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		AgreementList AgreementListObj = reqparam.getAgreementListObj();

		if (null != AgreementListObj) {

			responseObj = repositoryDelegator.updateAgreement(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createSummary(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SummaryList SummaryListObj = reqparam.getSummaryListObj();

		if (null != SummaryListObj) {

			repositoryDelegator.createSummary(SummaryListObj);
			responseObj.setSummaryListObj(SummaryListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchSummary(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchSummary(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchSummaryById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchSummaryById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateSummary(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SummaryList SummaryListObj = reqparam.getSummaryListObj();

		if (null != SummaryListObj) {

			responseObj = repositoryDelegator.updateSummary(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj createsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SuggestionIdeaList suggestionIdeaListObj = reqparam.getSuggestionIdeaListObj();

		if (null != suggestionIdeaListObj) {

			repositoryDelegator.createsuggestionidea(suggestionIdeaListObj);
			responseObj.setSuggestionIdeaListObj(suggestionIdeaListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchsuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria()) {

			responseObj = repositoryDelegator.fetchsuggestionidea(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public String fetchsuggestionideaById(String id) {

		String responseObj = "";

		if (null != id) {

			responseObj = repositoryDelegator.fetchsuggestionideaById(id);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updatesuggestionidea(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SuggestionIdeaList suggestionIdeaListObj = reqparam.getSuggestionIdeaListObj();

		if (null != suggestionIdeaListObj) {

			responseObj = repositoryDelegator.updatesuggestionidea(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateAttachmentDetail(String featureType, String featureId, String fileName,
			String attachmentType) {

		ResponseObj responseObj;

		responseObj = repositoryDelegator.updateAttachmentDetail(featureType, featureId, fileName, attachmentType,
				"add");
		setResponse(responseObj);

		return responseObj;
	}

	public ResponseObj deletefile(String featureType, String featureId, String fileName, String attachmentType) {

		ResponseObj responseObj;

		responseObj = repositoryDelegator.updateAttachmentDetail(featureType, featureId, fileName, attachmentType,
				"delete");
		setResponse(responseObj);

		return responseObj;
	}

	public ResponseObj createSurvey(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SurveyList surveyListObj = reqparam.getSurveyListObj();

		if (null != surveyListObj) {

			responseObj = repositoryDelegator.createSurvey(surveyListObj);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchSurvey(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		if (null != reqparam.getCriteria() && null != reqparam.getUserListObj()
				&& reqparam.getUserListObj().getUl().size() > 0) {

			responseObj = repositoryDelegator.fetchSurvey(reqparam);

			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchSurveyById(String surveyid, String userid) {

		ResponseObj responseObj;

		if (null != surveyid && null != userid) {

			responseObj = repositoryDelegator.fetchSurveyById(surveyid, userid);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj updateSurvey(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SurveyList surveyListObj = reqparam.getSurveyListObj();

		if (null != surveyListObj) {

			responseObj = repositoryDelegator.updateSurvey(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchActionLog(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		responseObj = repositoryDelegator.fetchActionLog(reqparam);

		setResponse(responseObj);

		return responseObj;
	}

	private String generatepwd() {

		String newPwd = UUID.randomUUID().toString();

		newPwd = newPwd.substring(0, 7);

		return newPwd;
	}

	public ResponseObj addcategory(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		CategoryList categoryListObj = reqparam.getCategoryListObj();

		if (null != categoryListObj) {

			repositoryDelegator.addcategory(categoryListObj);
			responseObj.setCategoryListObj(categoryListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchcategory(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		CategoryList categoryListObj;

		if (null != reqparam.getCriteria()) {

			categoryListObj = repositoryDelegator.fetchcategory(reqparam.getCriteria());
			responseObj.setCategoryListObj(categoryListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}
	
	public ResponseObj updatecategory(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		CategoryList categoryListObj = reqparam.getCategoryListObj();

		if (null != categoryListObj) {

			responseObj = repositoryDelegator.updateCategory(reqparam);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}	

	public ResponseObj fetchSpaceInfo(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		SpaceInfoDTO spaceInfoDTOObj = new SpaceInfoDTO();
		spaceInfoDTOObj.setUnit(UnionAppConstants.spaceUnit);
		spaceInfoDTOObj.setTotalspace(UnionAppConstants.totalSpaceAllocated);
		int usedSpace = repositoryDelegator.fetchUsedSpace().add(new BigDecimal(UnionAppConstants.miscSpaceUsed))
				.intValue();
		int remSpace = new Integer(UnionAppConstants.totalSpaceAllocated) - usedSpace;
		spaceInfoDTOObj.setRemspace(String.valueOf(remSpace));
		spaceInfoDTOObj.setUsedspace(String.valueOf(usedSpace));
		responseObj.setSpaceInfoDTOObj(spaceInfoDTOObj);
		setResponse(responseObj);
		return responseObj;
	}

	public ResponseObj fetchVisitorInfo(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();

		responseObj.setVisitorInfoListObj(repositoryDelegator.fetchVisitorInfo());
		setResponse(responseObj);

		return responseObj;
	}

		public void hello() {
		System.out.println("In Service");
	}

	public ResponseObj Testregister(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		// repositoryDelegator.register(reqparam);

		UserList userListObj = reqparam.getUserListObj();

		if (null != userListObj) {

			repositoryDelegator.register(userListObj);
			responseObj.setUserListObj(userListObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		/*
		 * System.out.println("InAddUser"); UserBO userBOObj = new UserBO();
		 * userBOObj.setUsname("TestUser"); userBOObj.setPwd("TestPwd");
		 * dao.addUser(userBOObj);
		 * 
		 * System.out.println("doneAddUser");
		 */
		return responseObj;
	}

	/*
	 * public ITestDAO getDao() { return dao; }
	 * 
	 * public void setDao(ITestDAO dao) { this.dao = dao; }
	 */

	private void setResponse(ResponseObj responseObj) {
		ResStatus resStatus = new ResStatus();
		resStatus.setCode("00");
		resStatus.setMsg("SUCCESS");
		responseObj.setResStatus(resStatus);
	}

	public ResponseObj setActiveUser(RequestObj reqparam) {

		ResponseObj responseObj = new ResponseObj();
		ActiveUserList activeUserListObj = reqparam.getActiveUserListObj();

		if (null != activeUserListObj) {

			repositoryDelegator.setActiveUser(activeUserListObj);
			setResponse(responseObj);

		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.INSUFFICIENTINPUT);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj sendNotification() throws Exception {

		ResponseObj responseObj = new ResponseObj();
		int responseCode = httpClientUtil.sendNotification();

		if (200 == responseCode) {
			setResponse(responseObj);
		} else {
			ServiceException serviceExceptionObj = new ServiceException(UnionAppMsgConstants.PROBLEM_IN_NOTIFCATION);
			throw serviceExceptionObj;
		}

		return responseObj;
	}

	public ResponseObj fetchNotificationItem(RequestObj reqparam) throws Exception {

		ResponseObj responseObj = new ResponseObj();

		responseObj = repositoryDelegator.fetchNotificationItem(reqparam);

		setResponse(responseObj);

		return responseObj;
	}

}
