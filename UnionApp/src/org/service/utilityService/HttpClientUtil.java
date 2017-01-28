package org.service.utilityService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.common.UnionAppConstants;

public class HttpClientUtil {

	
	// HTTP POST request
	public int sendNotification() throws Exception {

		String url = UnionAppConstants.fcmServerUrl;

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		

		// add header		
		post.setHeader("Authorization", "key="+UnionAppConstants.fcmServerKey);
		post.setHeader("Content-Type", "application/json");		
		
		StringEntity requestEntity = new StringEntity(
			    //"{\"data\":{\"score\":\"5x1\"},\"to\":\"eqNEPfDY-pU:APA91bHY7L2M_rBoCRGriP_Fhr6eFtf8ia4JCjYw3wPmVo4WBWhvjbNgBp2Jah8dLLueXLAp6FZtveuftQh_3yCdiSi8D434aqEBnPHGcZS5p0rueM2JppsC782yOGhgJVijLy8QNQzQ\"}",
				//"{\"notification\":{\"apps\":{\"alert\":{\"body\" : \"Helllo\",\"title\" : \"Title\"}},\"badge\":\"1\",\"sound\":\"default\"},\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"}",
				//"{\"notification\":{\"title\":\"Hello\",\"text\":\"Hej du har en besked fra WFS Klubben\"},\"priority\":\"high\",\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"}",
				"{\"notification\":{\"title\":\""+UnionAppConstants.notificationTitle+"\",\"text\":\""+UnionAppConstants.notififcaitonMessage+"\"},\"priority\":\"high\",\"to\":\"/topics/union\"}",
				//"{\"notification\":{\"title\":\"Hello\",\"text\":\"Hej du har en besked fra WFS Klubben\"},\"priority\":\"high\",\"to\":\"eqNEPfDY-pU:APA91bHY7L2M_rBoCRGriP_Fhr6eFtf8ia4JCjYw3wPmVo4WBWhvjbNgBp2Jah8dLLueXLAp6FZtveuftQh_3yCdiSi8D434aqEBnPHGcZS5p0rueM2JppsC782yOGhgJVijLy8QNQzQ\"}",
				//"{\"data\":[\"aps\":{\"alert\":{\"body\" : \"Helllo\",\"title\" : \"Title\"},\"badge\":\"1\",\"sound\":\"default\"},\"to\":\"eCP99TBwMQU:APA91bG1vwQfn7XhKodRe6HzrDRDq2daj006XPv1b6LyLGZYOsq3gnXQVM9QqG67CmTOgCbmCRXhVozlw8TDZVrScooIuNoA8srbwVXW5ZKluEhWhm9YPvrrAm2qKM7A4geGWbZgpGUf\"]}",
		ContentType.APPLICATION_JSON);
		post.setEntity(requestEntity);

		HttpResponse response = client.execute(post);
		System.out.println("Post parameters : " + post.getEntity());
		int responseCode=response.getStatusLine().getStatusCode();
		System.out.println("Notification Response Code : " +responseCode
                                    );

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		return responseCode;
	}

}