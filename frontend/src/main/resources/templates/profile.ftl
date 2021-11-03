
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="/login-form/js/jquery.min.js"></script>
<script src="/login-form/js/validate.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<!------ Include the above in your HEAD tag ---------->
<head>
  <title>Profile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<style>
#phone{
 cursor: pointer;
}
.alert{
text-align: center;
}
#phone_verification{
display:none;
}
.error{
    font-size: 12px;
    color: #da534d;
    padding: 3px 0px;
}
</style>
<link rel="stylesheet" href="/css/style.css">

<!-- Bootstrap Dropdown Hover JS -->
      <script src="/ecommerce/js/bootstrap-dropdownhover.min.js"></script>

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
                       <li><a href="/users/profile">Edit Profile</a></li>
                       <li><a href="/signout?redirect=/signin">Sign out</a></li>
                       <li><a href="/signout-from-all-devices?redirect=/signin"">Sign out from all devices</a></li>
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
                  </#if>
                 </div><!-- /.navbar-collapse -->
               </div><!-- /.container-fluid -->
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
  </div>
</nav>

<hr>
<#if userDto?has_content>
<div class="container bootstrap snippet">
    <div class="row">
  		<div class="col-sm-3"><!--left col-->
      <div class="text-center">
        <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
        <h4>${userDto.firstName} ${userDto.lastName}</h4>
        <input type="file" class="text-center center-block file-upload">
      </div></hr><br>

          <div class="panel panel-default">
            <div class="panel-heading">Website <i class="fa fa-link fa-1x"></i></div>
            <div class="panel-body"><a href="http://bootnipets.com">bootnipets.com</a></div>
          </div>

          <ul class="list-group">
            <li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span> 125</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span> 13</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span> 37</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span> 78</li>
          </ul>

          <div class="panel panel-default">
            <div class="panel-heading">Social Media</div>
            <div class="panel-body">
            	<i class="fa fa-facebook fa-2x"></i> <i class="fa fa-github fa-2x"></i> <i class="fa fa-twitter fa-2x"></i> <i class="fa fa-pinterest fa-2x"></i> <i class="fa fa-google-plus fa-2x"></i>
            </div>
          </div>

        </div><!--/col-3-->
    	<div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab">Profile</a></li>
              </ul>

<!-- Modal -->
<div class="modal fade" id="server_error" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modal_title">Website Under Maintenance</h5>
      </div>
      <div class="modal-body">
        <span id="message">
         Our website is currently undergoing scheduled maintenance .We'll be here soon with our new awesome site or function subscribe to get notified
        </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="close" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="signin">Sign</button>
      </div>
    </div>
  </div>
</div>

          <div class="tab-content" id="update_profile_popup">
            <div class="tab-pane active" id="home">
                <hr>
                  <form class="form" id="profile" method="post" name="profile">
                      <div class="form-group" id="formGroup">

                          <div class="col-xs-6">
                              <label for="first_name"><h4>First name</h4></label>
                              <input type="text" class="form-control" name="firstName" id="firstName" title="${userDto.firstName}" value="${userDto.firstName}">
                          </div>
                      </div>
                      <div class="form-group">
                          <div class="col-xs-6">
                            <label for="last_name"><h4>Last name</h4></label>
                              <input type="text" class="form-control" name="lastName" id="lastName" placeholder="last name" title="${userDto.lastName}" value="${userDto.lastName}">
                          </div>
                      </div>

                      <div class="form-group">

                          <div class="col-xs-6">
                              <label for="phone"><h4>Phone</h4></label>
							  <input type="text" class="form-control" name="phone" id="phone" title="${userDto.mobile}" value="${userDto.mobile}">
                          </div>
                      </div>

                      <div class="form-group">
                          <div class="col-xs-6">
                             <label for="mobile"><h4>Alternate Mobile number</h4></label>
                              <input type="text" class="form-control" name="alternate_mobile" id="alternate_mobile" placeholder="enter mobile number" title="<#if userDto.alternateMobile?has_content>${userDto.alternateMobile}</#if>" value=<#if userDto.alternateMobile?has_content>${userDto.alternateMobile}</#if>>
                          </div>
                      </div>
                      <div class="form-group">

                          <div class="col-xs-6">
                              <label for="email"><h4>Email</h4></label>
                              <input type="email" class="form-control" name="email" id="email" title=<#if userDto.email?has_content>${userDto.email}</#if> value = <#if userDto.email?has_content>${userDto.email}</#if>>
                          </div>
                      </div>
                      <div class="form-group">

                          <div class="col-xs-6">
                              <label for="email"><h4>Address</h4></label>
                              <input type="text" class="form-control" id="address" placeholder="Enter your address"  name="address" value="${address}" >
                          </div>
                      </div>
                      <div class="form-group">
                           <div class="col-xs-12">
                                <br>
                              	<button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Submit</button>
                            </div>
                      </div>
              	</form>

              <hr>

             </div><!--/tab-pane-->

              </div><!--/tab-pane-->

	<div id="phone_verification">
            <div class="tab-pane active" id="home">
                <hr>
                  <form class="form" method="post" id="update_mobile" name="update_mobile">
                      <div class="form-group">

                          <div class="col-xs-6">
                              <label for="mobile"><h4>Mobile</h4></label>
                              <input type="text" class="form-control" name="mobile" id="mobile">
                          </div>
                      </div>

                      <div class="form-group">
                           <div class="col-xs-12">
                                <br>
                              	<button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Submit</button>
                              	<button class="btn btn-lg" type="submit">Back</button>
                            </div>
                      </div>
              	</form>
              <hr>
             </div><!--/tab-pane-->
          </div><!--/tab-content-->
        </div><!--/col-9-->
    </div><!--/row-->
    <#else>
    <div class="container bootstrap snippet">
        <div class="row">
      		<div class="col-sm-3"><!--left col-->
          <div class="text-center">
            <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
            <h6></h6>
            <input type="file" class="text-center center-block file-upload">
          </div></hr><br>

              <div class="panel panel-default">
                <div class="panel-heading">Website <i class="fa fa-link fa-1x"></i></div>
                <div class="panel-body"><a href="http://bootnipets.com">bootnipets.com</a></div>
              </div>

              <ul class="list-group">
                <li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span> 125</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span> 13</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span> 37</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span> 78</li>
              </ul>

              <div class="panel panel-default">
                <div class="panel-heading">Social Media</div>
                <div class="panel-body">
                	<i class="fa fa-facebook fa-2x"></i> <i class="fa fa-github fa-2x"></i> <i class="fa fa-twitter fa-2x"></i> <i class="fa fa-pinterest fa-2x"></i> <i class="fa fa-google-plus fa-2x"></i>
                </div>
              </div>

            </div><!--/col-3-->
        	<div class="col-sm-9">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab">Profile</a></li>
                  </ul>

              <div class="tab-content" id="update_profile_popup">
                <div class="tab-pane active" id="home">
                    <hr>
                      <form class="form" action="##" method="post" id="registrationForm">
                          <div class="form-group">

                              <div class="col-xs-6">
                                  <label for="first_name"><h4>First name</h4></label>
                                  <input type="text" class="form-control" name="first_name" id="first_name" title="" value=>
                              </div>
                          </div>
                          <div class="form-group">

                              <div class="col-xs-6">
                                <label for="last_name"><h4>Last name</h4></label>
                                  <input type="text" class="form-control" name="last_name" id="last_name" placeholder="last name" title="" value="">
                              </div>
                          </div>

                          <div class="form-group">

                              <div class="col-xs-6">
                                  <label for="phone"><h4>Phone</h4></label>
    							  <input  a href="update.html" class="form-control" name="phone" id="phone" title="" value="">
                              </div>
                          </div>

                          <div class="form-group">
                              <div class="col-xs-6">
                                 <label for="mobile"><h4>Alternate Mobile number</h4></label>
                                  <input type="text" class="form-control" name="mobile" id="alternate_mobile" placeholder="enter mobile number" >
                              </div>
                          </div>
                          <div class="form-group">

                              <div class="col-xs-6">
                                  <label for="email"><h4>Email</h4></label>
                                  <input type="email" class="form-control" name="email" id="email" title= value>
                              </div>
                          </div>
                          <div class="form-group">

                              <div class="col-xs-6">
                                  <label for="email"><h4>Address</h4></label>
                                  <input type="email" class="form-control" id="location" placeholder="somewhere" title="enter a location" >
                              </div>
                          </div>
                          <div class="form-group">

                          </div>

                          <div class="form-group">
                               <div class="col-xs-12">
                                    <br>
                                  	<button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Submit</button>
                                </div>
                          </div>
                  	</form>

                  <hr>

                 </div><!--/tab-pane-->

                  </div><!--/tab-pane-->

    	<div id="phone_verification">
                <div class="tab-pane active" id="home">
                    <hr>
                          <div class="form-group">
                              <div class="col-xs-6">
                                  <label for="mobile"><h4>Mobile</h4></label>
                                  <input type="text" class="form-control" name="mobile" id="mobile">
                              </div>
                          </div>
                          <div class="form-group">
                               <div class="col-xs-12">
                                    <br>
                                  	<button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Submit</button>
                                </div>
                          </div>
                  <hr>
                 </div><!--/tab-pane-->
              </div><!--/tab-content-->
            </div><!--/col-9-->
        </div><!--/row-->
    </#if>
    <script src="/ecommerce/js/cookie.js"></script>
    <script src="/update/update.js"></script>
    <script>
    $(document).ready(function(){
      $("#phone").click(function(){
		$("#update_profile_popup").hide();
		$("#phone_verification").show();
      });
    });
    </script>