# javaweb-practice

存放老师课堂内容的**同步练习**

server 分支的 demo 在 [mazixiang.me/tomcat](https://mazixiang.me/tomcat)，删除了数据库相关操作

## 2020.11.17

添加文件下载的功能，链接是 `/listFiles.jsp`

从服务器上的文件下载到本地

对应的 Servlet：[`src/main/java/me/mazixiang/servlet/file/DownloadServlet.java`](src/main/java/me/mazixiang/servlet/file/DownloadServlet.java)

生成文件，导出所有学生的数据，链接是 `/export`，或者点击 `/listAllJSP.jsp` 表格下方的 `export` 链接来导出数据

对应的 Servlet：[`src/main/java/me/mazixiang/servlet/export/ExportServlet.java`](src/main/java/me/mazixiang/servlet/export/ExportServlet.java)

## 2020.11.11

添加文件上传的功能，链接是 `/file`

对应的 Servlet：[`src/main/java/me/mazixiang/servlet/file/FileServlet`](src/main/java/me/mazixiang/servlet/file/FileServlet.java)

由于新建了文件表，需要在数据库中新建 `file` 表，确保有如下字段

- `id varchar(255)`：文件的唯一 id
- `originalFileName varchar(255)`：原始的文件名
- `filePath varchar(255)`：在服务器上文件的存储路径

## 2020.11.4

添加了新的 JSP 页面来渲染表格

JSP 页面：[`src/main/webapp/listAllJSP.jsp`](src/main/webapp/listAllJSP.jsp)

## 2020.10.20

添加 Servlet 交互方式，删除学生信息后，自动跳转回 `listAll`

分离了显示所有学生信息的 Servlet：

视图对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/queryall/ListAllServlet.java`](src/main/java/me/mazixiang/servlet/queryall/ListAllServlet.java)

数据库操作对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/queryall/QueryAllServlet.java`](src/main/java/me/mazixiang/servlet/queryall/QueryAllServlet.java)

## 2020.10.13

添加修改学生信息的功能：在 `/queryAll` 页面对应的学生信息后面点击修改

对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/modify/ModifyShowServlet.java`](src/main/java/me/mazixiang/servlet/modify/ModifyShowServlet.java)

## 2020.10.8

添加删除学生信息的功能：在 `/queryAll` 页面对应的学生信息后面点击删除

对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/queryall/QueryAllServlet.java`](src/main/java/me/mazixiang/servlet/queryall/QueryAllServlet.java)

## 2020.10.2

通过访问 `/queryAll` 查询并输出所有学生信息

对应的 Servlet 类：[`me.mazixiang.servlet.queryall.QueryAllServlet`](src/main/java/me/mazixiang/servlet/queryall/QueryAllServlet)

## 2020.9.26

可以添加数据到数据库中：新建 `src/main/webapp/META-INF/config.json` 文件，示例如下

对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/formdb/StudentServlet.java`](src/main/java/me/mazixiang/servlet/formdb/StudentServlet.java)

对应的前端页面：[`src/main/webapp/student-db.html`](src/main/webapp/student-db.html)

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

`db_host` 是数据库的 IP 地址，一般是本地（`127.0.0.1` 或 `localhost`）；`db_port` 是数据库运行的端口，一般是 3306；`db_name` 是数据库名称；
`db_username` 是登陆数据库的用户名称；`db_password` 是用户名称对应的密码

在数据库中，新建一张拥有 `id, name, password, age, gender, hobbies, school` 的表，只有 `age` 属性是 `int` 类型，其他均为 `varchar` 类型。

其中列名称可变，修改 `src/main/java/me/mazixiang/servlet/formdb/StudentServlet.java` 中的 SQL 语句即可。

## 2020.9.14

处理表单：进入 `/student.html` 填写表单，提交给 `/s`，输出服务器对表单信息的响应

对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/form/StudentServlet.java`](src/main/java/me/mazixiang/servlet/form/StudentServlet.java)

对应的前端页面：[`src/main/webapp/student-db.html`](src/main/webapp/student-db.html)

## 2020.9.11

基本的 Hello World：正确配置服务器后，对 `/hello` 请求进行相应，在浏览器上输出一行  `Hello World！`

对应的 Servlet 类：[`src/main/java/me/mazixiang/servlet/hello/Hello.java`](src/main/java/me/mazixiang/servlet/hello/Hello.java)
