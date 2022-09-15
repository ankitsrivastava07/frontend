$(document).ready(function(){
if (!!$.cookie('session_Token')) {
formData = {
    sessionToken: $.cookie("session_Token")
  }
  $.ajax({
      type: "POST",
      url: "api/v1/user/product/add-to-cart-count-products",
      beforeSend: function(request) {
        request.setRequestHeader("Authentication", $.cookie("session_Token"));
        request.setRequestHeader("browser", $.cookie("browser"));
       },
          success: function(data){
             $("#product_count").html(data.productCount);
          }});
      }

if (!!$.cookie('session_Token') && !!$.cookie('browser')) {
  $.ajax({
      type: "POST",
      url: window.location.href,
      beforeSend: function(request) {
      request.setRequestHeader("Authentication", $.cookie("session_Token"));
      request.setRequestHeader("browser", $.cookie("browser"));
      },
      success: function(data){
      console.log("successfully sent");
    },
       error: function(error) {
       console.log(error.responseJSON.message);
 }
    });
}
});
/*
jQuery(document).ready(function() {
$.ajax
  ({
    type: "POST",
    url: "/validate-session",
    headers: {"Authentication": $.cookie("session_Token")},
    success: function (response){
       $("#signin").attr("href", "/signout");
       $("#signin").text("Sign out");
       $.cookie("session_Token",response.accessToken);
        $("#name").html("Hello "+response.firstName);
    }
});
});*/
