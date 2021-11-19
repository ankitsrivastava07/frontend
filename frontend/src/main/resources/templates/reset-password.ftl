<!doctype html >
<html lang="en" id="conform">
  <head id="clear">
  	<title>Reset Password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="/login-form/js/1.12.4.jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
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

#forget_password{
float:right;
color: #4A90E2;
font-size: 15px;
}
.btn.btn-primary{
background: #1089ff !important;
}
.btn{
margin-top : 25px;
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

#back {
    background-color: rgba(0,0,0,0);
    /* border: 0; */
    border-radius: 2px;
    /* -webkit-box-sizing: border-box; */
    /* box-sizing: border-box; */
    color: rgba(0,0,0,0.6);
    cursor: pointer;
    /* display: inline-block; */
    font-weight: 600;
    /* font-family: inherit; */
    /* height: 32px; */
    line-height: 32px;
    /* overflow: hidden; */
    /* outline-width: 2px; */
    /* padding: 0 8px; */
    text-align: center;
    display: block;
    margin: 12px auto 0;
}
body {
		font-family: 'Varela Round', sans-serif;
	}
	.modal-confirm {
		color: #434e65;
		width: 525px;
		margin: 30px auto;
	}
	.modal-confirm .modal-content {
		padding: 20px;
		font-size: 16px;
		border-radius: 5px;
		border: none;
	}
	.modal-confirm .modal-header {
		background: #1089ff;
		border-bottom: none;
        position: relative;
		text-align: center;
		margin: -20px -20px 0;
		border-radius: 5px 5px 0 0;
		padding: 35px;
	}
	.modal-confirm h4 {
		text-align: center;
		font-size: 36px;
		margin: 10px 0;
	}
	.modal-confirm .form-control, .modal-confirm .btn {
		min-height: 40px;
		border-radius: 3px;
	}
	.modal-confirm .close {
        position: absolute;
		top: 15px;
		right: 15px;
		color: #fff;
		text-shadow: none;
		opacity: 0.5;
	}
	.modal-confirm .close:hover {
		opacity: 0.8;
	}
	.modal-confirm .icon-box {
        color: #fff;
        width: 95px;
        height: 95px;
        display: inline-block;
        border-radius: 50%;
        z-index: 9;
        border: 5px solid #fff;
        padding: 15px;
        text-align: center;
        margin-left: auto;
        margin-right: auto;
    }
	.modal-confirm .icon-box i {
		font-size: 64px;
		margin: -4px 0 0 -4px;
	}
    .modal-confirm .btn {
        color: #fff;
        border-radius: 4px;
		background: #eeb711;
		text-decoration: none;
		transition: all 0.4s;
        line-height: normal;
		border-radius: 30px;
		margin-top: 10px;
		padding: 6px 20px;
        border: none;
    }
	.modal-confirm .btn:hover, .modal-confirm .btn:focus {
		background: #eda645;
		outline: none;
	}
	.modal-confirm .btn span {
		margin: 1px 3px 0;
		float: left;
	}
	.modal-confirm .btn i {
		margin-left: 1px;
		font-size: 20px;
		float: right;
	}
	.trigger-btn {
		display: inline-block;
		margin: 100px auto;
	}

/* Confirm modal css */
.checkbox-wrap input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0; }

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0; }

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "\f0c8";
  font-family: "FontAwesome";
  position: absolute;
  color: rgba(0, 0, 0, 0.1);
  font-size: 20px;
  margin-top: -4px;
  -webkit-transition: 0.3s;
  -o-transition: 0.3s;
  transition: 0.3s; }
  @media (prefers-reduced-motion: reduce) {
    .checkmark:after {
      -webkit-transition: none;
      -o-transition: none;
      transition: none; } }

/* Show the checkmark when checked */
.checkbox-wrap input:checked ~ .checkmark:after {
  display: block;
  content: "\f14a";
  font-family: "FontAwesome";
  color: rgba(0, 0, 0, 0.2); }

/* Style the checkmark/indicator */
.checkbox-primary {
  color: #1089ff; }
  .checkbox-primary input:checked ~ .checkmark:after {
    color: #1089ff; }

.btn {
  cursor: pointer;
  border-radius: 40px;
  -webkit-box-shadow: none !important;
  box-shadow: none !important;
  font-size: 15px; }
  .btn:hover, .btn:active, .btn:focus {
    outline: none; }
  .btn.btn-primary {
    background: #1089ff !important;
    border: 1px solid #1089ff !important;
    color: #fff !important; }
    .btn.btn-primary:hover {
      border: 1px solid #1089ff;
      background: transparent;
      color: #1089ff; }
    .btn.btn-primary.btn-outline-primary {
      border: 1px solid #1089ff;
      background: transparent;
      color: #1089ff; }
      .btn.btn-primary.btn-outline-primary:hover {
        border: 1px solid transparent;
        background: #1089ff;
        color: #fff; }
	.modal-confirm {
		color: #434e65;
		width: 525px;
		margin: 30px auto;
	}
	.modal-confirm .modal-content {
		padding: 20px;
		font-size: 16px;
		border-radius: 5px;
		border: none;
	}
	.modal-confirm .modal-header {
		background: #1089ff;
		border-bottom: none;
        position: relative;
		text-align: center;
		margin: -20px -20px 0;
		border-radius: 5px 5px 0 0;
		padding: 35px;
	}
	.modal-confirm h4 {
		text-align: center;
		font-size: 36px;
		margin: 10px 0;
	}
	.modal-confirm .form-control, .modal-confirm .btn {
		min-height: 40px;
		border-radius: 3px;
	}
	.modal-confirm .close {
        position: absolute;
		top: 15px;
		right: 15px;
		color: #fff;
		text-shadow: none;
		opacity: 0.5;
	}
	.modal-confirm .close:hover {
		opacity: 0.8;
	}
	.modal-confirm .icon-box {
		color: #fff;
		width: 95px;
		height: 95px;
		display: inline-block;
		border-radius: 50%;
		z-index: 9;
		border: 5px solid #fff;
		padding: 15px;
		text-align: center;
	}
	.modal-confirm .icon-box i {
		font-size: 64px;
		margin: -4px 0 0 -4px;
	}
    .modal-confirm .btn {
        color: #fff;
        border-radius: 4px;
		background: #eeb711;
		text-decoration: none;
		transition: all 0.4s;
        line-height: normal;
		border-radius: 30px;
		margin-top: 10px;
		padding: 6px 20px;
        border: none;
    }
	.modal-confirm .btn:hover, .modal-confirm .btn:focus {
		background: #eda645;
		outline: none;
	}
	.modal-confirm .btn span {
		margin: 1px 3px 0;
		float: left;
	}
	.modal-confirm .btn i {
		margin-left: 1px;
		font-size: 20px;
		float: right;
	}
	.trigger-btn {
		display: inline-block;
		margin: 100px auto;
	}

	#signin {
    margin: auto;
    width: 100px;
    background-color: #4a90e2;
    -webkit-box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.2);
    box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.2);
    height: 36px;
    cursor: pointer;
    line-height: 18px;
    cursor: pointer;
    font-size: 14px;
    }

	</style>
	<body id="modal_confirm">
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

<!-- Confirm Modal -->
<div id="confirm" class="modal fade" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">
				<div class="icon-box">
				</div>
			</div>
			<div class="modal-body text-center">
				<h5>Mail Saint!</h5>
				<p>We’ve sent an email to your email address. Follow the steps provided in the email to update your password or click Sign In button if you don’t want to change your password at this time.</p>
			<input type="submit" id="signin" class="btn btn-secondary" value="Sign In">
			</div>
		</div>
	</div>
</div>

<form id="reset-password" method="post" name="reset-password">
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
		      	<h3 class="text-center mb-4">Forget Password</h3>
			<div class="modal-body" id="modal1-body">
			<span id="email_response"></span>
		      		<div class="form-group">
		      		<label for="recipient-name" class="col-form-label">Email or Mobile number</label>
		      			<input type="text" class="form-control rounded-left" autocomplete=on maxlength="30" name="emailOrMobile" id="emailOrMobile" Placeholder="Enter your email/mobile number">
		      			<span class="error_email error"></span>
		      		</div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Reset Password</button>
	            </div>
               <a href="/signin" id="back">Back</a>
	          </form>
	        </div>
	        	</div>
			</div>
		</div>
		</div>
	</section>
	<script src="/login-form/js/login.js"></script>
	</body>
</html>
