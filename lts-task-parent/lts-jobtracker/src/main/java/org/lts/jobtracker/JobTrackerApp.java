package org.lts.jobtracker;

import com.github.ltsopensource.jobtracker.JobTracker;
import com.github.ltsopensource.jobtracker.JobTrackerBuilder;

/**
 * Hello world!
 *
 */
public class JobTrackerApp {
    public static void main( String[] args ) {
    	final JobTracker jobTracker = new JobTrackerBuilder()
                .setPropertiesConfigure("lts.properties")
                .build();

        // 启动节点
        jobTracker.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                jobTracker.stop();
            }
        }));
    }
}
