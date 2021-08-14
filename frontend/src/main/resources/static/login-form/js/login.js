$(document).ready(function() {

	$("#loginModalPop").click(function() {

		$(".modal").modal("show");
		document.body.style.overflow = "scroll";
	});
});

$(document).ready(function() {

	$("#login-form").validate({

		rules: {

			email: {
				required: true,
			},

			password: {
				required: true,
			},

		},
		messages: {

			email: {
				required: "Please enter your email/mobile number",
			},

			password: {
				required: "Please enter your password",
			},
		},
		submitHandler: function(form) {

			var formData = {

				"email": $("#email").val(),
				"password": $("#password").val()
			}
			login(formData);
		}
	})
})

$(document).ready(function() {

	$('#popup-modal').on('hidden.bs.modal', function() {
		var $alertas = $('#login-form');
		$alertas.validate().resetForm();
		$alertas.find('.error').removeClass('error');
		$('#login-form').trigger("reset");
	});

})

function login(formData) {

	if ($("#login-form").valid() && checkConnection()) {

		$.ajax({

			type: "POST",
			url: "/signin",
			contentType: "application/json",
			data: JSON.stringify(formData),
			cache: false,
			success: function(response) {

				$(".alert").remove();

				if (response.status)
					$(".modal-body").prepend(("<div class='alert alert-success' role='alert' data-fade='3000' >" + response.message + "</div>"));

				setTimeout(function() {

					if (!response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {

						$(".modal-body").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
					} else if (!response.status) {
						$(".alert").html(response.message);
					}

				}, 500);

				if (response.status) {
					window.location.href = "/"
				}
			},
			error: function(error) {
				url = window.location.pathname.replace(/\/+$/, '') + "/error";
				window.location.replace(url)
			}
		})
	}
	return false;
}

function changePassword(formData) {

	if ($("#change-password").valid() && checkConnection()) {

		$.ajax({

			type: "POST",
			url: "/change-password",
			contentType: "application/json",
			data: JSON.stringify(formData),
			cache: false,
			beforeSend: function(xhr) {
				var cookie = $.cookie("session_Token")
				xhr.setRequestHeader('session_Token', cookie);
			},
			success: function(response) {

				if (response.status)
					$(".modal-body").prepend(("<div class='alert alert-success' role='alert' data-fade='3000' >" + response.message + "</div>"));

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

				setTimeout(function() {

					if (!response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {

						$(".modal-body").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
					} else if (!response.status) {
						$(".alert").html(response.message);
					}

				}, 500);

				if (response.status)
					window.location.href = "/"
			},
			error: function(error) {
				url = window.location.pathname.replace(/\/+$/, '') + "/error";
				window.location.replace(url)
				alert("Something went wrong  please try again later")
			}
		})
	}

}

jQuery('#change-password').validate({

	rules: {
		password: {
			minlength: 5,
			required: true
		},
		password_confirm: {
			minlength: 5,
			required: true,
			equalTo: "#password"
		},
	},

	messages: {

		password: {
			required: "Please enter password",
			minlength: "Password should be atleast 5 characters long",
		},

		password_confirm: {
			minlength: "Confirm password should be atleast 5 characters long",
			equalTo: "Password not matched",
			required: "Please Enter confirm password"
		}
	},

	submitHandler: function(form) {

		var formData = {
			"password": $("#password").val(),
		}
		changePassword(formData)
	}

});

window.addEventListener("pageshow", function(event) {
	var historyTraversal = event.persisted ||
		(typeof window.performance != "undefined" &&
			window.performance.navigation.type === 2);
	if (historyTraversal) {
		// Handle page restore.
		window.location.reload();
	}
});

/*$('#login-form').on('keyup keypress', function(e) {
	var keyCode = e.keyCode || e.which;
	if (keyCode === 13) {
		return false;
	}
});
*/
function checkConnection(){

$.ajax('/check-connection', {
  statusCode: {
    0: function() {
      alert(" We can’t connect to the server at "+window.url+"please check your internet connection or the page which you are looking for has been removed.");
      return false
    }
  }
});
return true;
}