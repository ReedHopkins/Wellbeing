<html>

<head>
    <title>WellBeing</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
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
                    <a class="dropdown-item" href="pages/ingredients.html">Ingredient Search</a>
                    <a class="dropdown-item" href="pages/recipes.html">Recipe Search</a>
                    <a class="dropdown-item" href="pages/nutrients.html">Nutrient Search</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Pages
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="pages/onefood.html">Food Info Page</a>
                    <a class="dropdown-item" href="pages/onerecipe.html">Recipe Info Page</a>
                    <a class="dropdown-item" href="pages/onenutrient.html">Nutrient Info Page</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Tools
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="pages/ingredientinput.html">Ingredient Input</a>
                    <a class="dropdown-item" href="pages/mealplan.html">Meal Planner</a>
                    <a class="dropdown-item" href="pages/expirationtracker.html">Food Expiration Tracker</a>
                    <a class="dropdown-item" href="pages/nutrientselector.html">Nutrient Selector</a>
                    <a class="dropdown-item" href="pages/healthysubs.html">Healthy Substitutes</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="pages/user.html">User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="pages/about.html">About</a>
            </li>
        </ul>
    </div>
</nav>

<div class="jumbotron" style="text-align:center">
    <h1>Welcome to WellBeing</h1>
    <h2><i>Making nutrition easier</i></h2>
</div>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="images/healthyfoods.jpg" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="images/healthypeople.jpg" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="images/exercise.jpg" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<br>
<br>

<div style="text-align: center;">
    <a href="pages/mealplan.html">
        <button type="button" class="btn btn-outline-secondary">Check out our meal planner</button>
    </a>
    <br><br>
    <a href="pages/healthysubs.html">
        <button type="button" class="btn btn-outline-secondary">Check out some healthy alternatives</button>
    </a>
</div>


</body>

</html>