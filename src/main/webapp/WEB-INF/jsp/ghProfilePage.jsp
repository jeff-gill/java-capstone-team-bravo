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
			<div class="col-md-5">
				<img class="profilePic" src="../../img/placeholder.png" alt="place holder" />
			</div>
			<div class="col-md-7">
				<c:out value="Bio: ${profile.bio}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-5">
				<c:out value="Name: ${profile.firstName} ${profile.lastName}" />
				<br/>
				<c:forEach begin="1" end="${profile.rating}">
					<img class="panda" src="../../img/rating.png" width="6%">
				</c:forEach>
			</div>
			<div class="col-md-7">
				
			</div>
		</div>
	</div>
	

</section>



<c:import url="/WEB-INF/jsp/footer.jsp" />