package lv.javaguru.java2.servify.web_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.javaguru.java2.servify"})
public class SpringWebConfiguration implements WebMvcConfigurer{

//    public void addViewControllers(ViewControllerRegistry registry) {
//        //registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("index");
//        //registry.addViewController("/hello").setViewName("hello");
//        //registry.addViewController("/login").setViewName("login");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resourceViewResolver = new SpringResourceTemplateResolver();
        resourceViewResolver.setPrefix("classpath:/templates/");
        resourceViewResolver.setSuffix(".html");
        resourceViewResolver.setTemplateMode(TemplateMode.HTML);
        resourceViewResolver.setCharacterEncoding("UTF-8");
        resourceViewResolver.setCheckExistence(false);
        return resourceViewResolver;
    }

}

