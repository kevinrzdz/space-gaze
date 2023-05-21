package com.space_gaze.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http.cors().and().csrf().disable().authorizeHttpRequests().antMatchers(HttpMethod.POST, "/register").permitAll().antMatchers(HttpMethod.POST, "/**/img/**").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(jwtAuthenticationFilter).addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    AuthenticationManager AuthManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and().build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
