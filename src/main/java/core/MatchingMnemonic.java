package core;

public class MatchingMnemonic {

    public static final MatchingMnemonic NULL = new MatchingMnemonic(Utils.EMPTY_STRING, Utils.EMPTY_STRING);

    private final String acronym;
    private final MatchingMnemonicDetail detail;

    public MatchingMnemonic(String acronym, String expandedAcronym) {
        this(acronym, new MatchingMnemonicDetail(expandedAcronym, Utils.UNKNOWN_STRING, Utils.UNKNOWN_STRING));
    }

    public MatchingMnemonic(String acronym, MatchingMnemonicDetail detail) {
        this.acronym = acronym;
        this.detail = detail;
    }

    public String getAcronym() {
        return acronym;
    }

    public MatchingMnemonicDetail getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingMnemonic that = (MatchingMnemonic) o;

        if (that != null) {
            if (!acronym.equals(that.acronym)) return false;
            if (!detail.equals(that.detail)) return false;
        }


        return true;
    }

    @Override
    public int hashCode() {
        int result = acronym.hashCode();
        result = 31 * result + detail.hashCode();
        return result;
    }
}
