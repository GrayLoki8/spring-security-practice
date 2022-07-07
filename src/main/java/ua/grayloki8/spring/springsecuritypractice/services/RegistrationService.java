package ua.grayloki8.spring.springsecuritypractice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.grayloki8.spring.springsecuritypractice.models.User;
import ua.grayloki8.spring.springsecuritypractice.repositories.PeopleRepository;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(User user){
       user.setPassword( passwordEncoder.encode(user.getPassword()));
        peopleRepository.save(user);
    }






















}
