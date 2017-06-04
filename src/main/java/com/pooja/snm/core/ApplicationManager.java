package com.pooja.snm.core;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationManager {
	
	private NotificationManager notificationManager;
	private OutputStream output;
	
	ApplicationManager(){
		output = new PrintStream(System.out);
		notificationManager= new NotificationManager(new PrintStream(output));
	}
	
	public static void main(String[] args) {
		ApplicationManager application = new ApplicationManager();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String message = scanner.nextLine();	
			application.getNotificationManager().process(message);
			
		}
	}

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}
	
	

}
