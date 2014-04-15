package com.nortal.atlassian.confluence.plugin.groupaccess.crowd;

import java.util.ArrayList;
import java.util.List;

import com.atlassian.config.ConfigurationException;
import com.atlassian.crowd.integration.rest.service.factory.RestCrowdClientFactory;
import com.atlassian.crowd.model.group.Group;
import com.atlassian.crowd.exception.ApplicationPermissionException;
import com.atlassian.crowd.exception.GroupNotFoundException;
import com.atlassian.crowd.exception.InvalidAuthenticationException;
import com.atlassian.crowd.exception.OperationFailedException;
import com.atlassian.crowd.service.client.ClientProperties;
import com.atlassian.crowd.service.client.ClientPropertiesImpl;
import com.atlassian.crowd.service.client.ClientResourceLocator;
import com.atlassian.crowd.service.client.CrowdClient;

public class CrowdNestedGroupSearch {

	private CrowdClient crowdClient;
	private RestCrowdClientFactory restCrowdClientFactory;
	private ClientProperties clientProperties;
	private final String CROWD_PROPS = "crowd.properties";

	public CrowdNestedGroupSearch() throws ConfigurationException {
		initProperties();
		restCrowdClientFactory = new RestCrowdClientFactory();
		crowdClient = restCrowdClientFactory.newInstance(clientProperties);
	}

	public List<Group> doSearchAndGetGroups(String groupName) throws GroupNotFoundException, OperationFailedException,
			InvalidAuthenticationException, ApplicationPermissionException {
		List<Group> groups = new ArrayList<Group>();
		groups = crowdClient.getParentGroupsForGroup(groupName, 0, 30);
		return groups;
	}
	
	private void initProperties() throws ConfigurationException {
		ClientResourceLocator crl = new ClientResourceLocator(CROWD_PROPS);
		if (crl.getProperties() == null) {
			throw new ConfigurationException("The " + CROWD_PROPS + " can not be found");
		}
		clientProperties = ClientPropertiesImpl.newInstanceFromResourceLocator(crl);
	}
}
