jQuery.githubUser = function (username, callback) {
    jQuery.getJSON("https://api.github.com/users/shawheenattar/repos");
}

jQuery.fn.loadRepositories = function (username) {
    this.html("<span>Querying GitHub for " + username + "'s repositories...</span>");
    console.log("starting");

    var target = this;

    $.getJSON('https://api.github.com/users/shawheenattar/repos',{},function(data){console.log(data)});

    $.githubUser(username, function (data) {
        var repos = data.user.repositories;
        sortByNumberOfWatchers(repos);

        var list = $('<dl/>');
        target.empty().append(list);
        $(repos).each(function () {
            list.append('<dt><a href="' + this.url + '">' + this.name + '</a></dt>');
            list.append('<dd>' + this.description + '</dd>');
        });
    });

    console.log("done");

    function sortByNumberOfWatchers(repos) {
        repos.sort(function (a, b) {
            return b.watchers - a.watchers;
        });
    }
};