本项目是对来源项目 <a href="https://github.com/ltsopensource/light-task-scheduler">light-task-scheduler</a> 运用例子，可支持java代码和spring方式

运行任务，实现一个tasktracker运行不同任务，需要在jobclient中生成任务时添加如：job.setParam("type", "111");

任务分发器会根据type的值不同来运行不同任务

java任务提交配置
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
    
用spring配置tasktracker时， <property name="shardField" value="type"/> 指定用哪个参数来获取指定的执行任务
<bean id="taskTracker" class="com.github.ltsopensource.spring.TaskTrackerAnnotationFactoryBean" init-method="start">
        <!-- 使用JobRunnerItem注解一定要使用 JobDispatcher -->
        <!-- 任务调度类 -->
        <property name="jobRunnerClass" value="com.github.ltsopensource.spring.tasktracker.JobDispatcher"/>
        <!-- 如果指定为taskId, 取job.getTaskId() , 否则从 job.getParam("shardField") -->
        <!-- 指定 job.setParam("type", "111"); 任务分发器通过type参数获取值来找到具体的执行者，任务标识在下面任务配置类中配置 -->
        <property name="shardField" value="type"/>
        <property name="bizLoggerLevel" value="INFO"/>
        <property name="clusterName" value="test_cluster"/>
        <property name="registryAddress" value="zookeeper://127.0.0.1:2181"/>
        <property name="nodeGroup" value="test_trade_TaskTracker"/>
        <property name="workThreads" value="64"/>
        <property name="configs">
            <props>
                <prop key="job.fail.store">mapdb</prop>
            </props>
        </property>
    </bean>
    
    
配置具体任务执行者
<bean id="xmlJobScheduler" class="org.lts.tasktracker.springxml.XmlJobScheduler"></bean>

<bean class="com.github.ltsopensource.spring.tasktracker.MethodInvokingJobRunner">
    <property name="targetObject" ref="xmlJobScheduler"/>
    <property name="targetMethod" value="runJob2"/>
    <!-- job.setParam("type", "222"); 任务提交时指定值，用于查找对应的任务执行者 -->
    <property name="shardValue" value="222"/>
</bean>



