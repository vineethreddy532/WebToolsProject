<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1>Enter Product Details</h1>
        <form action="afteraddproducts.htm" method="post">
        <div class="form-group">
        <table class="table-hover" name="productTable" border="1px solid">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Product Location</th>
            </tr>
            <c:forEach begin="1" end="${requestScope.noOfProducts}" var="i">
                    <tr name="product${i}">
                        <td><input name="productName${i}" type="text" required="true"></td>
                        <td><input name="price${i}" type="number" step="0.0001" min="1" required="true"></td>
                        <td><input name="prodLocation${i}" type="text" required="true"></td>
                    </tr> 
                </c:forEach>
        </table>
             <br> <br>
             <input type="hidden" name="noOfProducts" value="${noOfProducts}">
                    <input type="submit" value="Add Products">      
                    </div>
        </form>
        </div>
</body>
</html>