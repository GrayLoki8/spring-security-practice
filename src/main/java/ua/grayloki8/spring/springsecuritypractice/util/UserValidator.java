package ua.grayloki8.spring.springsecuritypractice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.grayloki8.spring.springsecuritypractice.models.User;
import ua.grayloki8.spring.springsecuritypractice.services.PersonDetailsService;
@Component
public class UserValidator implements Validator {
    @Autowired
    private final PersonDetailsService personDetailsService;

    public UserValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User person=(User)target;
        try {
            personDetailsService.loadUserByUsername(person.getUserName());
        } catch (UsernameNotFoundException e) {
            return;
        }
        errors.rejectValue("username","Person with this name exists");
    }
}
