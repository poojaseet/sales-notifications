package com.pooja.snm.core;

import java.io.PrintStream;

import com.pooja.snm.core.report.ReportGenerator;
import com.pooja.snm.impl.MsgProcessorFactory;

public class NotificationManager {

	private int messageProcessed;
	
	private final  int TOTAL_MESSAGE_CAPACITY ;
	
	private final int INTERVAL_REPORT;

	private ReportGenerator reportGenerator;

	private PrintStream outputChannel;
	
	private final ProcessCache processCache = new ProcessCache();
	
	private final MsgProcessorFactory messageFactory = new MsgProcessorFactory(processCache);

	NotificationManager(PrintStream out, int finalReport, int intervalReport) {
		outputChannel = out;
		TOTAL_MESSAGE_CAPACITY = finalReport;
		INTERVAL_REPORT = intervalReport;
		reportGenerator = new ReportGenerator(outputChannel, processCache);
	}
	
	public NotificationManager(PrintStream out){
		outputChannel = out;
		TOTAL_MESSAGE_CAPACITY = 50;
		INTERVAL_REPORT = 10;
		reportGenerator = new ReportGenerator(outputChannel, processCache);
	}

	/**
	 * Process Message received from any source. Find the appropriate MessageType and passes corresponding Proccessor
	 * and then generates the report.
	 * @param message
	 */
	public void process(String message) {
		messageProcessed += 1;
		if (messageProcessed <= TOTAL_MESSAGE_CAPACITY) {
			MessageType messageType = MessageType.validate(message);
			if (messageType != null) {
				MessageProcessor messageProcessor = messageFactory.getMessageProcessor(messageType);
				messageProcessor.process(message);
				
			}else{
				outputChannel.println("Invalid message");
			}
			
			if (messageProcessed % INTERVAL_REPORT == 0) {
				reportGenerator.generateIntervalReport();
			}

			if (messageProcessed == TOTAL_MESSAGE_CAPACITY) {
				reportGenerator.generateFinalReport();
			}

		} else {			
			throw new RuntimeException("Report generation in process");
		}

		
	}

}
