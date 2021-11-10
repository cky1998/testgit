<%--
  Created by IntelliJ IDEA.
  User: 49328
  Date: 2021/11/5
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>name</h4>
    <%=request.getAttribute("user")%>
    <h4>----------</h4>
    ${ user.uname }
</body>
</html>
