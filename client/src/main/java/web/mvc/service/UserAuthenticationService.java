package web.mvc.service;

import io.jsonwebtoken.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserAuthenticationService {
    private String rawToken = null;
    private String username;
    private Integer userId;

    public void setToken(String token) {
        System.out.println("IDZE SET TOKEN");
        this.rawToken = token.replaceFirst("Bearer ", "");

        final String[] user = new String[1];

        SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                user[0] = claims.getSubject();
                return null;
            }
        };

        try {
            Jwts.parser()
                    .setSigningKeyResolver(signingKeyResolver)
                    .parseClaimsJws(rawToken)
                    .getBody();
        } catch (Exception e) {}
        this.username = user[0];
    }

    public String getUsername() {
        System.out.println("IDZIE GET USERNAME Z NEJMEM "+username);
        return this.username;
    }

    public Boolean isLoggedIn() {
        return rawToken != null;
    }

    public void logout() {
        this.username = null;
        this.rawToken = null;
    }

    public String getRawToken() {
        return this.rawToken;
    }

    public void setUserId(Integer userId){ this.userId=userId; }

    public Integer getUserId(){ return this.userId; }
}
