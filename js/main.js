//pie chart
var ctxP = document.getElementById("pieChart").getContext('2d');
var myPieChart = new Chart(ctxP, {
    plugins: [ChartDataLabels],
    type: 'pie',
    data: {
        labels: ["Carbohydrates", "Protein", "Fat"],
        datasets: [{
            data: [50, 30, 20],
            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C"],
            hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870"]
        }]
    },
    options: {
        responsive: true,
        legend: {
          position: 'right',
          labels: {
            padding: 20,
            boxWidth: 10
          }
        },
        plugins: {
          datalabels: {
            formatter: (value, ctx) => {
              let sum = 0;
              let dataArr = ctx.chart.data.datasets[0].data;
              dataArr.map(data => {
                sum += data;
              });
              let percentage = (value * 100 / sum).toFixed(0) + "%";
              return percentage;
            },
            color: 'white',
            labels: {
              title: {
                font: {
                  size: '16'
                }
              }
            }
          }
        }
    }
});

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

                $.getJSON(url + "/commits", {}, function (data) {
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

                $.getJSON(url + "/issues", {}, function (data) {
                    console.log(data);
                    var totalIssues = data.length;
                    console.log(totalIssues);
                    var dict = {
                        'shawheenattar': 0,
                        'adrianmb0': 0,
                        'ReedHopkins': 0,
                        'cameronsanders': 0,
                        'ramyarajasekaran': 0,
                        'evanchang2399': 0
                    };

                    $(data).each(function () {
                        dict[this.user.login] = dict[this.user.login] + 1;
                    })

                    list.append('<p>Total # of issues: ' + totalIssues + '</p>');

                    for (var key in dict) {
                        var val = dict[key];
                        list.append('<p>' + key + ': ' + val + ' issues</p>');
                    }

                    console.log(totalIssues);
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

                $.getJSON(url + "/commits", {}, function (data) {
                    var commits = 0;
                    $(data).each(function () {
                        if (this.author.login == username) commits = commits + 1;
                    })

                    list.append('<p>Number of commits: ' + commits + '</p>');
                });

                $.getJSON(url + "/issues", {}, function (data) {
                    var issues = 0;
                    $(data).each(function () {
                        if (this.user.login == username) issues = issues + 1;
                    })

                    list.append('<p>Number of issues: ' + issues + '</p>');
                });
            }
        });
    });
};