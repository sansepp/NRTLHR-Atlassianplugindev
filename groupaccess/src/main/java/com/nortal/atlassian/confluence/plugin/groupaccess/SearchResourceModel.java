package com.nortal.atlassian.confluence.plugin.groupaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.annotation.Nullable;
import javax.ws.rs.core.Response;

public class SearchResourceModel {

    @JsonProperty private List<String> result;
    @JsonProperty private String error;

    @JsonCreator
    public SearchResourceModel(Map<String, String> results,
    		@JsonProperty("error") @Nullable String error) {
    	
    	this.result = new ArrayList<String>();
    	
    	for (String key : results.keySet()) {
    		this.result.add(results.get(key) + " (" + key + ")");
    	}
    	
        this.error = error;
    }

    public List<String> getResult() {
        return result;
    }
    
    public String getError() {
    	return error;
    }
}