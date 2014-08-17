Zdocker
=======

安装步骤 <br/>

A.添加对外远程接口，使得 Docker API 在 tcp 上可用：<br/>
编辑 ：/etc/default/docker <br/>
追加 ：DOCKER_OPTS="-H 127.0.0.1:4243" <br/>

B.重起 docker <br/>
sudo service docker restart <br/>

C.编辑Zdocker <br/>
Zdocker\src\main\java\app\utils\AppPath.java <br/>
修改：public static String dockerPath = "http://192.168.0.134:4243";//变更为你服务器docker地址 <br/>

D.使用tomcat部署Zdocker并启动 <br/>
请求:http://<你的tomcatIP:端口>/项目名/packages/list <br/>
