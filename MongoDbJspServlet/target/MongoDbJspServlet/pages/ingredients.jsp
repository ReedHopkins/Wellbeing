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
		<h1>Ingredient Search</h1>
	</div>

	<%
		String dbURI = "mongodb://projectUser:team7@cluster0-shard-00-00-rwcw3.mongodb.net:27017,cluster0-shard-00-01-rwcw3.mongodb.net:27017,cluster0-shard-00-02-rwcw3.mongodb.net:27017/wellbeing?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";
		MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI));
		String db_name = "wellbeing", db_collection_name = "hebData";

		MongoDatabase db = mongoClient.getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);
		FindIterable<Document> elements = col.find();

		MongoCursor<Document> cursor = elements.iterator();

		ArrayList<Document> foodList = new ArrayList<Document>();

		try {
			while (cursor.hasNext()) {
				foodList.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		
		System.out.println(foodList.size());

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
		
		int last = foodList.size() / total;
		if (foodList.size() % total > 0) {
			last++;
		}

		List<Document> list;
		if (foodList.size() - pageid < total) {
			list = foodList.subList(pageid, foodList.size());
		} else {
			list = foodList.subList(pageid, pageid + total);
		}
		
		out.print("<div style='width:80%; margin: 0 auto; text-align: center;'>");
		out.print("<h1>Page No: " + spageid + "</h1>");
		out.print("<table border='1' cellpadding='4' width='60%' style='margin: 0 auto;'>");
		out.print("<tr><th>Food</th><th>HEB Price</th>");
		
		for (Document food : list) {
			out.print("<tr><td>" + (String) food.get("item") + "</td><td>" + (Double) food.get("price") + "</td></tr>");
		}
		
		out.print("</table>");
		out.print("</div>");
	%>
	<%
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
	%>
			
	<br>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="ingredients.jsp?page=1" aria-label="First Page"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">First</span></a></li>
			<% if (origPageId > 1) { %>
			<li class="page-item"><a class="page-link" href="ingredients.jsp?page=<%=origPageId-1%>" aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span class="sr-only">Previous</span></a></li>
			<%} else {%>
			<li class="page-item"><a class="page-link" href="#" aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span class="sr-only">Previous</span></a></li>
			<%} %>
			<%
			for (int i = 0; i < last && counter < 5; i++, pageNum++, counter++) {
				
				%>
				<li class="page-item"><a class="page-link" href="ingredients.jsp?page=<%=pageNum%>"><%=pageNum%></a></li>
				<%
			}
			%>
			<% if (origPageId < last) { %>
			<li class="page-item"><a class="page-link" href="ingredients.jsp?page=<%=origPageId+1%>" aria-label="Next"> <span aria-hidden="true">&#62;</span> <span class="sr-only">Next</span></a></li>
			<%} else {%>
			<li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&#62;</span> <span class="sr-only">Next</span></a></li>
			<%} 			%>
			<li class="page-item"><a class="page-link" href="ingredients.jsp?page=<%=last%>" aria-label="Last Page"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Last</span></a></li>
		</ul>
	</nav>


</body>
</html>
