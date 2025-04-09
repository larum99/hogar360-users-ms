package com.hogar360.users.users.domain.ports.out;

public interface PasswordEncoderPort {
    String encode(String password);
}
