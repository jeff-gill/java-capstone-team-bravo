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
<c:url var="form" value="/uploadGHFile" />

<c:url var="formAction" value="/users/gh/${currentUser.userName}" />
<c:url var="form" value="/uploadGHFile" />

<section>
	<div>
		<h2 class="profileName">
			<c:out value="${profile.firstName} ${profile.lastName}" />
		</h2>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">Private Messages</div>
		</div>
	</div>
	<div class="container-fluid messagingContainer" style="margin-right: 200px; margin-left:200px">
			<div class="row messageInputs">
				<div class="col-md-12">
					<br>
				</div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">To:</div>
				<div class="col-md-9">
					<input>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">From:</div>
				<div class="col-md-9">
					<input>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">Message Subject:</div>
				<div class="col-md-9">
					<input>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">Message Body:</div>
				<div class="col-md-9">
					<input>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-12"></div>
			</div>
			<div class="row messageInputs">

				<div class="col-md-4"></div>
				<div class="col-md-7">
					<div id="messageSubmit">
						<input class="messageSubmitButton" type="submit"
							value="Send Message" />
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />