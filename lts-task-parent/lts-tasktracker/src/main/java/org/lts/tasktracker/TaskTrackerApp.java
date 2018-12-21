package org.lts.tasktracker;

import com.github.ltsopensource.tasktracker.TaskTracker;
import com.github.ltsopensource.tasktracker.TaskTrackerBuilder;

/**
 * 任务执行者
 *
 */
public class TaskTrackerApp {
    public static void main( String[] args ) {
    	final TaskTracker taskTracker = new TaskTrackerBuilder()
                .setPropertiesConfigure("lts.properties")
                .build();

        taskTracker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                taskTracker.stop();
            }
        }));
    }
}
