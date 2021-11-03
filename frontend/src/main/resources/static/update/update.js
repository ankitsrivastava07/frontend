$(document).ready(function() {

	$("#profile").validate({

		rules: {
			firstName: {
				required: true,
			},

			lastName: {
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
				"firstName": $("#firstName").val(),
				"lastName": $("#lastName").val(),
				"email": $("#email").val(),
				"alternateMobile": $("#alternate_mobile").val(),
				"address": $("#address").val(),
				"browser" : $.cookie("browser")
			}
			updateUser(formData);
		}
	})
})

function updateUser(formData){
if(checkConnection()){
$.ajax({
   type: "POST",
   url: "/users/profile/edit",
   contentType: "application/json",
   data: JSON.stringify(formData),
   beforeSend: function(request) {
      request.setRequestHeader("Authentication", $.cookie("session_Token"));
    },
  success: function(response) {
    if(response.status){
    $("#altert_success").html(response.message);
    $("#alert_success").show();
    location.reload();
    }
    if(!response.status && response.alternateMobileAlreadyExist){
        $("#alert_msg").html(response.message);
        $("#alert_msg").show();
}
  },
   error: function(jqXHR, textStatus, err) {
        console.log(jqXHR, '\n', textStatus, '\n', err)
      }
});
return true;
}
return false;
}

function checkConnection(){
$.ajax('/check-connection', {
  statusCode: {
    0: function() {
      alert(" We can’t connect to the server please check your internet connection or the page which you are looking for has been removed.");
      return false
    }
  }
});
return true;
}
