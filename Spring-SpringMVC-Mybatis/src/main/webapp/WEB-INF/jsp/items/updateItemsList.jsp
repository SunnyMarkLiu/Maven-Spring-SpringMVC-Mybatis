<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items List</title>
    <script type="text/javascript">
        function queryItems() {
            document.itemsForm.action = "${pageContext.request.contextPath}/items/query_items";
            document.itemsForm.submit();
        }
        function batch_update_items() {
            document.itemsForm.action = "${pageContext.request.contextPath}/items/batch_update_items";
            document.itemsForm.submit();
        }
    </script>
</head>
<body>
<jsp:useBean id="itemsCustomList" scope="request" type="java.util.List"/>
<h1>Items List</h1>
<form name="itemsForm" method="post">
    <table width="100%" border=1>
        <thead>
        <tr>
            <td colspan="4"><input name="itemsCustom.name" type="text"/></td>
            <td><input type="button" value="查询" onclick="queryItems()"/></td>
            <td><input type="button" value="批量更新" onclick="batch_update_items()"/></td>
        </tr>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>picture</td>
            <td>createtime</td>
            <td>detail</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${itemsCustomList }" var="itemsCustom" varStatus="status">
            <tr>
                <td><input type="text" name="itemsCustoms[${status.index}].id" value="${itemsCustom.id}"></td>
                <td><input type="text" name="itemsCustoms[${status.index}].name" value="${itemsCustom.name}"></td>
                <td><input type="text" name="itemsCustoms[${status.index}].price" value="${itemsCustom.price}"></td>
                <td><input type="text" name="itemsCustoms[${status.index}].picture" value="${itemsCustom.picture}"></td>
                <td><input type="text" name="itemsCustoms[${status.index}].createtime" value="<fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
                <td><input type="text" name="itemsCustoms[${status.index}].detail" value="${itemsCustom.detail}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
