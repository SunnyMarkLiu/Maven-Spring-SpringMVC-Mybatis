<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items List</title>
</head>
<body>
<jsp:useBean id="itemsCustomList" scope="request" type="java.util.List"/>
<h1>Items List</h1>
<form action="${pageContext.request.contextPath}/items/query_items" method="post">
    <table width="100%" border=1>
        <thead>
        <tr>
            <td colspan="7"><input name="itemsCustom.name" type="text"/></td>
            <td><input type="submit" value="查询"/></td>
        </tr>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>picture</td>
            <td>createtime</td>
            <td>detail</td>
            <td>操作</td>
            <td>操作2</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${itemsCustomList }" var="itemsCustom">
            <tr>
                <td>${itemsCustom.id}</td>
                <td>${itemsCustom.name}</td>
                <td>${itemsCustom.price}</td>
                <td>${itemsCustom.picture}</td>
                <td><fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${itemsCustom.detail}</td>
                <td><a href="${pageContext.request.contextPath}/items/edit_items?id=${itemsCustom.id}">修改</a></td>
                <td><a href="${pageContext.request.contextPath}/items/edit_items2?id=${itemsCustom.id}">修改2</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
