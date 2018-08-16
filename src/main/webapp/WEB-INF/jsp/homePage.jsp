<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:out value="This is the homepage, thanks for visiting!" />

<c:url var="formAction" value="/users/homePage" />
<form action = "${formAction}" method = "POST" >
</form>


<c:import url="/WEB-INF/jsp/footer.jsp" />