/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;

import com.example.sprintJWT.Model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        System.out.println("loadBy...");
//        Usuario usuario = new Usuario();
//        usuario.setEmail("correo@correo.com");
//        usuario.setPassword("hihihi");
//        usuario.setNombre("Edwin");
//        //usuario.setPassword("12345");
//        usuario.setPassword("pass$2a$10$IweMYf.iA187BGVEVkPQw.M9OeANsM3LMD1PxecUIv5mYm0idb9Ui");
//        return new UserDetailsImpl(usuario);
//    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("loadBy...");
        Usuario usuario = new Usuario();
        usuario.setEmail("correo@correo.com");
        usuario.setNombre("Edwin");
        System.out.println("here");
        usuario.setPassword("12345");
        usuario.setPassword("$2a$10$IweMYf.iA187BGVEVkPQw.M9OeANsM3LMD1PxecUIv5mYm0idb9Ui");
        System.out.println("" + email);
        return new UserDetailsImpl(usuario);
    }
}
