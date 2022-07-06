package ua.grayloki8.spring.springsecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.grayloki8.spring.springsecuritypractice.services.PersonDetailsService;

import java.util.Collections;
@Component
public class AuthProvider implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public AuthProvider(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        System.out.println(name);
        UserDetails userDetails = personDetailsService.loadUserByUsername(name);
        String password=authentication.getCredentials().toString();
        System.out.println(password);
        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Password incorrect");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
