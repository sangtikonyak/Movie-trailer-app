package com.capstone.UserAuthenticationService.security;

import com.capstone.UserAuthenticationService.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);
}
