<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="shop-owner-init.htm" method="POST">
<table>
<c:forEach items = "${prodList}" var = "prod">
         <tr>
         <td>"${prod.productName}"</td>
         <td>"${prod.price}"</td>
         <td>"${prod.prodLocation}"</td>
         </tr>
      </c:forEach>
      <tr>
      <td><input type="submit" value="Ship Products"></td>
      </tr>
</table>
</form>
</body>
</html>