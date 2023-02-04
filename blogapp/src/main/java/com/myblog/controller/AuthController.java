package com.myblog.controller;

import com.myblog.payload.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;
    //this bean will not be created automatically.
    //first, we need to go our MyConfig class and override the method which is called authenticationManager();
    //Then spring boot will be able to create a bean of AuthenticationManager.

    @PostMapping("/sigin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword())
        );//If the details are correct then it will execute further code.Rather than, it will stop here only.

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //after entering correct username and password then it will set the authentication token.


        return new ResponseEntity<>("User signed-in successfully!!!", HttpStatus.OK);

    }


}
