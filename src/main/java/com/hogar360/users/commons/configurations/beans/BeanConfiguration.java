package com.hogar360.users.commons.configurations.beans;

import com.hogar360.users.users.domain.ports.in.UserServicePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.domain.usecases.UserUseCase;
import com.hogar360.users.users.infraestructure.adapters.persistence.UserPersistenceAdapter;
import com.hogar360.users.users.infraestructure.mappers.UserEntityMapper;
import com.hogar360.users.users.infraestructure.repositories.mysql.UserRepository;
import com.hogar360.users.users.infraestructure.adapters.encoders.PasswordEncoderAdapter;
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
}
