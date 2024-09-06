package com.emazon.msuser.domain.api;

import com.emazon.msuser.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
}
