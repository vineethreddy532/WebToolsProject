<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter Book Details</h1>
        <form action="afteraddproducts.htm" method="post">
        <table name="productTable" border="1px solid">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Product Location</th>
            </tr>
            <c:forEach begin="1" end="${requestScope.noOfProducts}" var="i">
                    <tr name="product${i}">
                        <td><input name="productName${i}" type="text" required="true"></td>
                        <td><input name="price${i}" type="text" required="true"></td>
                        <td><input name="prodLocation${i}" type="text" required="true"></td>
                    </tr> 
                </c:forEach>
        </table>
             <br> <br>
                    <input type="submit" value="Add Products">      
        </form>
</body>
</html>