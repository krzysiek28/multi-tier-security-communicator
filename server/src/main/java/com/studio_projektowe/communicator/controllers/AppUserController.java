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

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserController(AppUserRepository appUserRepository, AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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

    @RequestMapping("/{username}")
    public AppUser getUserByUsername(@PathVariable String username) {
        AppUser user = appUserService.getUserByUsername(username);
        return user;
    }

    @RequestMapping("/id/{userId}")
    public AppUser getUser(@PathVariable String userId) {
        return appUserService.getUser(Integer.parseInt(userId));
    }
}
