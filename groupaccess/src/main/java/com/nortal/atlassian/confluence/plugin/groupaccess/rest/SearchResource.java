package com.nortal.atlassian.confluence.plugin.groupaccess.rest;

import java.util.HashMap;
import java.util.Map;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.atlassian.sal.api.user.UserManager;
import com.nortal.atlassian.confluence.plugin.groupaccess.rest.model.SearchResourceModel;
import com.nortal.atlassian.confluence.plugin.groupaccess.service.SearchService;

/**
 * A resource of message.
 */
@Path("/{search}/{nested}")
public class SearchResource {

	private final SpaceManager spaceManager;
    private final UserManager userManager;
    private final SearchService searchService;

	public SearchResource(SpaceManager spaceManager, UserManager userManager) {
		this.spaceManager = spaceManager;
		this.userManager = userManager;
		searchService = new SearchService(this.spaceManager);
	}

	@GET
	@AnonymousAllowed
	@Produces({ MediaType.APPLICATION_JSON })
	public Response get(@PathParam("search") String search, @PathParam("nested") Boolean nested) {
		String userName = userManager.getRemoteUsername();
		if (userName == null || !userManager.isSystemAdmin(userName)) {
			return Response.serverError().build();
		}
		if (search == null || search.isEmpty() || search.trim().isEmpty()) {
			return Response.ok(new SearchResourceModel(new HashMap<String, String>(), "empty-string")).build();
		}
		Map<String, String> result = searchService.searchSpaces(search, nested);
		return Response.ok(new SearchResourceModel(result, result.size() > 0 ? "null" : "no-result")).build();	
	}
}