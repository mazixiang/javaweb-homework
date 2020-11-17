package me.mazixiang.servlet.modify;

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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ModifyShowServlet extends HttpServlet {

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
        String stuid = req.getParameter("stuid");

        StudentDao dao = new StudentDaoImpl(dbConfigString);

        try {
            Student student = dao.queryById(stuid);

            String checkedString = "checked=\"checked\"";
            String selectedString = "selected=\"selected\"";

            resp.setHeader("content-type", "text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String maleCheckboxAttr = student.getStuGender().equals("male") ? checkedString : "";
            String femaleCheckboxAttr = student.getStuGender().equals("female") ? checkedString : "";

            String school = student.getStuSchool();

            String optionString = "            <option value=\"机械动力工程学院\" " + (school.equals("机械动力工程学院") ? selectedString : "") + ">机械动力工程学院</option>\n" +
                    "            <option value=\"计算机科学与技术学院\" " + (school.equals("计算机科学与技术学院") ? selectedString : "") + ">计算机科学与技术学院</option>\n" +
                    "            <option value=\"材料科学与工程学院\" " + (school.equals("材料科学与工程学院") ? selectedString : "") + ">材料科学与工程学院</option>\n" +
                    "            <option value=\"电气与电子工程学院\" " + (school.equals("电气与电子工程学院") ? selectedString : "") + ">电气与电子工程学院</option>\n" +
                    "            <option value=\"自动化学院\" " + (school.equals("自动化学院") ? selectedString : "") + ">自动化学院</option>\n" +
                    "            <option value=\"测控技术与通信工程学院\" " + (school.equals("测控技术与通信工程学院") ? selectedString : "") + ">测控技术与通信工程学院</option>\n" +
                    "            <option value=\"化学与环境工程学院\" " + (school.equals("化学与环境工程学院") ? selectedString : "") + ">化学与环境工程学院</option>\n" +
                    "            <option value=\"软件与微电子学院\" " + (school.equals("软件与微电子学院") ? selectedString : "") + ">软件与微电子学院</option>\n" +
                    "            <option value=\"建筑工程学院\" " + (school.equals("建筑工程学院") ? selectedString : "") + ">建筑工程学院</option>\n" +
                    "            <option value=\"理学院\" " + (school.equals("理学院") ? selectedString : "") + ">理学院</option>\n";

            Map<String, Boolean> hobbiesMap = new HashMap<>();
            String[] hobbies = student.getStuHobbies().split("，");
            for (String i : hobbies) {
                hobbiesMap.put(i, true);
            }

            PrintWriter writer = resp.getWriter();
            String out = "<form name=\"form\" action=\"update\" method=\"post\" onsubmit=\"return validateForm()\">\n" +
                    "    <fieldset>\n" +
                    "<input type=\"hidden\" id=\"id\" name=\"stuid\" value=\"" + student.getStuId() + "\">" +
                    "        <div class=\"form-grid\">\n" +
                    "            <label for=\"name\">姓名：</label><input type=\"text\" id=\"name\" name=\"stuname\"  value=\"" + student.getStuName() + "\"><br>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"form-grid\">\n" +
                    "            <label for=\"password\">密码：</label><input type=\"password\" id=\"password\" name=\"stupass\"  value=\"" + student.getStuPass() + "\"><br>\n" +
                    "        </div>\n" +
                    "\n" +
                    "<div class=\"form-grid\">\n" +
                    "            <label for=\"age\">年龄：</label><input type=\"text\" id=\"age\" name=\"stuage\" value=\"" + student.getStuAge() + "\"\n" +
                    "                                               oninput=\"value=value.replace(/[^\\d]/g, '')\"><br>\n" +
                    "        </div>" +
                    "\n" +
                    "        <div class=\"form-grid\">\n" +
                    "            性别：\n" +
                    "            <input id=\"gender-male\" type=\"radio\" name=\"stugender\" " + maleCheckboxAttr + " value=\"male\"><label\n" +
                    "                for=\"gender-male\">男</label>\n" +
                    "            <input id=\"gender-female\" type=\"radio\" name=\"stugender\" " + femaleCheckboxAttr + " value=\"female\"><label for=\"gender-female\">女</label>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"form-grid\">\n" +
                    "            兴趣：\n" +
                    "            <input id=\"hobby-basketball\" type=\"checkbox\" name=\"stuhobbies\" value=\"篮球\"" + (hobbiesMap.getOrDefault("篮球", false) ? checkedString : "") + "><label\n" +
                    "                for=\"hobby-basketball\">篮球</label>\n" +
                    "            <input id=\"hobby-football\" type=\"checkbox\" name=\"stuhobbies\" value=\"足球\"" + (hobbiesMap.getOrDefault("足球", false) ? checkedString : "") + "><label\n" +
                    "                for=\"hobby-football\">足球</label>\n" +
                    "            <input id=\"hobby-table-tennis\" type=\"checkbox\" name=\"stuhobbies\" value=\"乒乓球\"" + (hobbiesMap.getOrDefault("乒乓球", false) ? checkedString : "") + "><label\n" +
                    "                for=\"hobby-table-tennis\">乒乓球</label>\n" +
                    "            <input id=\"hobby-movies\" type=\"checkbox\" name=\"stuhobbies\" value=\"电影\" " + (hobbiesMap.getOrDefault("电影", false) ? checkedString : "") + "><label\n" +
                    "                for=\"hobby-movies\">电影</label>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"form-grid\">\n" +
                    "            <label for=\"school\">学院：</label> <select id=\"school\" name=\"stuschool\">\n" +
                    optionString +
                    "        </select>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"form-grid\">\n" +
                    "            <input id=\"submit-btn\" type=\"submit\" value=\"更新\">\n" +
                    "        </div>";
            writer.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
