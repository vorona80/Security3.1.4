package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.List;



@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }
    @Override
    public void save (User user) {
//        String encoderPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encoderPassword);
//        userRepository.save(user);
    }
    @Override
    public User show (Long id) {
        return userRepository.getById(id);
    } //заменен метод getReferenceById(id)
    @Override
    public void update(Long id, User updareUser) {
        userRepository.save(updareUser);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not Found");
        }
//    return user;

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                user.getRoles());
    }

}
