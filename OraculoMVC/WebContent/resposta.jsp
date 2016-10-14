<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resposta dos melhores produtos</title>
</head>
<body>
	<h1>Os melhores do tipo # ${param.produto } # s�o:</h1>
	<ul>
		<c:forEach var="item" items="${produtos }">
			<li>${item }</li>
		</c:forEach>
	</ul>
</body>
</html>