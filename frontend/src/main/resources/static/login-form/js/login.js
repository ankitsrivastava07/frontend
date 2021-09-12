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

                 if(error.responseJSON.validationFailed)    {
                 $(".error").remove();
                     $.each(error.responseJSON.errors , function(index, val) {

                    var duration = 500;
                    $({to:0}).animate({to:1}, duration, function() {
                  			if ($(".error_"+val.fieldName).length == 0 || $(".error_"+val.fieldName).length == undefined) {
            						$("#"+val.fieldName).after(("<span class=error error_"+val.fieldName+">" + val.message + "</span>"));
            					} else{
            						$(".error_"+val.fieldName).val(val.message);
            					}
            					 })
                      });
                  }
               if(error.status === 400 && !error.responseJSON.validationFailed && !error.responseJSON.status){
				url = window.location.pathname.replace(/\/+$/, '') + "/error";
				window.location.replace(url)
				}
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
			beforeSend: function(xhr){
			xhr.setRequestHeader('Authorization', formData.code)
			},
			cache: false,
			success: function(response) {
		    $(".alert").remove();
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
				if (response.status){
                  	window.location ="/"
                    }
                  $('.modal').modal('show');
				setTimeout(function() {
					if (!response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
						$(".modal-body").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
					} else if (!response.status) {
						$(".alert").html(response.message);
					}
				}, 500);
				if (response.status){
				$.ajax({
                   url: "/ajax-signin",
                   type:'GET',
                   success: function(page){
                       $('#content').html(page);
                       //$('#change-password-body').hide();
                       $(".alert").remove();
                       	if ($(".alert").length == 0 || $(".input-group span").length == undefined) {
                                $(".modal-body").prepend(("<div class='alert alert-success' role='alert'>" + response.message + "</div>"));
                            } else {
                                $(".alert").html(response.message);
                            }
                   }
                });
					}
			},
			error: function(error) {
				url = window.location.pathname.replace(/\/+$/, '') + "/error";
				if(error.status==401){
    				$.ajax({
                       url: "/unauthorize-change-password",
                       type:'GET',
                       success: function(page){
                           $('#modal_review').html(page);
                           //$('#change-password-body').hide();
                           $(".alert").remove();
                           $('#modal_popup').modal('show')
                           $("#message").html(error.responseJSON.message);
                       }
                    });
              }
             else if(error.status==503){
                  				$.ajax({
                                     url: "/server-down",
                                     type:'GET',
                                     success: function(page){
                                     $('#modal_popup').remove();
                                         $('#modal_review').html(page);
                                         //$('#change-password-body').hide();
                                         $(".alert").remove();
                                         $('#modal_server').modal('show');
                                     }
                                  });
                            }
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
    var formData =""
        let searchParams = new URLSearchParams(window.location.search)
        let param=""
        searchParams.has('code')
        if(searchParams.has('code')){
         param= searchParams.get('code')
                 formData = {
         			"password": $("#password").val(),
         			"code" : param,
         			 }
        }
		changePassword(formData);
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

jQuery('#reset-password-form').validate({

	rules: {
		email: {
			maxlength: 100,
			required: true
      }
	},

	messages: {

		email: {
			required: "Please enter email",
			maxlength: "Email should not be 100 characters long",
		}
	},

	submitHandler: function(form) {

		var formData = {
			"email": $("#email").val(),
		}
		userNameCheck(formData)
	}

});

function userNameCheck(formData){
var flag=false
  if ($("#reset-password-form").valid() && checkConnection()) {

$.ajax({
        type: "POST",
        url: "/userName/check",
        contentType: "application/json",
        data: JSON.stringify(formData),
        cache: false,
        success: function(response) {
        $(".alert").remove("");

        if(response.status){
            $.ajax({
               url: "/confirmation-page",
               type:'GET',
               success: function(page){
               $("#clear").remove();
               $('#modal_confirm').html(page);
                   $('#confirm').modal('show');
               }
            });
        }
        },
        error: function(error) {
            url = window.location.pathname.replace(/\/+$/, '') + "/error";
            $(".alert").remove();
           setTimeout(function() {
            if(error.status==404){
             $(".alert").remove();
             console.clear()
             console.log(error.responseJSON.message+"  "+formData.email)
                if ($(".alert").length == 0 || $(".input-group span").length == undefined) {
                        $("#email_response").append(("<div class='alert alert-danger' role='alert'>" + error.responseJSON.message + "</div>"));
                    } else {
                        $(".alert").html(error.responseJSON.message);
                    }
}
            }, 500);

            if(error.status!=404){
              $(".alert").remove("");
                $.ajax({
                   url: "/server-down",
                   type:'GET',
                   success: function(page){
                   $('#modal_popup').remove();
                       $('#modal_review').html(page);
                       //$('#change-password-body').hide();
                       $(".alert").remove();
                       $('#modal_server').modal('show');
                   }
                });
              }
        }
});
return flag
}
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

$(document).on('click', '#signin', function(){
window.location ="/signin"
});

$(document).on('click', '#close', function(){
window.location ="/"
});
