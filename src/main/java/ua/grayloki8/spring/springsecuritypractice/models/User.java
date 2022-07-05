package ua.grayloki8.spring.springsecuritypractice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User2")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @Size(min = 2,max = 100,message = "Недостатньо символів, кількість повинна бути в межах від 2 до 100 символів")
    private String userName;
    @Min(value = 1900,message ="Рік народження повинний бути більше ніж 1900 рік")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name ="password" )
    private String password;

    public User() {
    }

    public User(String userName,int yearOfBirth, String password) {
        this.userName = userName;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
