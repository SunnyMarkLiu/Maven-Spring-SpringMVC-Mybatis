<%--
  Created by IntelliJ IDEA.
  User: markliu
  Date: 16-7-25
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input Product</title>
</head>
<body>
    <h2>Input Product</h2>
    <form action="product_save.action" method="post">
        <label for="name">Name:</label>
        <input name="name" id="name", type="text">
        <label>Price:</label>
        <input name="price" id="price", type="text">
        <input type="submit" value="Add Product">
    </form>
</body>
</html>
