package com.pooja.snm.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pooja.snm.core.MessageType;

public class MessageTypeTest {
	
	@Before
	public void beforeEachTest() {
		
	}

	@Test
	public void whenValidMessageOneThenMessageTypeOne() {
		assertEquals(MessageType.validate("apple at 10p").getMessageCode(),MessageType.ONE.getMessageCode());
	}
	
	@Test
	public void whenValidMessageTwoThenMessageTypeTwo() {
		assertEquals(MessageType.validate("20 sales of oranges at 10p each").getMessageCode(),MessageType.TWO.getMessageCode());
	}
	
	@Test
	public void whenValidMessage3ThenMessageType3() {
		assertEquals(MessageType.validate("20 sales of mangoes at 10p each").getMessageCode(),MessageType.TWO.getMessageCode());
	}
	
	@Test
	public void whenInValidMessageThenNull(){
		assertNull(MessageType.validate("20 sales of mangoes at jhhjhjjh"));
	}
	

}
