<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>success page</title>
</head>
<body>
	<h5>login was successful</h5>
	<p>
		name:${requestScope.user.username}<br />
		password:${requestScope.user.password}<br />
		age:${requestScope.user.age}<br />
	</p>
</body>
</html>