package me.mazixiang.servlet.delete;

import cn.hutool.core.io.file.FileReader;
import me.mazixiang.dao.StudentDao;
import me.mazixiang.dao.StudentDaoImpl;
import me.mazixiang.vo.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("stuid");
        StudentDao studentDao = new StudentDaoImpl(dbConfigString);
        Student student = new Student();
        student.setStuId(id);
        resp.setCharacterEncoding("utf-8");
        try {
            studentDao.delete(student);
            resp.getWriter().println("delete complete");
        } catch (Exception e) {
            resp.getWriter().println("delete failed");
            e.printStackTrace();
        }
        // forward 方式跳转
        // req.getRequestDispatcher("/queryAll").forward(req, resp);
        // redirect 方式
        resp.sendRedirect("queryAll");
    }
}
