package com.requestdesign.testingservice.service.auth;

import com.requestdesign.testingservice.dto.auth.UserDto;
import com.requestdesign.testingservice.entity.auth.User;
import com.requestdesign.testingservice.exceptions.auth.LoginIsUserException;
import com.requestdesign.testingservice.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        return user;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public void addUser(UserDto userDto) throws LoginIsUserException {
        UserDetails checkingUser = loadUserByUsername(userDto.getLogin());
        if(checkingUser != null) {
            throw new LoginIsUserException("Логин занят");
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }
}
