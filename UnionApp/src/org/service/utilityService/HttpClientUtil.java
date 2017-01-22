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

	private final String USER_AGENT = "Mozilla/5.0";

	public static void mainMethod(String[] args) throws Exception {

		HttpClientUtil http = new HttpClientUtil();
/*
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();*/

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=developer";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
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

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://fcm.googleapis.com/fcm/send";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		

		// add header
		//post.setHeader("User-Agent", USER_AGENT);
		//post.setHeader("Authorization", "key=AIzaSyAnpZ6KII9z3W_JBJCLSM-WOcE7Nm2bMkA");
		post.setHeader("Authorization", "key=AAAAk5K8e_I:APA91bGmA-3-jQM0SUSUYx1V_7to-IA1wu1R-_YqYdC1HRLNUQpH7N1NPe1V_sdfvgkQUfqEbfBbNZlQ6mGMFlf0C3SHPqAtDuzasfprUsw4HREARLnhXeO7TOJ4iJuATqU-C6WjPU0V");
		post.setHeader("Content-Type", "application/json");

		/*List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
		 	                    
		post.setEntity(new UrlEncodedFormEntity(urlParameters));*/
		
		
		StringEntity requestEntity = new StringEntity(
			    "{\"data\":{\"score\":\"5x1\"},\"to\":\"fMC5cgQPuKs:APA91bFhqQDhU_3TqCe-c_0385PANYRGVbHb_EmWJR8m-kVEoXcZs0YTi-C-x8xEZSVQspJ_odA8njJpoEX-xrOpl9TUpUjsaAM4mwXYBagB_YXnO7ewY3K8OsLVoxlRRgCadYv12tcf\"}",
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