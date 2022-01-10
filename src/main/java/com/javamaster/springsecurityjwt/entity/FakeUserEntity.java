package com.javamaster.springsecurityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FakeUserEntity {

    private  String login;
    private  String password;
    private  FakeRole role;

}
