package ut.com.nortal.atlassian.confluence.plugin.groupaccess.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.atlassian.confluence.security.SpacePermission;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.confluence.spaces.SpaceManager;
import com.nortal.atlassian.confluence.plugin.groupaccess.service.SearchService;

@RunWith (MockitoJUnitRunner.class)
public class SearchServiceTest {
	
	private SpaceManager spaceManager;
	private SpacePermission permission;
	private List<Space> spaces;
	private SearchService searchService;
	
    @Before
    public void setUp()
    {
    	spaceManager = mock(SpaceManager.class);
    	
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
    	
    	searchService = new SearchService(spaceManager);
    }

    @Test 
    public void searchSpacesTestResult() throws Exception {
    	Map<String, String> spaces = searchService.searchSpaces("atlassian-users", true);
        assertEquals(1, spaces.size());
        assertEquals("Name", spaces.get("Key"));
    }

    @Test 
    public void searchSpacesTestNoResult() throws Exception {
    	Map<String, String> spaces = searchService.searchSpaces("atlassian-developers", false);
        assertEquals(0, spaces.size());
    }
}
