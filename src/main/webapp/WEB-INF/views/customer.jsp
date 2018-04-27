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
$(document).ready(function() {
	$("#cartButton").click(function(event){
		var $row = $(this).closest("tr");  
	    $tds = $row.find("td");             
	var op = [];
	var i = 0;
	$.each($tds, function() {      
		op[i]=$(this).text();
		i++;
	});
		$($(this).closest("tr")).remove();
		event.preventDefault();
		callAjax(op);
	});
	var cart = $("#cartItems").val();
	$("#cartItems").val(cart +1);
});
function callAjax(op) {
	$.ajax({
	     type: "POST",
	     url: "cartChange.htm",
	     data: { name: op[0], price:op[1], location: op[2] }
	})
}
</script>
<body>
<h1>Cart Items<input type="text" id="cartItems" value="${checkedProd}" disabled></h1>
<form action="${contextPath}/checkout.htm" method="POST">
<table>
<c:forEach items = "${prodList}" var = "prod">
         <tr>
         <td>"${prod.productName}"</td>
         <td>"${prod.price}"</td>
         <td>"${prod.prodLocation}"</td>
         <td><button id="cartButton">Add to Cart</button></td>
         </tr>
      </c:forEach>
</table>
</form>
</body>
</html>