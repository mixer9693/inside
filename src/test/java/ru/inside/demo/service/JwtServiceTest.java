package ru.inside.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.inside.demo.dto.AuthorizationRequestDto;
import ru.inside.demo.dto.JwtTokenDto;
import ru.inside.demo.entity.User;
import ru.inside.demo.repository.UserRepository;
import ru.inside.demo.security.JwtTokenUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    String username = "Rafael";
    String password = "Scxc3fce3@f3";

    @BeforeEach
    void init()  {
        userRepository = mock(UserRepository.class);
        when(userRepository.findByName(username)).thenReturn(Optional.of(new User()));
        when(userRepository.findByName(AdditionalMatchers.not(eq(username)))).thenReturn(Optional.empty());

        passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.matches(eq(password), any())).thenReturn(true);
        when(passwordEncoder.matches(AdditionalMatchers.not(eq(password)), any())).thenReturn(false);

        jwtTokenUtil = mock(JwtTokenUtil.class);
        when(jwtTokenUtil.creteToken(any())).thenReturn(new JwtTokenDto("wjtToken"));

        jwtService = new JwtService(userRepository, passwordEncoder, jwtTokenUtil);
    }

    @Test
    void createToken() {
        AuthorizationRequestDto validRequest = getValidAuthorizationRequestDto();

        JwtTokenDto jwtTokenDto = jwtService.createToken(validRequest);

        verify(userRepository, times(1)).findByName(same(validRequest.getName()));
        verify(passwordEncoder, times(1)).matches(eq(validRequest.getPassword()), any());
        verify(jwtTokenUtil, times(1)).creteToken(any());
        assertNotNull(jwtTokenDto);

        AuthorizationRequestDto invalidNameReq = getInvalidNameAuthorizationRequestDto();
        assertThrows(UsernameNotFoundException.class, () -> jwtService.createToken(invalidNameReq));

        AuthorizationRequestDto invalidPassReq = getInvalidPasswordAuthorizationRequestDto();
        assertThrows(IllegalArgumentException.class, () -> jwtService.createToken(invalidPassReq));
    }

    @Test
    void isPasswordValid() {
        User user = new User();
        user.setPassword("encodedPassword");

        jwtService.isPasswordValid(password, user);
        verify(passwordEncoder, times(1)).matches(eq(password), eq(user.getPassword()));
    }


    AuthorizationRequestDto getValidAuthorizationRequestDto(){
        AuthorizationRequestDto dto = new AuthorizationRequestDto();
        dto.setName(username);
        dto.setPassword(password);
        return dto;
    }

    AuthorizationRequestDto getInvalidNameAuthorizationRequestDto(){
        AuthorizationRequestDto dto = new AuthorizationRequestDto();
        dto.setName("invalidName");
        dto.setPassword(password);
        return dto;
    }

    AuthorizationRequestDto getInvalidPasswordAuthorizationRequestDto(){
        AuthorizationRequestDto dto = new AuthorizationRequestDto();
        dto.setName(username);
        dto.setPassword("invalidPassword");
        return dto;
    }
}