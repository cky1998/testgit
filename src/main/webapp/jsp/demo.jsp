<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Title</title>
	</head>
	<body>
		<%
    pageContext.setAttribute("name", "page");
    request.setAttribute("name", "request");
    session.setAttribute("name", "session");
    application.setAttribute("name", "application");
%>
		<%=pageContext.getAttribute("name")%>
		<%=request.getAttribute("name")%>
		<%=session.getAttribute("name")%>
		<%=application.getAttribute("name")%>
		${user}
		<%=request.getAttribute("user.username")%>
		${user.username}
		${user.pwd}
	</body>
</html>
