$(document).ready(function() {

	$("#loginModalPop").click(function() {
		$(".modal").modal("show");
		document.body.style.overflow = "scroll";
	});
});

$(document).ready(function() {
	$("#login-form").validate({
		rules: {
			emailOrMobile: {
				required: true,
			},
			password: {
				required: true,
			},

		},
		messages: {
			emailOrMobile: {
				required: "Please enter your email/mobile number",
			},
			password: {
				required: "Please enter your password",
			},
		},
		submitHandler: function(form) {
			var formData = {
				"emailOrMobile": $("#emailOrMobile").val(),
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
			url: "/api/v1/user/login",
			contentType: "application/json",
			data: JSON.stringify(formData),
			cache: false,
			success: function(response,textStatus, request) {
			$(".error").remove();
				$(".alert").remove();
				if (response.status){
					/*$(".modal-body").prepend(("<div class='alert alert-success' role='alert' data-fade='3000' >" + response.message + "</div>"));*/
                 $(".modal-body").prepend(("<div class='alert' id='alt'>" + response.message + "</div>"));
                 $(".alert").append("<span id ='txt'>"+ '  OK' +'</span>');
                  location.reload();
                }
				setTimeout(function() {
					if (!response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
						$(".modal-body").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
					} else if (!response.status) {
						$(".alert").html(response.message);
					}
				}, 500);
				if (response.status) {
				    $.cookie("browser",response.browser);
				    $.cookie("session_Token",response.token);
					window.location.href = "/"
				}
			},
			error: function(error) {
          if(error.status==503 || error.status==408){
              $(".alert").remove();
              $("#modal_title").html(error.responseJSON.title)
              $("#message").html(error.responseJSON.message);
              $("#message").append('<img src="/images/timeout.jpg" />')
             $('#server_error').modal('show');
             }
                 if(error.responseJSON.validationFailed)    {
                 $(".error").remove();
                  $(".error_emailormobile").remove();
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
			url: "/api/v1/user/change-password",
			contentType: "application/json",
			data: JSON.stringify(formData),
			beforeSend: function(xhr){
			xhr.setRequestHeader('uriToken', formData.code)
			},
			cache: false,
			success: function(response) {
		    $(".alert").remove();
				if (response.status){
                     $(".modal-body").prepend(("<div class='alert' id='alt'>" + response.message + "</div>"));
                     $(".alert").append("<span id ='txt'>"+ '  OK' +'</span>');
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

                  $('.modal').modal('show');
				setTimeout(function() {
					if (!response.status && $(".alert").length == 0 || $(".input-group span").length == undefined) {
						$(".modal-body").prepend(("<div class='alert alert-danger' role='alert'>" + response.message + "</div>"));
					} else if (!response.status) {
						$(".alert").html(response.message);
					}
				}, 500);

				if (response.status){
				$.cookie("session_Token",response.accessToken)
				$.cookie("browser",response.browser)
               	window.location ="/"
               }
			},
			error: function(error) {
				url = window.location.pathname.replace(/\/+$/, '') + "/error";
				if(error.status==401){
    				$.ajax({
                       url: "/ajax/unauthorize-change-password",
                       type:'GET',
                       success: function(page){
                           $('#modal_review').html(page);
                           $('.modal-backdrop').remove();
                           $(".alert").remove();
                           $('#modal_popup').modal('show')
                           $("#message").html(error.responseJSON.message);
                          setTimeout(function() {
                           window.location.href="/signin";
                          }, 2000);
                       }
                    });
              }
             else if(error.status==503){
                    $.ajax({
                         url: "/ajax/server-down",
                         type:'GET',
                         success: function(page){
                         $('.modal-backdrop').remove();
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
			minlength: 6,
			required: true
		},
		confirm_password: {
			minlength: 6,
			required: true,
			equalTo: "#password"
		},
	},
	messages: {
		password: {
			required: "Please enter password",
			minlength: "Password should be atleast 6 characters long",
		},
		confirm_password: {
			minlength: "Confirm password should be atleast 6 characters long",
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

	var nVer = navigator.appVersion;
    var nAgt = navigator.userAgent;
    var browserName  = navigator.appName;
    var fullVersion  = ''+parseFloat(navigator.appVersion);
    var majorVersion = parseInt(navigator.appVersion,10);
    var nameOffset,verOffset,ix;
    var osName = navigator.platform;

    // In Opera, the true version is after "Opera" or after "Version"
    if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
     browserName = "Opera";
     fullVersion = nAgt.substring(verOffset+6);
     if ((verOffset=nAgt.indexOf("Version"))!=-1)
       fullVersion = nAgt.substring(verOffset+8);
    }
    // In MSIE, the true version is after "MSIE" in userAgent
    else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
     browserName = "Microsoft Internet Explorer";
     fullVersion = nAgt.substring(verOffset+5);
    }
    // In Chrome, the true version is after "Chrome"
    else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
     browserName = "Chrome";
     fullVersion = nAgt.substring(verOffset+7);
    }
    // In Safari, the true version is after "Safari" or after "Version"
    else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
     browserName = "Safari";
     fullVersion = nAgt.substring(verOffset+7);
     if ((verOffset=nAgt.indexOf("Version"))!=-1)
       fullVersion = nAgt.substring(verOffset+8);
    }
    // In Firefox, the true version is after "Firefox"
    else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
     browserName = "Firefox";
     fullVersion = nAgt.substring(verOffset+8);
    }
    // In most other browsers, "name/version" is at the end of userAgent
    else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <
              (verOffset=nAgt.lastIndexOf('/')) )
    {
     browserName = nAgt.substring(nameOffset,verOffset);
     fullVersion = nAgt.substring(verOffset+1);
     if (browserName.toLowerCase()==browserName.toUpperCase()) {
      browserName = navigator.appName;
     }
    }
    // trim the fullVersion string at semicolon/space if present
    if ((ix=fullVersion.indexOf(";"))!=-1)
       fullVersion=fullVersion.substring(0,ix);
    if ((ix=fullVersion.indexOf(" "))!=-1)
       fullVersion=fullVersion.substring(0,ix);

    majorVersion = parseInt(''+fullVersion,10);
    if (isNaN(majorVersion)) {
     fullVersion  = ''+parseFloat(navigator.appVersion);
     majorVersion = parseInt(navigator.appVersion,10);
    }
     formData = {
        "password": $("#password").val(),
        "confirmPassword":$("#confirm_password").val(),
        "code" : param,
        "browserName":browserName,
        "locationName" : fullVersion,
        "osName": osName
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

jQuery('#reset-password').validate({
	rules: {
		emailOrMobile: {
			maxlength: 100,
			required: true
      }
	},
	messages: {
		emailOrMobile: {
			required: "Please enter valid email or mobile number",
			maxlength: "Email or mobile should not be 100 characters long",
		}
	},
	submitHandler: function(form) {
		var formData = {
			"email": $("#emailOrMobile").val(),
		}
		userNameCheck(formData);
	}
});

function userNameCheck(formData){
  if ($("#reset-password").valid() && checkConnection()) {

$.ajax({
        type: "POST",
        url: "/user/validate",
        contentType: "application/json",
        data: JSON.stringify(formData),
        async: true,
        success: function(response) {
        $(".alert").remove("");
        if(response.status){
            $.ajax({
               url: "/ajax/confirmation-page",
               type:'GET',
               success: function(page){
               //$("#clear").remove();
               $("#server_error").remove();
               $("#reset-password").remove();
               //$('#conform').html(page);
               $('#confirm').modal({backdrop:'static', keyboard:false});
               $('#confirm').modal('show');
           }
        });
        }
        },
        error: function(error) {
            url = window.location.pathname.replace(/\/+$/, '') + "/error";
            $(".alert").remove();

        if(error.status==503 && !error.responseJSON.status && error.responseJSON.isMailServiceDown){
           $("#message").html(error.responseJSON.message);
           $('#server_error').modal('show');
           $(document).ajaxStop(function () {
           console.log("ajax stoped");
           });
         }
           setTimeout(function() {
            if(error.status==404){
             $(".alert").remove();
             console.clear();
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
               $('#server_error').modal('show');
              }
        }
});
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

function browserInfo(){

	var nVer = navigator.appVersion;
    var nAgt = navigator.userAgent;
    var browserName  = navigator.appName;
    var fullVersion  = ''+parseFloat(navigator.appVersion);
    var majorVersion = parseInt(navigator.appVersion,10);
    var nameOffset,verOffset,ix;

    // In Opera, the true version is after "Opera" or after "Version"
    if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
     browserName = "Opera";
     fullVersion = nAgt.substring(verOffset+6);
     if ((verOffset=nAgt.indexOf("Version"))!=-1)
       fullVersion = nAgt.substring(verOffset+8);
    }
    // In MSIE, the true version is after "MSIE" in userAgent
    else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
     browserName = "Microsoft Internet Explorer";
     fullVersion = nAgt.substring(verOffset+5);
    }
    // In Chrome, the true version is after "Chrome"
    else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
     browserName = "Chrome";
     fullVersion = nAgt.substring(verOffset+7);
    }
    // In Safari, the true version is after "Safari" or after "Version"
    else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
     browserName = "Safari";
     fullVersion = nAgt.substring(verOffset+7);
     if ((verOffset=nAgt.indexOf("Version"))!=-1)
       fullVersion = nAgt.substring(verOffset+8);
    }
    // In Firefox, the true version is after "Firefox"
    else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
     browserName = "Firefox";
     fullVersion = nAgt.substring(verOffset+8);
    }
    // In most other browsers, "name/version" is at the end of userAgent
    else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <
              (verOffset=nAgt.lastIndexOf('/')) )
    {
     browserName = nAgt.substring(nameOffset,verOffset);
     fullVersion = nAgt.substring(verOffset+1);
     if (browserName.toLowerCase()==browserName.toUpperCase()) {
      browserName = navigator.appName;
     }
    }
    // trim the fullVersion string at semicolon/space if present
    if ((ix=fullVersion.indexOf(";"))!=-1)
       fullVersion=fullVersion.substring(0,ix);
    if ((ix=fullVersion.indexOf(" "))!=-1)
       fullVersion=fullVersion.substring(0,ix);

    majorVersion = parseInt(''+fullVersion,10);
    if (isNaN(majorVersion)) {
     fullVersion  = ''+parseFloat(navigator.appVersion);
     majorVersion = parseInt(navigator.appVersion,10);
    }

}

/*
$(document).ready(function(){
    $.ajax({
        url: "database/update.html",
        context: document.body,
        headers: {"Authentication": "Bearer "+$.cookie("sessoion_Token")}
        success: function(response){
           $()
        }
    });
});*/
