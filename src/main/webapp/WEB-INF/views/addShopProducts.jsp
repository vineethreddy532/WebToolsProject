<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Number of Products</title>
</head>
<body>
<h1>How many products do you want to add</h1>
        <form action="sproducts.htm" method="post">
            <input type="number" name="noOfBooks" min="1" required="true">
            <input type="submit" value="Submit">
      </form>
</body>
</html>