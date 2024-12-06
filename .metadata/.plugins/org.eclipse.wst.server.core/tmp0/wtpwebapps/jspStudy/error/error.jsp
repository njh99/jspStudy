<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isErrorPage="true"%>
<%
response.setStatus(HttpServletResponse.SC_OK);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>요청처리 과정에서 예외가 발생되지 않고 잘 처리 되었습니다.</h1>
	<h2>에러타입 <%= exception.getClass().getName() %></h2>
	<h2>에러메세지 <%= exception.getMessage() %></h2>
</body>
</html>