package com.pooja.snm.core;

import java.util.regex.Pattern;


public enum MessageType {
	
	ONE(1,Pattern.compile("(\\w+)\\sat\\s(\\d+)p$",Pattern.CASE_INSENSITIVE)),
	
	TWO(2,Pattern.compile("(\\d+)\\ssales\\sof\\s(\\w+)s\\sat\\s(\\d+)p\\seach$", Pattern.CASE_INSENSITIVE)),
	
	THREE(3,Pattern.compile("(Add|Subtract|Multiply)\\s(\\d+)p?\\s(\\w+)s$", Pattern.CASE_INSENSITIVE));
	
	private int message;
	
	private Pattern pattern;
	
	private MessageType(int messageValue, Pattern patternValue){
		message = messageValue;
		pattern = patternValue;
	}
	
	public static MessageType validate(final String message) {
		for (MessageType messageType : MessageType.values()) {
			
			if (messageType.pattern.matcher(message).matches()) {
				return messageType;
			}
		}
		return null;
	}

	public Pattern getPattern(){
		return pattern;
	}
}
