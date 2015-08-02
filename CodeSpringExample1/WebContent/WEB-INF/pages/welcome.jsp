<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="false"%>

<html>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name}
		</h2>
	</c:if>							

	<h2><a href="<c:url value="/admin" />">Click here to Admin page</a></h2>		
	<h2><a href="<c:url value="/customerManagerApp/home" />">Click here to Customer page</a></h2>
</body>
</html>