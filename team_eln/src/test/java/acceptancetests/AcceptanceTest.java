package acceptancetests;

import java2.eln.config.ELNconfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

@ContextConfiguration(classes = ELNconfiguration.class)
@Sql({"/dbCreationScript.sql", "/dbDataInsertScript.sql"})
public class AcceptanceTest {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        getDatabaseCleaner().clean();
        appContext = new AnnotationConfigApplicationContext(ELNconfiguration.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
