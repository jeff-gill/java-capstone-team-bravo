<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script defer src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"
	integrity="sha384-4oV5EgaV02iISL2ban6c/RmotsABqE4yZxZLcYMAdG7FAPsyHYAPpywE9PJo+Khy"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:url value="/${currentUser.userName}/profile" var="reviewPageURL" >
<c:param name="userName">${userProfile.userName}</c:param>
</c:url>

<section class="myContainer">
	<div class="container-fluid senseiInfo"
		style="margin-right: 30px; margin-left: 30px">
		<div class="row">
			<div class="col-md-12">
				<h2 class="profileName">
					<c:out value="${userProfile.firstName} ${userProfile.lastName}" />
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12"></div>
		</div>
		<div class="row bioRow">
			<div class="col-md-3">
				<div id="profilePic">
					<c:url var="imgUrl" value="/image/${userProfile.profileImage}" />
					<img src="${imgUrl}" />
					<c:if test="${not empty message}">
  						  ${message} 
					</c:if>
				</div>
			</div>
			<div class="col-md-6">
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Bio Info: </b>
					</div>
					<div>
						<c:out value="${userProfile.bio}" />
					</div>
				</div>
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Email: </b>
					</div>

					<div>
						<c:out value="${userProfile.email}" />
					</div>
				</div>
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Phone #: </b>
					</div>
					<div>
						<c:out value="${userProfile.phone}" />
					</div>
				</div>
					<div class="userSections">
						<div class="userInfoLabels">
							<b>Panda Rating: </b>
						</div>
						<div class="pandaFaces">
							<c:forEach begin="1" end="${userProfile.rating}">
								<img class="panda" src="../img/rating.png" width="6%">
							</c:forEach>
							<a>&emsp;(out of 5)</a>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
		<div class="container-fluid"
			style="margin-right: 30px; margin-left: 30px">

			<div class="row">
				<div class="col-md-12"></div>
			</div>
			<div class="row button">
				<div class="col-md-3" id="classContent">
					<p>
						<a class="classInfoLabels"> <b>Subject: </b></a>
					</p>

				</div>
				<div class="col-md-3" id="classContent">
					<p>
						<a class="classInfoLabels"> <b>Location: </b></a>
					</p>
				</div>
				<div class="col-md-2" id="classContent">
					<p>
						<a class="classInfoLabels"> <b>Date: </b></a>
					</p>
				</div>
				<div class="col-md-2"></div>
			</div>
			<c:forEach items="${subject}" var="lesson">
				<div class="row subjectRow">
					<div class="col-md-3" id="classContent">
						<p>
							<c:out value="${lesson.subjectName}" />
						</p>

					</div>
					<div class="col-md-3" id="classContent">
						<p>
							<c:out value="${lesson.location}" />
						</p>
					</div>
					<div class="col-md-2" id="classContent">
						<p>
							<fmt:formatDate value="${lesson.date}" pattern="MM-dd-yyyy" />
						</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="padding: 20px"></div>

		<div class="container-fluid"
			style="margin-right: 30px; margin-left: 30px">

			<div class="row">
				<div class="col-md-12"></div>
			</div>
			<div class="row button">
				<div class="col-md-3" id="classContent">
					<p>
						<a class="classInfoLabels"><b>Rating: </b></a>
					</p>
				</div>
				<div class="col-md-3" id="classContent">
					<p>
						<a class="classInfoLabels"> <b>Reviewer Name: </b></a>
					</p>
				</div>
				<div class="col-md-6" id="classContent">
					<p>
						<a class="classInfoLabels"> <b>Review Details: </b></a>
					</p>
				</div>
			</div>
			<c:forEach items="${review}" var="review">
				<div class="row subjectRow">
					<div class="col-md-3" id="classContent">
						<p>
							<c:out value="${review.pandaRating}" />
						</p>

					</div>
					<div class="col-md-3" id="classContent">
						<p>
							<c:out value="${review.reviewer}" />
						</p>

					</div>
					<div class="col-md-3" id="classContent">
						<p>
							<c:out value="${review.review}" />
						</p>

					</div>
					<div class="col-md-9" id="classContent">
						<div class="pandaFaces">
						<p>
						<c:forEach begin="1" end="${review.pandaRating}">
							<img class="panda" src="../img/rating.png" width="6%">
						</c:forEach>
						<a>&emsp;(out of 5)</a>
						</p>
						</div>
					</div>
				</div>
			</c:forEach>
			<form action="${reviewPageURL}" method="POST">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
				<div class="modal-footer d-flex justify-content-center">
					<button class="btn btn-deep-orange" type="submit">Review</button>
				</div>
			</form>
		</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
