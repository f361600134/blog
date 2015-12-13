1. Visitor
	1.1>增加访问者信息, 解析每个访问者的IP地址,机型等信息, 每个访问者的信息要保留. 
	1.2>解析IP地址接口公开,对外可以直接调用, 并返回字符串

2. Timer
	2.1>增加计时器.用于处理1小时一次的IP地址入库请求.
	2.2>queue使用这个计时器.要与Spring配合使用
	
##########以上完成############
##########2015.12.10#########
	
3. Executor
	2.1>集成Spring线程池配置.
	<!-- 线程池配置,没有用上 -->
    <bean id="taskExecutor" 
    class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
    		<!-- 核心线程数 -->
            <property name="corePoolSize" value="2" />
            <!-- 最大线程数 -->
            <property name="maxPoolSize" value="5" />
            <!-- 队列大小 -->
            <property name="queueCapacity" value="6" /> 
            <!-- 线程池维护线程所允许的空闲时间,超过这个时间后会将大于corePoolSize的线程关闭, 默认为60s -->
        	<property name="keepAliveSeconds" value="300" />
    </bean>

4. File System(windows OS)
	4.1>实现用户上传文件和web app的分离.
	
5. Server System(mac OS)
    5.1>后台管理的完善,包括分页显示,文章删除,后台管理的代码优化.
    5.2>markdown编辑器的正常使用.可以考虑不上传图片,附件.
    5.3>考虑用ueditor编辑器替换markown.

















