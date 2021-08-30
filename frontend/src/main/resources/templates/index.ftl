<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" href="images/favicon.icon.png" type="image/ico">
  <title>e-ecommerce-shop </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="ecommerce/js/jquery.min.js"></script>
  <script src="ecommerce/js/cookie.js"></script>

  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800&display=swap"/>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/easyzoom@2.5.3/css/easyzoom.css" />
<script src="ecommerce/js/easyzoom.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="/css/jquery.bxslider.css">

      <!-- Bootstrap Dropdown Hover CSS -->
      <link href="css/animate.min.css" rel="stylesheet">
      <link href="css/bootstrap-dropdownhover.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.num {
      right: 11px;
      top: 6px;
      font-size: 25px;
      color: #fff;
}
#product_count{

        white-space: nowrap;
        text-align: center;
        line-height: 18px;
        padding: 0 6px;
        height: 18px;
        background: #ff3f6c;
        position: absolute;
        border-radius: 138%;
        font-size: 15px;
        color: #fff;
        top: auto;
}
.dropdown:hover>.dropdown-menu {
  display: block;
   margin-top: 0;
}

.new_customer{
 font-size: 11px;
}

</style>

</head>

<body>

<script src="ecommerce/js/main.js"></script>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="ecommerce/js/bootstrap.min.js"></script>
      <!-- Bootstrap Dropdown Hover JS -->
      <script src="ecommerce/js/bootstrap-dropdownhover.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container-fluid">
    <a class="navbar-brand" href=""><img src="/images/logo-dark3.png" class="img-fluid"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link text-light" aria-current="page" href="/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light" href="/">Mobile</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Fashion
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light" href="/" tabindex="-1" aria-disabled="true">Electronics</a>
		<li class="nav-item">
          <a class="nav-link text-light" href="/contact" tabindex="-1" aria-disabled="true">Contact Us</a>
        </li>
      </ul>

      <form class="d-flex" action="/" method="get">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-light" type="submit"><i class="fa fa-search"></i></button>
      </form>

      <!-- Bag Icon -->
<a href="/product/add-to-cart">
<i class="fa fa-shopping-bag num"></i>
<span id="product_count">0</span>
</a>

      <#if userName?has_content>
       <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Hello, ${userName}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                         <li><a href="/orders">Orders</a></li>
                         <li><a href="#">Wishlist</a></li>
                         <li><a href="#">Gift Cards</a></li>
                         <li><a href="#">Save Address</a></li>
                         <li><a href="/account">Your Account</a></li>
                         <li><a href="/signout?redirect=/signin">Sign out</a></li>
                         <li><a href="/signout-from-alldevices">Sign out from all devices</a></li>

          </ul>
        </li>
                 </div><!-- /.navbar-collapse -->
               </div><!-- /.container-fluid -->

	  <a href="/signin" class="btn btn-dark" data-bs-whatever="@mdo"></a>
      <#else>

 <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Hello, Sign in
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a href="/signin">Sign In</a></li>
                                          <li><a href="/orders">Orders</a></li>
                                          <li><a href="#">Wishlist</a></li>
                                          <li><a href="#">Gift Cards</a></li>
                                          <li><a href="#">Saved Address</a></li>
                                         <li>
                                          <span class="new_customer"> New Customer ?
                                           <a href="/register">Start Here</a></span>
                                           </li>
                                          <li><a href="/account">Your Account</a></li>
          </ul>
        </li>

          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->

      </#if>
    </div>
  </div>
</nav>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="margin-top:10px;">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="/images/Festive-Offers.jpg" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/images/depositphotos_250327322-stock-illustration-sale-get-up-to-50.jpg" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/images/E-commerce-banner2.jpg" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

	 <div class="home-product-slider">
	 <div id="head">
	 <h1>Furniture Bestsellers</h1>
	 <h3>Upto 70% off</h3>
	 </div>
	 <ul class="bxslider">

 <li class="bx-container">

  <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
			<img src="/images/harrison-2-seater_600x600.png">

        </div>
        </div>

 <div class="#">
 <div class="hover">
                            <p><strong>Inflatable Sofas</strong></p>
                             <p><small>Min 50% Off</small>
                               <small>Furn Central & more</small></p>

<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>
</div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
<img width="450" height="150"src="/images/marker-boss-chair.jpg"/></div>

 <div class="hover">
                           <p><strong>Office & Boss Chairs</strong></p>
                             <p><small>Min 50% Off</small>
                               <small>Fabric & Leatherette</small></p>
  <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
</div>
 </li>

 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
<img src="/images/login-bg.jpg"/> </div>

 <div class="hover">
                             <p><strong>Furniture</strong></p>
                            <p><small>From &#8377; 10,000</small></p>
							   </p>

<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>
 </div>

 </div>

 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
<img width="450" height="150"src="/images/jhula.jpg"/> </div>

 <div class="hover">
                             <p><strong>Hammock & Swings</strong></p>
                             <p><small>From ₹299</small>
                              <small>MT HUB, Swingzy...</small></p>



<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
<img width="170" height="170"src="/images/branded-smart-non-smart-led-t-v-500x500.jpg" style="margin-top:40px!important;"> </div>
 <div class="hover">
                             <p><strong>Trending Smart TVs</strong></p>
                             <p><small>Up to 65% Off</small
                               <small>Android | Google Assistant</small></p>
   <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
 </div>
 </li>


 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
            <div class="correct-item item">
<img width="250" height="150"src="/images/58.jpg"/></div>

 <div class="hover">
                             <p><strong>Study Table</strong></p>
                             <p><small>From ₹ 3,2901</small>
                               <small>Made in india Brands</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
</div>

</li>


 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item">
 <a onclick="ImageClick()">
<img src="/images/00739013_fast-shipping.jpg" id="tv_1" ></div>
 </a>


 <div class="hover">
                             <p><strong>Wakefit Athena <br> Study Table</strong></p>
                             <p><small>₹5,501</small>
                               <small>Free delivery</small></p>
       <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
 </div>
 </li>
 </ul>
 </div>
 <div class="home-product-slider">
 <div id="head">
	<h1>Up to 70% off | Electronics</h1>
	 <h3>Devices and Accessories</h3>
	 </div>

	 <ul class="bxslider">

 <li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">

 <img src="/images/600x600.jpg"/></div>


 <div class="hover">
                             <p><strong>Inflatable Sofas</strong></p>
                             <p><small>Min 50% Off</small>
                              <small>Furn Central & more</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>

  </div>
 </li>

 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
<img src="/images/MI.png"/></div>

 <div class="hover">
                             <p><strong>Office & Boss Chairs</strong></p>
                             <p><small>Min 50% Off</small>
                               <small>Fabric & Leatherette</small></p>
     <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
 </div>
 </li>

 <li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/gionee_s11_gylite.jpg"/></div>

 <div class="hover">
                            <p><strong>Sewing Machines</strong></p>
                             <p><small>Shop Now!</small>
							 <small>Usha, Singer & more</small></p>
    <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
</div>
</li>

 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/07.jpg"/></div>
 <div class="hover">
                             <p><strong>Top 20 Inflatable Sofa</strong></p>
                             <p><small>From ₹1,169</small>
                               <small>Cozy Corner</small></p>
    <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/10.jpg"/></div>

 <div class="hover">
                             <p><strong>Trending Smart TVs</strong></p>
                             <p><small>Up to 65% Off</small>
                              <small>Android | Google Assistant</small></p>
     <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
  </div>
 </li>


 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/V238940049_IN_PC_BAU_Edit_Creation_Laptops1x._SY304_CB667377205_.jpg"/></div>

 <div class="hover">
                             <p><strong>Electronics clearance store</strong></p>
                             <p><small>Up to 70% off</small>
                               <small>Flat, Round, Cube & more</small></p>

							 <small>Trendy Collection</small></p>
    <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>
 </div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/65.jpg"/></div>

<div class="hover">
                             <p><strong>Wakefit Athena <br> Study Table</strong></p>
                             <p><small>₹5,501</small>
 							 <small>Trendy Collection</small></p>
							 <small>Free delivery</small></p>
    <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
</div>
 </li>
<li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/41AwYdB4C0L._AC_SY200_.jpg"/></div>

 <div class="hover">
                             <p><strong>Wakefit Athena <br> Study Table</strong></p>
                             <p><small>₹5,501</small>
   							 <small>Trendy Collection</small></p>
                             <small>Free delivery</small></p>
    <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
</div>
 </li>
 </ul>
</div>
 <div id="head">
	 <h1>Home Top Deals</h1>
	<h3>From ₹99</h3>
	 </div>




	 <ul class="bxslider">


 <li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/41AwYdB4C0L._AC_SY200_.jpg"/></div>



 <div class="#">
 <div class="hover">
                             <p><strong>Inflatable Sofas</strong></p>
                             <p><small>Min 50% Off</small>
                               <small>Furn Central & more</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
 </div>
 </div>
 </li>

<li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/rr-104-status-original-imaffpe9a7c59rzq.jpeg"/></div>

 <div class="hover">
                             <p><strong>Office & Boss Chairs</strong></p>
                             <p><small>Min 50% Off</small>
                              <small>Fabric & Leatherette</small></p>

<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 <div>
 </div>
 </li>

 <li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/in-one-door-rr20t172yr8hl-rr20t172yr8-hl-rperspectivepeachred-thumb-227094079.jpg"/></div>

 <div class="hover">
                             <p><strong>Sewing Machines</strong></p>
                             <p><small>Shop Now!</small>
                               <small>Usha, Singer & more</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


</div>

 </div>

</li>
<li class="bx-container">
<div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/pwb000237748.jpg"/></div>


<div class="hover">
                             <p><strong>Hammock & Swings</strong></p>
                             <p><small>From ₹299</small>
                               <small>MT HUB, Swingzy...</small></p>



<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>



 </div>
 </div>
 </li>

 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/Sofa.jpg"/></div>



 <div class="hover">
                             <p><strong>Top 20 Inflatable Sofa</strong></p>
                             <p><small>From ₹1,169</small>
                               <small>Cozy Corner</small></p>
  <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/71sCQDl4i7L._AC_SS450_.jpg"/></div>
 <div class="hover">
                             <p><strong>Trending Smart TVs</strong></p>
                             <p><small>Up to 65% Off</small>
                              <small>Android | Google Assistant</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
</div>
 </li>

  <li class="bx-container">
  <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/butterfly-arrow-original-imafcz43zxwekpzc.jpeg"/></div>


 <div class="hover">
                          <p><strong>Home Temples</strong></p>
                            <p><small>From ₹399</small>
                              <small>Trendy Collection</small></p>

<div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>

 </div>
 </div>
</li>
 <li class="bx-container">

 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/ac-sticker-fridge-sticker-wall-sticker-split-ac-stickers-air-original-imafzhvvv3dhauah.jpeg"/></div>


 <div class="hover">
                           <p><strong>Study Table</strong></p>
                             <p><small>From ₹ 3,2901</small>
                               <small>Made in india Brands</small></p>
   <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
</div>

 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/65.jpg"/></div>


 <div class="hover">
                             <p><strong>Aquarium Tank</strong></p>
                             <p><small>From ₹299</small>
                               <small>Flat, Round, Cube & more</small></p>
  <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
</div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/blue-polycotton-ph0003-perfect-homes-by-flipkart-blue-original-imaf6dhhsfhvvjdn.jpeg"/></div>
 <div class="hover">
                         <p><strong>Wakefit Athena <br> Study Table</strong></p>
                            <p><small>₹5,501</small>
                               <small>Free delivery</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


</div>
 </div>
 </li>
 <li class="bx-container">
 <div class="correct-wrapper item-wrapper">
 <div class="correct-item item">
 <img src="images/58.jpg"/></div>
 <div class="hover">

                            <p><strong>Wakefit Athena <br> Study Table</strong></p>
                             <p><small>₹5,501</small>
                               <small>Free delivery</small></p>
 <div class="shop-add-btn-cont">
                    <a class="button medium gray-light shop-add-btn btn btn-outline-light" href="#">See More</a>
                  </div>


 </div>
 </div>
 </li>
</ul>
<!-- Footer -->

<footer class="bg-success text-white">
  <!-- Grid container -->
  <div class="container p-4">
    <!-- Section: Social media -->
    <section class="mb-4 text-center">
      <!-- Facebook -->
      <a class="btn btn-outline-light btn-floating m-1" href="https://www.facebook.com/" role="button"
        ><i class="fa fa-facebook-f"></i
      ></a>

      <!-- Twitter -->
      <a class="btn btn-outline-light btn-floating m-1" href="https://twitter.com/" role="button"
        ><i class="fa fa-twitter"></i
      ></a>

      <!-- Google -->
      <a class="btn btn-outline-light btn-floating m-1" href="https://www.google.com/" role="button"
        ><i class="fa fa-google-plus"></i
      ></a>

      <!-- Instagram -->
      <a class="btn btn-outline-light btn-floating m-1" href="https://www.instagram.com/" role="button"
        ><i class="fa fa-instagram"></i
      ></a>
    </section>
    <!-- Section: Social media -->

    <!-- Section: Form -->
    <section class="">
      <form action="">
        <!--Grid row-->
        <div class="row d-flex justify-content-center">
          <!--Grid column-->
          <div class="col-auto">
            <p class="pt-2">
			<a href="">

            </a><strong>Sign up for our newsletter</strong>
            </p>
          </div>
          <!--Grid column-->

          <!--Grid column-->
          <div class="col-md-5 col-12">
            <!-- Email input -->
            <div class="form-outline form-white mb-4">
              <input type="email" id="form5Example2" class="form-control" placeholder="Enter Your Email address"/>
            </div>
          </div>
          <!--Grid column-->

          <!--Grid column-->
          <div class="col-auto">
            <!-- Submit button -->
            <button type="submit" class="btn btn-outline-light mb-4">Subscribe
            </button>

          </div>
          <!--Grid column-->
        </div>
        <!--Grid row-->
      </form>
    </section>
    <!-- Section: Links -->
    <section class="">
      <!--Grid row-->
      <div class="row">
        <!--Grid column-->
        <div class="col-lg-3 col-md-6 mb-4 mb-md-0">

          <h5 class="text-uppercase">Home</h5>

          <ul class="list-unstyled mb-0">
            <li>
              <a href="#!" class="text-white">Mobile</a>
            </li>
            <li>
              <a href="#!" class="text-white">Fashion</a>
            </li>
            <li>
              <a href="/" class="text-white">Electronics</a>
            </li>
            <li>
              <a href="#!" class="text-white">Accessories</a>
            </li>
          </ul>
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase">About Us</h5>

          <ul class="list-unstyled mb-0">
            <li>
              <a href="#!" class="text-white">Who we are</a>
            </li>
            <li>
              <a href="#!" class="text-white">About us</a>
            </li>
            <li>
              <a href="#!" class="text-white">Privacy policy</a>
            </li>
            <li>
              <a href="#!" class="text-white">myecommerce.com</a>
            </li>
          </ul>
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase">About us</h5>

          <ul class="list-unstyled mb-0">
            <p>We are a young Profile always looking for new and creative ideas to help you with our products in your everyday work.</p>
          </ul>
        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase">Connect with Us</h5>

          <ul class="list-unstyled mb-0">
            <li>
              <a href="https://www.facebook.com/" class="text-white">Facebook</a>
            </li>
            <li>
              <a href="https://www.instagram.com/" class="text-white">Instagram</a>
            </li>
            <li>
              <a href="https://twitter.com/"class="text-white">Twitter</a>
            </li>
            <li>
              <a href="https://www.google.com/" class="text-white">Google +</a>
            </li>
          </ul>
        </div>
        <!--Grid column-->
      </div>
      <!--Grid row-->
    </section>
    <!-- Section: Links -->
  </div>
  <!-- Grid container -->

  <!-- Copyright -->
  <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
    &copy;2021
    <a class="text-white" href="#">myecommerce.com</a>
  </div>
  <!-- Copyright -->
</footer>


<!-- Footer -->
<script src="ecommerce/js/bootstrap.bundle.min.js"></script>
<script src="ecommerce/js/jquery.bxslider.min.js"></script>


<script>
jQuery(document).ready(function() {
  jQuery('.bxslider').bxSlider({
    minSlides: 1,
    maxSlides: 4,
    slideWidth: 350,
	 height:450,
    slideMargin:40,
	padding:30,
	slideZIndex: 50,
    responsive: true
  });

 });

function ImageClick(){
 alert("hh");

}

</script>
</body>
</html>
