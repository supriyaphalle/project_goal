package com.project.goal.service.implementation;

import com.project.goal.dto.UserLoginDto;
import com.project.goal.dto.UserRegistrationDto;
import com.project.goal.exception.UserException;
import com.project.goal.model.User;
import com.project.goal.repository.IUserRepository;
import com.project.goal.response.Response;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtToken jwtToken;


//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Response addUser(UserRegistrationDto userRegistrationDto, HttpServletRequest servletRequest) throws MessagingException, UserException {

        Optional<User> userdata = userRepository.findUserByEmail(userRegistrationDto.email);
        if (!userdata.isPresent()) {
            String password = userRegistrationDto.password;
            userRegistrationDto.password = password;
            User user = new User(userRegistrationDto);
            User savedUser = userRepository.save(user);
            return new Response("User Registered Successfully", 200, "User Registered Successfully");
        }

        throw new UserException("User Exists", UserException.ExceptionType.USER_ALREADY_EXIST);
    }


    public String loginUser(UserLoginDto userLoginDto) throws UserException {

        Optional<User> userData = userRepository.findUserByEmail(userLoginDto.email);
        if (userData.isPresent()) {

            boolean passwordCheck = userLoginDto.password.equals(userData.get().password);

            if (passwordCheck) {
                return jwtToken.generateToken(userData.get().id);
            }
            throw new UserException("Incorrect password", UserException.ExceptionType.INVALID_PASSWORD);
        }
        throw new UserException("Invalid Email id", UserException.ExceptionType.INVALID_EMAIL_ID);
    }

}
