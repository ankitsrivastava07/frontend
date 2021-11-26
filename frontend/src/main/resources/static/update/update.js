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
				"address": $("#address").val()
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
      request.setRequestHeader("browser", $.cookie("browser"));
    },
  success: function(response,textStatus,request) {
    $(".alert").remove();
    setTimeout(function() {
         if (response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
             $("#formGroup").prepend(("<div class='alert alert-success' role='alert'>" + response.message + "</div>"));
             location.reload();
         } else if (!response.status) {
             $(".alert").html(response.message);
         }
        // location.reload();
         }, 400);
         var duration = 300;
          if (!response.status && response.alternateMobileAlreadyExist || response.emailAlreadyExist && $(".alert").length == 0 || $(".input-group span").length == undefined) {
              $("#formGroup").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
          } else if (!response.status) {
              $(".alert").html(response.message);
          }
     setTimeout(function() {
          $(".alert").remove();
           }, 9500);
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
        else if(error.status==401 && error.responseJSON.redirect){
             window.location.replace(error.responseJSON.redirectURL)
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