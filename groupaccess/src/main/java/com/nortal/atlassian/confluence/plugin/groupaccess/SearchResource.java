package com.nortal.atlassian.confluence.plugin.groupaccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.sal.api.user.UserManager;

/**
 * A resource of message.
 */
@Path("/{search}")
public class SearchResource {

	private final SpaceManager spaceManager;
    private final UserManager userManager;

	public SearchResource(SpaceManager spaceManager, UserManager userManager) {
		this.spaceManager = spaceManager;
		this.userManager = userManager;
	}

	@GET
	@AnonymousAllowed
	@Produces({ MediaType.APPLICATION_JSON })
	public Response get(@PathParam("search") String search) {
		
		String userName = userManager.getRemoteUsername();
		if (userName == null || !userManager.isSystemAdmin(userName)) {
			return Response.ok().build();
		}
		
		Map<String, String> result = searchSpaces(search);
		return Response.ok(new SearchResourceModel(result, result.size() > 0 ? null : "no-result")).build();
	}
	
	private Map<String, String> searchSpaces(String groupName) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		List<Space> spaces = spaceManager.getAllSpaces();
		
		for (Space space : spaces) {
			for (SpacePermission permission : space.getPermissions()) {
				if (permission.isGroupPermission()) {
					if (permission.getGroup().equals(groupName)) {
						result.put(space.getKey(), space.getName());
					}
				}
			}
		}
		
		return result;
		
	}
}