<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Database Search</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="../index.html"> <img
			src="../images/logo.png" width="30" height="30"
			class="d-inline-block align-top" alt=""> WellBeing
		</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Databases </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../pages/ingredients.jsp">Ingredient
							Search</a> <a class="dropdown-item" href="../pages/recipes.jsp">Recipe
							Search</a> <a class="dropdown-item" href="../pages/nutrients.jsp">Nutrient
							Search</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Pages </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../pages/onefood.html">Food
							Info Page</a> <a class="dropdown-item" href="../pages/onerecipe.html">Recipe
							Info Page</a> <a class="dropdown-item"
							href="../pages/onenutrient.html">Nutrient Info Page</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Tools </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../pages/ingredientinput.html">Ingredient
							Input</a> <a class="dropdown-item" href="../pages/mealplan.html">Meal
							Planner</a> <a class="dropdown-item"
							href="../pages/expirationtracker.html">Food Expiration
							Tracker</a> <a class="dropdown-item"
							href="../pages/nutrientselector.html">Nutrient Selector</a> <a
							class="dropdown-item" href="../pages/healthysubs.html">Healthy
							Substitutes</a>
					</div></li>
				<li class="nav-item"><a class="nav-link"
					href="../pages/user.html">User</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../pages/about.html">About</a></li>
			</ul>
		</div>
	</nav>

	<div class="jumbotron" style="text-align: center">
		<h1>Database Search</h1>
	</div>

	<div id="search_bar">
		<form id="search_bar_form " name="searchBarForm" method="post" action="searchServlet">
			
			<!----- Search Bar ------>
			<input type="text" class="form-control" id="search_id" placeholder="Enter a food" name="login_id">

			<!----- SUBMIT BUTTON ------>
			<div>&nbsp;</div>
			<button id="search_btn" type="submit" class="btn btn-primary">Search</button>
		</form>
	</div>

</body>
</html>
