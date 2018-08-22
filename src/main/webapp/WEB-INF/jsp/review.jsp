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

<c:url var="formAction" value="/${currentUser.userName}/review" />

<section>
	<div>
		<h2 class="profileName">
			<b><c:out
					value="${currentUser.firstName} ${currentUser.lastName}" /></b>
		</h2>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 privateMessages" style="font-size: 28px">
				<i>Compose Private Messages</i>
			</div>
		</div>
	</div>
	<div class="container-fluid messagingContainer"
		style="margin-right: 100px; margin-left: 100px">
		<form action="${formAction}" method="POST">
<%-- 			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
 --%>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">Review For:</div>
				<div class="col-md-9">
					<c:out value="${userProfile.userName}" />
					<input type="hidden" name="reviewee" value="${userProfile.userName}" />
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-2">Review:</div>
				<div class="col-md-9">
					<textarea class="messageBody" name="review" maxlength="360"></textarea>
				</div>
				<div class="col-md-1"></div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-1"></div>
				<div class="col-md-3">Please choose a Panda Rating:</div>
				<div class="col-md-5 pandaRatingBar">
					<label class="pandaRating"> <input type="radio"
						name="pandaRating" value="1"/> <img src="../img/rating.png"><figcaption>1</figcaption></figure>
					</label> <label class="pandaRating"> <input type="radio"
						name="pandaRating" value="2"/> <img src="../img/rating.png"><figcaption>2</figcaption></figure>
					</label> <label class="pandaRating"> <input type="radio"
						name="pandaRating" value="3"/> <img src="../img/rating.png"><figcaption>3</figcaption></figure>
					</label> <label class="pandaRating"> <input type="radio"
						name="pandaRating" value="4"/> <img src="../img/rating.png"><figcaption>4</figcaption></figure>
					</label> <label class="pandaRating"> <input type="radio"
						name="pandaRating" value="5"/> <img src="../img/rating.png"><figcaption>5</figcaption></figure>
					</label>

					<div class="col-md-1"></div>
				</div>
			</div>
			<div class="row messageInputs">
				<div class="col-md-7"></div>
				<div class="col-md-4">
					<div id="messageSubmit">
						<input class="messageSubmitButton" type="reset"
							value="Clear Review"> <input class="messageSubmitButton"
							type="submit" value="Submit Review" />
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</form>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />