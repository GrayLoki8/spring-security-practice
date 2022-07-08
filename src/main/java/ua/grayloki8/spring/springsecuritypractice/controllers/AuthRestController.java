package ua.grayloki8.spring.springsecuritypractice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.grayloki8.spring.springsecuritypractice.dto.UserDTO;
import ua.grayloki8.spring.springsecuritypractice.models.User;
import ua.grayloki8.spring.springsecuritypractice.security.JWTUtil;
import ua.grayloki8.spring.springsecuritypractice.services.RegistrationService;
import ua.grayloki8.spring.springsecuritypractice.util.UserValidator;

import javax.validation.Valid;
import java.util.Map;

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
    @GetMapping("/login")
    public String loginPage(){
        return "suka";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") User user){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public Map<String,String> performRegistration(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        User user = convertToUser(userDTO);
        personValidator.validate(user,bindingResult);
        if (bindingResult.hasErrors()){
            return Map.of("message","Exception");
        }
        registrationService.register(user);
        String token = jwtUtil.generateToken(user.getUserName());
        return Map.of("jwt-token",token);
    }

    public User convertToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);
    }
}
