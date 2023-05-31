package shop;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class ShopApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
