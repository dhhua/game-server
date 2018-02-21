基于spring boot，netty，protostuff(protobuf)
## 正在持续完善中。。。
### server-engine
服务端引擎，负责编解码，请求事件分派，心跳检测等
### proto-expose
如果序列化使用的是protobuf, 将app中数据传输实体导出为proto等格式，方便跨语言传输数据
### server-test 
测试服务端，业务逻辑处理的app
### client-test
java客户端，发送请求



