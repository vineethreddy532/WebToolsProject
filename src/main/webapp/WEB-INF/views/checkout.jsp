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
	return false;
}
$(document).ready(function() {
	$("#checkoutButton")
});
function callAjax(op) {
	$.ajax({
	     type: "POST",
	     url: "removeCart.htm",
	     data: { name: op[0], price:op[1], location: op[2] }
	})
}
function myFunction2() {
	document.getElementById("myForm").method = "POST";
    document.getElementById("myForm").action = "submitProduct.htm";
}
</script>
<body>
<form id="myForm">
<table>
<c:forEach items = "${checkedList}" var = "prod">
         <tr>
         <td>"${prod.productName}"</td>
         <td>"${prod.price}"</td>
         <td>"${prod.prodLocation}"</td>
         <td><button id="cartButton" onclick="myFunction(this)">Remove from Cart</button></td>
         </tr>
      </c:forEach>
      <td><button onclick="myFunction2()">Checkout</button></td>
</table>
</form>
</body>
</html>