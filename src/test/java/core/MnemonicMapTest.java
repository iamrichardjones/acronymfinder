package core;

import org.junit.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MnemonicMapTest {

    @Test
    public void testAddToMap_SimpleCtor() {
        MnemonicMap mnemonicMap = new MnemonicMap();
        assertEquals(0, mnemonicMap.getMap().size());

        mnemonicMap.add(new MatchingMnemonic("bac", "banana apple cherry"));

        assertEquals(1, mnemonicMap.getMap().size());

        List<String> list = new ArrayList<String>();
        list.addAll(mnemonicMap.getMap().keySet());

        assertEquals("Should be upper case and ordered alphabetically", "ABC", list.get(0));

        Collection<List<MatchingMnemonic>> values = mnemonicMap.getMap().values();
        assertEquals(1, values.size());

        List<MatchingMnemonic> next = values.iterator().next();
        assertEquals(1, next.size());

        MatchingMnemonic matchingMnemonic = next.get(0);
        assertEquals("bac", matchingMnemonic.getMnemonic());
        assertEquals("banana apple cherry", matchingMnemonic.getDetail().getExpandedMnemonic());
        assertEquals(Utils.UNKNOWN_STRING, matchingMnemonic.getDetail().getOrigin());
        assertEquals(Utils.UNKNOWN_STRING, matchingMnemonic.getDetail().getCategory());
    }

    @Test
    public void testAddToMap_MatchingMnemonicDetailCtor() {
        MnemonicMap mnemonicMap = new MnemonicMap();
        assertEquals(0, mnemonicMap.getMap().size());

        mnemonicMap.add(new MatchingMnemonic("bat", new MatchingMnemonicDetail("banana apple tomato", "MyOrigin", "MyCategory")));

        assertEquals(1, mnemonicMap.getMap().size());

        List<String> list = new ArrayList<String>();
        list.addAll(mnemonicMap.getMap().keySet());

        assertEquals("Should be upper case and ordered alphabetically", "ABT", list.get(0));

        Collection<List<MatchingMnemonic>> values = mnemonicMap.getMap().values();
        assertEquals(1, values.size());

        List<MatchingMnemonic> next = values.iterator().next();
        assertEquals(1, next.size());

        MatchingMnemonic matchingMnemonic = next.get(0);
        assertEquals("bat", matchingMnemonic.getMnemonic());
        assertEquals("banana apple tomato", matchingMnemonic.getDetail().getExpandedMnemonic());
        assertEquals("MyOrigin", matchingMnemonic.getDetail().getOrigin());
        assertEquals("MyCategory", matchingMnemonic.getDetail().getCategory());
    }

    @Test
    public void testAddToMap_MatchingMnemonicDetailCtor_AddSameMnemonicDetailTwice() {
        MnemonicMap mnemonicMap = new MnemonicMap();
        assertEquals(0, mnemonicMap.getMap().size());

        mnemonicMap.add(new MatchingMnemonic("bat", new MatchingMnemonicDetail("banana apple tomato", "MyOrigin", "MyCategory")));
        mnemonicMap.add(new MatchingMnemonic("bat", new MatchingMnemonicDetail("banana apple tomato", "MyOrigin", "MyCategory")));

        assertEquals(1, mnemonicMap.getMap().size());

        List<String> list = new ArrayList<String>();
        list.addAll(mnemonicMap.getMap().keySet());

        assertEquals("Should be upper case and ordered alphabetically", "ABT", list.get(0));

        Collection<List<MatchingMnemonic>> values = mnemonicMap.getMap().values();
        assertEquals(1, values.size());

        List<MatchingMnemonic> next = values.iterator().next();
        assertEquals("Two values against one mnemonic key", 2, next.size());

        {
            MatchingMnemonic matchingMnemonic = next.get(0);
            assertEquals("bat", matchingMnemonic.getMnemonic());
            assertEquals("banana apple tomato", matchingMnemonic.getDetail().getExpandedMnemonic());
            assertEquals("MyOrigin", matchingMnemonic.getDetail().getOrigin());
            assertEquals("MyCategory", matchingMnemonic.getDetail().getCategory());
        }
        {
            MatchingMnemonic matchingMnemonic = next.get(1);
            assertEquals("bat", matchingMnemonic.getMnemonic());
            assertEquals("banana apple tomato", matchingMnemonic.getDetail().getExpandedMnemonic());
            assertEquals("MyOrigin", matchingMnemonic.getDetail().getOrigin());
            assertEquals("MyCategory", matchingMnemonic.getDetail().getCategory());
        }
    }

    @Test
    public void testGetFromMap() {
        MnemonicMap mnemonicMap = new MnemonicMap();
        MatchingMnemonic originalInput = new MatchingMnemonic("bac", "banana apple cherry");
        mnemonicMap.add(originalInput);

        assertEquals("xyz not in there", new ArrayList<MatchingMnemonic>(), mnemonicMap.getValues("xyz"));

        //and now the combination of abc - check they return something
        assertNotNull(mnemonicMap.getValues("abc"));
        assertNotNull(mnemonicMap.getValues("bac"));
        assertNotNull(mnemonicMap.getValues("bca"));
        assertNotNull(mnemonicMap.getValues("acb"));
        assertNotNull(mnemonicMap.getValues("cba"));
        assertNotNull(mnemonicMap.getValues("cab"));

        List<MatchingMnemonic> cab = mnemonicMap.getValues("cab");
        assertEquals(1, cab.size());
        assertEquals("original input should match", originalInput, cab.get(0));

        assertEquals(mnemonicMap.getValues("cba"), mnemonicMap.getValues("cab"));
        assertEquals(mnemonicMap.getValues("bac"), mnemonicMap.getValues("bca"));
        assertEquals(mnemonicMap.getValues("abc"), mnemonicMap.getValues("acb"));
        assertEquals(mnemonicMap.getValues("abc"), mnemonicMap.getValues("cba"));
    }
}