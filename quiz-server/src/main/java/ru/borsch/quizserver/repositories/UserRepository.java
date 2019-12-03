package ru.borsch.quizserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borsch.quizserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
