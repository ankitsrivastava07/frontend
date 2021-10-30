<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>Sign up Form</title>
<script src="/register-form/vendor/jquery/jquery.min.js"></script>
<script src="/login-form/js/validate.js"></script>
<script src="/login-form/js/cookie.js" type="text/javascript"></script>
<!-- Icons font CSS-->
<link
	href="register-form/vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link
	href="/register-form/vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<!-- Font special for pages-->
<link href="/register-form/css/font.css" rel="stylesheet">

<!-- Vendor CSS-->
<link href="/register-form/vendor/select2/select2.min.css"
	rel="stylesheet" media="all">
<link href="/register-form/vendor/datepicker/daterangepicker.css"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="/register-form/css/main.css" rel="stylesheet" media="all">
</head>

<style>
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

/* Firefox */
input[type=number] {
	-moz-appearance: textfield;
}

.email-error {
  border-color:red;
}

.align-right { text-align:right; }

.error {
	font-size: 14px;
	color: #da534d;
	padding: 3px 0px;
}

.alert-danger{
background-color: #fdf6f6;
    border-color: #de7575;
    color: #c20707;
}

.alert{
    padding: 12px;
    margin-top: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
    text-size-adjust: auto;
    font-size: 14px;
    text-align: center;

}
#err{
display: none;
}
.alert-success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
}
</style>

<body>
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title">
						<center>Sign up</center>
					</h2>
					<div class="alert alert-danger" role="alert" id="err">
                                           <span id="errMsg"></span>
                                            </div>
					<form method="POST" id="signup-form">
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">first name</label> <input
										class="input--style-4" type="text" name="firstName" autocomplete=on maxlength="100" id="firstName">
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<label class="label">last name</label> <input
										class="input--style-4" type="text" name="lastName" autocomplete=on maxlength="100" id="lastName">
								</div>
							</div>
						</div>
						<div class="row row-space">

							<div class="col-2">
								<div class="input-group">
									<label class="label">Mobile Number</label> <input
										class="input--style-4" type="number" id="mobile" autocomplete=on name="mobile">
										
								</div>
							</div>

							<div class="col-2">
								<div class="input-group">
									<label class="label">Email(Optional)</label> <input
										class="input--style-4" type="email" id="email" autocomplete=on name="email">
								</div>

							</div>
							</div>
							<div class="row row-space">
								<div class="col-2">
									<div class="input-group">
										<label class="label">Password</label> <input
											class="input--style-4" type="password" autocomplete=on minlength="8" id="password"
											name="password" maxlength="20">
									</div>
								</div>
							</div>
							<div class="p-t-15">
									<button class="btn btn--radius-2 btn--blue" id="submit" type="submit">Submit</button>
									<span class="align-right">Alredy have an account 
									<a href="/signin">Sign in</button></span>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Main JS-->
	<script src="/register-form/js/register.js"></script>
</body>
</html>
<!-- end document-->