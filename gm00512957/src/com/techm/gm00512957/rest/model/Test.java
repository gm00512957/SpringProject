package com.techm.gm00512957.rest.model;

import java.util.List;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;

public class Test {

	public static void main(String args[]){
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("fda15c75-19ed-4533-a4a6-544905eb86cd", "XmMHLQiAWf6o");

		List<SpeechModel> models = service.getModels().execute();
		System.out.println(models);
	}
}
