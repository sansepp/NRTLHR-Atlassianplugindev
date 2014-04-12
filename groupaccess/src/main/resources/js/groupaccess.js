AJS.toInit(function() {
    AJS.$('#search-submit-button').click(search);
});

function search(event) {
    var input = AJS.$("#search-form-input"),
        results = AJS.$('#search-results-container');

    AJS.$.ajax({
        url: '/confluence/rest/groupaccess/searchresource/1.0/' + input.val(),
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function(response) {
            results.empty();
            AJS.$.each(response.result, function() {
                AJS.$('#search-results-container').append(renderMatch(String(this)));
            });
        },
        error: function(request) {
            alert('oh noes!');
        }
    });
    event.preventDefault();
}

function renderMatch(match) {
    renderMatch.template = renderMatch.template ||
    AJS.$(AJS.$('#search-result-template').html());
	var container = renderMatch.template.clone(),
	    link = AJS.$('a.search-result-link', container);
	link.attr('href', 'http://neti.ee');
	link.html(match);
	return container;
}