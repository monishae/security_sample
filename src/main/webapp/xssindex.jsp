<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Ch07_XSSFilter</title>
</head>
<body>
	<h1>XSSFilter</h1>

    <h2>Blacklist Filter</h2>

    <form name="blacklist" method="post" action="output_xss.jsp">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" class="text-input"></td>
                <td><input type="submit" value="Submit" class="send-button"></td>
            </tr>
        </table>
    </form>
</body>
</html>
