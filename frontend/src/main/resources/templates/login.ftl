<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="/login-form/js/jquery.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="/login-form/css/font.css">
	<link rel="stylesheet" href="/login-form/css/style.css">
<script src="/login-form/js/validate.js"></script>

	</head>
	<style>
	
	.error{
    font-size: 14px;
    color: #da534d;
    padding: 3px 0px;
}
#icon{
color:red;
font-size:17px;
cursor:pointer;
}

#forget_password{
float:right;
color: #4A90E2;
font-size: 15px;
margin-top: 5px;
}
.btn.btn-primary{
background: #1089ff !important;
}

.form-control{
display: block;
    width: 100%;
    padding: .375rem .75rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
}

.alert-danger{
    color: #721c24;
    background-color: #f8d7da;
    border-color: #f5c6cb;
}

.alert{
    padding: 8px;
    /* margin-bottom: 20px; */
    /* border: 1px solid transparent; */
    border-radius: 4px;
    text-size-adjust: auto;
    font-size: 14px;
    text-align: center;
    box-shadow: 0 1px 4px 0 rgb(0 0 0 / 40%);

}

span#txt {
color: salmon;
cursor: pointer;
}

#alt{
text-align: center;
background-color: #545353;
border-color: #545353;
padding: 8px;
color: white;
font-weight: bold;
margin-left: auto;
margin-right: auto;
/* width: fit-content; */
font-size : small;
box-shadow: 0 25px 15px 0 rgb(0 0 0 / 40%);
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

<form id="login-form" action="/" name="login-form" autocomplete="on">
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
			<div class="alert alert-danger" role="alert">${message}</div>
			</#if>
		      		<div class="form-group">
		      		<label for="recipient-name" class="col-form-label">Email or Mobile number</label>
		      			<input type="text" class="form-control rounded-left" maxlength="30" name="emailOrMobile" id="emailOrMobile" autocomplete=on Placeholder="Enter your email/mobile number">
		      		</div>
	            <div class="form-group">

	            <label for="recipient-name" class="col-form-label">Password</label>
	            <a href="/forget-password" id="forget_password">Forget Password ?</a>
	              <input type="password" class="form-control rounded-left" maxlength="20" name="password" id="password" autocomplete=on Placeholder="Enter your password">
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Login</button>
	            	<span id="create_account">Don't have an account?
	            	<a href="/sign-up">Create Account</a>
	            	</span>
	           </div>
	          </form>
	        </div>
		   </div>
		  </div>
		 </div>
		</div>
	</section>
	<script src="ecommerce/js/cookie.js"></script>
	<script src="/login-form/js/login.js"></script>
	<script>
	$("#err_msg").html(sessionStorage.getItem("error"))
	</script>
	</body>
</html>

