package com.emazon.msuser.domain.spi;

import com.emazon.msuser.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {
    void createUser(User user);
    String getPasswordEncrypt(String password);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByDocument(String document);
}
