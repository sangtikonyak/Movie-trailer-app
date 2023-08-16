package com.capstone.UserAuthenticationService.proxy;


import com.capstone.UserAuthenticationService.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value ="favourite-container",url = "favourite-container:8085")
public interface UserMovieProxy {

    @PostMapping("/api/v2/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user);
}
