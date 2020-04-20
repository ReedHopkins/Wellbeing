<html>
<head>
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
<title>${nutrient.title}</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>${nutrient.title}</h1>
	</div>
	<div class="container" style="align-items: center">
		<div style="text-align: center;">
			<img src=${nutrient.pictureURL } width="300" height="200">
		</div>
		<br>
		<h3>Nutrient Description</h3>
		<p>${nutrient.description}</p>
		<h3>Recommended Daily Intake</h3>
		<p>${nutrient.dailyIntake}</p>
	</div>
</body>
</html>
