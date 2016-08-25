<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>

</head>
<body>
<jsp:useBean id="itemsCustom" scope="request" type="com.markliu.ssm.po.ItemsCustom"/>

<form id="itemForm" action="${pageContext.request.contextPath }/items/update_items"
      enctype="multipart/form-data" method="post">
    <input type="hidden" name="id" value="${itemsCustom.id }"/>
    修改商品信息：
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" value="${itemsCustom.name }"/></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="price" value="${itemsCustom.price }"/></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <input type="file" name="pictureMultipartFile" value="上传图片"/>
            </td>

        </tr>
        <tr>
            <td>生产日期</td>
            <td><input type="text" name="createtime" value="<fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
        </tr>
        <tr>
            <td>商品简介</td>
            <td>
                <textarea rows="3" cols="30" name="detail">${itemsCustom.detail }</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>

</form>
</body>

</html>