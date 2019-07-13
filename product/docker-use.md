docker use:
1. docker info  查看docker 的相关信息，如版本

2. docker的启动信息：
docker is configured to use the default
machine with IP 19.168.99.100

3.docker ps -a  查看所有启动的容器的相关信息：

container id, image, created(创建时间),
ports.

4. docker run -d --hostname my-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3.7.16-management
启动rabbitmq,当你之前没有下载过rabbitmq的镜像的时候，等几秒，会自动下载他
在浏览器输入： http://192.168.99.100:15672/ 
进入他的管理页面，需要登陆：guest/guest
访问地址不是localhost:15672

从底向上来打包应用； 对资源的有效隔离/管理，轻量级； 可复用，版本化