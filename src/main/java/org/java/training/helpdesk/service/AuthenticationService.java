package org.java.training.helpdesk.service;

import org.java.training.helpdesk.config.security.CustomUserDetailsService;
import org.java.training.helpdesk.config.security.CustomUserPrincipal;
import org.java.training.helpdesk.dto.UserCredentialDto;
import org.java.training.helpdesk.entity.User;
import org.java.training.helpdesk.exception.AuthException;
import org.java.training.helpdesk.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthenticationService {
    private final JwtUtils jwtUtils;
    private final DaoAuthenticationProvider provider;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationService( DaoAuthenticationProvider provider,
                                 CustomUserDetailsService customUserDetailsService,
                                 JwtUtils jwtUtils) {
        this.customUserDetailsService = customUserDetailsService;
        this.provider = provider;
        this.jwtUtils = jwtUtils;
    }

    public User getAuthenticateUser() {
        return  ((CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUser();
    }

    public User authenticate(UserCredentialDto credentialDto) {
        try {
            provider.authenticate(new UsernamePasswordAuthenticationToken(credentialDto.getEmail(), credentialDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthException("Incorrect credentials");
        }
        return (customUserDetailsService.loadUserByUsername(credentialDto.getEmail())).getUser();
    }

    public String getToken(UserCredentialDto dto) {
        return jwtUtils.createToken(dto.getEmail());
    }

    public boolean logoutIfBlocked(Set<Long> idSet) {
        if (idSet.contains(getAuthenticateUser().getId())) {
            SecurityContextHolder.clearContext();
            return true;
        }
        return false;
    }
}