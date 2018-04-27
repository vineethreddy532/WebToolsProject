<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>eCommerce Application</title>
  </head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <li class="active"><a href="shopOwner.htm">Home</a></li>
      <li><a href="logout.htm">Logout</a></li>
    </ul>
  </div>
</nav>
<div class="container">
<table class="table-bordered">
<c:forEach items = "${userList}" var = "prod">
         <tr>
         <td>"${prod.userEmail}"</td>
         <td>"${prod.id}"</td>
         </tr>
      </c:forEach>
</table>
<form action="viewConfirmedProd.htm" method="POST">
<select name="productList">
<c:forEach items = "${userList}" var = "prod">
        <option value="${prod.userEmail}">${prod.userEmail}</option>
      </c:forEach>
     </select>
     <input type="submit" value="Select User">
</form>
</div>
</body>
</html>