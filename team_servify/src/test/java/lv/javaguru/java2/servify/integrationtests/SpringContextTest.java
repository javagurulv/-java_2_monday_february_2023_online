package lv.javaguru.java2.servify.integrationtests;

import lv.javaguru.java2.servify.core.config.ServifyCoreConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServifyCoreConfiguration.class})
public class SpringContextTest {

    @Autowired private ApplicationContext appContext;

    @Test
    public void start() {
        assertNotNull(appContext);
    }
}
