package com.emazon.msuser.adapters.driven.jpa.mysql.adapter;

import com.emazon.msuser.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.msuser.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.msuser.domain.model.User;
import com.emazon.msuser.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class UserAdapter implements UserPersistencePort {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void createAuxUser(User user) {
        userRepository.save(userEntityMapper.toUserEntity(user));
    }

    public Optional<User> getUserByEmail(String email) {
        return userEntityMapper.toUserOptional(userRepository.findByEmail(email));
    }

    public Optional<User> getUserByDocument(String document) {
        return userEntityMapper.toUserOptional(userRepository.findByDocumentNumber(document));
    }

    @Override
    public String getPasswordEncrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
