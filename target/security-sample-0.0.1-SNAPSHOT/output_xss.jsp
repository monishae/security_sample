<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>XSSFilter</title>
</head>
<body>
    <h1>XSSFilter Output</h1>

	<p><strong>Hello</strong> <%= request.getParameter("name") %></p>
</body>
</html>