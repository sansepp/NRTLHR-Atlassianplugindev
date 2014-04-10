package ut.com.nortal.atlassian.confluence.plugin.groupaccess;

import org.junit.Test;
import com.nortal.atlassian.confluence.plugin.groupaccess.MyPluginComponent;
import com.nortal.atlassian.confluence.plugin.groupaccess.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}