<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Customer Id : ${customerObject.customerId}</h1>
	<h1>Customer Name : ${customerObject.customerName}</h1> 
	
	<a href="<c:url value="/customerManagerApp/home" />">
		<span >Back To List</span>
	</a>										 
</body>
</html>