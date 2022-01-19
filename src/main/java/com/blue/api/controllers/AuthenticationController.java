package com.blue.api.controllers;

import com.blue.api.config.JWTTokenHelper;
import com.blue.api.controllers.dto.request.AuthenticationRequest;
import com.blue.api.controllers.dto.response.AuthenticationResponse;
import com.blue.api.controllers.dto.response.UserInfoResponse;
import com.blue.api.entities.User;
import com.blue.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenHelper jwtTokenHelper;
    private final UserService userService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager, JWTTokenHelper jwtTokenHelper, UserService userService
            ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String token = jwtTokenHelper.generateToken(user.getEmail());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);

        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(UsernamePasswordAuthenticationToken user) {
        User userData = (User) user.getPrincipal();

        User userResponse = userService.loadUserByUsername(userData.getEmail());

        UserInfoResponse userInfo = new UserInfoResponse();

        userInfo.setName(userResponse.getUsername());
        userInfo.setEmail(userResponse.getEmail());
        userInfo.setRoles(userResponse.getAuthorities().toArray());

        return ResponseEntity.ok(userInfo);
    }
}
