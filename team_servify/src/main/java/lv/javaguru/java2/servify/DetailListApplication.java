package lv.javaguru.java2.servify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import lv.javaguru.java2.servify.config.ServifyConfiguration;
import lv.javaguru.java2.servify.console_ui.*;


class DetailListApplication {

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ServifyConfiguration.class);

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


