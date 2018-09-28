package com.neron.sendbx.util;


import lombok.Data;

@Data
public class User {
    String email;
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
