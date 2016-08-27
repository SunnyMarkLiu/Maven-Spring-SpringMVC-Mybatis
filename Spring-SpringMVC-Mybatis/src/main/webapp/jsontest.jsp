<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Json 交互测试</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.3.js"></script>
    <script type="text/javascript">
        function requestJsonAndResponseJson() {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/json/requestJsonAndResponseJson',
                data: '{"id":1,"name":"NikeShoes","price":200}',
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    var jsonStr = JSON.stringify(data);
                    alert(jsonStr);
                },
                error: function (errorInfo) {
                    alert(errorInfo.toString());
                }
            });
        }
        function requestKeyValueAndResponseJson() {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/json/requestKeyValueAndResponseJson',
                data: 'id=1&name=HongshuangxiPingPong2&price=400',
                success: function (data) {
                    var jsonStr = JSON.stringify(data);
                    alert(jsonStr);
                },
                error: function (errorInfo) {
                    alert(errorInfo.toString());
                }
            });
        }
    </script>
</head>
<body>
<button onclick="requestJsonAndResponseJson()">json 请求 json 响应</button>
<button onclick="requestKeyValueAndResponseJson()">key/value 请求 json 响应</button>
</body>
</html>
