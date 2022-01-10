package com.javamaster.springsecurityjwt.service;

import com.javamaster.springsecurityjwt.entity.FakeRole;
import com.javamaster.springsecurityjwt.entity.FakeUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private List<FakeUserEntity> users;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = List.of(
                new FakeUserEntity("user",passwordEncoder.encode("password"), FakeRole.ROLE_USER)
                ,new FakeUserEntity("admin",passwordEncoder.encode("password"), FakeRole.ROLE_ADMIN));
    }



    public FakeUserEntity saveUser(FakeUserEntity userEntity) {
        users.add(userEntity);
        return userEntity;
    }

    public FakeUserEntity findByLogin(String login) {
        return users.stream().filter(user -> login.equalsIgnoreCase(user.getLogin())).findFirst().orElseThrow(()-> new IllegalArgumentException());
    }

    public FakeUserEntity findByLoginAndPassword(String login, String password) {
        FakeUserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
