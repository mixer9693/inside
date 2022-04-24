package ru.inside.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentMatchers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.inside.demo.entity.User;
import ru.inside.demo.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private String username = "usernameTest";

    @BeforeEach
    void init(){
        userRepository = mock(UserRepository.class);
        when(userRepository.findByName(username)).thenReturn(Optional.of(getUser()));
        when(userRepository.findByName(AdditionalMatchers.not(ArgumentMatchers.eq(username)))).thenReturn(Optional.empty());

        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void loadUserByUsername() {
        org.springframework.security.core.userdetails.UserDetails udUser
                = userDetailsService.loadUserByUsername(username);
        verify(userRepository, times(1)).findByName(username);
        assertEquals(getUser().getName(), udUser.getUsername());
        assertEquals(getUser().getPassword(), udUser.getPassword());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("wrongName"));
    }

    User getUser(){
        User user = new User();
        user.setName(username);
        user.setPassword("pass");
        user.setId(1);
        return user;
    }
}