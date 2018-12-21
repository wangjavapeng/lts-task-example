����Ŀ�Ƕ���Դ��Ŀ <a href="https://github.com/ltsopensource/light-task-scheduler">light-task-scheduler</a> �������ӣ���֧��java�����spring��ʽ

��������ʵ��һ��tasktracker���в�ͬ������Ҫ��jobclient����������ʱ����磺job.setParam("type", "111");

����ַ��������type��ֵ��ͬ�����в�ͬ����

java�����ύ����
 private static void submitCronJob1(JobClient jobClient) {
        Job job = new Job();
        job.setTaskId("t_cron_555");
        //���ݲ�������������ַ����зַ�������Щ����������lts-tasktracker-shard-xml.xml��ָ��
        job.setParam("type", "111");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");      // ִ��Ҫִ�и������taskTracker�Ľڵ�������
        job.setNeedFeedback(true);
        job.setReplaceOnExist(true);        // ����������д�����������ʱ���Ƿ��滻����
        job.setCronExpression("0 0/1 * * * ?");
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }
    
��spring����tasktrackerʱ�� <property name="shardField" value="type"/> ָ�����ĸ���������ȡָ����ִ������
<bean id="taskTracker" class="com.github.ltsopensource.spring.TaskTrackerAnnotationFactoryBean" init-method="start">
        <!-- ʹ��JobRunnerItemע��һ��Ҫʹ�� JobDispatcher -->
        <!-- ��������� -->
        <property name="jobRunnerClass" value="com.github.ltsopensource.spring.tasktracker.JobDispatcher"/>
        <!-- ���ָ��ΪtaskId, ȡjob.getTaskId() , ����� job.getParam("shardField") -->
        <!-- ָ�� job.setParam("type", "111"); ����ַ���ͨ��type������ȡֵ���ҵ������ִ���ߣ������ʶ���������������������� -->
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
    
    
���þ�������ִ����
<bean id="xmlJobScheduler" class="org.lts.tasktracker.springxml.XmlJobScheduler"/>

<bean class="com.github.ltsopensource.spring.tasktracker.MethodInvokingJobRunner">
    <property name="targetObject" ref="xmlJobScheduler"/>
    <property name="targetMethod" value="runJob2"/>
    <!-- job.setParam("type", "222"); �����ύʱָ��ֵ�����ڲ��Ҷ�Ӧ������ִ���� -->
    <property name="shardValue" value="222"/>
</bean>



