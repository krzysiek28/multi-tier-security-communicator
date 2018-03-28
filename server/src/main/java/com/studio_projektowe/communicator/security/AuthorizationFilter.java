package com.studio_projektowe.communicator.security;


import com.studio_projektowe.communicator.entities.AppUser;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import static com.studio_projektowe.communicator.security.SecurityUtils.SECRET;
import static com.studio_projektowe.communicator.security.SecurityUtils.TOKEN_PREFIX;

@Service
public class AuthorizationFilter {

    private final AppUserRepository appUserRepository;

    public AuthorizationFilter(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    private int getUserIdFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        AppUser user = appUserRepository.findByUsername(username);

        return user.getId();
    }

    public void isAuthorizedTo(String token, String resourceId, ResourceType resource) throws UnauthorizedException {
        int userId = getUserIdFromToken(token);
        switch(resource) {
            case USER:
                userAuth(userId, Integer.parseInt(resourceId));
                break;
            default:
                throw new IllegalArgumentException("Blad podczas autoryzacji!");
        }
    }

    private void userAuth(int userId, int requestUser) throws UnauthorizedException {
        AppUser one = appUserRepository.findOne(requestUser);
        if ((one == null) || (!one.getId().equals(userId)))
            throw new UnauthorizedException("Brak pozwolenia!");
    }
}
