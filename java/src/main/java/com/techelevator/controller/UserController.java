package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    // handle get request for all user profile information
    @RequestMapping(path = "/user/{userId}/profile", method = RequestMethod.GET)
    public UserDto profile(@PathVariable int userId){
        UserDto user;
        try {
            user = userDao.getUserDtoById(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }
        return user;
    }

    // handle PUT request for updating user profile information
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/user/{userId}/profile", method = RequestMethod.PUT)
    public UserDto updateProfile(@PathVariable int userId, @RequestBody UserDto updatedUser){
        UserDto result;
        try {
            result = userDao.updateUserDto(updatedUser);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect." + e.getMessage());
        }
        return result;
    }

}
