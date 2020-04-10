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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<title>Recipe page</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="../index.html">
				<img src="../images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
				WellBeing
			</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Databases
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../pages/ingredients.jsp">Ingredient Search</a>
							<a class="dropdown-item" href="../pages/recipes.jsp">Recipe Search</a>
							<a class="dropdown-item" href="../pages/nutrients.jsp">Nutrient Search</a>
						</div>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Pages
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../pages/onefood.html">Food Info Page</a>
							<a class="dropdown-item" href="../pages/onerecipe.html">Recipe Info Page</a>
							<a class="dropdown-item" href="../pages/onenutrient.html">Nutrient Info Page</a>
						</div>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Tools
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="../pages/ingredientinput.html">Ingredient Input</a>
							<a class="dropdown-item" href="../pages/mealplan.html">Meal Planner</a>
							<a class="dropdown-item" href="../pages/expirationtracker.html">Food Expiration Tracker</a>
							<a class="dropdown-item" href="../pages/nutrientselector.html">Nutrient Selector</a>
							<a class="dropdown-item" href="../pages/healthysubs.html">Healthy Substitutes</a>
						</div>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="../pages/user.html">User</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="../pages/about.html">About</a>
					</li>
				</ul>
			</div>
		</nav>

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

            	%>

			<div class="jumbotron" style="text-align:center">
    			<%
    			    out.print("<h1>" + (String) recipe.get("title") +  "</h1>");
    			%>
    		</div>
    		<div class="container" style="align-items:center">
    			<div style="text-align: center;">
    			<%
    			     out.print("<h4> Image: </h4>" + "<img src = " + (String) recipe.get("image") +" style=\"height:300px;width:300px;\">)
    			%>
    			</div>
    			<br>
    			<h3>Recipe Information</h3>
    			<ul>
    				<li>Bake Time: <% out.print(Integer.toString((int)recipe.get("readyInMinutes")));	%> minutes</li>
    				<li>Makes: <% out.print(Integer.toString((int)recipe.get("servings")));	%> servings</li>
    				<li>Health Score: <% out.print(Double.toString((double)recipe.get("healthScore")));	%></li>
    			</ul>
    			<!-- need to parse through ingredients list dynamically -->
    			<h3>Ingredients</h3>
    			<ul>
    				<li>1/2 cup sugar</li>
    				<li>1/2 cup packed brown sugar</li>
    				<li>3 tablespoons all-purpose flour</li>
    				<li>1 teaspoon ground cinnamon</li>
    				<li>1/4 teaspoon groud ginger</li>
    				<li>1/4 teaspoon ground nutmeg</li>
    				<li>6 to 7 cups thinly sliced peeled tart apples</li>
    				<li>1 tablespoon lemon juice</li>
    				<li>Pastry for double-crust pie</li>
    				<li>1 tablespoon butter</li>
    				<li>1 large egg white</li>
    				<li>additional sugar</li>
    			</ul>
    			<br>
    			<!-- need to parse through instructions list dynamically -->
    			<h3>Directions</h3>
    			<ol>
    				<li>In a small bowl, combine the sugars, flour and spices; set aside. In a large bowl, toss apples with lemon juice. Add sugar mixture; toss to coat.</li>
    				<li>Line a 9-in. pie plate with bottom crust; trim even with edge. Fill with apple mixture; dot with butter. Roll remaining crust to fit top of pie; place over filling. Trim, seal and flute edges. Cut slits in crust.</li>
    				<li>Beat egg white until foamy; brush over crust. Sprinkle with sugar. Cover edges loosely with foil.</li>
    				<li>Bake at 375 degrees for 25 minutes. Remove foil and bake until crust is golden brown and filling is bubbly, 20-25 minutes longer. Cool on a wire rack.</li>
    			</ol>
    			<br>
    			<!-- need to scrape nutritional info dynamically from website-->
    			<h3>Nutritional Information</h3>
    			<p>The nutrional facts below correspond to one of the 8 slices of pie</p>
    			<ul>
    				<li>Calories: 414</li>
    				<li>Fat: 16g</li>
    				<li>Sodium: 227mg</li>
    				<li>Carbohydrates: 67g</li>
    				<li>Fiber: 2g</li>
    				<li>Sugars: 38g</li>
    				<li>Protein: 3g</li>
    			</ul>
    		</div>
    	</body>
    </html>