# javaweb-homework

存放老师课堂上布置的作业的源代码，会根据作业提交新的源代码

demo 在 [mazixiang.me/tomcat](https://mazixiang.me/tomcat)

## 2020.9.26

可以添加数据到数据库中：编辑 `src/main/webapp/META-INF/config.json` 文件，换为你的数据库主机 IP 地址，数据库名称，数据库用户名和对应的密码。在数据库中，新建一张拥有 `id, name, password, age, gender, hobbies, school` 的表，只有 `age` 属性是 `int` 类型，其他均为 `varchar` 类型。其中列名称可变，修改 `src/main/java/me/mazixiang/servlet/formdb/StudentServlet.java` 中的 SQL 语句即可。

## 2020.9.14

处理表单：进入 `student.html` 填写表单，提交给 `/s`，输出服务器对表单信息的响应

## 2020.9.11

基本的 Hello World：正确配置服务器后，对 `/hello` 请求进行相应，在浏览器上输出一行  `Hello World！`