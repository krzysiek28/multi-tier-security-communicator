package com.studio_projektowe.communicator.controllers;

import com.studio_projektowe.communicator.entities.AppUser;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.security.SecurityUtils;
import com.studio_projektowe.communicator.services.AppUserService;
import org.jsoup.Jsoup;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.studio_projektowe.communicator.security.SecurityUtils.HEADER_STRING;
import static com.studio_projektowe.communicator.security.SecurityUtils.TOKEN_PREFIX;

/**
 * UserController is controller which is responsible for
 * handle all requests user related
 */
@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     *
     * @param appUserRepository
     * @param bCryptPasswordEncoder class responsible for encrytping passwords
     * @param appUserService
     */
    public AppUserController(AppUserRepository appUserRepository, AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * method which allows user to sign up
     * method checks if user with same data exsist
     * if not create new user
     *
     * @param user json with all user data
     * @param res http response
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody AppUser user, HttpServletResponse res) {
        if (appUserRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (appUserRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with provided email already exists!");
        }
        if (appUserRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Login already exists!");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(Jsoup.parse(user.getUsername()).text());
        user.setLogin(Jsoup.parse(user.getLogin()).text());
        user.setEmail(Jsoup.parse(user.getEmail()).text());
        user.setRole(user.getRole());
        user.setEnabled(user.getEnabled());
        user.setPersonalData(Jsoup.parse(user.getPersonalData()).text());
        appUserRepository.save(user);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
    }

    /**
     * method finds and returns user by username
     * @param username
     * @return
     */
    @RequestMapping("/{username}")
    public AppUser getUserByUsername(@PathVariable String username) {
        AppUser user = appUserService.getUserByUsername(username);
        return user;
    }

    /**
     * method finds and returns user by id
     *
     * @param userId
     * @return
     */
    @RequestMapping("/id/{userId}")
    public AppUser getUser(@PathVariable String userId) {
        return appUserService.getUser(Integer.parseInt(userId));
    }

    /**
     * method delete user by id
     *
     * @param userId
     */
    @RequestMapping("/delete/{userId}")
    public void deleteUser(@PathVariable String userId) {
        appUserService.deleteUser(Integer.parseInt(userId));
    }
}
