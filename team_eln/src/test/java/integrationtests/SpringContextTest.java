package integrationtests;

import java2.eln.config.ELNconfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = ELNconfiguration.class)
public class SpringContextTest {

    @Autowired
    private ApplicationContext appContext;

    @Test
    public void start() {
        assertNotNull(appContext);
    }
}
