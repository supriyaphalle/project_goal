package com.project.goal.controller;


import com.project.goal.dto.UserLoginDto;
import com.project.goal.dto.UserRegistrationDto;
import com.project.goal.response.Response;
import com.project.goal.service.implementation.UserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", exposedHeaders = "Token")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/user")
    public ResponseEntity<Response> addUser(@RequestBody UserRegistrationDto userRegistrationDto,
                                            BindingResult bindingResult,
                                            HttpServletRequest servletRequest) throws MessagingException {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101, "Empty Field"), HttpStatus.BAD_REQUEST);
        }
        Response responseMessage = userService.addUser(userRegistrationDto, servletRequest);
        return new ResponseEntity<>(responseMessage,
                HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserLoginDto userLoginDto,
                                              BindingResult bindingResult,
                                              HttpServletResponse httpHeaders) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101, "Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String token = userService.loginUser(userLoginDto);

        httpHeaders.setHeader("Token", token);
        return new ResponseEntity<Response>(new Response("User Login Successfully", 200, ""), HttpStatus.OK);
    }

}
