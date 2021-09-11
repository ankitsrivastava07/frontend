<!DOCTYPE html>
<html lang="en">
<title>Reset Password</title>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="/login-form/js/jquery.min.js"></script>
<script src="/login-form/js/validate.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!------ Include the above in your HEAD tag ---------->

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
 <style>
 	.error{
     font-size: 14px;
     color: #da534d;
     padding: 3px 0px;
 }

#back {
   background-color: rgba(0,0,0,0);
       /* border: 0; */
       border-radius: 2px;
       /* -webkit-box-sizing: border-box; */
       /* box-sizing: border-box; */
       color: rgba(0,0,0,0.6);
       cursor: pointer;
       /* display: inline-block; */
       font-size: 1.6rem;
       font-weight: 600;
       /* font-family: inherit; */
       /* height: 32px; */
       line-height: 32px;
       /* overflow: hidden; */
       /* outline-width: 2px; */
       /* padding: 0 8px; */
       position: relative;
       text-align: center;
       text-decoration: none;
       /* vertical-align: middle; */
       /* z-index: 0; */
       border-radius: 28px !important;
       display: block;
       border-radius: 28px !important;
       margin: 12px auto 0;
}

 </style>
 </head>
 <body id="modal_confirm">
 <div class="form-gap"></div>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                  <h3><i class="fa fa-lock fa-4x"></i></h3>
                  <h2 class="text-center">Forgot Password?</h2>
                  <p>You can reset your password here.</p>
                  <span id="email_response"></span>
                  <div class="panel-body">
                    <form id="reset-password-form" role="form" autocomplete="on" class="form" method="post">
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="email" name="email" placeholder="enter your email address" class="form-control"  type="email">
                          <span id="email_response"></span>
                        </div>
                      </div>
                      <div class="form-group">
                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                         <div class="form-group">
                           <a href="/signin" id="back" value="Back">Back
                          </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
	</div>
</div>
	<script src="/login-form/js/login.js"></script>
</body>
</html>
