<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Sensei</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
<script
	src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../css/site.css">
</head>
<h2 cssClass="error">${error}</h2>

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true,
					minlength: 15,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<section class="myContainer">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">
				<div class="col-md-2 senseiPic" id="senseiPic">
					<img src="../../img/SenseiMaster.jpg">
				</div>
				<div class="senseiMottoHome">wonder. learn. connect. teach.
					repeat.</div>
				<c:url var="formAction" value="/login" />
				<form method="POST" action="${formAction}">
					<input type="hidden" name="destination"
						value="${param.destination}" /> <input type="hidden"
						name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
					<div class="form-group">
						<label for="userName">User Name: </label> <input type="text"
							id="userName" name="userName" placeHolder="User Name"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Password: </label> <input type="password"
							id="password" name="password" placeHolder="Password"
							class="form-control" />
					</div>
					<button type="submit" class="btn btn-default">Login</button>
				</form>
			</div>
			<div class="col-sm-6">
				<c:url var="formAct" value="/" />
				<form method="POST" action="${formAct}">
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
					<div class="form-group">
						<label for="userName">User Name: </label> <input type="text"
							id="userName" name="userName" placeHolder="User Name"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Password: </label> <input type="password"
							id="password" name="password" placeHolder="Password"
							class="form-control" />
					</div>
					<!-- 							<div class="form-group">
								<label for="confirmPassword">Confirm Password: </label>
								<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
							</div> -->
<<<<<<< HEAD
					<div class="form-group">
						<label for="firstName">First Name: </label> <input type="text"
							id="firstName" name="firstName" placeHolder="First Name"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="lastName">Last Name: </label> <input type="text"
							id="lastName" name="lastName" placeHolder="Last Name"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="bio">Bio: </label> <input type="text" id="bio"
							name="bio" placeHolder="Bio" class="form-control" />
					</div>
					<div class="form-group">
						<label for="email">Email: </label> <input type="text" id="email"
							name="email" placeHolder="Email" class="form-control" />
					</div>
					<label for="isSensei">Choose Sensei or Grasshopper: </label>
					<div class="form-group">
						<input type="radio" value="true" name="isSensei" /> Sensei <input
							type="radio" value="false" name="isSensei" /> Grasshopper
						<%-- <form:errors path="activityLevel" cssClass="error" /> --%>
					</div>
					<button type="submit" class="btn btn-default">Create User</button>
				</form>
			</div>
		</div>
	</div>
=======
							<div class="form-group">
								<label for="firstName">First Name: </label>
								<input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
							</div>
							<div class="form-group">
								<label for="lastName">Last Name: </label>
								<input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
							</div>
							<div class="form-group">
								<label for="bio">Bio: </label>
								<input type="text" id="bio" name="bio" placeHolder="Bio" class="form-control" />
							</div>
							<div class="form-group">
								<label for="email">Email: </label>
								<input type="text" id="email" name="email" placeHolder="Email" class="form-control" />
							</div>
							<label for="isSensei">Choose Sensei or Grasshopper: </label>
							<div class="form-group">
								<input type="radio" value="true" name="sensei" /> Sensei
								<input type="radio" value="false" name="sensei" /> Grasshopper
								<form:errors path="activityLevel" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-default">Create User</button>
					</form>
				</div>	
		</div>	
	</div>	
>>>>>>> fb20bf952b1773687948309d85c7e99224d73816
</section>


<c:import url="/WEB-INF/jsp/footer.jsp" />