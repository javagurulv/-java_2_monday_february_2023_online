package shop.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "shop.core")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
@EntityScan(basePackages = "shop.core.domain")
@EnableJpaRepositories(value = "shop.core.database.jpa")
public class SpringCoreConfiguration {

//    Liquibase wants money for triggers
//    @Bean
//    public SpringLiquibase springLiquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");
//        liquibase.setShouldRun(true);
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }

}
