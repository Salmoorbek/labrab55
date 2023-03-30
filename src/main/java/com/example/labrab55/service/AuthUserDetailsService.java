package com.example.labrab55.service;

import com.example.labrab55.dao.UserDao;
import com.example.labrab55.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optUser = Optional.ofNullable(userDao.findByEmail(email));
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found");
        }
        return optUser.get();
    }
}