package me.mazixiang.servlet.queryall;

import cn.hutool.core.io.file.FileReader;
import me.mazixiang.dao.student.StudentDao;
import me.mazixiang.dao.student.StudentDaoImpl;
import me.mazixiang.vo.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        StudentDao studentDao = new StudentDaoImpl(dbConfigString);
        try {
            List<Student> studentList = studentDao.queryAll();
            req.setAttribute("studentList", studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // forward 方式，如果在 ListAllServlet 使用 include 作为交互方式请注释掉，否则会无限循环
        // req.getRequestDispatcher("/listAll").forward(req, resp);
    }
}
