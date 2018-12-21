package org.lts.tasktracker.springxml;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.logger.BizLogger;
import com.github.ltsopensource.tasktracker.runner.JobContext;

/**
 * 具体任务执行者，用于任务调度指定执行
 */
public class XmlJobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlJobScheduler.class);


    public Result runJob1(JobContext jobContext) throws Throwable {
        try {
            LOGGER.info("runJob1 我要执行：" + jobContext.getJob());
            BizLogger bizLogger = jobContext.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志");

        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功，runJob1");
    }

    public Result runJob2(JobContext jobContext) throws Throwable {
        try {
            LOGGER.info("runJob2 我要执行");
            BizLogger bizLogger = jobContext.getBizLogger();
            // 会发送到 LTS (JobTracker上)
            bizLogger.info("测试，业务日志");
        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_LATER, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功，runJob2");
    }

}
