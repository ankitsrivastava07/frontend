  $(document).ready(function(){
if (!!$.cookie('session_Token')) {
formData = {
    sessionToken: $.cookie("session_Token")
  }
  $.ajax({
      type: "POST",
      url: "/product/add-to-cart-count-products",
          data: formData,
          success: function(data){
             $("#product_count").html(data.productCount);
          }});
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
