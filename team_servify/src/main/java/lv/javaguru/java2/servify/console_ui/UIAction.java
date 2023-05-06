package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.domain.UserType;

import java.util.List;

public interface UIAction {

    void execute();

    String getMenuItem();
    List<UserType> getAccessUserByType();

}
