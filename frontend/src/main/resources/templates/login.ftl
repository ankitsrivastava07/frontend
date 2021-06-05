<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="login-form/css/font.css">
	<link rel="stylesheet" href="login-form/css/style.css">
<script src="login-form/js/jquery.min.js"></script>
<script src="login-form/js/validate.js"></script>

	</head>
	<style>
	
	.error{
    font-size: 14px;
    color: #da534d;
    padding: 3px 0px;
}


.form-control{
display: block;
    width: 100%;
    padding: .375rem .75rem;
    font-size: 0.9rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
}

.alert-danger{
background-color: #fdf6f6;
    border-color: #de7575;
    color: #c20707;
}

.alert{
    padding: 8px;
    /* margin-bottom: 20px; */
    /* border: 1px solid transparent; */
    border-radius: 4px;
    text-size-adjust: auto;
    font-size: 14px;
    text-align: center;
}

	</style>
	
	<body>
<form id="login-form" method="post" name="login-form" autocomplete="on">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<i class="fa fa-user-o text-light" aria-hidden="true"></i>
		      	</div>
		      	<h3 class="text-center mb-4">Sign in</h3>
			<div class="modal-body" id="modal1-body">
			
			<#if message?? && message?has_content>
			<div class="alert alert-danger" role="alert"> ${message} </div>
			</#if>
		      		<div class="form-group">
		      		<label for="recipient-name" class="col-form-label">Email or Mobile number</label>
		      			<input type="text" class="form-control rounded-left" maxlength="30" name="email" id="email" autocomplete=on Placeholder="Enter your email/mobile number">
		      		</div>
	            <div class="form-group">
	            <label for="recipient-name" class="col-form-label">Password</label>
	              <input type="password" class="form-control rounded-left" maxlength="20" name="password" id="password" autocomplete=on Placeholder="Enter your password">
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Login</button>
	            	<span>Don't have an account?
	            	<a href="/register">Create Account</a>
	            	</span>
									
	            </div>
	          </form>
	        </div>
				</div>
			</div>
		</div>
		</div>
	</section>
	
	<script src="login-form/js/login.js"></script>
	</body>
</html>

