package com.example.labrab55.service;

import com.example.labrab55.dao.UserDao;
import com.example.labrab55.dto.UserDto;
import com.example.labrab55.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    public UserService(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }
    public UserDto registerUser(String name, String email, String password) {
//        if (userDao.findByEmail(email) != null) {
//            throw new RuntimeException("Пользователь с таким адресом почты уже зарегистрирован");
//        }
        for (int i = 0; i < userDao.getAllUsers().size(); i++) {
            if (Objects.equals(userDao.getAllUsers().get(i).getEmail(), email)) {
                throw new RuntimeException("Пользователь с таким адресом почты уже зарегистрирован");
            }
        }
        var usr = User.builder()
                .email(email)
                .name(name)
                .enabled(true)
                .password(encoder.encode(password))
                .role("USER")
                .build();

        userDao.createNewUser(usr);
        return UserDto.from(usr);
    }
}
