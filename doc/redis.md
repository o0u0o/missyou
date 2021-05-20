# redis相关知识

## redis键空间通知（keyspace notification）
当redis受到某个时间的影响（键被删除、或者过期），redis就会发布一个通知

我们可以用redis的键空间通知来触发（pub/sub 即发布/订阅 事件通知）

### 键空间通知的2种类型
用具监听某写时间
- key-space
  比如我们在redis set一个key为zhangsan 当我们del zhangsan 就会返回时间的名称
- key-event
  返回时间的名字
  
在业务中我们可以监听一些事件，比如删除、过期(x)，对redis列表(l)的操作，对集合(l)的操作等等

- 1、在redis.conf 开启
  ```
  redis-server /usr/local/etc/redis.conf
  ```
- 2、订阅expried（过期）事件
```bash
psubscribe __keyevent@0__:expired
```

## 归还优惠券和库存扣除