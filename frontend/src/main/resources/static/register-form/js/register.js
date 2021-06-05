
$(document).ready(function() {

	$.validator.addMethod("passwordValidation", function(value) {
		return /^(?=.*[a-z])[A-Za-z0-9\d=!\-@$&!%#()<>._*]+$/.test(value) // consists of only these
			&& /[a-z]/.test(value)
			&& /[A-Z]/.test(value)
			&& /\d/.test(value)
	}, "Password must contain at least 1 capital letter, 1 small letter, 1 number and 1 special character. For special characters you can pick one of these @$&!%#()<>-"

	);

	$("#signup-form").validate({

		rules: {

			firstName: {
				required: true,
			},

			lastName: {
				required: true,
			},

			email: {
				required: false,
			},

			password: {
				required: true,
				minlength: 8,
				maxlength: 20,
				passwordValidation: true
			},

			mobile: {
				required: true,
				number: true
			},

		},
		messages: {

			firstName: {
				required: "Please enter  First Name",
			},

			lastName: {
				required: "Please enter  Last Name",
			},

			password: {
				required: "Please enter  password",

				minlength: "Please enter password atleast 8 characters long",
			},

			email: {
				required: "Please enter email",
			},

			mobile: {
				required: "Please enter mobile number",
			},

		},

		submitHandler: function(form) {

			var formData = {

				"firstName": $("#firstName").val(),
				"lastName": $("#lastName").val(),
				"email": $("#email").val(),
				"mobile": $("#mobile").val(),
				"password": $("#password").val()
			}
			register(formData);
		}
	})

	function register(formData) {

		if ($("#signup-form").valid()) {

			$.ajax({
				type: "POST",
				url: "/register",
				contentType: "application/json",
				data: JSON.stringify(formData),
				cache: false,
				success: function(response) {

					setTimeout(function() {
						var len = $(".input-group span").length;

						if ($(".input-group span").length == 0 || $(".input-group span").length == undefined) {
							$(".error").remove();

							$.each(response.errorMessage, function(key, value) {
								var span = $('<span />').addClass(key + '-error error').html(value);
								$("#" + key).after(span);
							});
						} else {
							$("#" + key).html(value);
						}
					}, 500);

					if (response.status) {
						$('#signup-form')[0].reset();
						alert("Successfully Registered your account please proceed to login");
						window.location.href = "/signin"
					}
				},
				complete: function() {
					$(".error").remove();
				},
				error: function(error) {
					alert("Something went wrong please try again later")
				}
			})
		}
	}

});

$(document).ready(function() {
	$('#email,#mobile').keyup(function() {
		$(".error").remove();
	});
});