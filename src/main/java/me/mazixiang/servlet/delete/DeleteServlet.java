package me.mazixiang.servlet.delete;

import cn.hutool.core.io.file.FileReader;
import me.mazixiang.dao.StudentDao;
import me.mazixiang.dao.StudentDaoImpl;
import me.mazixiang.vo.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    private String dbConfigString;

    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        String configFilePath = this.getServletContext().getRealPath(application.getInitParameter("ConfigFile"));
        FileReader fileReader = new FileReader(configFilePath);
        dbConfigString = fileReader.readString();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("stuid");
        StudentDao studentDao = new StudentDaoImpl(dbConfigString);
        Student student = new Student();
        student.setStuId(id);
        try {
            studentDao.delete(student);
            res.getWriter().println("delete complete");
        } catch (Exception e) {
            res.getWriter().println("delete failed");
            e.printStackTrace();
        }
    }
}
