package com.nortal.atlassian.confluence.plugin.groupaccess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.crowd.model.group.Group;
import com.nortal.atlassian.confluence.plugin.groupaccess.rest.CrowdNestedGroupSearch;

public class SearchService {
	private final SpaceManager spaceManager;
	private Map<String, String> result;
	private List<String> groupNames;
	private CrowdNestedGroupSearch crowdNestedGroupSearch;

	public SearchService(SpaceManager spaceManager) {
		this.spaceManager = spaceManager;
	}

	public Map<String, String> searchSpaces(String groupName, Boolean nestedSearch) {
		init();
		populateGroupNames(groupName, nestedSearch);
		spacesForGroups();
		return result;
	}

	private void init() {
		if (crowdNestedGroupSearch == null) {
			crowdNestedGroupSearch = new CrowdNestedGroupSearch();
		}
		if (result == null) {
			result = new HashMap<String, String>();
		} else {
			result.clear();
		}
		if (groupNames == null) {
			groupNames = new ArrayList<String>();
		} else {
			groupNames.clear();
		}
	}

	private void populateGroupNames(String groupName, Boolean nestedSearch) {
		if (nestedSearch) {
			addIntoGroupNames(groupName);
			searchNestedUpperGroups(groupName);
		} else {
			addIntoGroupNames(groupName);
		}
	}

	private void searchNestedUpperGroups(String groupName) {
		for (Group group : crowdNestedGroupSearch.doSearchAndGetGroups(groupName)) {
			addIntoGroupNames(group.getName());
		}
	}

	private void addIntoGroupNames(String groupName) {
		groupNames.add(groupName);
	}

	private void spacesForGroups() {
		for (Space space : spaceManager.getAllSpaces()) {
			for (SpacePermission permission : space.getPermissions()) {
				if (permission.isGroupPermission() && groupNames.contains(permission.getGroup())) {
					result.put(space.getKey(), space.getName());
				}
			}
		}
	}
}
