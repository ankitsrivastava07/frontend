function updateUser(e){return!!checkConnection()&&($.ajax({type:"POST",url:"/api/v1/user/profile/edit",contentType:!1,processData:!1,data:e,beforeSend:function(e){e.setRequestHeader("AuthToken","Bearer "+$.cookie("session_Token")),e.setRequestHeader("browser",$.cookie("browser"))},success:function(e){$(".alert").remove(),setTimeout(function(){e.status&&0==$(".alert").length||null==$(".input-group span").length?($("#formGroup").prepend("<div class='alert'>"+e.message+"</div>"),$(".alert").append("<span id ='txt'>  OK</span>")):e.status||$(".alert").html(e.message),location.reload()},400),e.status||!e.alternateMobileAlreadyExist&&!e.emailAlreadyExist||0!=$(".alert").length&&null!=$(".input-group span").length?e.status||$(".alert").html(e.message):$("#formGroup").prepend("<div class='alert'>"+e.message+"</div>"),setTimeout(function(){$(".alert").remove(),location.reload()},5500)},error:function(e){url=window.location.pathname.replace(/\/+$/,"")+"/error",$(".alert").remove(),503==e.status?($("#server_error").modal("show"),$(document).ajaxStop(function(){console.log("ajax stoped")})):401==e.status&&!e.responseJSON.status&&e.responseJSON.isMailServiceDown?($("#message").html(e.responseJSON.message),$("#server_error").modal("show"),$(document).ajaxStop(function(){console.log("ajax stoped")})):401==e.status&&e.responseJSON.isSessionExpired?($("#modal_title").html(e.responseJSON.errorCode),$("#message").html(e.responseJSON.message),$("#server_error").modal("show"),$(document).ajaxStop(function(){console.log("ajax stoped")}),$("body").click(function(e){$(e.target).closest("#server_error").length||$(e.target).is("#server_error")||location.reload()}),setTimeout(function(){location.reload()},5500)):400!=e.status||e.responseJSON.validFile||($("#file_error").html(e.responseJSON.message),$(document).ajaxStop(function(){console.log("ajax stoped")}))}}),!0)}function checkConnection(){return $.ajax("/check-connection",{statusCode:{0:function(){return alert(" We can’t connect to the server please check your internet connection or the page which you are looking for has been removed."),!1}}}),!0}$(document).ready(function(){jQuery.validator.addMethod("filesize",function(e,t,r){return 0==t.files.length||t.files[0].size<3e6},"Image size must be less than 3MB ")}),$(document).ready(function(){"use strict";$("#profile").validate({rules:{firstName:{required:!0},lastName:{required:!0},email:{required:!0},alternate_mobile:{required:!1},address:{required:!1},imageUpload:{required:!1,filesize:!0}},messages:{firstName:{required:"Please enter your first name"},lastName:{required:"Please enter your last name"},email:{required:"Please enter your email"}},submitHandler:function(e){var t=$("#profile")[0],r=new FormData(t);r.append("image",$("#imageUpload")[0].files[0]),$("#file_error").html(""),updateUser(r)}})}),$(document).ready(function(){imageUpload.onchange=(e=>{const[t]=imageUpload.files;t&&(img.src=URL.createObjectURL(t))}),$("#file-upload").change(function(){$(this).prev("label").clone();var e=$("#file-upload")[0].files[0].name;$(this).prev("label").text(e)})}),$(document).ready(function(){$("#txt").click(function(){alert("Hello")})}),$(document).ready(function(){$("#close").click(function(){$("#server_error").modal("hide")})});