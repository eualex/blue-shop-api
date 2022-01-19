package com.blue.api.controllers.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {

    private String name;
    private String email;
    private Object roles;
}
