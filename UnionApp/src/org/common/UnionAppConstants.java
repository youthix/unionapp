package org.common;

public interface UnionAppConstants {
	final String serverAbsPath = "/var/lib/tomcat7/webapps/attachments/";
	//String localAbsPath="/C:/Saurabh/Images/";
	final String serverUriPath = "http://185.134.30.228:8080/attachments/";
	final String meeting_eng = "meeting";
	final String activity_eng = "activity";
	final String newsletter_eng = "neewsletter";
	final String amr_eng = "amr";
	final String agreement_eng = "agreement";
	final String summary_eng = "summary";
	final String suggestionidea_eng = "suggestionidea";
	final String payrate_eng = "payrate";
	final String survey_eng = "survey";
	final String create_eng = "create";
	final String update_eng = "update";
	final String document_eng = "document";
	final String image_eng = "image";
	final String notapproved = "notapproved";
	final String approved = "approved";
	final String meeting = "Møde";
	final String activity = "Aktivitet"; 
	final String contact = "Kontaktliste";
	final String newsletter = "Nyhedsbrev"; 
	final String amr = "AMR";
	final String agreement = "Aftaler"; 
	final String summary = "Referat" ;
	final String suggestionidea = "Forslag og ideer"; 
	final String payrate = "Lønsats"; 
	final String survey = "Afstemning" ;
	final String create = "Oprette";
	final String update = "Opdatere";
	final String document = "Dokument";
	final String image = "Billede";	
	
	
	//Notification
	
	final String meeting_noti = "Næste møde";
	final String activity_noti = "Aktiviteter"; 
	final String newsletter_noti = "Nyhedsbrev"; 
	final String amr_noti = "AMR";
	final String agreement_noti = "Lokalaftale"; 
	final String summary_noti = "Referat" ;
	final String suggestionidea_noti = "Forslag og ideer"; 
	final String payrate_noti = "Lønsats"; 
	final String survey_noti = "Afstemning" ;
	final String contact_noti = "Kontaktliste" ;
	

	
	final String totalSpaceAllocated="100";
	final String miscSpaceUsed="30";
	final String spaceUnit="mb";
	final String dbName="Unionapp";
	
	//FCM constants
	final String fcmServerKey="AAAAozxQtE0:APA91bFh2JsmdW2hOqP3_psipNvFTpkLLCD1hzdZX5S-YFxpUIU8jlHxVJLZ6j-UcVeOH8r-GtFBdsgIkD7Ot6-cJl5UStjiH0lgAFaE3L3ZoVaCgdmN__xiZ3fwb-1mhhZpVcEWNJjh";
	final String fcmServerUrl="https://fcm.googleapis.com/fcm/send";
	final String notificationTitle="Unik-Apps notifikation";
	final String notififcaitonMessage="Hej du har en besked fra WFS Klubben";
}
