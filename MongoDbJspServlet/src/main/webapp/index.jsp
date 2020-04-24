<html>

<head>
<title>WellBeing</title>

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

<link rel="stylesheet" href="css/main.css">
</head>

<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>Welcome to WellBeing</h1>
		<h2>
			<i>Making nutrition easier</i>
		</h2>
	</div>

	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block w-100" src="images/ingredients.jpg" alt="Ingredients">
				<div class="carousel-caption d-none d-md-block">
					<a href="ModelServlet?model=Ingredient"><button type="button" class="btn main-button">Foods & Ingredients</button></a>
				</div>

			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="images/recipes.jpg" alt="Recipes">
				<div class="carousel-caption d-none d-md-block">
					<a href="ModelServlet?model=Recipe"><button type="button" class="btn main-button">Recipes</button></a>
				</div>
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="images/nutrients.jpg" alt="Nutrients">
				<div class="carousel-caption d-none d-md-block">
					<a href="ModelServlet?model=Nutrient"> <button type="button" class="btn main-button">Nutrients</button> </a>
				</div>
			</div>
		</div>
		
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<br>
	<br>

</body>

</html>