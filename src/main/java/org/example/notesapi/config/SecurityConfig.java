package org.example.notesapi.config;

import org.example.notesapi.security.CustomUserDetailsService;
import org.example.notesapi.security.JwtAuthenticationFilter;
import org.example.notesapi.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService, CustomUserDetailsService customUserDetailsService) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS
                        ))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/*"
                        ).permitAll()
                         .requestMatchers(
                                 "/swagger-ui/**",
                                 "/swagger-ui.html",
                                 "/v3/api-docs/**"
                         ).permitAll()
                         .anyRequest().authenticated());
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtService, customUserDetailsService),
                UsernamePasswordAuthenticationFilter.class
        )
                .exceptionHandling(errors -> errors
                        .authenticationEntryPoint((req, resp, e) -> {
                            resp.setContentType("application/json");
                            resp.setStatus(401);
                            resp.getWriter().write(
                                    "{\"status\":401,\"message\":\"Authentication required\"}"
                            );
                        }));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }


}
