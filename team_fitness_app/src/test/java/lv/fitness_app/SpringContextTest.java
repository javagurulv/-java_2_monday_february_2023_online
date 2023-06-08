package lv.fitness_app;

import lv.fitness_app.web_ui.config.SpringWebConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfiguration.class})
public class SpringContextTest {

	@Autowired private ApplicationContext appContext;

	@Test
	public void start() {
		assertNotNull(appContext);
	}

}
