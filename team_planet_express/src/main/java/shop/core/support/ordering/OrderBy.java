package shop.core.support.ordering;

import shop.core.domain.item.Item_;

public enum OrderBy {

    NAME(Item_.NAME),
    PRICE(Item_.PRICE);
    private final String defaultName;

    OrderBy(String name) {
        this.defaultName = name;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
