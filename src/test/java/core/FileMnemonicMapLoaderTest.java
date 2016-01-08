package core;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FileMnemonicMapLoaderTest {

    @Test
    public void testLoadFilePopulated() throws IOException {
        MnemonicMap mnemonicMap = new MnemonicMap();

        StringReader stringReader = new StringReader(
                "Slogan|Southwest Airlines|You are now free to move about the country|Yanftmatc" + System.lineSeparator() +
                "Slogan|Virginia Slims Cigarettes|You’ve come a long way, baby|Ycalwb");

        FileMnemonicMapLoader testLoader = new FileMnemonicMapLoader(new BufferedReader(stringReader));
        testLoader.load(mnemonicMap);
        Map<String, List<MatchingMnemonic>> afterMap = mnemonicMap.getMap();
        Set<String> strings = afterMap.keySet();
        assertEquals(2, strings.size());
        TestUtils.assertContains("AACFMNTTY", strings);
        TestUtils.assertContains("ABCLWY", strings);

        assertEquals(1, afterMap.get("AACFMNTTY").size());
        assertEquals(1, afterMap.get("ABCLWY").size());

        assertEquals("You are now free to move about the country", afterMap.get("AACFMNTTY").get(0).getDetail().getExpandedMnemonic());
        assertEquals("You’ve come a long way, baby", afterMap.get("ABCLWY").get(0).getDetail().getExpandedMnemonic());
    }

    @Test
    public void testLoadFile_EmptyString() throws IOException {
        MnemonicMap mnemonicMap = new MnemonicMap();

        StringReader stringReader = new StringReader("");

        FileMnemonicMapLoader testLoader = new FileMnemonicMapLoader(new BufferedReader(stringReader));
        testLoader.load(mnemonicMap);
        assertEquals(0, mnemonicMap.getMap().keySet().size());
    }

    @Test
    public void testLoadFile_JustNewLines() throws IOException {
        MnemonicMap mnemonicMap = new MnemonicMap();

        StringReader stringReader = new StringReader(System.lineSeparator() + System.lineSeparator());

        FileMnemonicMapLoader testLoader = new FileMnemonicMapLoader(new BufferedReader(stringReader));
        testLoader.load(mnemonicMap);
        assertEquals(0, mnemonicMap.getMap().keySet().size());
    }
}