package com.LoginLogoutAPI.userauthapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Email
@NotBlank
public class SendCodeRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // Constructors (optional but useful)
    public SendCodeRequest() {}

    public SendCodeRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

