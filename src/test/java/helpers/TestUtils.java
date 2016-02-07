package helpers;

import junit.framework.AssertionFailedError;

import java.util.Collection;

public class TestUtils {

    public static void assertContains(String value, Collection<String> list) {
        for (String s : list) {
            if (value.equals(s)) {
                return;
            }
        }
        throw new AssertionFailedError(String.format("String %s not found in list", value));
    }
}
