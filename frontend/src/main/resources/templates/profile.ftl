
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
button.btn.btn-primary.loginButton{
    outline-color: #4a90e2;
    margin-top: 15px;
    text-transform: capitalize;
    width: 20%;
    display: inline-block;
    background-color: #4a90e2;
    /* border-radius: 2px; */
    /* -webkit-box-shadow: 0 2px 6px 0 rgb(0 0 0 / 20%); */
    box-shadow: 0 2px 6px 0 rgb(0 0 0 / 20%);
    min-width: 123px;
    padding: 8px 16px;
    height: 36px;
    color: #ffffff;
    font-weight: 500;
    text-align: center;
    line-height: 18px;
    cursor: pointer;
    font-size: 14px;
    border-width: 0;
}

#phone{
 cursor: pointer;
}

span#txt {
color: salmon;
cursor: pointer;
}

.alert{
text-align: center;
background-color: #545353;
border-color: #545353;
padding: 8px;
color: white;
font-weight: bold;
margin-left: auto;
margin-right: auto;
width: fit-content;
font-size : small;
box-shadow: 0 25px 15px 0 rgb(0 0 0 / 40%);
}

#phone_verification{
display:none;
}
.error{
    font-size: 12px;
    color: #da534d;
    padding: 3px 0px;
}
.circular--square {
 border-radius: 50%;
     width: 50%;
     height: 120px;
     width: 60px;
     height: 60px;
     -webkit-border-radius: 60px;
     -webkit-background-clip: padding-box;
     -moz-border-radius: 60px;
     -moz-background-clip: padding;
     border-radius: 60px;
     background-clip: padding-box;
     /* margin: 7px 0 0 5px; */
     /* float: left; */
     background-size: cover;
     /* background-position: center center;
}
.login-layer .btn-primary {
    margin-top: 30px;
    text-transform: capitalize;
    width: 100%;
    display: inline-block;
    background-color: #4a90e2;
    border-radius: 2px;
    -webkit-box-shadow: 0 2px 6px 0 rgb(0 0 0 / 20%);
    box-shadow: 0 2px 6px 0 rgb(0 0 0 / 20%);
    min-width: 123px;
    padding: 8px 16px;
    height: 36px;
    color: #ffffff;
    font-weight: 500;
    text-align: center;
    line-height: 18px;
    cursor: pointer;
    font-size: 14px;
    border-width: 0;
}
.login-layer .loginButton {
    margin-bottom: 11px;
}
</style>
<body>

<!-- Modal -->
<div class="modal fade" id="server_error" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modal_title">Website Under Maintenance</h5>
      </div>
      <div class="modal-body" id="message">
        Our website is currently undergoing scheduled maintenance .We'll be here soon with our new awesome site or function subscribe to get notified
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="close" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="signin">Sign</button>
      </div>
    </div>
  </div>
</div>

<hr>
<#if userDto?? && userDto?has_content>
<div class="container bootstrap snippet">
    <div class="row">
  		<div class="col-sm-3"><!--left col-->
      <div class="text-center">
      <#if fileStream?has_content>
        <img src="${fileStream}" class="avatar img-circle img-thumbnail" id="img">
        <input type="file" accept="image/*" class="text-center center-block file-upload" name="imageUpload" id="imageUpload" onchange="validateFileExtension(this.value)">
        <span id="file_error" class="error"></span>
        <#else>
        <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar" id="img">
       <input type="file" class="text-center center-block file-upload" name="imageUpload"  id="imageUpload">
       <span id="file_error" class="error"></span>
        </#if>
        <h4>${userDto.firstName} ${userDto.lastName}</h4>
      </div></hr><br>

          <div class="panel panel-default">
            <div class="panel-heading">Website <i class="fa fa-link fa-1x"></i></div>
            <div class="panel-body"><a href="http://bootnipets.com">bootnipets.com</a></div>
          </div>

         <div class="panel panel-default">
            <div class="panel-heading">Orders <i class="fa fa-link fa-1x"></i></div>
            <div class="panel-body"><a href="/orders">Orders and Returns</a></div>
          </div>

        <div class="panel panel-default">
            <div class="panel-heading">Profile <i class="fa fa-link fa-1x"></i></div>
            <div class="panel-body"><a href="/users/profile">Profile</a></div>
          </div>

        </div><!--/col-3-->
    	<div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab">Profile</a></li>
              </ul>

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
                              <input type="text" class="form-control" id="address" placeholder="Enter your address"  name="address" value="${address}" title="${address}" autocomplete="on">
                          </div>
                      </div>
                      <div class="form-group">
                           <div class="col-xs-12">
                                <br>
                              	<button type="submit" class="btn btn-primary loginButton">Submit</button>
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
            <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" id="img" alt="avatar">
            <h6></h6>
            <input type="file" accept="image/*" class="text-center center-block file-upload" name="image" id="image">
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
                                  <input type="text" class="form-control" id="location" placeholder="somewhere" title="enter a location" >
                              </div>
                          </div>
                          <div class="form-group">

                          </div>

                          <div class="form-group">
                               <div class="col-xs-12">
                                    <br>
                                  	<button type="submit" class="btn-primary loginButton">Submit</button>
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
                                  	<button class="btn btn-lg" type="submit" id="back">Back</button>
                                </div>
                          </div>
                  <hr>
                 </div><!--/tab-pane-->
              </div><!--/tab-content-->
            </div><!--/col-9-->
        </div><!--/row-->
    </#if>
      <script>
      $(document).ready(function(){
        $("#phone").click(function(){
  		$("#update_profile_popup").hide();
  		$("#phone_verification").show();
        });

        $("#back").click(function(){
        $("#update_profile_popup").show();
        $("#phone_verification").hide();
        });
      });
  <#if serviceStatus?? && serviceStatus=="503">
      $(document).ready(function(){
      $("#server_error").modal('show');
      });
      </#if>
      </script>

    <script src="/ecommerce/js/cookie.js"></script>
    <script src="/update/update.js"></script>
</body>
</html>