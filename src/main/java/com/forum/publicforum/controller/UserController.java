package com.forum.publicforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forum.publicforum.req.LoginRequest;
import com.forum.publicforum.req.SignupRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.service.UserService;


@Controller
@RequestMapping(value = "/forum")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> addNewUser(@Valid @RequestBody SignupRequest signupRequest) throws Exception {
        return new ResponseEntity<BaseResponse>(userService.create(signupRequest), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<BaseResponse>(userService.login(loginRequest), HttpStatus.FOUND);
    }
}
