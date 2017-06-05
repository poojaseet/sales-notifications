package com.pooja.snm.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pooja.snm.core.ProcessCache;

public class MsgProcessorTwoTest {
	
	private MsgProcessorTwoImpl tested;
	
	private ProcessCache processCache;
	
	@Before
	public void beforeEachTest() {
		processCache = new ProcessCache();
		tested = new MsgProcessorTwoImpl(processCache);
	}

	@Test
	public void whenFirstValidMessageAddedThenCacheSizeis1() {
		tested.process("20 sales of oranges at 10p each");
		assertEquals(processCache.getSales().size(),1);		
	}
	
	@Test
	public void whenInValidMessageAddedThenCacheSizeis0() {
		tested.process("iuiiiy");
		assertEquals(processCache.getSales().size(),0);		
	}
	
	@Test
	public void when2SameTypeProductAddedThenCacheValueSizeAdded() {
		tested.process("20 sales of oranges at 10p each");
		tested.process("10 sales of oranges at 20p each");
		assertEquals(processCache.getSales().size(),1);		
		assertEquals(processCache.getSales().get("orange").size(),2);
	}
	
	

}
