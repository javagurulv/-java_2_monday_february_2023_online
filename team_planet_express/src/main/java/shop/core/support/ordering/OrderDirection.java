package shop.core.support.ordering;

public enum OrderDirection {

    ASCENDING("ASC"),
    DESCENDING("DESC");

    private final String text;

    OrderDirection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
