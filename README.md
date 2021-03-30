
## 1. 项目特性

本项目代码仅限于得帆在江淮汽车的poc中使用，为该poc定制化开发，项目特性如下：
* token、auth_code等存储都基于内存。为了poc简易性，不依托任何数据库，不实现分布式拓展
* 用户的账号密码都存储在AD中，因此集成了LDAP认证

## 2. 演示地址

### 2.1. 授权码模式
地址：http://localhost:8081/oauth/authorize?response_type=code&client_id=baidu_client&redirect_uri=https://www.baidu.com

### 2.2. 隐藏模式
地址：http://localhost:8081/oauth/authorize?response_type=token&client_id=baidu_client&redirect_uri=https://www.baidu.com

### 2.3. 解析token
地址（get）：http://localhost:8081/uaa/parseJwt 
请求头：Authorization ：Bearer access_token

### 2.4. 注销
地址：http://localhost:8081/logout