<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<fmt:formatDate value="${bean.date}" pattern="MM-dd-yyyy" />

<c:import url="/WEB-INF/jsp/header.jsp" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script defer
	src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"
	integrity="sha384-4oV5EgaV02iISL2ban6c/RmotsABqE4yZxZLcYMAdG7FAPsyHYAPpywE9PJo+Khy"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:url var="formAction" value="/users/sensei/${currentUser.userName}" />
<c:url var="form" value="/uploadSenseiFile" />
<c:url var="formA" value="/users/sensei/${currentUser.userName}/createSubject" />
<c:url var="formUpdate" value="/users/sensei/${currentUser.userName}/updateSubject" />

<section class="myContainer">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h2 class="profileName">
					<c:out value="${profile.firstName} ${profile.lastName}" />
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12"></div>
		</div>
		<div class="row bioRow">
			<div class="col-md-5">
				<div id="profilePic">
					<c:url var="imgUrl" value="/image/${profile.profileImage}" />
					<img src="${imgUrl}" />
					<c:if test="${not empty message}">
  						  ${message} 
					</c:if>
				</div>
				<div class="text-right">

					<a href="" class="btn btn-rounded mb-4 updateProfileButton"
						data-toggle="modal" data-target="#modalProfilePicForm">Update
						Profile Image!</a>
				</div>
				<div class="updateButton">
					<div>
						<div class="modal fade" id="modalProfilePicForm" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header text-center">
										<h4 class="modal-title w-100 font-weight-bold">Edit
											Profile Image</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form method="POST" action="${form}"
										enctype="multipart/form-data">
										<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
										File to upload: <input type="file" name="file"> <br />
										<br /> <input type="submit" value="Upload">
										<div class="modal-footer d-flex justify-content-center"></div>
									</form>
									<c:if test="${not empty message}">
									${message}
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="text-right button">
						<a href="" class="btn btn-rounded mb-4 updateButton"
							data-toggle="modal" data-target="#modalProfileInfoForm">Update
							Profile Info!</a>
					</div>
					<div class="modal fade" id="modalProfileInfoForm" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header text-center">
									<h4 class="modal-title w-100 font-weight-bold">Edit
										Profile</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form action="${formAction}" method="POST">
									<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
									<div class="modal-body mx-3">
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right"
												for="orangeForm-name"> First Name: </label><input type="text"
												id="orangeForm-name" name="firstName"
												class="form-control validate" value="${profile.firstName}">
										</div>
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right"
												for="orangeForm-name"> Last Name: </label><input type="text"
												id="orangeForm-name" name="lastName"
												class="form-control validate" value="${profile.lastName}">
										</div>
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right"
												for="orangeForm-email">Primary Email Address: </label><input
												type="email" id="orangeForm-email" name="email"
												class="form-control validate" value="${profile.email}">
										</div>
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right"
												for="orangeForm-pass">Primary Phone Number: </label><input
												type="tel" id="orangeForm-pass" name="phone"
												class="form-control validate" value="${profile.phone}">
										</div>
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right"
												for="orangeForm-pass">Bio Info:</label><input type="tel"
												id="orangeForm-pass" name="bio"
												class="form-control validate" value="${profile.bio}">
										</div>
										<br />
									</div>
									<div class="modal-footer d-flex justify-content-center">
										<button class="btn btn-deep-orange" type="submit">LET'S
											DO IT!!</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div>
						<div class="text-right">
							<a href="" id="updateButton" class="btn btn-rounded mb-4"
								id="updateButton" data-toggle="modal"
								data-target="#modalRegisterForm">Create a Class</a>
						</div>


						<div class="modal fade" id="modalRegisterForm" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header text-center">
										<h4 class="modal-title w-100 font-weight-bold">Schedule a
											Class</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>

									<form action="${formA}" method="POST">
										<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
										<div class="modal-body mx-3">
											<div class="md-form mb-5">
												<label data-error="wrong" data-success="right"
													for="orangeForm-name">Class Name:</label><input type="text"
													id="orangeForm-name" name="subjectName"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-5">
												<label data-error="wrong" data-success="right"
													for="orangeForm-email">Class Location:</label><input
													type="text" id="orangeForm-email" name="location"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-5">
												<label data-error="wrong" data-success="right"
													for="orangeForm-email">Date:</label><input
													type="text" id="orangeForm-email" name="date"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-4">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Start Time:</label><input
													type="time" id="orangeForm-pass" name="startTime"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-4">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Class End Time:</label><input
													type="time" id="orangeForm-pass" name="endTime"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-4">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Cost:</label><input
													type="text" id="orangeForm-pass" name="cost"
													class="form-control validate">
											</div>
											<br />
											<div class="md-form mb-4">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Available Slots:</label><input
													type="text" id="orangeForm-pass" name="availableSlots"
													class="form-control validate">

											</div>
											<br />
											<div class="md-form mb-4">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Description:</label><input
													type="text" id="orangeForm-pass" name="description"
													class="form-control validate">
											</div>

										</div>
										<div class="modal-footer d-flex justify-content-center">
											<button class="btn btn-deep-orange" type="submit">LET'S
												DO IT!!</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-6">
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Bio Info:</b>
					</div>
					<div>
						<c:out value="${profile.bio}" />
					</div>
				</div>
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Email:</b>
					</div>

					<div>
						<c:out value="${profile.email}" />
					</div>
				</div>
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Phone #: </b>
					</div>
					<div>
						<c:out value="${profile.phone}" />
					</div>
				</div>
				<div class="userSections">
					<div class="userInfoLabels">
						<b>Panda Rating: </b>
					</div>
					<div class="pandaFaces">
						<c:forEach begin="1" end="${profile.rating}">
							<img class="panda" src="../../img/rating.png" width="6%">
						</c:forEach>
						<a>&emsp;(out of 5)</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<p>Class Schedule:</p>
		</div>
	</div>
	<c:forEach items = "${subject}" var = "lesson">
	<div class="row">
		<div class="col-md-3" id="classContent">
			<p>
				<a class="classInfoLabels"> <b>Subject:</b> <c:out value = "${lesson.subjectName}" /></a> 
			</p>

		</div>
		<div class="col-md-3" id="classContent">
			<p>
				<a class="classInfoLabels"> <b>Location:</b> <c:out value = "${lesson.location}" /></a> 
			</p>
		</div>
		<div class="col-md-2" id="classContent">
			<p>
				<a class="classInfoLabels"> <b>Date:</b><fmt:parseDate pattern="MM/dd/yyyy" value="${bean.dateString}" var="parsedDate" />
<fmt:formatDate value="${lesson.date}" pattern="MM-dd-yyyy" /><%--  <c:out value = "${lesson.date}" /> --%></a>
			</p>
		</div>
		<div class="col-md-2" id="classDeets">
			<div class="text-left button">
				<a href="" class="btn btn-rounded mb-4 classInfoButton"
					data-toggle="modal" data-target="#modalClassInfoForm">View/Edit Class</a>
			</div>
			<div class="modal fade" id="modalClassInfoForm" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header text-center">
							<h4 class="modal-title w-100 font-weight-bold">Class Description</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="${formUpdate}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
							<input type="hidden" name="classId" value="${lesson.classId}">
							<div class="modal-body mx-3">
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-name"> Subject:</label> <input type="text"
										id="orangeForm-name" name="subjectName"
										class="form-control validate" value="${lesson.subjectName}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-name">Location:</label><input type="text"
										id="orangeForm-name" name="location"
										class="form-control validate" value="${lesson.location}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-email">Class Date:</label><input type="text"
										id="orangeForm-email" name="date"
										class="form-control validate" value="${lesson.date}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-pass">Start Time:</label><input type="time"
										id="orangeForm-pass" name="startTime" class="form-control validate" value = "${lesson.startTime}">
								</div>
									<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-pass">End Time:</label><input type="time"
										id="orangeForm-pass" name="endTime" class="form-control validate" value = "${lesson.endTime}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-pass">Cost:</label><input type="text"
										id="orangeForm-pass" name="cost" class="form-control validate" value = "${lesson.cost}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-pass">Available Slots:</label><input
										type="text" id="orangeForm-pass" name="availableSlots"
										class="form-control validate" value = "${lesson.availableSlots}">
								</div>
								<div class="md-form mb-5">
									<label data-error="wrong" data-success="right"
										for="orangeForm-pass">Class Description:</label><input
										type="text" id="orangeForm-pass" name="description"
										class="form-control validate" value = "${lesson.description}">
								</div>
								<br />
							</div>
							
							<div class="modal-footer d-flex justify-content-center">
								<button class="btn btn-deep-orange" type="submit">Done</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2" id="classDeets"></div>
	</div>
	</c:forEach>
	<div class="row">
		<div class="col-md-5"></div>

		<div class="col-md-7"></div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
