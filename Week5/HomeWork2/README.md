## Spring装配Bean的三种方式
### 怎么理解装配？
将Bean安装到Spring容器中进行管理，并配置依赖关系；
___
### 一、基于XML的显式配置
- 工作中不常用，但某些情况下只能使用显式的手动配置；
- 如：需要引入一些第三方库，且想让Spring管理这些Bean时，我们无法修改其源码，只能通过显式配置； 
- 配置在applicationContext.xml中；
- 使用时，通过 new ClassPathXmlApplicationContext("applicationContext.xml")，得到应用上下文对象；
___
### 二、基于Java的装配
要通过@Configuration和@Bean搭配完成；
```
@Configuration 注解：声明当前类是个配置类，作用同applicationContext.xml一样
@Bean 注解：作用在方法上，声明当前方法的返回值是个由Spring管理的Bean，默认作用域为单例
```
使用时，通过 
___
### 三、隐式配置
分两步：1、组件扫描；2、自动装配；
#### 组件扫描
```
what：Spring自动发现应用上下文中创建的bean；
how：用@Component标记的类，表明是可被扫描的bean；
```
#### 开启组件扫描的两种方式：
1.java config 配置  
```
@Configuration指定某个类为配置类；
@ComponentScan指定要扫描的组件范围，不设置参数，则默认扫描当前类的包及子包
```
2.xml 配置
```
<context:component-scan base-package="包路径"/>
```
#### 自动装配
通过@Autowired来进行依赖注入
___
## 其他总结
1. Spring中所有的bean都有一个ID，通过@Component设置的，自动扫描时，会以类名的首字母小写，作为ID（**当类名是以两个或以上的大写字母开头的话，bean的名字会与类名保持一致**）；如果想要自定义，则需设置@Component参数；
2. @Bean注解的bean的ID，默认是方法名；如果要自定义，则需通过name属性指定；