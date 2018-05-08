package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.entities.AppUser;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;

    public AppUser getUser(Integer userId) {
        if (!appUserRepository.exists(userId)) {
            throw new IllegalArgumentException("Uzytkownik nie istnieje");
        }

        return appUserRepository.findOne(userId);
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public void updateUser(Integer userId, AppUser appUser) {
        if (!appUserRepository.exists(userId)) {
            throw new IllegalArgumentException("Uzytkownik nie istnieje!");
        }

        AppUser dbUser = appUserRepository.findOne(userId);
        dbUser.setUsername(appUser.getUsername());
        dbUser.setLogin(appUser.getLogin());
        dbUser.setPassword(appUser.getPassword());
        dbUser.setEmail(appUser.getEmail());
        dbUser.setRole(appUser.getRole());
        dbUser.setPersonalData(appUser.getPersonalData());
        dbUser.setEnabled(appUser.getEnabled());
        appUserRepository.save(dbUser);
    }

    public void deleteUser(Integer userId) {
        if (!appUserRepository.exists(userId)) {
            throw new IllegalArgumentException("Uzytkownik nie istnieje!");
        }

        appUserRepository.delete(userId);
    }

}
