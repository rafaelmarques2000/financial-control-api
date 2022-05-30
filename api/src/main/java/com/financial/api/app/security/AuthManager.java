package com.financial.api.app.security;

import com.financial.api.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Component
public class AuthManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtUtils tokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String userId;

        try {
            userId = tokenProvider.getUserIdFromToken(authToken);
        } catch (Exception e) {
            userId = null;
        }

        if (userId != null && !tokenProvider.isExpired(authToken)) {
                List<SimpleGrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, userId,roles);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}