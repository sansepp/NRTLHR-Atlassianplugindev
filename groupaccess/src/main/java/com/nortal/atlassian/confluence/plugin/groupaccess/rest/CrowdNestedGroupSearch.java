package com.nortal.atlassian.confluence.plugin.groupaccess.rest;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.crowd.integration.rest.service.factory.RestCrowdClientFactory;
import com.atlassian.crowd.model.group.Group;
import com.atlassian.crowd.exception.ApplicationPermissionException;
import com.atlassian.crowd.exception.GroupNotFoundException;
import com.atlassian.crowd.exception.InvalidAuthenticationException;
import com.atlassian.crowd.exception.OperationFailedException;
import com.atlassian.crowd.service.client.CrowdClient;

public class CrowdNestedGroupSearch {
	
	private CrowdClient crowdClient;
	private RestCrowdClientFactory restCrowdClientFactory;
	
	public CrowdNestedGroupSearch() {
		restCrowdClientFactory = new RestCrowdClientFactory();
		crowdClient = restCrowdClientFactory.newInstance("http://localhost:4990/crowd", "crowd_auth", "admin");
	}

	public List<Group> doSearchAndGetGroups(String groupName){
		List<Group> groups = new ArrayList<Group>();
		try {
			groups = crowdClient.getParentGroupsForGroup(groupName, 0, 20);
		} catch (GroupNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groups;
    }

}
