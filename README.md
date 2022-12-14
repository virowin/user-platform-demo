# INTRODUCTION
1. using spring boot in one single project with 4 modules.
2. provide api to edit/read/soft delete single or multiple user(s)
3. maven package & mysql server & java server in docker.
4. send mail to registered user (fake, cn.virowin.user.platform.provider.service.UserServiceImpl)
5. validation(cn.virowin.user.platform.server.vo.request.UserHttpRequestVO), handling(cn.virowin.user.platform.server.handler),testing(user-platform-starter/test)
6. api documentation, below this.
7. microservices(dubbo)
8. ui (not completed, undergoing..)

# HOW TO RUN

make sure you have installed mvn & docker, and the application will bind 7333(java),3309(mysql),2181(zookeeper)

##run in idea:
(need mysql-server env local)

edit user-platform-starter/src/main/resources/application.yml

you should only edit druid setting and change to your mysql setting.mysql init data is APP-META/init.sql.

(zookeeper will run by application, you can also edit dubbo-*.properties or not)

##run in docker:

1. enter APP-META 
2. sh run.sh (need java env)

##for test url:

localhost:7333/user/select

localhost:7333/user/registor?nickname=testtest1&email=ggggg@126.com&password=333QQQ@@@


#API DOCUMENTATION

##basic response object

|key|type|desc|example|parents|
|------|-----|-----|-------|----|
|message|[string]|response message|success||
|code|[int]|response code|200||
|status|[int]|0:ok, other:error|0|||
|data|[object]|response body data|||


## select user api
### description
get all user info from db by pagination
### url
localhost:7333/user/select
### request

|param|type|desc|required|
|---|---|----|----|
|page|int|default: 1|not required|
|size|init|default: 10|not required|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|data|[object]|||
|list|[array]|user info array list|  |data|
|email|[string]| user email|abc@126.com|list|
|nickname|[string]| |virowin|list|
|dateline|[datetime]| | |list|

### response example
http://127.0.0.1:7333/user/select

{"message":"success","status":0,"data":{"list":[{"id":57,"nickname":"asv","email":"233333@126.com","dateline":"2022-08-23T03:43:23.000+00:00"}],"count":1},"code":200}



## get user api
### description
get one user by id
### url
localhost:7333/user/get
### request

|param|type|desc|required|
|---|---|----|----|
|id|long| |required|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|userInfo|[object]| | ||
|email|[string]| user email|abc@126.com|list|
|nickname|[string]| |virowin|list|
|dateline|[datetime]| | |list|

### response example
http://127.0.0.1:7333/user/get?id=57

{"message":"success","status":0,"data":{"userInfo":{"id":57,"nickname":"asv","email":"233333@126.com","dateline":"2022-08-23T03:43:23.000+00:00"}},"code":200}




## register api
### description
register user
### url
localhost:7333/user/register
### request
|param|type|desc|required|
|---|---|----|----|
|email|[string]| user email|true|
|nickname|[string]| |true|
|password|[string]| |true|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|id|[int]|user id | ||

### response example
http://127.0.0.1:7333/user/register?id=1&email=233333@126.com&nickname=asv&password=123qqQ@

{"message":"success","status":0,"data":{"id":1},"code":200}



## edit user api
### description
edit user's nickname or password
### url
localhost:7333/user/update
### request

|param|type|desc|required|
|---|---|----|----|
|email|[string]| user email|true|
|nickname|[string]| |true|
|password|[string]|new pwd|true|
|oldPassword|[string]|old pwd|true|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### response example
http://127.0.0.1:7333/user/update?id=1&email=233333@126.com&nickname=asv&password=123qqQ@1&oldPassword=123qqQ@

{"message":"success","status":0,"data":{"status":0},"code":200}



## delete user api
### description
soft delete user
### url
localhost:7333/user/delete
### request

|param|type|desc|required|
|---|---|----|----|
|id|[int]| user id|true|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### response example
http://127.0.0.1:7333/user/delete?id=57

{"message":"success","status":0,"data":{"status":0},"code":200}



## delete batch user api
### description

soft batch delete user
### url
localhost:7333/user/batchDelete
### request

|param|type|desc|required|
|---|---|----|----|
|ids|[string]|id list like: 1,2,3|true|

### response
|key|type|desc|example|parents|
|---|-----|---|----|----|
|status|[int]|0:ok | ||

### response example
http://127.0.0.1:7333/user/batchDelete?id=57,58

{"message":"success","status":0,"data":{"status":0},"code":200}