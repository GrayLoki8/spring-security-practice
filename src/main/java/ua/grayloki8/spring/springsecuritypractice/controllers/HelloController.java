package ua.grayloki8.spring.springsecuritypractice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.grayloki8.spring.springsecuritypractice.security.PersonDetails;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(Model model){

        model.addAttribute("test","test");
        return "auth/hello2";
    }
    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)auth.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "auth/hello2";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
