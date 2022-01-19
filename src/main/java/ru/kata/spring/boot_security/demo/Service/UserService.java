package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUser(/*int count*/);
    public void save (User user);
    public User show (Long id);
    public void update(Long id, User updareUser);
    public void delete(Long id);
}
