<%@ page import="com.jcg.mongodb.servlet.Ingredient" %>
<%@ page import="org.bson.Document" %>
<%@ page import="com.jcg.mongodb.servlet.DatabaseSingleton" %>
<%@ page import="com.jcg.mongodb.servlet.Recipe" %>
<%@ page import="java.util.ArrayList" %>
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
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>${ingredient.item}</h1>
	</div>
	<div class="container" style="align-items: center">
		<div style="text-align: center;">
			 <img src=${ingredient.image} width="300" height="200">
		</div>
		<br>
		<h3>Price</h3>
		<p> $${ingredient.price} per ${ingredient.unit}</p>
		<br>
		<h3>Ingredient Nutrients</h3>
		<%
			Ingredient ingredient = (Ingredient) request.getAttribute("ingredient");
			for (String nutrient : ingredient.getnutrients()) {
				String name = DatabaseSingleton.findNutrient(nutrient);
				if (name != null) {
					out.print("<li><a href=\"NutrientInstanceServlet?nutrientTitle=" + name + "\">"
					+ nutrient + "</a> </li>");
				}else{
					out.print("<li>" + nutrient + "</li>");
				}
			}
		%>
		<br>
		<h3>Tags</h3>
		<%
			for (String tag : ingredient.gettags()) {
				out.print("<li>" + tag + "</li>");
			}

		%>
		<br>
        <h3>Badges</h3>
		<%
			for (String badge : ingredient.getbadges()) {
				out.print("<li>" + badge + "</li>");
			}

		%>
		<br>
        <h3>Aisle</h3>
		<p>${ingredient.aisle}</p>
		<br>
        <h3>Recipes with this ingredient: </h3>
		<%
			ArrayList<Recipe> info = DatabaseSingleton.searchRecipesForIngredient(ingredient.getitem());
            for(Recipe r: info){
                if (r != null) {
				    out.print("<li><a href=\"RecipeInstanceServlet?recipeId=" + r.getid() + "\">"
					+ r.gettitle() + "</a> </li>");
                }
            }
		%>
		<br>
	</div>
</body>
</html>
