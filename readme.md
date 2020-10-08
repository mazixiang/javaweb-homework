# javaweb-homework

存放老师课堂上布置的作业的源代码，会根据作业提交新的源代码

demo 在 [mazixiang.me/tomcat](https://mazixiang.me/tomcat)

## 2020.10.8

添加删除学生信息的功能：在 queryAll 页面对应的学生行后面点击删除即可

## 2020.10.2

通过访问 `queryAll` 查询并输出所有学生信息

## 2020.9.26

可以添加数据到数据库中：新建 `src/main/webapp/META-INF/config.json` 文件，示例如下

```json
{
  "db": {
    "db_host": "127.0.0.1",
    "db_port": "3306",
    "db_name": "YOUR_DB_NAME",
    "db_username": "YOUR_USERNAME",
    "db_password": "YOUR_PASSWORD"
  }
}
```

db_host 是数据库的 IP 地址，一般是本地；db_port 是数据库运行的端口，一般是3306；db_name 是数据库名称；
db_username 是登陆数据库的用户名称；db_password 是用户名称对应的密码

在数据库中，新建一张拥有 `id, name, password, age, gender, hobbies, school` 的表，只有 `age` 属性是 `int` 类型，其他均为 `varchar` 类型。

其中列名称可变，修改 `src/main/java/me/mazixiang/servlet/formdb/StudentServlet.java` 中的 SQL 语句即可。

## 2020.9.14

处理表单：进入 `student.html` 填写表单，提交给 `/s`，输出服务器对表单信息的响应

## 2020.9.11

基本的 Hello World：正确配置服务器后，对 `/hello` 请求进行相应，在浏览器上输出一行  `Hello World！`