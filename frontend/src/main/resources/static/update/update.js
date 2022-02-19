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
            var formTag = $("#profile")[0];
            var formData = new FormData(formTag);
            formData.append('image', $('#imageUpload')[0].files[0]);
            $("#file_error").html("");
            updateUser(formData);
	}
	})
})

function updateUser(formData){
if(checkConnection()){
$.ajax({
   type: "POST",
   url: "/api/v1/user/profile/edit",
   contentType: false,
   processData: false,
   data: formData,
   beforeSend: function(request) {
      request.setRequestHeader("AuthToken", "Bearer "+$.cookie("session_Token"));
      request.setRequestHeader("browser", $.cookie("browser"));
    },
  success: function(response) {
    $(".alert").remove();
     setTimeout(function() {
         if (response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
             $("#formGroup").prepend(("<div class='alert'>" + response.message + "</div>"));
             location.reload();
             $(".alert").append("<span id ='txt'>"+ '  OK' +'</span>');
         } else if (!response.status) {
             $(".alert").html(response.message);
         }
         location.reload();
         }, 400);
         var duration = 300;
          if (!response.status && response.alternateMobileAlreadyExist || response.emailAlreadyExist && $(".alert").length == 0 || $(".input-group span").length == undefined) {
              $("#formGroup").prepend(("<div class='alert'>" + response.message + "</div>"));
          } else if (!response.status) {
              $(".alert").html(response.message);
          }
       setTimeout(function() {
         $(".alert").remove();
        location.reload();
      }, 5500);
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
           else if(error.status==401 && error.responseJSON.isSessionExpired){
               $("#modal_title").html(error.responseJSON.errorCode);
               $("#message").html(error.responseJSON.message);
               $('#server_error').modal('show');
               $(document).ajaxStop(function () {
               console.log("ajax stoped");
               });

            $('body').click(function (event)
            {
               if(!$(event.target).closest('#server_error').length && !$(event.target).is('#server_error')) {
               // location.reload();
               }
            });

              setTimeout(function() {
            //     location.reload();
                }, 5500);
             }
           else if(error.status==400 && !error.responseJSON.validFile){
              $("#file_error").html(error.responseJSON.message);
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

$(document).ready(function() {
imageUpload.onchange = evt => {
  const [file] = imageUpload.files
  if (file) {
    img.src = URL.createObjectURL(file)
  }
}
$('#file-upload').change(function() {
  var i = $(this).prev('label').clone();
  var file = $('#file-upload')[0].files[0].name;
  $(this).prev('label').text(file);
});
});

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

$(document).ready(function() {
  $("#txt").click(function () {
    //$(".alert").remove();
    alert("Hello!");
    //window.reload();
  });
});