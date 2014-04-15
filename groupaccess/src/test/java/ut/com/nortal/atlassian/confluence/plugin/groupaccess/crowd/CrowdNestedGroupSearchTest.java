package ut.com.nortal.atlassian.confluence.plugin.groupaccess.crowd;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.atlassian.crowd.model.group.Group;
import com.nortal.atlassian.confluence.plugin.groupaccess.crowd.CrowdNestedGroupSearch;

@RunWith (MockitoJUnitRunner.class)
public class CrowdNestedGroupSearchTest {
	
	private CrowdNestedGroupSearch crowdNestedGroupSearch;

	@Test
    public void testDoSearchAndGetGroupsEmpty() throws Exception
    {
		crowdNestedGroupSearch = new CrowdNestedGroupSearch();
		List<Group> groups = crowdNestedGroupSearch.doSearchAndGetGroups("atlassian-users");
        assertEquals(0, groups.size());
    }

	@Test
    public void testDoSearchAndGetGroupsWithResult() throws Exception
    {
		crowdNestedGroupSearch = new CrowdNestedGroupSearch();
		List<Group> groups = crowdNestedGroupSearch.doSearchAndGetGroups("atlassian-developers");
        assertEquals(1, groups.size());
    }
}
