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
					<img src="../../img/${profile.profileImage}.jpg "
						alt="place holder" />
				</div>
				<div>
					<div class="text-right">
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
								<form id="ajax-profile-info" method="post">
									<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
									<div class="modal-body mx-3">
										<div class="md-form mb-5">
											<input type="text" id="orangeForm-name" name="firstName"
												class="form-control validate"> <label
												data-error="wrong" data-success="right"
												for="orangeForm-name"> First Name</label>
										</div>
										<div class="md-form mb-5">
											<input type="text" id="orangeForm-name" name="lastName"
												class="form-control validate"> <label
												data-error="wrong" data-success="right"
												for="orangeForm-name"> Last Name</label>
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
												data-error="wrong" data-success="right"
												for="orangeForm-pass">Primary Phone Number</label>
										</div>
										<div class="md-form mb-5">
											<input type="tel" id="orangeForm-pass" name="bio"
												class="form-control validate"> <label
												data-error="wrong" data-success="right"
												for="orangeForm-pass">Bio Info</label>
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
						<div class="text-center">
							<a href="" id="updateButton" class="btn btn-rounded mb-4"
								id="updateButton" data-toggle="modal"
								data-target="#modalRegisterForm">Update Your Class
								Schedule!!</a>
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

									<form id="ajax-class-schedule" method="post">
										<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
										<div class="modal-body mx-3">
											<div class="md-form mb-5">
												<input type="text" id="orangeForm-name" name="className"
													class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-name">Class Name</label>
											</div>
											<br />
											<div class="md-form mb-5">
												<input type="email" id="orangeForm-email" name="location"
													class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-email">Class Location</label>
											</div>
											<br />
											<div class="md-form mb-4">
												<input type="password" id="orangeForm-pass" name="date"
													class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Date</label>
											</div>
											<br />
											<div class="md-form mb-4">
												<input type="password" id="orangeForm-pass"
													class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Time</label>
											</div>
											<br />
											<div class="md-form mb-4">
												<input type="password" id="orangeForm-pass" name="cost"
													class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Cost</label>
											</div>
											<br />
											<div class="md-form mb-4">
												<input type="password" id="orangeForm-pass"
													name="availableSlots" class="form-control validate">
												<label data-error="wrong" data-success="right"
													for="orangeForm-pass">Available Slots</label>
											</div>
											<br />
											<div class="md-form mb-4">
												<input type="password" id="orangeForm-pass"
													name="description" class="form-control validate"> <label
													data-error="wrong" data-success="right"
													for="orangeForm-pass">Class Description</label>
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
					<p>
						<a class="classInfoLabels"> <b>Available Slots:</b></a>
						placeholder
						<%-- ${class.availableSlots} --%>
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
	<div class="row">
		<div class="col-md-5"></div>

		<div class="col-md-7"></div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
