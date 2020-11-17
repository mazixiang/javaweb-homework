package me.mazixiang.servlet.export;

import me.mazixiang.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class ExportServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/queryAll").include(req, resp);
        List<Student> studentList = (List<Student>) req.getAttribute("studentList");

        String exportFileName = "allStudents.csv";

        resp.setContentType(getServletContext().getMimeType(exportFileName));
        resp.setHeader("Content-Disposition", "attachment;filename=" + exportFileName);

        OutputStream out = resp.getOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println("id,name,pass,age,gender,hobbies,school");

        for (Student student : studentList) {
            printStream.println(student.toCsvString());
        }
        printStream.close();
        out.close();

    }
}
