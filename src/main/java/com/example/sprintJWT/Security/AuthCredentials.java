/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;
import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
