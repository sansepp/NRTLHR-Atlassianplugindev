AJS.toInit(function() {
    AJS.$('#search-submit-button').click(search);
});

function search(event) {
	AJS.$("#aui-message-bar").empty();
	
    var input = AJS.$("#search-form-input"),
        results = AJS.$('#search-results-container');
    
    if (input.val() == '') {
    	AJS.messages.warning({
 		   title:AJS.params.searchEmptySearch
 		});
    } else {
	    AJS.$.ajax({
	        url: '/confluence/rest/groupaccess/searchresource/1.0/' + input.val(),
	        type: 'get',
	        cache: false,
	        dataType: 'json',
	        success: function(response) {
	            results.empty();
	            results.removeClass("result-border");
	            if (response.error == 'null') {
	                AJS.$.each(response.result, function() {
	                	results.append(renderMatch(String(this)));
	                });
	                results.addClass("result-border");
	            } else if (response.error == "empty-string"){
	            	AJS.messages.warning({
	            		   title:AJS.params.searchEmptySearch
	            		});
	            } else if (response.error == "no-result") {
	            	AJS.messages.generic({
	         		   title:AJS.params.searchResultNodata
	         		});
	            }
	        },
	        error: function(request) {
	            results.empty();
	            results.removeClass("result-border");
	        	AJS.messages.error({
	     		   title:AJS.params.searchResultError
	     		});
	        }
	    });
    }
    event.preventDefault();
}

function renderMatch(match) {
    renderMatch.template = renderMatch.template || AJS.$(AJS.$('#search-result-template').html());
	var container = renderMatch.template.clone(),
	    space = AJS.$('div.search-result', container);
	space.html(match);
	return container;
}

function renderError(error) {
	renderError.template = renderError.template || AJS.$(AJS.$('#search-error-template').html());
	var container = renderError.template.clone(),
		message = AJS.$('div.search-error-message', container);
	message.html(error);
	return container;
}