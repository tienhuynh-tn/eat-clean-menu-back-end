package com.happy3friends.eatcleanmenubackend.config;

import com.happy3friends.eatcleanmenubackend.security.CustomUserDetailsService;
import com.happy3friends.eatcleanmenubackend.security.RestAuthenticationEntryPoint;
import com.happy3friends.eatcleanmenubackend.security.TokenAuthenticationFilter;
import com.happy3friends.eatcleanmenubackend.security.oauth2.CustomOAuth2UserService;
import com.happy3friends.eatcleanmenubackend.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.happy3friends.eatcleanmenubackend.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.happy3friends.eatcleanmenubackend.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // The primary spring security annotation that is used to enable web security in a project
/**
 * @EnableGlobalMethodSecurity: This is used to enable method level security based on annotations.
 * - securedEnabled: It enables the @Secured annotation using which you can protect your controller/service methods
 *      ex: @Secured({"ROLE_USER", "ROLE_ADMIN"})
 * - jsr250Enabled: It enables the @RolesAllowed annotation
 *      ex: @RolesAllowed("ROLE_ADMIN")
 * - prePostEnabled: It enables more complex expression based access control syntax with @PreAuthorize and @PostAuthorize annotations
 *      ex: @PreAuthorize("isAnonymous()"), @PreAuthorize("hasRole('USER')")
 */
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
/**
 * WebSecurityConfigurerAdapter: This class implements Spring Security’s WebSecurityConfigurer interface.
 * It provides default security configurations and allows other classes to
 * extend it and customize the security configurations by overriding its methods.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    // CustomUserDetailsService: To authenticate a User or perform various role-based checks, Spring security needs to load users details somehow.

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /**
     * By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
     *       the authorization request. But, since our service is stateless, we can't save it in
     *       the session. We'll save the request in a Base64 encoded cookie instead.
     */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    /**
     * AuthenticationManagerBuilder and AuthenticationManager
     * - AuthenticationManagerBuilder is used to create an AuthenticationManager instance which is
     * the main Spring Security interface for authenticating a user.
     * - We’ve provided our customUserDetailsService and a passwordEncoder to build the AuthenticationManager.
     * - We’ll use the configured AuthenticationManager to authenticate a user in the login API.
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * The HttpSecurity configurations are used to configure security functionalities like csrf, sessionManagement, and add rules to protect resources based on various conditions.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .httpBasic()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**")
                        .permitAll()
                    .antMatchers("/auth/**", "/oauth2/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                        .and()
                    .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")
                        .and()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}