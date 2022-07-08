package ua.grayloki8.spring.springsecuritypractice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationDTO {
    @NotEmpty
    @Size(min = 2,max = 100,message = "Недостатньо символів, кількість повинна бути в межах від 2 до 100 символів")

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
