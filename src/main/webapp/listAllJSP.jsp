<%@ page import="me.mazixiang.vo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/queryAll"></jsp:include>
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>password</td>
        <td>age</td>
        <td>gender</td>
        <td>hobby</td>
        <td>school</td>
        <td>operations</td>
    </tr>
    <%--    <%--%>
    <%--        List<Student> studentList = (List<Student>) request.getAttribute("studentList");--%>
    <%--        for (Student student : studentList) {--%>
    <%--            pageContext.setAttribute("student", student);--%>
    <%--    %>--%>

    <%--    <tr>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuId() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuId}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuName() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuName}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuPass() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuPass}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuAge() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuAge}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuGender() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuGender}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuHobbies() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuHobbies}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <%= student.getStuSchool() %>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            ${student.stuSchool}--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <a href="delete?stuid=<%= student.getStuId() %>">delete</a>--%>
    <%--            |--%>
    <%--            <a href="update?stuid=<%= student.getStuId() %>">update</a>--%>
    <%--        </td>--%>
    <%--        <td>--%>
    <%--            <a href="delete?stuid=${student.stuId}">delete</a>--%>
    <%--            |--%>
    <%--            <a href="update?stuid=${student.stuId}">update</a>--%>
    <%--        </td>--%>
    <%--    </tr>--%>
    <%--    <%--%>
    <%--        }--%>
    <%--    %>--%>

    <c:forEach items="${studentList}" var="student">
        <tr>
            <td>
                    ${student.stuId}
            </td>
            <td>
                    ${student.stuName}
            </td>
            <td>
                    ${student.stuPass}
            </td>
            <td>
                    ${student.stuAge}
            </td>
            <td>
                    ${student.stuGender}
            </td>
            <td>
                    ${student.stuHobbies}
            </td>
            <td>
                    ${student.stuSchool}
            </td>
            <td>
                <a href="delete?stuid=${student.stuId}">delete</a>
                |
                <a href="update?stuid=${student.stuId}">update</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="export">Export</a>
</body>
</html>
