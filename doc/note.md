## Spring
Spring 最大的特点是具有IOC（控制翻转）和AOP（面向切面编程）编程原则/思想，Spring简化了IOC和AOP的应用，降低了使用的难度，
- IOC（控制翻转）：SpringBoot中 容器注入实例对象就使用了IOC的思想
- AOP面向切面编程：过滤器和拦截器应用了IOC的思想

## Java常见技术框架
- SSH(Spring Structs2 Hibernate)
- SSM(Spring SpringMVC Mybatis)

## SpringBoot
约定大于*配置*，
Interface是一种抽象，因为Java提倡面向抽象/接口编程，当多个类实现了同一个Interface, 我们就需要通过xml文件配置去实例化具体要使用对象、SpringBoot实现了自动装配简化了配置
## Spring 与 SpringBoot
- SSM :Spring + Spring MVC + MyBatis  （Spring + Spring MVC）是一个框架
- Spring Framework = Spring + Spring MVC

### Spring Framework 与 SpringBoot的关系
- SpringBoot 是 Spring Framework 的应用
- SpringBoot 无需繁琐的xml配置
- SpringBoot 的核心优势 自动配置/装配
误区：
1、SpringBoot 和 Spring（SpringFramework） 都可以使用注解，并不是Spring只能用xml配置


### 自动配置（非常重要）有什么用？

搞清楚什么自动配置有什么作用？
类比没有SpringBoot 如何开发的

- Spring Boot实现了自动配置，降低了项目搭建的复杂度。实现开箱即用
- 它主要是为了解决使用Spring框架需要进行大量的配置太麻烦的问题，
所以它并不是用来替代Spring的解决方案，
而是和Spring框架紧密结合用于提升Spring开发者体验的工具。
同时它集成了大量常用的第三方库配置(例如Jackson, JDBC, Mongo, Redis, Mail等等)，
Spring Boot应用中这些第三方库几乎可以零配置的开箱即用(out-of-the-box)

## SpringBoot
之前讲解路径：OCP（开闭原则） -> IOC 
IOC具体实现：容器 加入容器 需要的时候通过容器注入
抽象意义（目的）：把控制权交给用户 实现灵活的OCP原则

## 如何将对象加入容器（考虑到灵活性）？
- 1、XML方式（以前常用）
- 2、注解形式
- 3、编程方式 + 注解

### 注解形式
#### Stereotype Annotations 模式注解
1、@Component 被Spring容器


## 常用的模式注解：
0、@Component 最基础的注解 以下注解回利用到 @Component
    作用：将组件/类/bean 注入到 Spring 容器中

标明类的不同功能
1、@Service 标明为服务层并加入到Spring容器
2、@Controller 标明为控制器并加入到Spring容器
3、@Repository 标明为dao（数据仓库）并加入到Spring容器

4、@Configuration 

启动类和Controller的桥接点 容器

## IOC 对象实例化 和注入时机探讨
1、在SpringBoot应用启动时就开始把对象进行实例化并注入到代码中（默认的）
2、我门可以更改这一过场，使得可以延迟实例化 使用 @Lazy 注解

## @Autowired 注入防暑
- bytype 适用于只有一个接口的Bean实现
- byname 

### bytype 是默认的注入方式
寻找ISkill 接口实现Bean
- 1、可能找不到任何一个Bean 会报错
- 2、可能知道到一个 Spring回直接将之注入
- 3、找到多个 并不一定报错  会按照字段的名字推断选择哪个bean 实在没找到报错

@ 主动指定要注入bean的名字

## 面向对象里对于"变化"的解决方案
- 1、制定一个接口（interface）多个类实现同一个接口 - 这就是设计模式中的策略模式
- 2、一个类，通过更改类的属性，解决变化            -
举例：MySQL 默认端口为3306 如果更改为3307 需要用到配置文件进行属性更改  
3、

## @ComponentScan

## 策略模式的变化方案
- 1、byname 切换 bean anne
- 2、@Qualifier 注解指定bean
- 3、有选择的只注入一个bean 注释掉某个bean 上的@Component注解
- 4、使用 @Primary 注解（用处多） 提供bean 的优先级
    自己开发的库，有三个方法，对外提供接口
    
# 条件注解 @Conditional
    条件注解也可用于解决策略模式的变化

使用@Conditional 可以编写自定义条件注解：
@Conditional + Condition 接口

## 内置成品(springboot写好的)条件注解
- @ConditionalOnProperty 

# 自动装配/配置

1、原理是什么(面试常问)？

2、为什么要有自动装配，自动装配到底解决了什么问题？

使用第三方库/sdk步骤
1、安装/拷贝
2、使用时导入SDK/函数

## SpringBoot 是怎么将第三方的sdk加入IOC容器中的呢？

1、@Component注解 和 @Configuration


## @EnableXXX
模块装配

# uber jar neiqnei

```bash
java -jar xx.jar
```

war + tomcat

# 实体与实体关系配置
1、单表查询性能好 但是开发周期大

## 数据库标语表的关系(重要)
1、一对一   
学生 - 学好

2、一对多
班级 - 学生

3、多对多
老师 - 学生
设计好的数据库一定要考虑到数据库对应的(3种)关系

### 1对n(简单)

### 1对1（并非是必须的关系）
1、查询效率，避免一个表太多字段
2、业务的角度考虑需要将一个表拆分出多个一对一的表

### n对n
需要中间表

teacher

student

teacher_student

注意：第三张表需要有业务意义


 
面向对象 是对现实事务的抽象

## ORM
### 数据库 - 数据表 字段

### 面向对象的方式来操作数据库 Object Relation Mapping

数据库相关
1、数据库
2、数据表 - 对应面向对象的 类
3、记录 - 对应对象
4、字段 - 属性/成员变量

## Model - 生成数据表

关闭model生成数据表机制，推荐使用数据库设计工具对SQL熟悉的花可以手写DDL

表 -> Model (逆向生成Model)


## 双向一对对
关系维护端和关系被维护的端
1、多方 - 关系维护端
2、一方 - 关系被维护端

## 如何禁止JPA生成物理外键

## 如何拓展数据库
面临最多的情况：
1、表的数据字段不够用

特性：对于一张表们来说，字段不具备扩展性的，而记录具备扩展性

使用配置表 config(key value)

id  name    value table_name
1   color1  green theme
2   color2  red   theme
3
4   name

## 静态资源
- 图片、html、css、js、vue、视频

静态资源的管理和访问

### 静态资源最标准的托管方式
1、不推荐将静态资源放在springBoot后端，应该单独构建一个服务，如Nginx服务或者阿里云、七牛云、或者码云的OSS
2、不推荐使用云服务器，原因是静态资源访问对云服务带宽要求高，而高带宽的云服务价格昂贵。
3、大公司一般构建静态资源服务器集群，如Nginx


## 分页参数

传统的 pageNum pageSize

移动端: start = 0 count

## 分类 
### 1、父ID标识
通过父节点查询子节点方便，但是回溯查询困难

### 2、路径表示法
回溯查询: 子节点查询父节点(通过路径字段解决) 
如： 1、/node1_id/node2_id/node3_id
    2、/node1_id#name/node2_id#name/node3_id#name
    
缺点: 占用数据的空间资源

## Sku的规格(Spec)设计(服务端)
规格(Spec) 2个衍生的实体
1、规格名(Spec_Key)     如：尺码、颜色 ...
2、规格值(Spec_Value)   如：xl、蓝色 ...

### 规格具备主观性，并非客观的东西
如：


SPU(颜色、尺寸、尺码)

SKU1 SKU2 SKU3


Spu - Spec_key   多对多关系
Spu - Spec_value 多对多关系

Spu - Sku        一对多关系

## 设计数据库 从实体和模型出发 "设计完整" 的数据库

好的数据库设计 - 还将考虑到前端的操作
1、前端好不好要用
2、前端查询数据库多不多，减少查询次数，提高数据库的效率

## 序列化出现：
解决对象的传输和存储

MongoDB 适合存储文档型的数据
MySQL 支持JSON

## code 自动定义的协议
如下：
15$1-19#6-28 
15-spuId
1-规格名
19-规格值
# 规格值之间的分割
6-规格名2
28-规格值2

# Redis 业务和实践中的难点（缓存更新的时机和体量）


## 权限
分组、角色

权限：是否能够访问API

建议：用户不和权限有关系
分组和权限有关系
每个用户必须有一个分组



