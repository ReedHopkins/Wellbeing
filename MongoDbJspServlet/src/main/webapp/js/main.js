var commitDict = {
		'shawheenattar': 0,
		'adrianmb0': 0,
		'ReedHopkins': 0,
		'cameronsanders': 0,
		'ramyarajasekaran': 0,
		'evanchang2399': 0
};

var issuesDict = {
		"shawheenattar": 0,
		"adrianmb0": 0,
		"ReedHopkins": 0,
		"cameronsanders": 0,
		"ramyarajasekaran": 0,
		"evanchang2399": 0
};

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
                var totalCommits = 0;

                $.getJSON(url + "/commits?per_page=100&page=1", {}, function (data) {
                	
                    totalCommits = totalCommits + data.length;
                    
                    $(data).each(function () {
                    	commitDict[this.author.login] = commitDict[this.author.login] + 1;
                    })
                    
                })
                .done(function() {
                	
                	$.getJSON(url + "/commits?per_page=100&page=2", {}, function (data) {
                		
                        totalCommits = totalCommits + data.length;
                        
                        $(data).each(function () {
                        	commitDict[this.author.login] = commitDict[this.author.login] + 1;
                        })
                        
                    })
                	.done(function() {
                		
                		list.append('<p>Total # of commits: ' + totalCommits + '</p>');
                        for (var key in commitDict) {
                            var val = commitDict[key];
                            list.append('<p>' + key + ': ' + val + ' commits</p>');
                        }
                        
                	})
                	.done(function() {
                		
	                	$.getJSON(url + "/issues?per_page=100", {}, function (data) {
	                        console.log(data);
	                        $(data).each(function () {
	                        	issuesDict[this.user.login] = issuesDict[this.user.login] + 1;
	                        })
	                    })
	                    
	                    .done(function() {
	                    	
	                    	console.log("totalCommits: " + totalCommits);
	                        console.log(commitDict);
	                        console.log("issue: ");
	                        console.log(issuesDict);
	                        
	                    });  
                	}); 
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
    
    var list = $('<div/>');
    target.empty().append(list);
    list.append('<p><strong>Number of commits: </strong>' + commitDict[username] + '</p>');
    list.append('<p><strong>Number of issues: </strong>' + issuesDict[username] + '</p>');
};

// jQuery.fn.loadContributorStats = function (username) {
// var target = this;
//
// $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function
// (data) {
//
// var repos = data;
//
// var list = $('<div/>');
// target.empty().append(list);
// $(repos).each(function () {
//
// if (this.name == "Wellbeing") {
// var url = this.url;
//
// var commits = 0;
// $.getJSON(url + "/commits?per_page=100&page=1", {}, function (data) {
// $(data).each(function () {
// if (this.author.login == username) commits = commits + 1;
// })
// })
// .done(function() {
// $.getJSON(url + "/commits?per_page=100&page=2", {}, function (data) {
// var commits = 0;
// $(data).each(function () {
// if (this.author.login == username) commits = commits + 1;
// })
// })
// .done(function() {
// list.append('<p><strong>Number of commits: </strong>' + commits + '</p>');
// });
// });
//
// var issues = 0;
// $.getJSON(url + "/issues?per_page=100", {}, function (data) {
// $(data).each(function () {
// if (this.user.login == username) issues = issues + 1;
// })
//
// list.append('<p><strong>Number of issues: </strong>' + issues + '</p>');
// });
// }
// });
// });
// };



//
//
// jQuery.fn.loadRepositories = function () {
// var target = this;
//
// $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function
// (data) {
//
// var repos = data;
// sortByNumberOfWatchers(repos);
//
// var list = $('<div/>');
// list.append('<h3>Commits</h3>');
// target.empty().append(list);
// $(repos).each(function () {
//
// if (this.name == "Wellbeing") {
// var url = this.url + "/commits?per_page=100&page=";
//
// var dict = {
// 'shawheenattar': 0,
// 'adrianmb0': 0,
// 'ReedHopkins': 0,
// 'cameronsanders': 0,
// 'ramyarajasekaran': 0,
// 'evanchang2399': 0
// };
// var totalCommits = 0;
// console.log(url);
//                
// for (var page = 1; page <= 2; page++) {
// $.getJSON(url + page, {}, function (data) {
// totalCommits = totalCommits + data.length;
//                        
// $(data).each(function () {
// dict[this.author.login] = dict[this.author.login] + 1;
// })
//
// });
// }
// list.append('<p>Total # of commits: ' + totalCommits + '</p>');
//                
// console.log(dict);
// console.log(totalCommits);
//
// for (var key in dict) {
// var val = dict[key];
// list.append('<p>' + key + ': ' + val + ' commits</p>');
// }
// }
// });
// });
//
// function sortByNumberOfWatchers(repos) {
// repos.sort(function (a, b) {
// return b.watchers - a.watchers;
// });
// }
// };
//
// jQuery.fn.loadContributorStats = function (username) {
// var target = this;
//
// $.getJSON('https://api.github.com/orgs/WellBeingEating/repos', {}, function
// (data) {
//
// var repos = data;
//
// var list = $('<div/>');
// target.empty().append(list);
// $(repos).each(function () {
//
// if (this.name == "Wellbeing") {
// var url = this.url;
//                
// var commits = 0;
// for (var page = 1; page <= 2; page++) {
// $.getJSON(url + "/commits?per_page=100&page=" + page, {}, function (data) {
//	                	
// $(data).each(function () {
// if (this.author.login == username) commits = commits + 1;
// })
// });
// }
// list.append('<p><strong>Number of commits: </strong>' + commits + '</p>');
//
// var issues = 0;
// $.getJSON(url + "/issues?per_page=100", {}, function (data) {
// $(data).each(function () {
// if (this.user.login == username) issues = issues + 1;
// })
// });
// list.append('<p><strong>Number of issues: </strong>' + issues + '</p>');
// }
// });
// });
// };
