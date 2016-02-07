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
        assertEquals("bac", next.get(0).getMnemonic());
        assertEquals("banana apple cherry", next.get(0).getDetail().getExpandedMnemonic());
        assertEquals(Utils.UNKNOWN_STRING, next.get(0).getDetail().getOrigin());
        assertEquals(Utils.UNKNOWN_STRING, next.get(0).getDetail().getCategory());
    }

    @Test
    public void testGetFromMap() {
        MnemonicMap mnemonicMap = new MnemonicMap();
        MatchingMnemonic originalInput = new MatchingMnemonic("bac", "banana apple cherry");
        mnemonicMap.add(originalInput);

        assertEquals(new ArrayList(), mnemonicMap.getValues("xyz"));
        assertNotNull(mnemonicMap.getValues("abc"));
        assertNotNull(mnemonicMap.getValues("bac"));
        assertNotNull(mnemonicMap.getValues("bca"));
        assertNotNull(mnemonicMap.getValues("acb"));
        assertNotNull(mnemonicMap.getValues("cba"));
        assertNotNull(mnemonicMap.getValues("cab"));

        List<MatchingMnemonic> cab = mnemonicMap.getValues("cab");
        assertEquals(1, cab.size());
        assertEquals(originalInput, cab.get(0));
        assertEquals(mnemonicMap.getValues("cba"), mnemonicMap.getValues("cab"));
        assertEquals(mnemonicMap.getValues("bac"), mnemonicMap.getValues("abc"));
    }
}