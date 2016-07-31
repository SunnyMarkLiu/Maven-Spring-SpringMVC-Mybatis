<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input Product</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css" />
</head>
<body>
    <h2>Input Product</h2>
    <form action="<%=request.getContextPath()%>/product/product_input?requestParam=testparam&newrequestParam=new_param" method="post">
        <label for="id">id:</label>
        <input name="id" id="id" type="text">
        <label for="name">Name:</label>
        <input name="name" id="name" type="text">
        <label>Price:</label>
        <input name="price" id="price" type="text">
        <input type="submit" value="Add Product">
    </form>
</body>
</html>
