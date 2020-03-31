jQuery.fn.loadRepositories = function () {
    var target = this;

    $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function (data) {

        var repos = data;
        sortByNumberOfWatchers(repos);

        var list = $('<div/>');
        list.append('<h3>Commits</h3>');
        target.empty().append(list);
        $(repos).each(function () {

            if (this.name == "Wellbeing") {
                var url = this.url;

                $.getJSON(url + "/commits?per_page=100", {}, function (data) {
                    console.log(data);
                    var totalCommits = data.length;
                    console.log(totalCommits);
                    var dict = {
                        'shawheenattar': 0,
                        'adrianmb0': 0,
                        'ReedHopkins': 0,
                        'cameronsanders': 0,
                        'ramyarajasekaran': 0,
                        'evanchang2399': 0
                    };

                    $(data).each(function () {
                        dict[this.author.login] = dict[this.author.login] + 1;
                    })

                    list.append('<p>Total # of commits: ' + totalCommits + '</p>');

                    for (var key in dict) {
                        var val = dict[key];
                        list.append('<p>' + key + ': ' + val + ' commits</p>');
                    }

                    console.log(totalCommits);
                    console.log(dict);
                });
            }
        });
    });

    function sortByNumberOfWatchers(repos) {
        repos.sort(function (a, b) {
            return b.watchers - a.watchers;
        });
    }
};

jQuery.fn.loadContributorStats = function (username) {
    var target = this;

    $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function (data) {

        var repos = data;

        var list = $('<div/>');
        target.empty().append(list);
        $(repos).each(function () {

            if (this.name == "Wellbeing") {
                var url = this.url;

                $.getJSON(url + "/commits?per_page=100", {}, function (data) {
                    var commits = 0;
                    $(data).each(function () {
                        if (this.author.login == username) commits = commits + 1;
                    })

                    list.append('<p><strong>Number of commits: </strong>' + commits + '</p>');
                });

                $.getJSON(url + "/issues?per_page=100", {}, function (data) {
                    var issues = 0;
                    $(data).each(function () {
                        if (this.user.login == username) issues = issues + 1;
                    })

                    list.append('<p><strong>Number of issues: </strong>' + issues + '</p>');
                });
            }
        });
    });
};