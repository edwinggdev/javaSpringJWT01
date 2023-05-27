/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
            HttpServletResponse response) 
            throws AuthenticationException{
        System.out.println("attemptAuthentication...");
        AuthCredentials authCredentials = new AuthCredentials();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);  
        }catch(IOException e){
         
        } catch (java.io.IOException ex) {
            Logger.getLogger(JWTAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain claim,
            Authentication authResult) throws IOException, ServletException, java.io.IOException{
        System.out.println("successfulAuthentication...");    
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        System.out.println("here");
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
         System.out.println("token");   
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        
        super.successfulAuthentication(request, response, claim, authResult);
    }
}
