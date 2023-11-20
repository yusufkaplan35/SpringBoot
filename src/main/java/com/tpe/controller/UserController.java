package com.tpe.controller;

import com.tpe.domain.User;
import com.tpe.dto.UserRequest;
import com.tpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    //Not: Register() ****************
    @PostMapping // http://localhost:8080/register
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest){

        userService.saveUser(userRequest);

        String myResponse = "User is registered successfully";
        return new ResponseEntity<>(myResponse, HttpStatus.CREATED);
    }



}
