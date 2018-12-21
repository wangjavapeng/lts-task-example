package org.lts.tasktracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.ltsopensource.tasktracker.runner.JobRunner;

/**
 * 任务容器
 * @author wangpeng
 *
 */
public class TaskJobHolder {
	
	private static final Map<String, JobRunner> jobMap = new ConcurrentHashMap<>();
	
	//初始化
	static{
		addJob("TestJobRunner", new TestJobRunner());
		addJob("TestRunner", new TestRunner());
	}
	
	/**
	 * 添加任务
	 * @param key
	 * @param job
	 */
	public static void addJob(String key, JobRunner job){
		if(key == null || key == ""){
			key = "default";
		}
		jobMap.put(key.toUpperCase(), job);
	}
	
	/**
	 * 返回具体任务执行者
	 * @param key
	 * @return
	 */
	public static JobRunner getJob(String key){
		if(key == null || key == ""){
			key = "default";
		}
		return jobMap.get(key.toUpperCase());
	}
	
}
