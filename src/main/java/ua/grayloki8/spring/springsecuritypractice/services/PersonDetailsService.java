package ua.grayloki8.spring.springsecuritypractice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.grayloki8.spring.springsecuritypractice.models.User;
import ua.grayloki8.spring.springsecuritypractice.repositories.PeopleRepository;
import ua.grayloki8.spring.springsecuritypractice.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    @Autowired
    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> person = peopleRepository.findByUserName(username);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(person.get());
    }















}
