package org.lts.tasktracker.springxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class TaskTrackerApp {
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	new ClassPathXmlApplicationContext("/lts-tasktracker-shard-xml.xml");
    }
}
