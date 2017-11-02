package com.techm.gm00512957.rest.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class TextToSpeech {
	String url = "https://stream.watsonplatform.net/text-to-speech/api/v1/synthesize";
	public static void main(String args[]){
		TextToSpeech t2s= new TextToSpeech();
		t2s.execute();		
	}
	public void execute(){
 		HttpClient  client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		//post.setHeader("User-Agent", "Chrome");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "audio/wav");
		post.setHeader("Authorization", "Basic OGM0MTRmYjMtOGFjZS00MjA5LTkzNjgtZjI1YTRkMTg5MGQzOnpDemt5MFkxVnhlMA==");
		String stringData="{\"text\":\"what is horlicks\"}";
			
		/*List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("text", "what is horlicks"));*/
		

		try {
		//	post.setEntity(new UrlEncodedFormEntity(urlParameters));
		//	post.setEntity(new StringEntity(requestEntity));
			post.setEntity(new StringEntity(
					stringData,
				    ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(post);
			System.out.println("Response Code : "
			                + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	

		
	}
		
		

}
