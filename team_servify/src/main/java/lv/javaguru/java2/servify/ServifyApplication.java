package lv.javaguru.java2.servify;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import lv.javaguru.java2.servify.config.ServifyConfiguration;
import lv.javaguru.java2.servify.console_ui.*;


class ServifyApplication {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        try {
            var context = new AnnotationConfigApplicationContext(ServifyConfiguration.class);
            var uiMenu = context.getBean(UIMenu.class);
            uiMenu.startUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


