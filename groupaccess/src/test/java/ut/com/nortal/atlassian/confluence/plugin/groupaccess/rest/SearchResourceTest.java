package ut.com.nortal.atlassian.confluence.plugin.groupaccess.rest;
/*
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.sal.api.user.UserManager;
import com.nortal.atlassian.confluence.plugin.groupaccess.rest.SearchResource;
import javax.ws.rs.core.Response;
import org.junit.Test;*/

//@RunWith (MockitoJUnitRunner.class)
public class SearchResourceTest {
    //TODO! HOW TO TEST REST RESOURCES?
	
	/*
	private SpaceManager spaceManager;
	private UserManager userManager;
	private List<Space> spaces;
	private SpacePermission permission;
	private SearchResource searchResource;

    @Before
    public void setUp()
    {
    	spaceManager = mock(SpaceManager.class);
    	userManager = mock(UserManager.class);
    	
    	spaces = new ArrayList<Space>();
    	
    	Space space = new Space();
    	space.setName("Name");
    	space.setKey("Key");
    	
    	permission = mock(SpacePermission.class);
    	when(permission.isGroupPermission()).thenReturn(true);
    	when(permission.getGroup()).thenReturn("atlassian-users");

    	space.addPermission(permission);
    	spaces.add(space);
    	
    	when(spaceManager.getAllSpaces()).thenReturn(spaces);
    	
    	searchResource = new SearchResource(spaceManager, userManager);
    }
    
    @Test
    public void testGetResult() throws Exception {
    	when(userManager.getRemoteUsername()).thenReturn("admin_crowd");
    	when(userManager.isSystemAdmin("admin_crowd")).thenReturn(true);
    	Response resp = searchResource.get("atlassian-users", true);

    	assertTrue(true);
    }
    
    @Test
    public void testGetNoResult() throws Exception {
    	when(userManager.getRemoteUsername()).thenReturn("admin_crowd");
    	when(userManager.isSystemAdmin("admin_crowd")).thenReturn(true);
    	Response resp = searchResource.get("atlassian-developers", false);

    	assertTrue(true);
    }
    
    @Test
    public void testGetError() throws Exception {
    	when(userManager.getRemoteUsername()).thenReturn(null);
    	when(userManager.isSystemAdmin("admin_crowd")).thenReturn(false);
    	Response resp = searchResource.get("atlassian-users", true);

    	assertTrue(true);
    }
    
    @Test
    public void testGetEmpty() throws Exception {
    	when(userManager.getRemoteUsername()).thenReturn("admin_crowd");
    	when(userManager.isSystemAdmin("admin_crowd")).thenReturn(true);
    	Response resp = searchResource.get("atlassian-users", true);

    	assertTrue(true);
    }
    
    */
	
	

}
