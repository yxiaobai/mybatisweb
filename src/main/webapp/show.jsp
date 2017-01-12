<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>显示学生信息</title>
</head>
<body>
<c:forEach items="${st}" var="s">
    ${s.name}<br>
</c:forEach>
</body>
</html>
