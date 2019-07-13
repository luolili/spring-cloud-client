在你选择完spring cloud config的版本后，出现某某unknown或无法导入的情况

先加一个这个包的一个注解，然后利用idea的alt+enter来寻找要导入的jar包

在将config server注册到eureka的配置写完之后，测试他有没有注册到eureka上。

2.
Invalid config server configuration.
先在github上创建一个repo,添加order.yml文件，然后于config项目的yml里配置
新创建的repo的https地址
日志：
Adding property source: file:/C:/Users/ADMINI~1/AppData/Local/Temp/config-repo-3562650739285832160/order.yml
在启动的时候会出现超时的问题：
设置github的connection timeout为更大的数

启动成功后，访问：http://localhost:8080/order-a.yml
命名规则：
/{name}-{profile}.yml
or
/{label}/{name}-{profile}.yml
name:order  profile:环境  label：分支(branch)

basedir:本地的配置文件的地址

于order项目里面加入clien依赖：