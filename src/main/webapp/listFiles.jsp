<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Files</title>
</head>
<body>
<jsp:include page="/queryAllFiles"></jsp:include>
<h1>Files</h1>
<ul>
    <c:forEach items="${fileModelList}" var="fileModel">
        <li>${fileModel.originalFileName} <a href="download?fileId=${fileModel.id}">download</a></li>
    </c:forEach>
</ul>
</body>
</html>
