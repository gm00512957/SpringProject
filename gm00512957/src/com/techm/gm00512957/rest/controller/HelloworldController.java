package com.techm.gm00512957.rest.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

@RestController
@RequestMapping("/hello")
public class HelloworldController {
	
	 protected final Log logger = LogFactory.getLog(getClass());
	
	//@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	 @RequestMapping(value = "/t2s", method = RequestMethod.GET)
	 public String t2s() {
		logger.info("HelloController : printHelloWorld : ENTER");	
		String result="Hello ";
		
		  //"url": "https://stream.watsonplatform.net/text-to-speech/api",
		  //"username": "8c414fb3-8ace-4209-9368-f25a4d1890d3",
		  //"password": "zCzky0Y1Vxe0"
		
		/*
		{
			  "url": "https://stream.watsonplatform.net/speech-to-text/api",
			  "username": "fda15c75-19ed-4533-a4a6-544905eb86cd",
			  "password": "XmMHLQiAWf6o"
			}*/
		
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("8c414fb3-8ace-4209-9368-f25a4d1890d3", "zCzky0Y1Vxe0");
		
		List<Voice> voices = service.getVoices().execute();
		System.out.println(voices);
		
		logger.info("HelloController : printHelloWorld : EXIT");
		return result;	 
	 }
	 
	 @RequestMapping(value = "/s2t", method = RequestMethod.GET)
	 public String s2t() {
		logger.info("HelloController : printHelloWorld : ENTER");	
		String result="Hello ";
		
		
		/*
		{
			  "url": "https://stream.watsonplatform.net/speech-to-text/api",
			  "username": "fda15c75-19ed-4533-a4a6-544905eb86cd",
			  "password": "XmMHLQiAWf6o"
			}*/
		
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("fda15c75-19ed-4533-a4a6-544905eb86cd", "XmMHLQiAWf6o");

		RecognizeOptions options = new RecognizeOptions.Builder()
		  .model("en-US_BroadbandModel").contentType("audio/flac")
		  .interimResults(true).maxAlternatives(3)
		  .keywords(new String[]{"colorado", "tornado", "tornadoes"})
		  .keywordsThreshold(0.5).build();

		BaseRecognizeCallback callback = new BaseRecognizeCallback() {
		  @Override
		  public void onTranscription(SpeechResults speechResults) {
		    System.out.println(speechResults);
		  }

		  @Override
		  public void onDisconnected() {
		    System.exit(0);
		  }
		};

		try {
		  service.recognizeUsingWebSocket
		    (new FileInputStream("D:/GM00512957/TECHM/MI18 Hackthon/M118/audio-file.flac"), options, callback);
		}
		catch (FileNotFoundException e) {
		  e.printStackTrace();
		}
		
		
		
		return result;	 
	 }
	 
}
