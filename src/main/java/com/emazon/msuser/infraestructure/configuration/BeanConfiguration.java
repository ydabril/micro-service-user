package com.emazon.msuser.infraestructure.configuration;

import com.emazon.msuser.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.emazon.msuser.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.msuser.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.msuser.domain.api.IUserServicePort;
import com.emazon.msuser.domain.api.use_case.UserUseCase;
import com.emazon.msuser.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }
    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserAdapter(passwordEncoder, userRepository, userEntityMapper);
    }
}
