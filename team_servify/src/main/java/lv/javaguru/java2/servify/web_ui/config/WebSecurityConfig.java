package lv.javaguru.java2.servify.web_ui.config;

import lv.javaguru.java2.servify.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(passwordEncoder());
        daoProvider.setUserDetailsService(userService);
        return daoProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/home").permitAll();
                    auth.requestMatchers("/register/**").permitAll();
                    auth.requestMatchers("/login/**", "/error").permitAll();
                    auth.requestMatchers("/detail/**").permitAll();
                    auth.requestMatchers("/orders/**", "/error").hasAnyAuthority("CUSTOMER", "MANAGER");
                    auth.requestMatchers("/create-order/**", "/error").hasAnyAuthority("CUSTOMER", "MANAGER");
                    auth.requestMatchers("/index/**").hasAuthority("MANAGER");
                    auth.requestMatchers("/admin/**", "/error").hasAuthority("MANAGER");
                    auth.requestMatchers("/user/**").hasAnyAuthority("CUSTOMER", "MANAGER");
                    auth.anyRequest().authenticated();
                });
        http
                .formLogin()
                .loginPage("/login");
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home");
        return http.build();
    }
}
