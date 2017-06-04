package com.pooja.snm.core;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class NotificationManagerTest {
	
	private NotificationManager tested;
	
	private OutputStream output;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void beforeEachTest() {
		output = new ByteArrayOutputStream();
		tested = new NotificationManager(new PrintStream(output),10, 5);
	}
	
	@After
	public void after(){
		ProcessCache.clearCache();
	}

	@Test
	public void whenFirstMessageNoOutput() {
		tested.process("apple at 10p");
		assertTrue((output.toString()).isEmpty());
	}
	
	@Test
	public void whenMessageCountIsLessThanIntervalThenNoReportGenerated(){
		tested.process("apple at 10p");
		tested.process("mango at 20p");	
		assertTrue(!(output.toString().contains("Interval Report Generated")));
	}
	
	
	@Test
	public void whenMessageTypeOneOnlyThenTotalDisplay(){
		tested.process("apple at 10p");
		tested.process("apple at 20p");
		tested.process("apple at 30p");
		tested.process("mango at 40p");
		tested.process("orange at 50p");
		assertTrue(output.toString().contains("apple|3|60"));
		assertTrue(output.toString().contains("mango|1|40"));
	}
	
	@Test
	public void whenInvalidMessageProcessedThenReportGenerated(){
		IntStream.range(0, 5).forEach(a -> tested.process("Invalid Message"));
		assertTrue(output.toString().contains("Interval Report generated"));
		
	}

	
	@Test
	public void whenMessageTypeTwoThenNoCumulativeReport(){
		tested.process("10 sales of oranges at 40p each");
		tested.process("20 sales of oranges at 20p each");
		tested.process("20 sales of apples at 10p each");
		tested.process("20 sales of apples at 10p each");
		tested.process("1 sales of bananas at 10p each");
		tested.process("20 sales of mangoes at 10p each");
		tested.process("20 sales of mangoes at 10p each");
		assertTrue(output.toString().contains("apple|40|400"));
		assertTrue(output.toString().contains("orange|30|800"));
		assertTrue(output.toString().contains("banana|1|10"));
	}
	
	@Test
	public void whenMessageTypeOneAndTwoThenNoCumulativeReport(){
		tested.process("10 sales of oranges at 40p each");
		tested.process("apple at 30p");
		tested.process("20 sales of apples at 10p each");
		tested.process("20 sales of oranges at 20p each");
		tested.process("orange at 50p");
		assertTrue(output.toString().contains("orange|31|850"));
		assertTrue(output.toString().contains("apple|21|230"));

		
	}
	
	@Test
	public void whenMessageTypeOnlyThree(){
		//
	}
	
	@Test
	public void whenMessages3WithoperationAddThenValueAdded(){
		tested.process("apple at 10p");   
		tested.process("10 sales of oranges at 10p each");
		tested.process("Add 20p apples");
		tested.process("apple at 20p");
		tested.process("orange at 10p");
		tested.process("10 sales of apples at 10p each");
		tested.process("Add 10p apples");
		tested.process("Add 10p oranges");
		tested.process("apple at 10p");
		tested.process("apple at 10p");	
		assertTrue(output.toString().contains("apple|14|290"));
		assertTrue(output.toString().contains("apple|20|Add|10|Add"));
		
	}
	
	
	@Test
	public void whenMessage3WithSubThenValueSUB(){
		tested.process("apple at 10p");   
		tested.process("10 sales of oranges at 10p each");
		tested.process("Subtract 20p apples");
		tested.process("apple at 20p");
		tested.process("orange at 10p");
		tested.process("10 sales of apples at 10p each");
		tested.process("Subtract 10p apples");
		tested.process("Subtract 10p oranges");
		tested.process("apple at 10p");
		tested.process("apple at 10p");	
		assertTrue(output.toString().contains("orange|11|0"));
		assertTrue(output.toString().contains("apple|14|10"));
		assertTrue(output.toString().contains("apple|20|Subtract|10|Subtract"));

	}
	
	@Test
	public void whenMessage3WithMultiplyThenCalculate(){
		tested.process("apple at 10p");   
		tested.process("10 sales of oranges at 10p each");
		tested.process("Multiply 20p apples");
		tested.process("apple at 20p");
		tested.process("orange at 10p");
		tested.process("10 sales of apples at 10p each");
		tested.process("Multiply 10p apples");
		tested.process("Multiply 10p oranges");
		tested.process("apple at 10p");
		tested.process("apple at 10p");	
	   assertTrue(output.toString().contains("orange|10|Multiply"));
	   assertTrue(output.toString().contains("apple|20|Multiply|10|Multiply"));
	}
	
	@Test
	public void whenMessageGreaterThanTotalThenThrowException(){
		IntStream.range(0, 10).forEach(a -> tested.process("Invalid Message"));
		
		expectedException.expect(java.lang.RuntimeException.class);
		tested.process("Invalid Message");
		expectedException.expectMessage("Report generation in process");
	}
	
}
