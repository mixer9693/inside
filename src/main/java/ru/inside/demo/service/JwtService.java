package ru.inside.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inside.demo.dto.AuthorizationRequestDto;
import ru.inside.demo.dto.JwtTokenDto;
import ru.inside.demo.entity.User;
import ru.inside.demo.repository.UserRepository;
import ru.inside.demo.security.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenDto createToken(AuthorizationRequestDto jwtRequestDto)
            throws UsernameNotFoundException, IllegalArgumentException {
        User user = userRepository.findByName(jwtRequestDto.getName())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with name %s not found", jwtRequestDto.getName())));

        if (!isPasswordValid(jwtRequestDto.getPassword(), user)){
            throw new IllegalArgumentException("Incorrect password");
        }

        return jwtTokenUtil.creteToken(user);
    }

    public boolean isPasswordValid(String password, User user){
        return passwordEncoder.matches(password, user.getPassword());
    }

}
