package com.pooja.snm.impl;

import com.pooja.snm.core.MessageProcessor;
import com.pooja.snm.core.MessageType;
import com.pooja.snm.core.ProcessCache;

public class MsgProcessorFactory {
	
//  private final static ProcessCache processCache = ProcessCache.getInstance();
	
	private final  MsgProcessorOneImpl processorOne ;
	private final   MsgProcessorTwoImpl processorTwo ;
	private final   MsgProcessorThreeImpl processorThree;
		
	public MsgProcessorFactory(ProcessCache processCache){
		processorOne = new MsgProcessorOneImpl(processCache); 
		processorTwo = new MsgProcessorTwoImpl(processCache);
		processorThree = new MsgProcessorThreeImpl(processCache);
	}
	
	public  MessageProcessor getMessageProcessor(MessageType messageType){
		 switch(messageType){
		 
		 case ONE: return processorOne;
		 case TWO: return processorTwo;
		 case THREE : return processorThree;
		   
		 }
		 
		 return null;
	}





//	public static ProcessCache getProcesscache() {
//		return processCache;
//	}

	
}
