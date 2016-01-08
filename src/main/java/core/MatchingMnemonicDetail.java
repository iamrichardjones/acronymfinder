package core;


public class MatchingMnemonicDetail {

    private final String expandedAcronym;
    private final String origin;
    private final String category;

    public MatchingMnemonicDetail(String expandedAcronym, String origin, String category) {
        this.expandedAcronym = expandedAcronym;
        this.origin = origin;
        this.category = category;
    }

    public String getExpandedAcronym() {
        return expandedAcronym;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingMnemonicDetail that = (MatchingMnemonicDetail) o;

        if (!expandedAcronym.equals(that.expandedAcronym)) return false;
        if (!origin.equals(that.origin)) return false;
        if (!category.equals(that.category)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expandedAcronym.hashCode();
        result = 31 * result + origin.hashCode();
        return result;
    }
}
