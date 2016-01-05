package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MnemonicMap {

    private final Map<String, MatchingMnemonic> map = new HashMap<String, MatchingMnemonic>();

    public MatchingMnemonic getValues(String acronym) {
        char[] charArray = acronym.toUpperCase().toCharArray();
        Arrays.sort(charArray);
        MatchingMnemonic result = map.get(new String(charArray));
        return result == null ? MatchingMnemonic.NULL : result;
    }

    public void add(MatchingMnemonic detail) {
        char[] charArray = detail.getAcronym().toUpperCase().toCharArray();
        Arrays.sort(charArray);
        map.put(new String(charArray), detail);
    }

    //for tests
    public Map<String, MatchingMnemonic> getMap() {
        return map;
    }
}
