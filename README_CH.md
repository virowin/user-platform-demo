# 说明
1. 使用springboot和4个模块.
2. 用api提供了增删改查接口.
3. 在docker中maven打包、mysql服务、java服务.
4. 注册成功发邮件(因无smtp所以假实现 写在cn.virowin.user.platform.provider.service.UserServiceImpl)
5. 验证httpget参数(cn.virowin.user.platform.server.vo.request.UserHttpRequestVO), handling(cn.virowin.user.platform.server.handler),单元测试(user-platform-starter/test)
6. api文档，见下方.
7. 微服务框架(dubbo)
8. 界面 (还没完成，正在做。。)

# 如何运行

确保已安装maven和docker，将会绑定以下端口 7333(java),3309(mysql),2181(zookeeper)

##idea中运行
(需要本地mysql服务环境)

修改 user-platform-starter/src/main/resources/application.yml

只需要改里面的druid配置，初始化sql在 APP-META/init.sql.

(zookeeper会在java中运行，也可以修改dubbo-*.properties的配置链接自己的zookeeper)

##在docker中运行:

1. cd APP-META 
2. sh run.sh

##用于测试的链接:

localhost:7333/user/select

localhost:7333/user/registor?nickname=testtest1&email=ggggg@126.com&password=333QQQ@@@


#API文档

##基础结构

|key|type|desc|example|parents|
|------|-----|-----|-------|----|
|message|[string]|response message|success||
|code|[int]|response code|200||
|status|[int]|0:ok, other:error|0|||
|data|[object]|response body data|||


## 查询接口
### 说明
获取所有的注册用户、邮箱
### url
localhost:7333/user/select
### 请求参数

|param|type|desc|required|
|---|---|----|----|
|page|int|default: 1|not required|
|size|init|default: 10|not required|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|data|[object]|||
|list|[array]|user info array list|  |data|
|email|[string]| user email|abc@126.com|list|
|nickname|[string]| |virowin|list|
|dateline|[datetime]| | |list|

### 返回示例
http://127.0.0.1:7333/user/select

{"message":"success","status":0,"data":{"list":[{"id":57,"nickname":"asv","email":"233333@126.com","dateline":"2022-08-23T03:43:23.000+00:00"}],"count":1},"code":200}



## 获取单个用户接口
### 说明
根据id获取单个用户
### url
localhost:7333/user/get
### 请求参数

|param|type|desc|required|
|---|---|----|----|
|id|long| |required|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|userInfo|[object]| | ||
|email|[string]| user email|abc@126.com|list|
|nickname|[string]| |virowin|list|
|dateline|[datetime]| | |list|

### 返回示例
http://127.0.0.1:7333/user/get?id=57

{"message":"success","status":0,"data":{"userInfo":{"id":57,"nickname":"asv","email":"233333@126.com","dateline":"2022-08-23T03:43:23.000+00:00"}},"code":200}




## 注册用户接口
### 说明
用于用户注册
### url
localhost:7333/user/register
### 请求参数
|param|type|desc|required|
|---|---|----|----|
|email|[string]| user email|true|
|nickname|[string]| |true|
|password|[string]| |true|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|id|[int]|user id | ||

### 返回示例
http://127.0.0.1:7333/user/register?id=1&email=233333@126.com&nickname=asv&password=123qqQ@

{"message":"success","status":0,"data":{"id":1},"code":200}



## 修改用户接口
### 说明
修改用户的用户名密码
### url
localhost:7333/user/update
### 请求参数

|param|type|desc|required|
|---|---|----|----|
|email|[string]| user email|true|
|nickname|[string]| |true|
|password|[string]|new pwd|true|
|oldPassword|[string]|old pwd|true|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### 返回示例
http://127.0.0.1:7333/user/update?id=1&email=233333@126.com&nickname=asv&password=123qqQ@1&oldPassword=123qqQ@

{"message":"success","status":0,"data":{"status":0},"code":200}



## 删除用户
### 说明
软删除用户
### url
localhost:7333/user/delete
### 请求参数

|param|type|desc|required|
|---|---|----|----|
|id|[int]| user id|true|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### 返回示例
http://127.0.0.1:7333/user/delete?id=57

{"message":"success","status":0,"data":{"status":0},"code":200}



## 批量软删除用户
### 说明

批量软删除用户
### url
localhost:7333/user/batchDelete
### 请求参数

|param|type|desc|required|
|---|---|----|----|
|ids|[string]|id list like: 1,2,3|true|

### 返回结构
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### 返回示例
http://127.0.0.1:7333/user/batchDelete?id=57,58

{"message":"success","status":0,"data":{"status":0},"code":200}