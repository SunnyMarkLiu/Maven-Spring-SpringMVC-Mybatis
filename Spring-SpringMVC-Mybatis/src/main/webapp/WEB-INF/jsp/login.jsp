<%--
  Created by IntelliJ IDEA.
  User: markliu
  Date: 16-8-27
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    username:<input name="username" type="text" />
    password:<input name="password" type="password" />
    <input type="submit" value="login">
</form>
</body>
</html>
