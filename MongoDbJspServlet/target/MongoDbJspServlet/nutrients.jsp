<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Nutrient Search</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>Nutrient Search</h1>
	</div>

	<div id="search_form">

		<form id="nutrient_search_form" name="searchForm" method="post"
			action="ModelServlet?model=Nutrient">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text" id="basic-addon1"><a
						href="ModelServlet?model=Nutrient"><i class="fa fa-refresh"></i></a></span>
				</div>
				<input type="text" class="form-control" id="search_term"
					placeholder="Enter a nutrient..." name="search_term">

				<div class="input-group-append">
					<button class="btn btn-success" id="submit_btn" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</form>

		<div class="dropdown">
			<button class="btn btn-success dropdown-toggle" type="button"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Filters</button>
			<div class="dropdown-menu">
				<a class="dropdown-item"
					href="ModelServlet?model=Nutrient&filter=macro">Macronutrients</a>
				<a class="dropdown-item"
					href="ModelServlet?model=Nutrient&filter=micro">Micronutrients</a>
			</div>
		</div>
		
		<br>

		<div class="dropdown">
			<button class="btn btn-success dropdown-toggle" type="button"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Sort</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="ModelServlet?model=Nutrient&sort=atoz">Alphabetical:
					A-Z</a> 
				<a class="dropdown-item"
					href="ModelServlet?model=Nutrient&sort=ztoa">Alphabetical: Z-A</a> 
				<a
					class="dropdown-item"
					href="ModelServlet?model=Nutrient&sort=intakelowtohigh">Daily Intake:
					Low to High</a>
				<a class="dropdown-item"
					href="ModelServlet?model=Nutrient&sort=intakehightolow">Daily Intake:
					High to Low</a> 
			</div>
		</div>
		<br>
		<br>

		<p class="search_param" style="display: ${show_param}">Showing
			results for: "${search_term}"</p>

		<h3>${subtitle}</h3>

		<div class="grid-container">
			<c:forEach items="${nutrient}" var="nutrient">

				<div class="ft-recipe">
					<div class="ft-recipe__thumb">
						<img src="${nutrient.pictureURL}" alt="${nutrient.title}" />
					</div>
					<div class="ft-recipe__content">
						<header class="content__header">
							<div class="row-wrapper">
								<h4 class="recipe-title">${nutrient.title}</h4>
								<div class="user-rating"></div>
							</div>
							<ul class="recipe-details">
								<li class="recipe-details-item time"><i
									class="ion ion-ios-calendar-outline"></i><span class="value">${nutrient.dailyIntake}</span><span
									class="title">Daily Intake</span></li>
							</ul>
						</header>
						<p class="description">${nutrient.description}</p>
						<footer class="content__footer">
							<a
								href="NutrientInstanceServlet?nutrientTitle=<c:out value="${nutrient.title}"/>">View
								Nutrient Details</a>
						</footer>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

	<br>
	<nav aria-label="Page navigation example"
		style="display: ${showPagination};">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="${first}"
				aria-label="First Page"> <span aria-hidden="true">&laquo;</span>
					<span class="sr-only">First</span></a></li>
			<li class="page-item"><a class="page-link" href="${previous}"
				aria-label="Previous"> <span aria-hidden="true">&#60;</span> <span
					class="sr-only">Previous</span></a></li>

			<c:forEach items="${pageNums}" var="page">
				<li class="page-item"><a class="page-link"
					href="ModelServlet?model=Nutrient&page=${page}${params}"><c:out
							value="${page}" /></a></li>
			</c:forEach>

			<li class="page-item"><a class="page-link" href="${next}"
				aria-label="Next"> <span aria-hidden="true">&#62;</span> <span
					class="sr-only">Next</span></a></li>
			<li class="page-item"><a class="page-link" href="${last}"
				aria-label="Last Page"> <span aria-hidden="true">&raquo;</span>
					<span class="sr-only">Last</span></a></li>
		</ul>
	</nav>

</body>
</html>