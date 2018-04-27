<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Login page</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
    <div class="navbar-header">
      <h1><a class="navbar-brand">Welcome to Ecommerce Application</a></h1>
    </div>
    </ul>
  </div>
</nav>
<div class="container">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/login.htm" method="POST">
	<div class="form-group">
		<table class="table-bordered">
		<tr>
		    <td>Email:</td>
		    <td><input type="email" name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>
		</div>
	</form>
	<a href="${contextPath}/forgotpassword.htm">Forgot password?</a>
	<a href="${contextPath}/create.htm">Register User</a>
	</div>
</body>
</html>