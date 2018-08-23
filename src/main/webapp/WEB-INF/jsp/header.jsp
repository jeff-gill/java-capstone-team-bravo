<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Sensei</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
<script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
<script src="/js/validation.js"></script>

<link rel="stylesheet" href="../css/site.css">

<script type="text/javascript">
	$(document).ready(function() {
		$("time.timeago").timeago();

		$("#logoutLink").click(function(event) {
			$("#logoutForm").submit();
		});

		var pathname = window.location.pathname;
		$("nav a[href='" + pathname + "']").parent().addClass("active");

	});
</script>


</head>
<body>
	<header class="header">
		<c:url var="homePageHref" value="/" />
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2 senseiPic" id="senseiPic">
					<img src="../img/SenseiMaster.jpg">
				</div>
				<div class="col-md-4" id="senseiTitle">
					<h1>Sensei</h1>
				</div>
				<div class="col-md-1"></div>
				<c:url var="searchPage" value="/users/search" >
					<c:param name="userName">${userProfile.userName }</c:param>
					<%-- <c:param name="subjectName">${subject.subjectName }</c:param> --%>
				</c:url>
				<form action = "${searchPage}" method="GET">
				<div class="col-md-5" id="mottoContainer">
				
				

					<h1 id="sensaiMotto">wonder. learn. connect. teach. repeat.</h1>
					<div id="navSearchBar">
						<input type="text" name="userName" id="navSearchText"
							placeholder="Search for sensei..." /> <input
							class="formSubmitButton" type="submit" value="userName" />
					</div>
					<div id="navSearchBar">
						<input type="text" name="subjectName" id="navSearchText"
							placeholder="Search for subject..." /> <input
							class="formSubmitButton" type="submit" value="subjectName" />
					</div>
				</div>
				</form>
			</div>
		</div>


		<a href="${homePageHref}"><img src="${imgSrc}"
			class="img-responsive" /></a>
	</header>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty currentUser}">
						<c:url var="loginHref" value="/login" />
						<li><a href="${loginHref}">Log Out</a></li>
					</c:when>
					<c:otherwise>
						<c:url var="logoutAction" value="/logout" />
						<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
						</form>
						<li><a id="logoutLink" href="${homePageHref}">Log Out</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<li>
				<a id="logoutLink" href="../users/${currentUser.userName}">Profile Page</a>
			</li>
			</ul>
			
		</div>
	</nav>