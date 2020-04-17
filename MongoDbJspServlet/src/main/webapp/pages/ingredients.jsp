<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
<link rel="stylesheet" href="css/main.css">
	
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Ingredient Search</title>
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
						<a class="dropdown-item" href="IngredientServlet">Ingredient
							Search</a> <a class="dropdown-item" href="RecipeServlet">Recipe
							Search</a> <a class="dropdown-item" href="NutrientServlet">Nutrient
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
		<h1>Ingredient Search</h1>
	</div>
	<br>
	
	<div id="search_form" style="width: 60%; margin: 0 auto; text-align: center;">
		<form id="ingredient_search_form" name="searchForm" method="post"
			action="IngredientServlet">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text" id="basic-addon1"><a href="IngredientServlet"><i
						class="fa fa-refresh"></i></a></span>
				</div>
				<input type="text" class="form-control" id="search_term"
					placeholder="Enter a food..." name="search_term">

				<div class="input-group-append">
					<button class="btn btn-primary" id="submit_btn" type="submit"><i
						class="fa fa-search"></i></button>
				</div>
			</div>
		</form>

		<br>
		<h3>${subtitle}</h3>
		<br>
	
		<table border='1' cellpadding='4' width='100%' style='margin: 0 auto; display: ${showPagination}'>
			<tr>
				<th>Food</th>
				<th>Price</th>
				<th>Image</th>
			</tr>
			<c:forEach items="${ingredient}" var="ingredient">
				<tr>
					<td><a href="IngredientInstanceServlet?ingredientTitle=<c:out value="${ingredient.item}"/>"><c:out value="${ingredient.item}" /></td>
					<td><c:out value="${ingredient.price}" />/<c:out value="${ingredient.unit}" /></td>
					<%--<td><c:out value="${ingredient.image}" /></td>--%>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<br>
	<nav aria-label="Page navigation example" style="display: ${showPagination};">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="${first}" aria-label="First Page"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">First</span></a></li>
			<li class="page-item"><a class="page-link" href="${previous}" aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span class="sr-only">Previous</span></a></li>
			
			<c:forEach items="${pageNums}" var="page">
				<li class="page-item"><a class="page-link" href="IngredientServlet?search_term=${search_term}&page=${page}"><c:out value="${page}" /></a></li>
			</c:forEach>

			<li class="page-item"><a class="page-link" href="${next}" aria-label="Next"> <span aria-hidden="true">&#62;</span> <span class="sr-only">Next</span></a></li>
			<li class="page-item"><a class="page-link" href="${last}" aria-label="Last Page"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Last</span></a></li>
		</ul>
	</nav>

</body>
</html>
