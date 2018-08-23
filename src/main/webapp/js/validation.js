$(document).ready(function () {
  		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form1").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true,
					minlength: 10,
					capitals: true,
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, it must be at least 10 characters",
					capitals: "Field must contain a capital letter",
				}
			},
			errorClass : "error"
		});
		$.validator.addMethod('capitals', function(otherThing){
			return otherThing.match(/[A-Z]/);
		});
		$("form2").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true,
					minlength: 10,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, make it at least 10 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});