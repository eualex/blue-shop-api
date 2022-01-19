package com.blue.api.controllers.dto.request;

import java.security.Principal;

public class UserPrincipalRequest implements Principal {
    private String email;

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
