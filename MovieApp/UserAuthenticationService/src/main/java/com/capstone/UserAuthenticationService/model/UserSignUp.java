package com.capstone.UserAuthenticationService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUp {
    private String email, userName, password, mobileNo;
}
