
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script defer
	src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"
	integrity="sha384-4oV5EgaV02iISL2ban6c/RmotsABqE4yZxZLcYMAdG7FAPsyHYAPpywE9PJo+Khy"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:url var="formSearch" value="/users/search">
	<c:param name="subjectName">${subject}</c:param>
</c:url>
<c:url var="formSubmitButton" value="/users/${profile.userName}" />

<section class="myContainer">
	<div class="container-fluid"
		style="margin-right: 30px; margin-left: 30px">
		<div class="row searchTerm">
			<div class="col-md-12">
				<h1>
					Results for <i><b>"${param.subjectName}"</b></i> search.
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12"></div>
		</div>
		<div class="row button">
			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Profile Picture: </b></a>
				</p>

			</div>
			<div class="col-md-3" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>User Name & Email: </b></a>
				</p>

			</div>

			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Subject: </b></a>
				</p>

			</div>


			<div class="col-md-3" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Panda Rating: </b></a>
				</p>
			</div>
		</div>
		<input type="hidden" name="userName" value="${searchInfo.userName}" />
		<c:forEach items="${senseis}" var="searchInfo">
			<div class="row subjectRow">
				<div class="col-md-2" id="classContent">

					<div id="profilePicThumb">
						<c:url var="imgUrl" value="/image/${searchInfo.profileImage}" />
						<c:if test="${not empty imgUrl}">
							<img src="${imgUrl}"
								onerror="arguments[0].currentTarget.style.display='none'" />
						</c:if>
					</div>
				</div>

				<div class="col-md-3" id="classContent">
					<p>
						<c:out value="${searchInfo.userName}" />
					</p>
					<p>
						<c:out value="${searchInfo.email}" />
					</p>

				</div>

				<div class="col-md-2" id="classContent">
					<p>
						<c:out value="${searchInfo.subjectName}" />
					</p>
				</div>

				<div class="col-md-3" id="classContent">
					<div class="pandaFaces">
						<c:forEach begin="1" end="${searchInfo.rating}">
							<img class="panda" src="../img/rating.png" width="6%">
						</c:forEach>
						<a>&emsp;(out of 5)</a>
					</div>
				</div>
				<div class="col-md-1 btn-group" id="classContent">
					<c:url value="/${currentUser.userName}/profile" var="reviewPageURL">
						<c:param name="userName">${searchInfo.userName}</c:param>
					</c:url>
					<a href="${reviewPageURL}"><button>Go To Profile</button>
				</div>
			</div>
		</c:forEach>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
