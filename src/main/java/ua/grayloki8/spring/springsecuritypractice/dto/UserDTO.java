package ua.grayloki8.spring.springsecuritypractice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotEmpty
    @Size(min = 2,max = 100,message = "Недостатньо символів, кількість повинна бути в межах від 2 до 100 символів")
    private String userName;
    @Min(value = 1900,message ="Рік народження повинний бути більше ніж 1900 рік")
    private int yearOfBirth;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
