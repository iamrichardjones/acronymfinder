package core;


public class MatchingMnemonicDetail {

    private final String expandedAcronym;
    private final String origin;

    public MatchingMnemonicDetail(String expandedAcronym, String origin) {
        this.expandedAcronym = expandedAcronym;
        this.origin = origin;
    }

    public String getExpandedAcronym() {
        return expandedAcronym;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingMnemonicDetail that = (MatchingMnemonicDetail) o;

        if (!expandedAcronym.equals(that.expandedAcronym)) return false;
        if (!origin.equals(that.origin)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expandedAcronym.hashCode();
        result = 31 * result + origin.hashCode();
        return result;
    }
}
