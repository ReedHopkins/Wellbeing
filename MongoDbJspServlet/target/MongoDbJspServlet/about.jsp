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
<title>WellBeing</title>

<style>
p {
	margin: 0;
}
</style>
</head>

<body>

	<jsp:include page="header.jsp" />

	<div class="jumbotron" style="text-align: center">
		<h1>About WellBeing</h1>
	</div>
	<div class="container">
		<h1>Our Objective</h1>
		<a href="https://github.com/WellBeingEating/Wellbeing">Github Link</a>
		<p>
			The motivation behind our database is to solve our own problems we
			had in our day to day lives. Nutrition is something that can get
			really complicated at first sight but gets easy quickly with a bit of
			guidance. Hence, we wanted to create a database that makes nutrition
			easier for all. By providing recipes, ingredients, and macro and
			micronutrients we cover the bases of nutrition and then personalize
			it to suit everyone&#39;s specific needs. In doing so, we are helping
			people figure out how to eat excellently and exactly in their own
			way. This gives everyone a chance to give their bodies the best fuel
			they <i><b>want</b></i> to get and not just need
		</p>
		<br>
		<h1>Team Morning-7</h1>
		<div class="row">
			<div class="col-sm-4">
				<h4>Ramya Rajasekaran</h4>
				<img src="images/Ramya.jpg" width="200" height="200"> <br>
				<b>Bio:</b> Hey there! I&#39;m Ramya and I&#39;m a fourth year ECE
				major at UT Austin. I&#39;m interested in distributes systems and
				machine learning. My hobbies include playing music and watching
				Netflix. <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Back-End Programming <br>
				<b>Number of Unit Tests:</b> 4
				<p id="ramya-stats"></p>
			</div>
			<div class="col-sm-4">
				<h4>Reed Hopkins</h4>
				<img src="images/Reed.jpg" width="200" height="200"> <br>
				<b>Bio:</b> I&#39;m Reed, a third year ECE major at the University
				of Texas at Austin, specializing in software engineering. I was born
				and raised in Houston, Texas and I enjoy cooking, mountain biking,
				and listening to music in my free time. My interests include
				artificial intelligence, data science, and automation, however, I
				enjoy engineering in all aspects. <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Front-End Programming <br>
				<b>Number of Unit Tests:</b> 1
				<p id="reed-stats"></p>
			</div>
			<div class="col-sm-4">
				<h4>Evan Chang</h4>
				<img src="images/Evan.jpg" width="200" height="200"> <br>
				<b>Bio:</b>Hey I&#39;m Evan and I am a third year Electrical and
				Computer Engineering student at the University of Texas at Austin
				specializing in Software Engineering. I&#39;m from Gaithersburg,
				Maryland, but I came to UT for the great engineering program, the
				beautiful campus, and the wide variety of fun things to do in
				Austin. Outside of my studies, I&#39;m an avid football fan, a
				runner, and I love exploring the outdoors. <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Back-End Programming <br>
				<b>Number of Unit Tests:</b> 4
				<p id="evan-stats"></p>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<h4>Cameron Sanders</h4>
				<img src="images/Cameron.jpg" width="200" height="200"> <br>
				<b>Bio:</b> Hello! My name is Cameron, and I am a fourth year
				Electrical and Computer Engineering major at UT Austin focusing in
				software development. I am passionate about personalized health and
				genomics. When I&#39;m not doing class work I enjoy spending time
				with my family as well as hitting the park with my dogs. <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Back-End Programming <br>
				<b>Number of Unit Tests:</b> 4
				<p id="cam-stats"></p>
			</div>
			<div class="col-sm-4">
				<h4>Shawheen Attar</h4>
				<img src="images/Shawheen.jpeg" width="200" height="200"> <br>
				<b>Bio:</b> I am a currently a third-year Computer Engineering
				student at UT Austin. My interests lie with using technology to
				build projects and solve problems, whether it be for classes, for
				personal projects, or for startups. I also like woodworking and
				being the best space cadet ever. In the multiverse. Through
				eternity. <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Front-End Programming <br>
				<b>Number of Unit Tests:</b> 0
				<p id="shawheen-stats"></p>
				<br>
				<br>
			</div>
			<div class="col-sm-4">
				<h4>Adrian Melo</h4>
				<img src="images/Adrian.jpeg" width="200" height="200"> <br>
				<b>Bio:</b> Hey! I&#39;m a current Electrical and Computer
				Engineering student at the University of Texas at Austin. I firmly
				believe that anyone can achieve anything that they put their mind,
				time, and effort to. If I&#39;m not doing school, you can always
				find me hanging out with friends and/or outdoor &ndash; most likely both.
				:) <br>
				<b>Major:</b> Electrical and Computer Engineering, Software
				Engineering and Systems Track <br>
				<b>Primary Responsibilities:</b> Front-End Programming <br> <b>Number
					of Unit Tests:</b> 0
				<p id="adrian-stats"></p>
			</div>
		</div>
		<br>
		<h1>Github Stats</h1>

		<div id="opensource-projects"></div>



		<br>
		<h1>Data Collection</h1>
		<p>
			<a href="https://spoonacular.com/food-api">Spoonacular API</a> - Used
			to get recipe information including calorie count, cooking time, etc
		</p>
		<p>
			<a href="https://www.nutritionix.com/business/api">Nutritionix
				API</a> - Used to get ingredient information including brand, calories,
			fat, sugar, and other nutrient information
		</p>
		<p>
			Web Scraping - There was no available API for food in conjunction
			with prices. Given this scenario, we had to scrape a site (in our
			case <a href="https://www.heb.com/browse/shop">HEB's</a> site), using
			Python and BeautifulSoup, and grab prices of food off of the webpage.
		</p>
		<br>
		<h1>Tools</h1>
		<p>AWS EC2: Our website is deployed using Amazon Web Services.
			Deploying on AWS EC2 involes uploading a .WAR file to the Tomcat
			deployment manager, which is running on our EC2 instance.</p>
		<p>Apache Tomcat: Tomcat is an open-source application that allows
			us to easily upload and deploy our project on our EC2 instance. In
			essence, it&#39;s a servlet and JSP container. Once configured on our
			EC2 instance, we can sign in to the deployment manager using our EC2
			public ip and upload a .WAR file to be deployed with our given
			deployment specifications.</p>
		<p>Sublime: Sublime is one of our IDEs of choice for the
			development of this website. It was used for writing HTML, CSS, and
			Python code.</p>
		<p>Bootstrap: Bootstrap is a great resource for premade css
			elements. We used bootstrap to design the website and pulled many
			elements from it such as the navigation bar and database</p>
		<p>RapidAPI: RapidAPI provides a web interface to test API
			endpoints. It was used to test endpoints for the Spoonacular API.</p>
		<p>Selenium: Selenium is a portable framework for testing web
			applications. Selenium provides a playback tool for authoring
			functional tests without the need to learn a test scripting language
		
		<p>Mocha: Mocha is a feature-rich JavaScript test framework
			running on Node.js and in the browser, making asynchronous testing
			simple and fun.
		<p>
			Chai: Chai is a BDD / TDD assertion library for node and the browser
			that can be delightfully paired with any javascript testing
			framework. <br>
	</div>

	<script src="js/main.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			commits = $("#opensource-projects").loadRepositories();
			$("#adrian-stats").loadContributorStats("adrianmb0");
			$("#shawheen-stats").loadContributorStats("shawheenattar");
			$("#cam-stats").loadContributorStats("cameronsanders");
			$("#reed-stats").loadContributorStats("ReedHopkins");
			$("#evan-stats").loadContributorStats("evanchang2399");
			$("#ramya-stats").loadContributorStats("ramyarajasekaran");
		});
	</script>
</body>

</html>
