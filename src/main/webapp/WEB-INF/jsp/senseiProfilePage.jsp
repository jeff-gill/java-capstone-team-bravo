<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script defer src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"
	    integrity="sha384-4oV5EgaV02iISL2ban6c/RmotsABqE4yZxZLcYMAdG7FAPsyHYAPpywE9PJo+Khy"
	    crossorigin="anonymous"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

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
		<div class="col-md-12"></div>
		</div>
		<div class="row">
			<div class="col-md-5">
				<c:out value="${profile.firstName} ${profile.lastName}" />
				<br/>
				<c:out value="${profile.email}" />
				<br/>
				<c:out value="${profile.phone}" />
				<br/>
				<h4>Panda Rating:</h4>
				<c:forEach begin="1" end="${profile.rating}">
					<img class="panda" src="../../img/rating.png" width="6%">
				</c:forEach>
				<br/>
				
			</div>
			<div class="col-md-7">
			
			
			<div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Schedule a Class</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <input type="text" id="orangeForm-name" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-name">Class Name</label>
                </div>
                <br/>
                <div class="md-form mb-5">
                    <input type="email" id="orangeForm-email" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-email">Class Location</label>
                </div>
				 <br/>
                <div class="md-form mb-4">
                    <input type="password" id="orangeForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Class Date</label>
                </div>
                 <br/>
                 <div class="md-form mb-4">
                    <input type="password" id="orangeForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Class Time</label>
                </div>
                 <br/>
                 <div class="md-form mb-4">
                    <input type="password" id="orangeForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Class Cost</label>
                </div>
                 <br/>
                 <div class="md-form mb-4">
                    <input type="password" id="orangeForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Available Slots</label>
                </div>
                 <br/>
                 <div class="md-form mb-4">
                    <input type="password" id="orangeForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Class Description</label>
                </div>

            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button class="btn btn-deep-orange">LET'S DO IT!!</button>
            </div>
        </div>
    </div>
</div>

<div class="text-center">
    <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalRegisterForm">Update Your Class Schedule!!</a>
</div>
<br/>

				<table>
					<tr>
						<th>Name</th>
						<th>Location</th>
						<th>Date</th>
						<th>Time</th>
						<th>Cost</th>
						<th>Available Slots</th>
						<th>Class Description</th>
					</tr>
					<tr>
						<td>Sword Fighting</td>
						<td>Goodale Park</td>
						<td>10/25/2018</td>
						<td>10:00-11:00</td>
						<td>$25</td>
						<td>4</td>
						<td>Learn to slash zombies</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp" />
