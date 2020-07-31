# 路线图

## 路线

### 1. 解决谁去分配资源的问题, 即选举 leader

采用 zookeeper 选举 OK

### 2. 解决 leader 如何与 follower 如何通讯的问题

1. leader 建立 tcp server 监听端口
2. follower 也要建立 tcp server
    - 通讯哪些内容?
    - 建立 tcp server?

能不能直接通过 zookeeper 搞定?

服务 s1,s2,s3

1. 先注册 /members 下的临时节点, 监听 /assigned
2. 争夺 /coordinator 下的 leader
3. 获取资源列表? 从 zookeeper 中获取?
4. 


    
### 3. 解决 leader 如何分配资源的问题

## 参考

1. zookeeper 分布式协调/服务发现?
    - http://jm.taobao.org/2018/06/13/%E5%81%9A%E6%9C%8D%E5%8A%A1%E5%8F%91%E7%8E%B0%EF%BC%9F/


