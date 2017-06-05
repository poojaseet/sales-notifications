package com.pooja.snm.impl;

import org.junit.Before;

import com.pooja.snm.core.ProcessCache;

public class MsgProcessorThreeTest {
	
	private MsgProcessorThreeImpl tested;
	
	private ProcessCache processCache;
	
	@Before
	public void beforeEachTest() {
		processCache = new ProcessCache();
		tested = new MsgProcessorThreeImpl(processCache);
	}

	

}
