zuul---gateway
1. 如何用zuul来访问product服务：
http://localhost:8083/product/product/list
8083:是gate-way项目的端口，第一个product是 服务的名称
/product/list：是服务的访问地址

自定义访问路径：
加zuul配置:


2.gateway timeout:504错误
ribbon:
  ReadTimeout: 90000
  SocketTimeout: 90000