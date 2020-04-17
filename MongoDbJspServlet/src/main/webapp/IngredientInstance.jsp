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
<title>${ingredient.item}</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.html">
			<img src="images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
			WellBeing
		</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="IngredientServlet">Ingredients</a></li>
				<li class="nav-item"><a class="nav-link" href="RecipeServlet">Recipes</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="NutrientServlet">Nutrients</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="user.html">User</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="about.html">About</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="jumbotron" style="text-align: center">
		<h1>${ingredient.item}</h1>
	</div>
	<div class="container" style="align-items: center">
		<div style="text-align: center;">
			<!-- <img src=${ingredient.image} width="300" height="200"> -->
		</div>
		<br>
		<h3>Ingredient Nutrients</h3>
		<h3>Price</h3>
		<p>${ingredient.price}${ingredient.unit}</p>
	</div>
</body>
</html>
