Zdocker
=======

安装步骤

A.添加对外远程接口，使得 Docker API 在 tcp 上可用：
编辑 ：/etc/default/docker 
追加 ：DOCKER_OPTS="-H 127.0.0.1:4243"

B.重起 docker
sudo service docker restart

C.编辑Zdocker
Zdocker\src\main\java\app\utils\AppPath.java
修改：public static String dockerPath = "http://192.168.0.134:4243";//变更为你服务器docker地址

D.使用tomcat部署Zdocker并启动
请求:http://<你的tomcatIP:端口>/项目名/packages/list
