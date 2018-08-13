<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


	<c:out value = "random gibberish" />

	<c:forEach var = "user" items = "${profile}">
		<c:out value = "${user.userName}"/>
		<c:out value = "${user.bio}"/>
		<c:out value = "${user.firstName} ${user.lastName}"/>
	</c:forEach>





<c:import url="/WEB-INF/jsp/footer.jsp" />
