package ru.inside.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.inside.demo.dto.AuthorizationRequestDto;
import ru.inside.demo.dto.JwtTokenDto;
import ru.inside.demo.service.JwtService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtService jwtService;

    @PostMapping(path = "/api/authenticate")
    public JwtTokenDto authenticate(@RequestBody @Valid AuthorizationRequestDto jwtRequestDto){
        return jwtService.createToken(jwtRequestDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UsernameNotFoundException.class})
    public String handleUserNotFoundException(UsernameNotFoundException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public String handleBadCredentialsException(IllegalArgumentException e){
        return e.getMessage();
    }
}
