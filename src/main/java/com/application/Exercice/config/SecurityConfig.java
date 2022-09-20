package com.application.Exercice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.Exercice.jwt.JwtAuthenticationEntryPoint;
import com.application.Exercice.jwt.JwtFilter;

    @Configuration
    @EnableWebSecurity
    //@EnableWebSecurity allows Spring to find and automatically apply the class to the global Web Security
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig  {
    
        @Autowired
        private JwtAuthenticationEntryPoint authenticationEntryPoint;
        @Autowired
        private JwtFilter filter;
        @Autowired
        private UserDetailsService userDetailsService;
    
        @Bean
        public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder,
                UserDetailsService userDetailService)
                throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder)
                    .and()
                    .build();
        }
    
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests().antMatchers("/login", "/swagger-ui/*", "/v2/api-docs" ).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }

