package utils;

import org.junit.Test;
import utils.Utils;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testRightPad_AddSome() {
        String threeLong = "abc";

        assertEquals("abc  ", Utils.fixedLengthString(threeLong, 5));
    }

    @Test
    public void testRightPad_AddNone() {
        String fiveLong = "abcde";

        assertEquals("abcde", Utils.fixedLengthString(fiveLong, 5));
        assertEquals("abcde", Utils.fixedLengthString(fiveLong, 4));//less than length of string
    }
}