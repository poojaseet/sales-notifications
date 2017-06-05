package com.pooja.snm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalesUtils {
	

	
	public static final String DELIMITER = "|";
	
	public static List<String> getTokens(Pattern patternRegex, String line) {
		List<String> tokens; 
		Matcher matcher = getRegexMatcher(patternRegex, line);
		if(matcher == null){
			return Collections.emptyList();
		}
		tokens =  new ArrayList<String>(matcher.groupCount());
		for (int i = 0; i <= matcher.groupCount(); i++) {
			tokens.add(matcher.group(i));
		}

		return tokens;
	}
	
	private static Matcher getRegexMatcher(Pattern pattern, String line) {
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return matcher;
		} else {
			return null;
		}
	}

}
