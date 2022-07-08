package ua.grayloki8.spring.springsecuritypractice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grayloki8.spring.springsecuritypractice.security.JWTUtil;
import ua.grayloki8.spring.springsecuritypractice.services.RegistrationService;
import ua.grayloki8.spring.springsecuritypractice.util.UserValidator;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private UserValidator personValidator;
    private RegistrationService registrationService;
    private JWTUtil jwtUtil;
    private ModelMapper modelMapper;
    @Autowired
    public AuthRestController(UserValidator personValidator, RegistrationService registrationService, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }


}
