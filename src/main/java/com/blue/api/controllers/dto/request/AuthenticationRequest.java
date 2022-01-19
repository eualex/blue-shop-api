package com.blue.api.controllers.dto.request;

import lombok.Getter;

@Getter
public class AuthenticationRequest {

    private String email;
    private String password;
}
