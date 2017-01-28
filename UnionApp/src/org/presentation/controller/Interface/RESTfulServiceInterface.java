package org.presentation.controller.Interface;

import java.io.InputStream;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import org.presentation.dto.RequestObj;
import org.presentation.dto.ResponseObj;
import org.presentation.dto.feature.SurveyDTO;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public interface RESTfulServiceInterface {

	ResponseObj login(RequestObj reqparam);

	ResponseObj logout(RequestObj reqparam);

	ResponseObj register(RequestObj reqparam);

	ResponseObj fetch(RequestObj reqparam);
	
	ResponseObj fetchAllUser(RequestObj reqparam);
	
	ResponseObj update(RequestObj reqparam);

	ResponseObj updateuserprofile(RequestObj reqparam);

	ResponseObj updatepwd(RequestObj reqparam);

	ResponseObj resetpwd(RequestObj reqparam);

	ResponseObj createmeeting(RequestObj reqparam);

	ResponseObj updatemeeting(RequestObj reqparam);

	ResponseObj fetchmeeting(RequestObj reqparam);

	ResponseObj acceptdenymeeting(RequestObj reqparam);

	ResponseObj createactivity(RequestObj reqparam);

	ResponseObj updateactivity(RequestObj reqparam);

	ResponseObj fetchactivity(RequestObj reqparam);

	ResponseObj acceptdenyactivity(RequestObj reqparam);

	ResponseObj fetchNewsLetter(RequestObj reqparam);

	ResponseObj updateNewsLetter(RequestObj reqparam);

	ResponseObj createNewsLetter(RequestObj reqparam);

	String fetchNewsLetterById(String id);

	ResponseObj fetchAmr(RequestObj reqparam);

	ResponseObj updateAmr(RequestObj reqparam);

	ResponseObj createAmr(RequestObj reqparam);

	String fetchAmrById(String id);

	ResponseObj fetchPayrate(RequestObj reqparam);

	ResponseObj updatePayrate(RequestObj reqparam);

	ResponseObj createPayrate(RequestObj reqparam);

	String fetchPayrateById(String id);

	ResponseObj fetchAgreement(RequestObj reqparam);

	ResponseObj updateAgreement(RequestObj reqparam);

	ResponseObj createAgreement(RequestObj reqparam);

	String fetchAgreementById(String id);

	ResponseObj fetchSummary(RequestObj reqparam);

	ResponseObj updateSummary(RequestObj reqparam);

	ResponseObj createSummary(RequestObj reqparam);

	String fetchSummaryById(String id);

	ResponseObj createsuggestionidea(RequestObj reqparam);

	ResponseObj updatesuggestionidea(RequestObj reqparam);

	ResponseObj fetchsuggestionidea(RequestObj reqparam);

	String fetchsuggestionideaById(String id);

	ResponseObj addcategory(RequestObj reqparam);

	ResponseObj fetchcategory(RequestObj reqparam);

	ResponseObj uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@HeaderParam(value = "featureType") String featureType, @HeaderParam(value = "featureID") String featureId,
			@HeaderParam(value = "attachmentType") String attachmentType,
			@HeaderParam(value = "attachmentTitle") String attachmentTitle,
			@HeaderParam(value = "attachmentName") String attachmentName);
	
	ResponseObj deleteFile(RequestObj reqparam);

	ResponseObj helloSurvey(RequestObj reqparam);
	
	ResponseObj fetchSurvey(RequestObj reqparam);

	ResponseObj updateSurvey(RequestObj reqparam);

	ResponseObj createSurvey(RequestObj reqparam);

	ResponseObj fetchSurveyById(String surveyid, String userid);
	
	ResponseObj fetchActionLog(RequestObj reqparam);
	
	ResponseObj fetchSpaceInfo(RequestObj reqparam);
	
	ResponseObj fetchVisitorInfo(RequestObj reqparam);
	
	ResponseObj setActiveUser(RequestObj reqparam);
	
	ResponseObj sendNotification();
	
	String testMail();

}
