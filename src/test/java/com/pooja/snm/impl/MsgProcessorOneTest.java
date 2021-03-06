package com.pooja.snm.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pooja.snm.core.ProcessCache;

public class MsgProcessorOneTest {
	
	private MsgProcessorOneImpl tested;
	
	private ProcessCache processCache;
	
	@Before
	public void beforeEachTest() {
		processCache = new ProcessCache();
		tested = new MsgProcessorOneImpl(processCache);
	}

	@Test
	public void whenFirstValidMessageAddedThenCacheSizeis1() {
		tested.process("apple at 10p");
		assertEquals(processCache.getSales().size(),1);		
	}
	
	@Test
	public void whenInValidMessageAddedThenCacheSizeis0() {
		tested.process("iuiiiy");
		assertEquals(processCache.getSales().size(),0);		
	}
	
	@Test
	public void when2SameTypeProductAddedThenCacheValueSizeAdded() {
		tested.process("apple at 10p");
		tested.process("apple at 10p");
		assertEquals(processCache.getSales().size(),1);		
		assertEquals(processCache.getSales().get("apple").size(),2);
	}
	
	

}
