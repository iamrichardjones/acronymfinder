package core;

public class AcronymDetail {

    public static final AcronymDetail NULL = new AcronymDetail(Utils.NULL_STRING, Utils.NULL_STRING);

    private final String acronym;
    private final String expandedAcronym;
    private final String origin;

    public AcronymDetail(String acronym, String expandedAcronym) {
        this(acronym, expandedAcronym, Utils.NULL_STRING);
    }

    public AcronymDetail(String acronym, String expandedAcronym, String origin) {
        this.acronym = acronym;
        this.expandedAcronym = expandedAcronym;
        this.origin = origin;
    }

    public String getAcronym() {
        return acronym;
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

        AcronymDetail that = (AcronymDetail) o;

        if (acronym != null ? !acronym.equals(that.acronym) : that.acronym != null) return false;
        if (expandedAcronym != null ? !expandedAcronym.equals(that.expandedAcronym) : that.expandedAcronym != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = acronym != null ? acronym.hashCode() : 0;
        result = 31 * result + (expandedAcronym != null ? expandedAcronym.hashCode() : 0);
        return result;
    }
}
