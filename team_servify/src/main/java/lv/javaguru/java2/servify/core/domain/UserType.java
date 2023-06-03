package lv.javaguru.java2.servify.core.domain;

public enum UserType {
    CUSTOMER, //can make orders and look historical data
    MANAGER, //manage application, prices, users e.t.c.
    ANONYMOUS //by default can make order without save history
}
