/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String bearerToken = request.getHeader("Authorization");
        System.out.println("doFilterInternal");
        if(bearerToken != null && bearerToken.startsWith("Bearer ") ){
            String token = bearerToken.replace("Bearer " , "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            System.out.println("token valido: " + token);
        }
        System.out.println("passed");
        filterChain.doFilter(request, response);
        System.out.println("ha filtrado");
            
    }
}
