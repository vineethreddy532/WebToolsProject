<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function myFunction(e){
	var $row = $(e).closest("tr");  
    $tds = $row.find("td");             
var op = [];
var i = 0;
$.each($tds, function() {      
	op[i]=$(this).text();
	i++;
});
	$($(e).closest("tr")).remove();
	event.preventDefault();
	callAjax(op);
	var cart = $("#cartItems").val();
	$("#cartItems").val(parseInt(cart) +1);
	return false;
}
$(document).ready(function() {
	$("#checkoutButton")
});
function callAjax(op) {
	$.ajax({
	     type: "POST",
	     url: "cartChange.htm",
	     data: { name: op[0], price:op[1], location: op[2] }
	})
}
function myFunction2() {
	document.getElementById("myForm").method = "POST";
    document.getElementById("myForm").action = "checkout.htm";
}
</script>
<body>
<h1>Cart Items<input type="text" id="cartItems" value="${checkedProd}" disabled></h1>
  <form id="myForm">
<table>
<c:forEach items = "${prodList}" var = "prod">
         <tr>
         <td>"${prod.productName}"</td>
         <td>"${prod.price}"</td>
         <td>"${prod.prodLocation}"</td>
         <td><button id="cartButton" onclick="myFunction(this)">Add to Cart</button></td>
         </tr>
      </c:forEach>
      <tr>
      <td><button onclick="myFunction2()">Checkout</button></td>
      </tr>
</table>
</form>
</body>
</html>