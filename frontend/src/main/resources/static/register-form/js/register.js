
$(document).ready(function() {

	$.validator.addMethod("mobileNumberVaildation", function(value) {
		return /^(?=.*[a-z])[A-Za-z0-9\d=!\-@$&!%#()<>._*]+$/.test(value) // consists of only these

	});

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
					number: true,
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
					"password": $("#password").val(),
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
					beforeSend: function(response) {
						$(".alert").remove();
						$(".mobile-error").remove();
					},
					success: function(response) {
						$(".input-group span").remove();

						if (response.status) {
							$(".title").after(("<div class='alert alert-success' role='alert' data-fade='3000' >" + response.message + "</div>"));
							$('#signup-form')[0].reset();
							window.location.href = "/popup"
						}

						setTimeout(function() {
							$.each(response.errorMessage, function(key, value) {

								if (!response.status && $(".input-group span").length == 0 || $(".input-group span").length == undefined) {
									var span = $('<span />').addClass(key + '-error error').html(value);
									$("#" + key).after(span);
								}
								else if (!response.status) {
									$("#" + key).html(value);
								}
							});
						}, 500);

						if (response.status) {
							$('#signup-form')[0].reset();
							//$.removeCookie('session_Token', { path: '/' });
							//	window.location.href = "/"
						}
					},
					error: function(xhr, ajaxOptions, thrownError) {
						$(".alert").remove();
						setTimeout(function() {
							if ($(".alert").length == 0 || $(".input-group span").length == undefined) {
								$(".title").after(("<div class='alert alert-danger' role='alert'>" + xhr.responseText + "</div>"));
								$(".alert").html(xhr.responseText);

							} else {
								$(".alert").html(xhr.responseText);
							}
						}, 500);
					}
				});
			}
		}

	});
});
