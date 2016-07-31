<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload file</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css" />
</head>
<body>
    <h2>upload file</h2>
    <form action="<%=request.getContextPath()%>/file/do_upload_file" method="post" enctype="multipart/form-data">
        <input type="file" name="upload_file">
        <input type="submit" value="上传">
    </form>
</body>
</html>
