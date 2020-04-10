<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ page import="org.bson.Document"%>

<%@ page import="com.mongodb.BasicDBObject"%>
<%@ page import="com.mongodb.MongoClient"%>
<%@ page import="com.mongodb.MongoClientURI"%>
<%@ page import="com.mongodb.client.FindIterable"%>
<%@ page import="com.mongodb.client.MongoCollection"%>
<%@ page import="com.mongodb.client.MongoDatabase"%>
<%@ page import="com.mongodb.client.MongoCursor"%>

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
<title>Recipe Page</title>
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
		<h1>Recipe Page</h1>
	</div>

	<%
	    String recipeName = request.getParameter("recipeName");
		String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
		MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI));
		String db_name = "wellbeing", db_collection_name = "recipes";

		MongoDatabase db = mongoClient.getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);

        Document recipe = new Document();

		BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("title", recipeName);
        DBCursor cursor = collection.find(whereQuery);
		try {
			while (cursor.hasNext()) {
				recipe = cursor.next();
			}
		} finally {
			cursor.close();
		}

		System.out.println(recipe);

		out.print("<h3>" + (String) recipe.get("title") +  "</h3>");
        out.print("<h4> Cook Time: " + Integer.toString((int)recipe.get("readyInMinutes")) + "</h4>");
        out.print("<h4> Servings: " + Integer.toString((int)recipe.get("servings")) + "</h4>");
        out.print("<h4> Health Score: " + Double.toString((double)recipe.get("healthScore")) + "</h4>");
        out.print("<h4> Image: </h4>" + "<img src = " + (String) recipe.get("image") +" style=\"height:100px;width:150px;\">)

	%>



</body>
</html>