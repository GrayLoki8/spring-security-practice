package ua.grayloki8.spring.springsecuritypractice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.grayloki8.spring.springsecuritypractice.security.PersonDetails;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(Model model){

        model.addAttribute("test","test");
        return "auth/hello2";
    }
    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)auth.getPrincipal();
        System.out.println(personDetails.getPerson());
        return personDetails.getPerson().toString();
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
