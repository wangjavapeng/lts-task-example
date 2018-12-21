package org.lts.tasktracker;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;

public class TestRunner implements JobRunner{
	 private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);
	 
	@Override
	public Result run(JobContext jobContext) throws Throwable {
		try {
//          BizLogger bizLogger = LtsLoggerFactory.getBizLogger();
          BizLogger bizLogger = jobContext.getBizLogger();

          // TODO 业务逻辑
          LOGGER.info("TestRunner 执行了！");
          // 会发送到 LTS (JobTracker上)
          bizLogger.info("测试，业务日志啊啊啊啊啊");

      } catch (Exception e) {
          LOGGER.info("Run job failed!", e);
          return new Result(Action.EXECUTE_FAILED, e.getMessage());
      }
      return new Result(Action.EXECUTE_SUCCESS, "TestRunner 执行成功了");
	}

}
