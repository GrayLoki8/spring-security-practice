package ua.grayloki8.spring.springsecuritypractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider authProvider;

    public SecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth){
        System.out.println("from sec con");
        auth.authenticationProvider(authProvider);;
    }
}
