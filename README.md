# user-platform-demo

make sure you have installed mvn & docker, and the application will bind 7333(java),3309(mysql),2181(zookeeper)

#run in idea:
(need mysql-server env local)

edit user-platform-starter/src/main/resources/application.yml

you should only edit druid setting and change to your mysql setting.mysql init data is APP-META/init.sql.

(zookeeper will run by application, you can also edit dubbo-*.properties or not)

#run in docker:

1. enter APP-META , run runMysql.sh in your terminal(using docker).
2. run runJava.sh in your terminal's anthor tab(using docker, mvn).

#for test url:

localhost:7333/user/select

localhost:7333/user/registor?nickname=testtest1&email=ggggg@126.com&password=333QQQ@@@

