<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>eCommerce Application</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="active"><a href="admin.htm">Home</a></li>
			<li><a href="logout.htm">Logout</a></li>
		</ul>
	</div>
	</nav>
	<table  class="table-bordered">
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Product Location</th>
			<th>Product Status</th>
			<th>User Email</th>
		</tr>
		<c:forEach items="${checkedList}" var="prod">
			<tr>
				<td>"${prod.productName}"</td>
				<td>"${prod.price}"</td>
				<td>"${prod.prodLocation}"</td>
				<td>"${prod.prodStatus}"</td>
				<td>"${prod.user.userEmail}"</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>