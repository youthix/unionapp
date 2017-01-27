package org.service.utilityService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientUtil {

	
	public static void mainMethod(String[] args) throws Exception {

		HttpClientUtil http = new HttpClientUtil();
/*
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();*/

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://fcm.googleapis.com/fcm/send";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		

		// add header
		//post.setHeader("User-Agent", USER_AGENT);
		//post.setHeader("Authorization", "key=AIzaSyAnpZ6KII9z3W_JBJCLSM-WOcE7Nm2bMkA");
		post.setHeader("Authorization", "key=AAAAozxQtE0:APA91bFh2JsmdW2hOqP3_psipNvFTpkLLCD1hzdZX5S-YFxpUIU8jlHxVJLZ6j-UcVeOH8r-GtFBdsgIkD7Ot6-cJl5UStjiH0lgAFaE3L3ZoVaCgdmN__xiZ3fwb-1mhhZpVcEWNJjh");
		post.setHeader("Content-Type", "application/json");

		/*List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
		 	                    
		post.setEntity(new UrlEncodedFormEntity(urlParameters));*/
		
		
		StringEntity requestEntity = new StringEntity(
			    //"{\"data\":{\"score\":\"5x1\"},\"to\":\"eqNEPfDY-pU:APA91bHY7L2M_rBoCRGriP_Fhr6eFtf8ia4JCjYw3wPmVo4WBWhvjbNgBp2Jah8dLLueXLAp6FZtveuftQh_3yCdiSi8D434aqEBnPHGcZS5p0rueM2JppsC782yOGhgJVijLy8QNQzQ\"}",
				//"{\"notification\":{\"apps\":{\"alert\":{\"body\" : \"Helllo\",\"title\" : \"Title\"}},\"badge\":\"1\",\"sound\":\"default\"},\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"}",
				"{\"notification\":{\"title\":\"Hello\",\"text\":\"Hello I am Saurabh\"},\"priority\":\"high\",\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"}",
				//"{\"data\":[\"aps\":{\"alert\":{\"body\" : \"Helllo\",\"title\" : \"Title\"},\"badge\":\"1\",\"sound\":\"default\"},\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"]}",
			    ContentType.APPLICATION_JSON);
		post.setEntity(requestEntity);

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}

}