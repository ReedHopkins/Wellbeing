jQuery.fn.loadRepositories = function () {
    this.html("<span>Querying GitHub for repositories...</span>");
    console.log("starting");

    var target = this;

    $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function (data) {

        console.log(data)
        var repos = data;
        sortByNumberOfWatchers(repos);

        var list = $('<dl/>');
        target.empty().append(list);
        $(repos).each(function () {

            var url = this.url;

            $.getJSON(url + "/commits", {}, function (data) {
                console.log(data);
                $(data).each(function () {
                    console.log(this.commit.author.name);
                    list.append('<dt><a href="' + this.url + '">' + this.title + '</a></dt>');
                    list.append('<dd>' + this.body + '</dd>');
                })
            });


        });

    });

    console.log("done");

    function sortByNumberOfWatchers(repos) {
        repos.sort(function (a, b) {
            return b.watchers - a.watchers;
        });
    }
};