package com.hogar360.users.commons.configurations.beans;

import com.hogar360.users.users.domain.ports.in.AuthenticationServicePort;
import com.hogar360.users.users.domain.ports.in.UserServicePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.domain.usecases.AuthenticationUseCase;
import com.hogar360.users.users.domain.usecases.UserUseCase;
import com.hogar360.users.users.infrastructure.adapters.persistence.AuthenticationPersistenceAdapter;
import com.hogar360.users.users.infrastructure.adapters.persistence.UserPersistenceAdapter;
import com.hogar360.users.users.infrastructure.mappers.UserEntityMapper;
import com.hogar360.users.users.infrastructure.repositories.mysql.UserRepository;
import com.hogar360.users.users.infrastructure.adapters.encoders.PasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoderPort());
    }

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public PasswordEncoderPort passwordEncoderPort() {
        return new PasswordEncoderAdapter();
    }

    @Bean
    public AuthenticationServicePort authenticationServicePort() {
        return new AuthenticationUseCase(userPersistencePort(), passwordEncoderPort());
    }

}
