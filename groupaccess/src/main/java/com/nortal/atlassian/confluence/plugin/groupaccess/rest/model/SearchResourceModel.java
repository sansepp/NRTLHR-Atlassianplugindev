package com.nortal.atlassian.confluence.plugin.groupaccess.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.annotation.Nullable;

public class SearchResourceModel {

    @JsonProperty private List<String> result;
    @JsonProperty private String error;

    @JsonCreator
    public SearchResourceModel(Map<String, String> results,
    		@JsonProperty("error") @Nullable String error) {
    	populateResult(results);
        this.error = error;
    }
    
    private void populateResult(Map<String, String> results) {
    	this.result = new ArrayList<String>();
    	for (String key : results.keySet()) {
    		this.result.add(results.get(key) + " (" + key + ")");
    	}
    }

    public List<String> getResult() {
        return result;
    }
    
    public String getError() {
    	return error;
    }
}