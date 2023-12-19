<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="user" class="beans.UserBean" scope="session"/>
<jsp:setProperty property="*" name="user"/>
<body>
<%--invoke B.L method of JB --%>
<h5> Routing status : ${sessionScope.user.validateUser()}</h5>
</body>
</html>