## 实现思路
```
1. 引入依赖
2. 配置文件
3. 启动类添加jms注解
4. 初始化&实现配置类
5. 生产者入口
6. 消费者监听
```
## 代码示例
### 引入依赖
```
<!--集成ActiveMq-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
<!--消息队列连接池-->
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-pool</artifactId>
</dependency>
```
### 配置文件application.yml
```
server:
  port: 8888

spring:
  activemq:
    broker-url: tcp://127.0.0.1:61616
    user: admin
    password: admin
    close-timeout: 15s   # 在考虑结束之前等待的时间
    in-memory: true      # 默认代理URL是否应该在内存中。如果指定了显式代理，则忽略此值。
    non-blocking-redelivery: false  # 是否在回滚回滚消息之前停止消息传递。这意味着当启用此命令时，消息顺序不会被保留。
    send-timeout: 0     # 等待消息发送响应的时间。设置为0等待永远。
    queue-name: active.queue
    topic-name: active.topic.name.model

  #  packages:
  #    trust-all: true #不配置此项，会报错
  pool:
    enabled: true
    max-connections: 10   #连接池最大连接数
    idle-timeout: 30000   #空闲的连接过期时间，默认为30秒
```
### 启动类添加@EnableJms注解
### 初始化&实现配置类
```
@Configuration
public class ActiveMqConfig
{
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.topic-name}")
    private String password;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Value("${spring.activemq.topic-name}")
    private String topicName;

    @Bean(name = "queue")
    public Queue queue()
    {
        return new ActiveMQQueue(queueName);
    }

    @Bean(name = "topic")
    public Topic topic()
    {
        return new ActiveMQTopic(topicName);
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate()
    {
        return new JmsMessagingTemplate(connectionFactory());
    }

    /**
     * 在Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory)
    {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    /**
     * 在Topic模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory)
    {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
```
### 生产者入口
```
@RestController
public class ActiveMQController
{
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @PostMapping("/queue/test")
    public String sendQueue(@RequestBody String str)
    {
        sendMessage(queue, str);
        return "success";
    }

    @PostMapping("/topic/test")
    public String sendTopic(@RequestBody String str)
    {
        sendMessage(topic, str);
        return "success";
    }

    /**
     * 发送消息
     *
     * @param destination 发送到的队列
     * @param message     待发送的消息
     */
    private void sendMessage(Destination destination, final String message)
    {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
```
### 消费者监听
#### queue：
```
@Component
public class QueueConsumerListener
{
    /**
     * queue模式下的消费者
     * @param message
     *
     * destination：监听的queue
     * containerFactory：实现监听的工厂
     */
    @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
    public void readActiveQueue(String message)
    {
        System.out.println("queue接收到：" + message);
    }
}
```
#### topic：
```
@Component
public class TopicConsumerListener
{
    /**
     * topic模式消费者
     *
     * @param message
     *
     * destination：要监听的topic
     * containerFactory：实现监听的工厂
     */
    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void readActiveTopic(String message)
    {
        System.out.println("topic接收到：" + message);
    }
}
```
## 代码执行结果
```
1、启动本地ActiveMQ服务
2、请求/queue/test接口，后台打印日志：

queue接收到：{
    "test":"test"
}

3、查看ActiveMQ服务，该条消息已消费
4、测试topic，步骤同上
```