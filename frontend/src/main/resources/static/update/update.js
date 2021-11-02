$(document).ready(function() {

	$("#login-form").validate({

		rules: {
			first_name: {
				required: true,
			},

			last_name: {
				required: true,
			},

			email: {
            	required: true,
   			},
            alternate_mobile: {
                required: false,
            },
           address: {
                 required: false,
                   },
		},
		messages: {

			first_name: {
				required: "Please enter your first name",
			},

			last_name: {
				required: "Please enter your last name",
			},
        email: {
                  required: "Please enter your email",
                },
		},
		submitHandler: function(form) {

			var formData = {
				"firstName": $("#first_name").val(),
				"lastName": $("#last_name").val(),
				"email": $("#email").val(),
				"alternate_mobile": $("#alternate_mobile").val(),
				"address": $("#address").val(),
			}
			updateUser(formData);
		}
	})
})

function updateUser(formData){
$.ajax({
  type: "POST",
  beforeSend: function(request) {
    request.setRequestHeader("Authentication", $.cookie("session_Token"));
  },
  url: "/users/profile/edit",
  data: formData,
  success: function(response) {
    $("#results").append("The result =" + StringifyPretty(response));
    alert(response);
  },
   error: function(jqXHR, textStatus, err) {
        console.log(jqXHR, '\n', textStatus, '\n', err)
      }
});
}
