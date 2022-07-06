package ua.grayloki8.spring.springsecuritypractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.grayloki8.spring.springsecuritypractice.models.User;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String name);
}
