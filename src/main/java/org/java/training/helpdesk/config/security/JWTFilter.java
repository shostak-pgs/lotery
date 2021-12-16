package org.java.training.helpdesk.config.security;

import org.java.training.helpdesk.exception.FilterException;
import org.java.training.helpdesk.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final String ARTICLES_OVERVIEW_URL = "/api/articles/**";
    private static final String CHAPTERS_OVERVIEW_URL = "/api/articles/**/chapters";
    private static final String COMMENTS_OVERVIEW_URL = "/api/comments/**";
    private static final String AUTH = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String EMPTY = "";

    private final CustomUserDetailsService customUserDetailsService;
    private List<String> excludeUrlPatterns = Arrays.asList(ARTICLES_OVERVIEW_URL, CHAPTERS_OVERVIEW_URL, COMMENTS_OVERVIEW_URL);
    private final JwtUtils jwtUtils;

    public JWTFilter(CustomUserDetailsService customUserDetailsService, JwtUtils jwtUtils) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest,
                                    @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException {
        String username = EMPTY;
        String jwt = getToken(httpServletRequest);
        if(jwt != null) {
            username = jwtUtils.extractUsername(jwt);
        }

        if(!username.equals(EMPTY) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                newToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(newToken);
            }
        } try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (IOException ex){
            throw new FilterException("IOException in filter chain occurred");
        }
    }

    private String getToken(HttpServletRequest request) {
       String authHeader = request.getHeader(AUTH);
        if(authHeader != null && authHeader.startsWith(BEARER)) {
            return authHeader.replace(BEARER, EMPTY);
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        PathMatcher pathMatcher = new AntPathMatcher();
        return excludeUrlPatterns.stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()) && request.getMethod().equals("GET"));
    }
}