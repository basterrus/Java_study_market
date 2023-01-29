package ru.baster.spring.sample.shop.study.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.baster.spring.sample.shop.study.market.dto.JwtRequestDto;
import ru.baster.spring.sample.shop.study.market.dto.JwtResponseDto;
import ru.baster.spring.sample.shop.study.market.dto.RegistrationUserDto;
import ru.baster.spring.sample.shop.study.market.exception.AppError;
import ru.baster.spring.sample.shop.study.market.model.User;
import ru.baster.spring.sample.shop.study.market.service.UserService;
import ru.baster.spring.sample.shop.study.market.util.JwtTokenUtil;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping("/sign_in")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto authRequest) {
        log.info("authRequest username: {}, password: {}", authRequest.getUsername(), authRequest.getPassword());
        try {

            Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponseDto(jwt));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),
                    HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/sign_up")
    public ResponseEntity<?> sign_up(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "The password doesn't match"), HttpStatus.BAD_REQUEST);
        }

        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "The user already exists"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(registrationUserDto.getEmail());
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        userService.createUser(user);

        UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}