package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jones on 30/12/2015.
 */
public class AcronymMap {

    private final Map<String, AcronymDetail> map = new HashMap<String, AcronymDetail>();

    public AcronymDetail getValues(String acronym) {
        char[] charArray = acronym.toCharArray();
        Arrays.sort(charArray);
        return map.get(new String(charArray));
    }

    public void add(AcronymDetail detail) {
        char[] charArray = detail.getAcronym().toCharArray();
        Arrays.sort(charArray);
        map.put(new String(charArray), detail);
    }

    //for tests
    public Map<String, AcronymDetail> getMap() {
        return map;
    }
}
