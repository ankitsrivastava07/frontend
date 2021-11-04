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

			firstName: {
				required: "Please enter your first name",
			},

			lastName: {
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
   $(".alert").remove();
    setTimeout(function() {
        if (response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
            $("#formGroup").prepend(("<div class='alert alert-success' role='alert'>" + response.message + "</div>"));
        } else if (!response.status) {
            $(".alert").html(response.message);
        }
        //location.reload();
        }, 400);
        var duration = 300;
        $({to:0}).animate({to:1}, duration, function() {
         if (!response.status && response.alternateMobileAlreadyExist || response.emailAlreadyExist && $(".alert").length == 0 || $(".input-group span").length == undefined) {
             $("#formGroup").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
         } else if (!response.status) {
             $(".alert").html(response.message);
         }
        });
  },
   error: function(error) {
               url = window.location.pathname.replace(/\/+$/, '') + "/error";
               $(".alert").remove();

           if(error.status==503){
              $('#server_error').modal('show');
              $(document).ajaxStop(function () {
              console.log("ajax stoped");
              });
            }
        else if(error.status==401 && !error.responseJSON.status && error.responseJSON.isMailServiceDown){
              $("#message").html(error.responseJSON.message);
              $('#server_error').modal('show');
              $(document).ajaxStop(function () {
              console.log("ajax stoped");
              });
            }
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