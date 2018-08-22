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

<c:url var="formUpdate"
	value="/users/${currentUser.userName}/updateSubject" />
<c:url var="formAction" value="/users/${currentUser.userName}" />
<c:url var="form" value="/uploadFile" />
<c:url var="formA" value="/users/${currentUser.userName}/createSubject" />

<section class="myContainer">
	<div class="container-fluid"
		style="margin-right: 30px; margin-left: 30px">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
		<div class="row button">
			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Subject: </b></a>
				</p>

			</div>
			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Profile Picture: </b></a>
				</p>

			</div>
			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>User Name: </b></a>
				</p>

			</div>
			<div class="col-md-2" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Email: </b></a>
				</p>
			</div>
			<div class="col-md-4" id="classContent">
				<p>
					<a class="classInfoLabels"> <b>Panda Rating: </b></a>
				</p>
			</div>
		</div>
		<c:forEach items="${subject}" var="lesson">
			<div class="row subjectRow">
				<div class="col-md-2" id="classContent">
					<p>
						<c:out value="${lesson.subjectName}" />
					</p>

				</div>
				<div class="col-md-2" id="classContent">
					<div id="profilePicThumb">
						<c:url var="imgUrl" value="/image/${profile.profileImage}" />
						<img src="${imgUrl}" />
						<c:if test="${not empty message}">
  						  ${message} 
					</c:if>
					</div>
				</div>
				<div class="col-md-2" id="classContent">
					<p>
						<c:out value="${profile.userName}" />
					</p>

				</div>
				<div class="col-md-2" id="classContent">
					<p>
						<fmt:formatDate value="${profile.email}" pattern="MM-dd-yyyy" />
					</p>
				</div>
				<div class="col-md-2" id="classContent">
					<div class="pandaFaces">
						<c:forEach begin="1" end="${profile.rating}">
							<img class="panda" src="../img/rating.png" width="6%">
						</c:forEach>
						<a>&emsp;(out of 5)</a>
					</div>
				</div>
				<div class="col-md-2" id="classContent">
					<input class="formSubmitButton" type="submit" value="Profile Page">
				</div>
			</div>
		</c:forEach>
		<div class="row">
			<div class="col-md-5"></div>
			<div class="col-md-7"></div>
		</div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
