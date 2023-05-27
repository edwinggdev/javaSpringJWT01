/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager AuthManager) throws Exception{
//        
//    return http
//        .csrf().disable()
//        .authorizeRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .httpBasic()
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .build();
//}
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        System.out.println("Filter Chain");
    return http
        .csrf().disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles()
//                .build());
//        return manager;
//    }
    
    
    
//    @Bean
//    AuthenticationManager authManager(HttpSecurity http) throws Exception{
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//                     
//    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        System.out.println("passwordEncoder");
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception{
        System.out.println("authmanager");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
                     
    }//
    
    
    public static void main(String[] args) {
        System.out.println("pass" + new BCryptPasswordEncoder().encode("12345"));
    }
}
