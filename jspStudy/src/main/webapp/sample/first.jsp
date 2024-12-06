<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>여기서 아래에 있는 맴버 변수를 쓸것.<%= declation%></h1>
<h1>여기서 아래에 있는 맴버 함수를 콜 할 수 있다.<%= getDeclation()%></h1>
<h1>연산처리</h1>
<%
//자바 연산처리(제어문,반복문 가능)
//여기에 객체: context, config, request, response, pagecontext
String message = "연산처리문입니다";//message는 서비스에 있는 지역변수다
out.println("내장객체를 이용한 출력"+message);
%>
<h1>맴버변수 선언</h1>
<%!
//맴버변수선언
String declation = "맴버함수 선언";
%>
<h1>맴버함수 선언</h1>
<%!
//맴버함수선언
public String getDeclation(){
	return declation;
}
%>
</body>
</html>