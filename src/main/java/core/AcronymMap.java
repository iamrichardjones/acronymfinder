package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AcronymMap {

    private final Map<String, AcronymDetail> map = new HashMap<String, AcronymDetail>();

    public AcronymDetail getValues(String acronym) {
        char[] charArray = acronym.toCharArray();
        Arrays.sort(charArray);
        AcronymDetail result = map.get(new String(charArray));
        return result == null ? AcronymDetail.NULL : result;
    }

    public void add(AcronymDetail detail) {
        char[] charArray = detail.getAcronym().toUpperCase().toCharArray();
        Arrays.sort(charArray);
        map.put(new String(charArray), detail);
    }

    //for tests
    public Map<String, AcronymDetail> getMap() {
        return map;
    }
}
