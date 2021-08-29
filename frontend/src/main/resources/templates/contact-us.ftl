<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" href="images/favicon.icon.png" type="image/ico">
  <title>e-commerce-shop</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="js/jquery.min.js"></script> 
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/easyzoom@2.5.3/css/easyzoom.css" />
<script src="https://cdn.jsdelivr.net/npm/easyzoom@2.5.3/src/easyzoom.js"></script>
  
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"crossorigin="anonymous">
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js""anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/jquery.bxslider.css">


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.html"><img src="images/logo-dark3.png" class="img-fluid"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link text-light" aria-current="page" href="">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light" href="product-details.html">Mobile</a>
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
          <a class="nav-link text-light" href="#" tabindex="-1" aria-disabled="true">Electronics</a>
		<li class="nav-item">
          <a class="nav-link text-light" href="Contact.html" tabindex="-1" aria-disabled="true">Contact Us</a>
        </li>		
      </ul>
	  
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-light" type="submit"><i class="fa fa-search"></i></button>
      </form>
	  <button type="button text-light" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Sign in</button>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Sign In
</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">Your Email *</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">Your Password *</label>
             <input type="password" class="form-control" id="password">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Login</button>
        <button type="button" class="btn btn-dark">Sign Up</button>
      </div>
    </div>
  </div>
</div>
    </div>
  </div>
</nav>
</nav>
  <section class="ftco-section">
		<div class="container-fluid">
			<div class="row justify-content-center">
				
			</div>
			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="wrapper img" style="background:url(images/img.jpg);border-top: 6px solid #aba7a6;
">
					<div id="box">
					
						<div class="row no-gutters mb-5">
						
							<div class="col-md-7">

								<div class="contact-wrap w-100 p-md-5 p-4">
									<h1 class="headin-section">Contact Us</h1>
									<div id="form-message-warning" class="mb-4"></div> 
				      		<div id="form-message-success" class="mb-4">
				            Your message was sent, thank you!
				      		</div>
									<form method="POST" id="contactForm" name="contactForm" class="contactForm">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group ">
													<label class="label" for="name">Full Name</label>
													<input type="text" class="form-control" name="name" id="name" placeholder="Name">
												</div>
											</div>
											<div class="col-md-6"> 
												<div class="form-group">
													<label class="label" for="name">Email Address</label>
													<input type="email" class="form-control" name="email" id="email" placeholder="Email">
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="label" for="name">Subject</label>
													<input type="text" class="form-control" name="subject" id="subject" placeholder="Subject">
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label class="label" for="#">Message</label>
													<textarea name="message" class="form-control" id="message" cols="30" rows="4" placeholder="Message"></textarea>
												</div>
											</div>
											<div class="col-md-12 text-center p-5 w-100">
												<div id="form-group">
													<input type="submit text-center" value="Send Message" class="btn btn-warning text-light " style="border-radius: 30px; padding:10px 30px 15px 20px;" >
													<div class="submitting"></div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="col-md-5 d-flex align-items-stretch p-5 " style="width:500px; margin-top:110px; 
	height:300px;">
								<div id="leftwards"><p>
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut nibh lobortis sapien tincidunt imperdiet vitae at lacus. Cras ornare dolor in ex vulputate elementum. Cras sed eleifend tortor. Curabitur porta dolor sit amet mi viverra iaculis. Aenean pulvinar leo et sapien tristique, a mattis ipsum ultricies. Vivamus ultricies massa quam, ut pharetra quam laoreet ac. Aenean eleifend nec diam vitae rhoncus. Morbi vel turpis id justo faucibus rutrum quis vitae diam. Nulla aliquam quam nec nulla ullamcorper imperdiet. Morbi sit amet augue a ligula condimentum auctor sollicitudin at diam. Praesent ac tempor velit. Suspendisse nec sapien fermentum, pharetra nisi a, cursus tellus. In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus.
			          </div></p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="dbox w-100 text-center">
			        		<div class="icon d-flex align-items-center justify-content-center">
			        			<span class="fa fa-map-marker fa-lg"></span>
			        		</div>
			        		<div class="text">
				            <p><span>Address:</span> 198 West 21th Street, Suite 721 New York NY 10016</p>
				          </div>
			          </div>
							</div>
							<div class="col-md-3">
								<div class="dbox w-100 text-center">
			        		<div class="icon d-flex align-items-center justify-content-center">
			        			<span class="fa fa-phone fa-lg"></span>
			        		</div>
			        		<div class="text">
				            <p><span>Phone:</span> <a href="tel://1234567920">+ 1235 2355 98</a></p>
				          </div>
			          </div>
							</div>
							<div class="col-md-3">
								<div class="dbox w-100 text-center">
			        		<div class="icon d-flex align-items-center justify-content-center">
			        			<span class="fa fa-paper-plane fa-lg"></span>
			        		</div>
			        		<div class="text">
				            <p><span>Email:</span> <a href="mailto:info@yoursite.com">info@yoursite.com</a></p>
				          </div>
			          </div>
							</div>
							<div class="col-md-3">
								<div class="dbox w-100 text-center">
			        		<div class="icon d-flex align-items-center justify-content-center">
			        			<span class="fa fa-globe fa-lg"></span>
			        		</div>
			        		<div class="text">
				            <p><span>Website</span> <a href="#">yoursite.com</a></p>
				          </div>
			          </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
		
	</section>
     
   <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d224369.03563213558!2d77.25804092082214!3d28.51668170736545!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390ce5a43173357b%3A0x37ffce30c87cc03f!2sNoida%2C%20Uttar%20Pradesh!5e0!3m2!1sen!2sin!4v1624231679895!5m2!1sen!2sin" width="100%" height="350" style="border:0;" allowfullscreen="" loading="lazy"></iframe>   


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
              <a href="#!" class="text-white">Electronics</a>
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

 
<script src="js/jquery.bxslider.min.js"></script>	
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

 
</script>

</body>
</html>