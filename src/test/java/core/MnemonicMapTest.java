package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MnemonicMapTest {

    @Test
    public void testAddToMap () {
        MnemonicMap mnemonicMap = new MnemonicMap();
        assertEquals(0, mnemonicMap.getMap().size());

        mnemonicMap.add(new MatchingMnemonic("bac", "banana apple cherry"));

        assertEquals(1, mnemonicMap.getMap().size());
        List<String> next1 = new ArrayList<String>();
        next1.addAll(mnemonicMap.getMap().keySet());

        assertEquals(next1.toString(), "ABC", next1.get(0));
        MatchingMnemonic next = mnemonicMap.getMap().values().iterator().next();
        assertEquals("bac", next.getAcronym());
        assertEquals("banana apple cherry", next.getDetail().getExpandedAcronym());
        assertEquals(Utils.UNKNOWN_STRING, next.getDetail().getOrigin());
    }

    @Test
    public void testGetFromMap () {
        MnemonicMap mnemonicMap = new MnemonicMap();
        MatchingMnemonic originalInput = new MatchingMnemonic("bac", "banana apple cherry");
        mnemonicMap.add(originalInput);

        assertEquals(MatchingMnemonic.NULL, mnemonicMap.getValues("xyz"));
        assertNotNull(mnemonicMap.getValues("abc"));
        assertNotNull(mnemonicMap.getValues("bac"));
        assertNotNull(mnemonicMap.getValues("bca"));
        assertNotNull(mnemonicMap.getValues("acb"));
        assertNotNull(mnemonicMap.getValues("cba"));
        assertNotNull(mnemonicMap.getValues("cab"));

        assertEquals(originalInput, mnemonicMap.getValues("cab"));
        assertEquals(mnemonicMap.getValues("cba"), mnemonicMap.getValues("cab"));
        assertEquals(mnemonicMap.getValues("bac"), mnemonicMap.getValues("abc"));
    }
}