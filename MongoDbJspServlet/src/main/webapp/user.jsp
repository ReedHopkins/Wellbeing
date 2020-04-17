<html>

<head>
<title>User</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="css/main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>User Information</h1>
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Name</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Email</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Height</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Weight</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Goal</span>
			<button class="btn btn-outline-secondary" type="button">Bulk</button>
			<button class="btn btn-outline-secondary" type="button">Maintain</button>
			<button class="btn btn-outline-secondary" type="button">Cut</button>
		</div>
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Daily Calorie
				Intake</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text" id="basic-addon1">Food
				Allergies</span>
		</div>
		<input type="text" class="form-control" aria-label="Username"
			aria-describedby="basic-addon1">
	</div>
</body>

</html>
