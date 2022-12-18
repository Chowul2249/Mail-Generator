<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
</head>
<body>
<h1>
	Hello world!  
</h1>
<h2>${message}</h2>
<form action="register" method="post">
Email:<input type="text" id="email" name="email"><br>
<input type="submit" value="submit">

</form>
</body>
</html>
