package me.mazixiang.servlet.queryall;

import cn.hutool.core.io.file.FileReader;
import me.mazixiang.dao.StudentDao;
import me.mazixiang.dao.StudentDaoImpl;
import me.mazixiang.vo.Student;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryAllServlet extends HttpServlet {

    private String dbConfigString;

    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        String configFilePath = this.getServletContext().getRealPath(application.getInitParameter("ConfigFile"));
        FileReader fileReader = new FileReader(configFilePath);
        dbConfigString = fileReader.readString();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDaoImpl studentDao = new StudentDaoImpl(dbConfigString);
        try {
            List<Student> studentList = studentDao.queryAll();
            resp.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            resp.setCharacterEncoding("UTF-8");
            // 注意：resp.setHeader("content-type", "text/html;charset=UTF-8"); 放在这里是没有效果的！
            writer.println(
                    "<table border=\"1\" title=\"示例表格\">\n" +
                            "  <caption>示例表格</caption>\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th scope=\"col\">ID</th>\n" +
                            "      <th scope=\"col\">姓名</th>\n" +
                            "      <th scope=\"col\">密码</th>\n" +
                            "      <th scope=\"col\">年龄</th>\n" +
                            "      <th scope=\"col\">性别</th>\n" +
                            "      <th scope=\"col\">爱好</th>\n" +
                            "      <th scope=\"col\">学院</th>\n" +
                            "      <th scope=\"col\">操作</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tobdy>");
            for (Student student : studentList) {
                writer.println(
                        "<tr>" +
                                "<td>" + student.getStuId() + "</td>" +
                                "<td>" + student.getStuName() + "</td>" +
                                "<td>" + student.getStuPass() + "</td>" +
                                "<td>" + student.getStuAge() + "</td>" +
                                "<td>" + student.getStuGender() + "</td>" +
                                "<td>" + student.getStuHobbies() + "</td>" +
                                "<td>" + student.getStuSchool() + "</td>" +
                                "<td><a href=\"modify?stuid=" + student.getStuId() + "\">修改</a> <a href=\"delete?stuid=" + student.getStuId() + "\">删除</a></td>" +
                                "</tr>");
            }

            writer.println("</tobdy>\n" +
                    "</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
