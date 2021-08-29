/*
$.get("/add-to-cart-count-products", function(data, status){
    alert("Data: " + data + "\nStatus: " + status);
  });
*/

  $(document).ready(function(){

if (!!$.cookie('session_Token')) {

formData = {
    sessionToken: $.cookie("session_Token")
  }

  $.ajax({
      url: "/product/add-to-cart-count-products",
          data: formData,
          success: function(data){
             $("#product_count").html(data.productCount);
          }});
 }
  });
