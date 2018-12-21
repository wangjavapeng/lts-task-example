package org.lts.jobclient;

import java.util.Date;

import com.github.ltsopensource.core.commons.utils.DateUtils;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.JobClientBuilder;
import com.github.ltsopensource.jobclient.domain.Response;

/**
 * 任务提交类
 *
 */
public class JobClientApp {
	
	//提交任务客户端
    public static void main( String[] args ) {
    	 JobClient jobClient = new JobClientBuilder()
                 .setPropertiesConfigure("lts.properties")
                 //添加任务执行完成监听
                 .setJobCompletedHandler(new JobCompletedHandlerImpl())
                 .build();

         jobClient.start();
         //任务也可在后台动态添加
//         submitCronJob1(jobClient);
//         submitCronJob2(jobClient);
    }
    
    //提交任务
    private static void submitCronJob1(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_cron_555");
        //传递参数，用于任务分发器中分发任务，这些参数都是在lts-tasktracker-shard-xml.xml中指定
        job.setParam("type", "111");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");      // 执行要执行该任务的taskTracker的节点组名称
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setCronExpression("0 0/1 * * * ?");
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }
  //提交任务
    private static void submitCronJob2(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_cron_666");
        //传递参数，用于任务分发器中分发任务，这些参数都是在lts-tasktracker-shard-xml.xml中指定
        job.setParam("type", "222");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");      // 执行要执行该任务的taskTracker的节点组名称
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setCronExpression("0 0/1 * * * ?");
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }

    private static void submitRepeatJob(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_repeat_555");
        job.setParam("shopId", "1122222221");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setRepeatCount(50);             // 一共执行50次
        job.setRepeatInterval(50 * 1000L);  // 50s 执行一次
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }

    private static void submitRealtimeJob(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_realtime_555");
        job.setParam("shopId", "1122222221");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }

    private static void submitTriggerTimeJob(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_trigger_time_555");
        job.setParam("shopId", "1122222221");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setTriggerTime(DateUtils.addHour(new Date(), 1).getTime());   // 1 小时之后执行
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }
}
