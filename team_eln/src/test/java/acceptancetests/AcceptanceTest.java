package acceptancetests;

import java2.eln.config.ELNconfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class AcceptanceTest {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(ELNconfiguration.class);
    }
}
