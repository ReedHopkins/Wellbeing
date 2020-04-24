<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.bson.Document"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.client.MongoCollection"%>
<%@page import="com.jcg.mongodb.servlet.Recipe"%>
<%@ page import="com.jcg.mongodb.servlet.DatabaseSingleton" %>

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
<title>Recipe page</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">

		<h1>${recipe.title}</h1>
	</div>
	<div class="container" style="align-items: center">
		<div style="text-align: center;">
			<img src=${recipe.image } width="300" height="200">
		</div>
		<br>
		<h3>Recipe Information</h3>
		<ul>
			<li>Prep Time: ${recipe.readyInMinutes} minutes</li>
			<li>Makes: ${recipe.servings} servings</li>
			<li>HealthScore: ${recipe.healthScore}/100</li>
		</ul>
		<h3>Ingredients</h3>
		<ul>

			<%
				Recipe recipe = (Recipe) request.getAttribute("recipe");
				//ArrayList<String> ingredientURL = (ArrayList) request.getAttribute("ingredientURL");

				for (Document ingredient : recipe.getingredients()) {
					String name = DatabaseSingleton.findIngredient(ingredient.getString("name"));
                    if (name != null) {
                        out.print("<li><a href=\"IngredientInstanceServlet?ingredientTitle="+ name +"\">"
                        + ingredient.getString("name") + "</a></li>\n");
                    } else{
					    out.print("<li>" + ingredient.getString("name") + "</li>");
					}

				}
			%>
		</ul>
		<br>
		<h3>Directions</h3>
		<p>${recipe.instructions}</p>
		<br>
		<h3>Nutritional Information</h3>
		<ul>
			<li>Calories: ${recipe.calories}</li>
			<li>Carbohydrates: ${recipe.carbs}</li>
			<li>Fat: ${recipe.fat}</li>
			<li>Protein: ${recipe.protein}</li>
			<%
			    ArrayList<String> nutrientURL = (ArrayList) request.getAttribute("nutrientURL");

				for (Document nutrient : recipe.getgoodnutrients()) {
				    String name = DatabaseSingleton.findNutrient(nutrient.getString("title"));
					if (name != null) {
                    	out.print("<li><a href=\"NutrientInstanceServlet?nutrientTitle=" + name + "\">"
                    	+ nutrient.getString("title") + "</a> : " + nutrient.getString("amount") + "</li>");
                 	}else{
						out.print("<li>" + nutrient.getString("title") + ": " + nutrient.getString("amount") + "</li>");
				    }
				}
				for (Document nutrient : recipe.getbadnutrients()) {
                    String name = DatabaseSingleton.findNutrient(nutrient.getString("title"));
					if (nutrientURL.contains(nutrient.getString("title"))) {
                    	out.print("<li><a href=\"NutrientInstanceServlet?nutrientTitle=" + name + "\">"
                    	+ nutrient.getString("title") + "</a> : " + nutrient.getString("amount") + "</li>");
                 	}else{
						out.print("<li>" + nutrient.getString("title") + ": " + nutrient.getString("amount") + "</li>");
				    }
				}
			%>
		</ul>
	</div>
</body>
</html>
