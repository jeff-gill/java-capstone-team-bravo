<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	
		<c:url var="formAction" value="/users/gh/${currentUser.userName}" />

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
				<img src="../../img/${profile.profileImage}.jpg " alt="place holder" /></div>
				<div class="updateButton">
					<div class="modal fade" id="modalProfileInfoForm" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header text-center">
									<h4 class="modal-title w-100 font-weight-bold">Edit Profile Image</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form method="POST" action="uploadFile" enctype="multipart/form-data">
									File to upload: <input type="file" name="file" >
									<br />
									<br />
									<input type="submit" value="Upload">
										<div class="modal-footer d-flex justify-content-center">
											<button class="btn btn-deep-orange" type="submit">LET'S DO IT!!</button>
										</div>
									</form>
									<c:if test="${not empty message}">
									${message}
									</c:if>
							</div>
						</div>
						</div>
					<div class="text-right">
					
					<a href="" class="btn btn-rounded mb-4 updateProfileButton" 
						data-toggle="modal" data-target="#modalProfileInfoForm">Update
						Profile Info!</a>
				</div>
				<div class="modal fade" id="modalProfileInfoForm" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header text-center">
								<h4 class="modal-title w-100 font-weight-bold">Edit Profile</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action = "${formAction}" method = "POST">
								<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
								<div class="modal-body mx-3">
									<div class="md-form mb-5">
										<input type="text" id="orangeForm-name" name="firstName"
											class="form-control validate"> <label
											data-error="wrong" data-success="right" for="orangeForm-name">
											First Name</label>
									</div>
									<div class="md-form mb-5">
										<input type="text" id="orangeForm-name" name="lastName"
											class="form-control validate"> <label
											data-error="wrong" data-success="right" for="orangeForm-name">
											Last Name</label>
									</div>
									<div class="md-form mb-5">
										<input type="email" id="orangeForm-email" name="email"
											class="form-control validate"> <label
											data-error="wrong" data-success="right"
											for="orangeForm-email">Primary Email Address</label>
									</div>
									<div class="md-form mb-5">
										<input type="tel" id="orangeForm-pass" name="phone"
											class="form-control validate"> <label
											data-error="wrong" data-success="right" for="orangeForm-pass">Primary
											Phone Number</label>
									</div>
									<div class="md-form mb-5">
										<input type="tel" id="orangeForm-pass" name="bio"
											class="form-control validate"> <label
											data-error="wrong" data-success="right" for="orangeForm-pass">Bio
											Info</label>
									</div>
								
									<div class="md-form mb-5">
										<input type="tel" id="orangeForm-pass" name="interests"
											class="form-control validate"> <label
											data-error="wrong" data-success="right" for="orangeForm-pass">Interests
											</label>
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
		<div class="row">
			<div class="col-md-4" id="classContent">
				<div class="classSections">
					<p>
						<a class="classInfoLabels"> <b>Subject:</b></a> placeholder
						<%-- ${class.subjectName} --%>
					</p>
					<p>
						<a class="classInfoLabels"> <b>Location:</b></a> placeholder
						<%-- ${class.location} --%>
					</p>
					<p>
						<a class="classInfoLabels"> <b>Date:</b></a> placeholder
						<%-- ${class.date} --%>
					</p>
				</div>
			</div>
			<div class="col-md-4" id="classContent">
				<div class="classSections">
					<p>
						<a class="classInfoLabels"> <b>Time:</b></a> placeholder
						<%-- ${class.time} --%>
					</p>
					<p>
						<a class="classInfoLabels"> <b>Cost:</b></a>placeholder
						<%-- ${class.cost} --%>
					</p>
				</div>
			</div>
			<div class="col-md-4" id="classContent">
				<div class="classSections">
					<p>
						<a class="classInfoLabels"> <b>Class Description:</b></a>placeholder
						<%-- ${class.description} --%>
					</p>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
