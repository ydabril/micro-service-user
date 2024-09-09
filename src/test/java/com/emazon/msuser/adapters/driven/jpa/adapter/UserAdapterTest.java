package com.emazon.msuser.adapters.driven.jpa.adapter;

import com.emazon.msuser.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.emazon.msuser.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.msuser.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.msuser.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.msuser.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserAdapterTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password", null);

        userEntity = new UserEntity();
    }

    @Test
    void saveArticleShouldSaveArticle() {
        when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);

        userAdapter.createAuxUser(user);

        verify(userRepository).save(userEntity);
    }

    @Test
    void testFindUserByEmail() {
        String email = "email@gmail.com";
        userEntity.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUserOptional(Optional.of(userEntity)))
                .thenReturn(Optional.of(new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@gmail.com", "password", null)));

        Optional<User> result = userAdapter.getUserByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void testFindUserByDocument() {
        String documentNumber = "0000000000";
        userEntity.setEmail(documentNumber);

        when(userRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUserOptional(Optional.of(userEntity)))
                .thenReturn(Optional.of(new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@gmail.com", "password", null)));

        Optional<User> result = userAdapter.getUserByDocument(documentNumber);

        assertTrue(result.isPresent());
        assertEquals(documentNumber, result.get().getDocumentNumber());

        verify(userRepository).findByDocumentNumber(documentNumber);
    }
}
