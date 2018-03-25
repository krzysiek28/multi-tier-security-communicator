package com.studio_projektowe.communicator.controllers;

import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.services.AppUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController:
 * handle all requests user related
 */

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    public AppUserController(AppUserRepository appUserRepository, AppUserService appUserService){
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }
}
