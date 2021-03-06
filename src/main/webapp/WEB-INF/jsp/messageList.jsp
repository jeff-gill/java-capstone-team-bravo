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

<c:url var="formAction" value="/messages/${currentUser.userName}" />


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
				<i>View All Private Messages</i>
			</div>
		</div>
	</div>
	<div class="container-fluid messagingListContainer"
		style="margin-right: 100px; margin-left: 100px">
		<div class="row messagesHeader">
			<div class="col-md-2">To:</div>
			<div class="col-md-2">From:</div>
			<div class="col-md-2">Subject:</div>
			<div class="col-md-6">Body:</div>
		</div>
		<c:forEach items="${message}" var="userMessage">
			<div class="row messagesList">
				<form action="${formAction}" method="GET">
					<div>
						<div class="col-md-2">
							<c:out value="${userMessage.senderName}" />
						</div>
						<div class="col-md-2">
							<c:out value="${userMessage.receiverName}" />
						</div>
						<div class="col-md-2">
							<c:out value="${userMessage.messageSubject}" />
						</div>
						<div class="col-md-6">
							<c:out value="${userMessage.messageBody}" />
						</div>
					</div>
				</form>
			</div>

		</c:forEach>
	</div>
</section>