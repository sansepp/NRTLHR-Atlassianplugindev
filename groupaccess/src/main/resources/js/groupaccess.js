AJS.toInit(function() {
    AJS.$('#search-submit-button').click(search);
    AJS.$('#search-form-checkbox').click(checkbox);
});

function search(event) {
	AJS.$("#aui-message-bar").empty();
	
    var input = AJS.$("#search-form-input"),
        results = AJS.$('#search-results-container'),
        nested = 'false';
	
	results.empty();
	results.removeClass("result-border");
    
    if (AJS.$('#search-form-checkbox').attr('checked')) {
    	nested = 'true';
    }
    
    if (input.val() == '') {
    	AJS.messages.warning({
 		   title:AJS.params.searchEmptySearch
 		});
		
	} else if (input.val().length > 255) {
    	AJS.messages.warning({
 		   title:AJS.params.searchTooLong
 		});
    } else {
	    AJS.$.ajax({
	        url: '/confluence/rest/groupaccess/searchresource/1.0/' + input.val() + '/' + nested,
	        type: 'get',
	        cache: false,
	        dataType: 'json',
	        success: function(response) {
	            if (response.error == 'null') {
	                AJS.$.each(response.result, function() {
	                	results.append(renderMatch(String(this)));
	                });
	                results.addClass("result-border");
	                
	            	AJS.messages.success({
	            		   title:AJS.params.searchResultOk + ' ' + input.val()
	            		});
	                
	            } else if (response.error == "empty-string"){
	            	AJS.messages.warning({
	            		   title:AJS.params.searchEmptySearch
	            		});
	            } else if (response.error == "no-result") {
	            	AJS.messages.generic({
	         		   title:AJS.params.searchResultNodata + ' ' + input.val()
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

function checkbox(event) {
	AJS.$("#aui-message-bar").empty();
	if (AJS.$('#search-form-checkbox').attr('checked')) {
		AJS.messages.warning({
			   title:AJS.params.searchNestedWarning
			});
	}
}

function renderMatch(match) {
    renderMatch.template = renderMatch.template || AJS.$(AJS.$('#search-result-template').html());
	var container = renderMatch.template.clone(),
	    space = AJS.$('div.search-result', container);
	space.html(match);
	return container;
}