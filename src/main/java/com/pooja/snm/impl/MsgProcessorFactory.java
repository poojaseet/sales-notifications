package com.pooja.snm.impl;

import com.pooja.snm.core.MessageProcessor;
import com.pooja.snm.core.MessageType;
import com.pooja.snm.core.ProcessCache;

public class MsgProcessorFactory {
	
  private final static ProcessCache processCache = ProcessCache.getInstance();
	
	private final static  MsgProcessorOneImpl processorOne = new MsgProcessorOneImpl(processCache);
	private final static  MsgProcessorTwoImpl processorTwo = new MsgProcessorTwoImpl(processCache);
	private final static  MsgProcessorThreeImpl processorThree  = new MsgProcessorThreeImpl(processCache);
		
	MsgProcessorFactory(){
		
	}
	
	public static MessageProcessor getMessageProcessor(MessageType messageType){
		 switch(messageType){
		 
		 case ONE: return processorOne;
		 case TWO: return processorTwo;
		 case THREE : return processorThree;
		   
		 }
		 
		 return null;
	}





	public static ProcessCache getProcesscache() {
		return processCache;
	}

	
}
