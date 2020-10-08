package me.mazixiang.servlet.form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String stuname = req.getParameter("stuname");

        String stupass = req.getParameter("stupass");

        String stuage = req.getParameter("stuage");

        String stugender = req.getParameter("stugender");

        String[] hobbies = req.getParameterValues("stuhobbies");

        String stuschool = req.getParameter("stuschool");

        StringBuilder text = new StringBuilder();

        text.append("你好，").append(stuname).append(", 你成功提交了你的表单！你填入了 ").append(stupass).append(" 作为密码，你已经 ")
                .append(stuage).append(" 岁了。你是一名").append(stugender.equals("male") ? "男" : "女").append("生。");

        if (hobbies != null && hobbies.length > 0) {
            text.append("你喜欢");
            for (int i = 0; i < hobbies.length; i++) {
                if (i != 0) {
                    text.append("、");
                }
                text.append(hobbies[i]);
            }
        } else {
            text.append("你没有选择爱好");
        }

        text.append("。你目前在").append(stuschool).append("学习。");
        String html = "<div>" + text.toString() + "</div>";
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(html);
    }
}
