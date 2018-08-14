<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:out value="Username: ${profile.userName}"/> <br/>
	<c:out value="Bio: ${profile.bio}"/><br/>
	<c:out value="Name: ${profile.firstName} ${profile.lastName}"/>

<c:import url="/WEB-INF/jsp/footer.jsp" />