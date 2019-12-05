package ru.borsch.quizserver.service;

import ru.borsch.quizserver.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUserName(String username);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    boolean isUserExist(User user);

    User getCurrentUser();
}
