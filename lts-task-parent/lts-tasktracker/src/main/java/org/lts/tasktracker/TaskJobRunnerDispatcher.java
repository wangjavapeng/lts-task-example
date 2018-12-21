package org.lts.tasktracker;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

/**
 * 集中任务调度器，只需要在配置中配置这个任务执行者，有该任务执行者执行任务分发
 * @author wangpeng
 *
 */
public class TaskJobRunnerDispatcher implements JobRunner {
	
	@Override
	public Result run(JobContext jobContext) throws Throwable {
		try {
			String type = jobContext.getJob().getParam("type");
			JobRunner jobRunner = TaskJobHolder.getJob(type);
			jobRunner.run(jobContext);
		} catch (Exception e) {
			 return new Result(Action.EXECUTE_FAILED, e.getMessage());
		}
		return new Result(Action.EXECUTE_SUCCESS, "分发成功。。。");
	}

}
