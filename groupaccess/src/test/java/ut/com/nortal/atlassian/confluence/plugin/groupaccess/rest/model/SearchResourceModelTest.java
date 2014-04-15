package ut.com.nortal.atlassian.confluence.plugin.groupaccess.rest.model;

import static org.junit.Assert.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.nortal.atlassian.confluence.plugin.groupaccess.rest.model.SearchResourceModel;

@RunWith (MockitoJUnitRunner.class)
public class SearchResourceModelTest {

	@Test
    public void testSimpleConstructor() throws Exception
    {
        ObjectMapper m = new ObjectMapper();
        SearchResourceModel searchResourceModel = m.readValue("{\"results\":{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"},\"error\":\"true\"}", SearchResourceModel.class);
        assertEquals("true", searchResourceModel.getError());
        assertEquals(3, searchResourceModel.getResult().size());
    } 

}
