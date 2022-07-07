package ua.grayloki8.spring.springsecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.grayloki8.spring.springsecuritypractice.services.PersonDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //config Spring Security
        //config authorizationn

        http.authorizeRequests().
                antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/auth/login","/error","/auth/registration").permitAll().
                anyRequest().hasAnyRole("USER","ADMIN").
               and().
                formLogin().loginPage("/auth/login").
                loginProcessingUrl("/process_login").
        defaultSuccessUrl("/hello",true).
        failureUrl("/auth/login?error")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }



    //Configure authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
