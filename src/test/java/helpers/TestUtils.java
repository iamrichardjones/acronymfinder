package helpers;

import junit.framework.AssertionFailedError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestUtils {

    public static void assertContains(String value, Collection<String> inputList) {
        if (inputList != null && value != null) {
            for (String s : inputList) {
                if (value.equals(s)) {
                    return;
                }
            }
        }
        throw new AssertionFailedError(String.format("String %s not found in list", value));
    }

    @Test
    public void testAssertContains_DoesContain(){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");

        assertContains("one", list);
    }

    @Test
    public void testAssertContains_DoesNotContain(){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");

        //check error message here too
        try {
            assertContains("three", list);
            fail("Should not get here - three is not part of list");
        }
        catch (AssertionFailedError error) {
            assertEquals("String three not found in list", error.getMessage());
        }
    }

    @Test(expected = AssertionFailedError.class)
    public void testAssertContains_NullList(){
        assertContains("", null);
    }

    @Test(expected = AssertionFailedError.class)
    public void testAssertContains_NullValue(){
        assertContains(null, new ArrayList<String>());
    }
}