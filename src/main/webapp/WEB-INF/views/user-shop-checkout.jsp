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
<table>
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
</body>
</html>