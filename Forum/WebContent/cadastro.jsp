<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usu�rio</title>
</head>
<body>

	<h1>Cadastrar novo usu�rio</h1>
	<form action="cadastrar">
		Login: <input type="text" name="login" /><br>
		Senha: <input type="password" name="senha" /><br>
		Nome:  <input type="text" name="nome" /><br>
		Email: <input type="text" name="email" /><br>
		<input type="submit" value="cadastrar" /><br>
	</form>

</body>
</html>