package org.java.training.helpdesk.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.help_desk_app.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String BASE_URL = "/";
    private static final String UI_LOGIN_URL = "/login";
    private static final String LOGOUT_URL = "/api/logout";
    private static final String LOGIN_URL = "/api/login";
    private static final String USERS_URL = "/api/users";
    private static final String ACTIVATE_URL = "/api/activate";
    private static final String ARTICLES_OVERVIEW_URL = "/api/articles/**";
    private static final String CHAPTERS_OVERVIEW_URL = "/api/articles/**/chapters";
    private static final String COMMENTS_OVERVIEW_URL = "/api/comments/**";
    private static final String JS_FORMAT = "/*.js";
    private static final String SVG_FORMAT = "/*.svg";
    private static final String JSON_FORMAT = "/*.json";
    private static final String ICO_FORMAT = "/*.ico";
    private static final String CSS_FORMAT = "/*.css";
    private static final String PNG_FORMAT = "/*.png";
    private static final String STATIC_FOLDER = "/static/**";

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;
    private final JWTFilter JWTFilter;

    public WebSecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint, PasswordEncoder encoder,
                             JWTFilter JWTFilter, CustomUserDetailsService customUserDetailsService) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.encoder = encoder;
        this.JWTFilter = JWTFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().antMatchers(BASE_URL, UI_LOGIN_URL, LOGIN_URL, USERS_URL, ACTIVATE_URL).permitAll()
            .antMatchers(HttpMethod.GET, ARTICLES_OVERVIEW_URL, COMMENTS_OVERVIEW_URL,
                    CHAPTERS_OVERVIEW_URL, PNG_FORMAT, SVG_FORMAT, STATIC_FOLDER, JS_FORMAT, JSON_FORMAT, ICO_FORMAT, CSS_FORMAT)
                .permitAll()
                .antMatchers(HttpMethod.POST, ACTIVATE_URL).permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL)).
                logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);})
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(JWTFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}