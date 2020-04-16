<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
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
<title>Nutrient Search</title>
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
		<h1>Nutrient Search</h1>
	</div>

	<%/*
		String spageid = request.getParameter("page");

		if (spageid == null) {
			spageid = "1";
		}

		int pageid = Integer.parseInt(spageid);
		int origPageId = pageid;
		int total = 10;
		if (pageid == 1) {
			pageid = 0;
		} else {
			pageid = pageid - 1;
			pageid = pageid * total;
		}
		
		DBCollectionListObj data = Util.getFromDB("nutrients", pageid, total);
		ArrayList<Document> list = data.getSubList();
		int last = data.getLast();
		*/
		%>

	<div style='width:80%; margin: 0 auto; text-align: center;'>
	<table border='1' cellpadding='4' width='100%' style='margin: 0 auto;'>
		<tr><th>Nutrient</th><th>Description</th><th>Recommended Daily Intake</th><th>Image</th></tr>
		<c:forEach items="${nutrient}" var="nutrient">
			<tr>
				<td><a href="NutrientInstanceServlet?nutrientTitle=<c:out value="${nutrient.title}"/>"> <c:out value="${nutrient.title}"/> </a></td>
				<td><c:out value="${nutrient.description}"/></td>
				<td><c:out value="${nutrient.dailyIntake}"/></td>
				<td><img src=<c:out value="${nutrient.pictureURL}"/> style="height: 100px; width: 150px;"></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<%/*
			int pageNum = 1;
			if (origPageId > 3) {
				pageNum = origPageId - 2;
			}
			if (origPageId > last - 2) {
				pageNum = origPageId - 3;
			}
			if (origPageId > last - 1) {
				pageNum = origPageId - 4;
			}
			int counter = 0;
			*/
	%>
			
	<!-- <br>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="nutrients.jsp?page=1" aria-label="First Page"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">First</span></a></li>
			<%// if (origPageId > 1) { %>
			<li class="page-item"><a class="page-link" href="nutrients.jsp?page=<%//=origPageId-1%>" aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span class="sr-only">Previous</span></a></li>
			<%//} else {%>
			<li class="page-item"><a class="page-link" href="#" aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span class="sr-only">Previous</span></a></li>
			<%//} %>
			<%
			//for (int i = 0; i < last && counter < 5; i++, pageNum++, counter++) {
				
				%>
				<li class="page-item"><a class="page-link" href="nutrients.jsp?page=<%//=pageNum%>"><%//=pageNum%></a></li>
				<%
			//}
			%>
			<%// if (origPageId < last) { %>
			<li class="page-item"><a class="page-link" href="nutrients.jsp?page=<%//=origPageId+1%>" aria-label="Next"> <span aria-hidden="true">&#62;</span> <span class="sr-only">Next</span></a></li>
			<%//} else {%>
			<li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&#62;</span> <span class="sr-only">Next</span></a></li>
			<%//} %>
			<li class="page-item"><a class="page-link" href="nutrients.jsp?page=<%//=last%>" aria-label="Last Page"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Last</span></a></li>
		</ul>
	</nav>

	-->
</body>
</html>
