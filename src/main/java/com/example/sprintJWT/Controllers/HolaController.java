/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@RestController
//@RequestMapping("/*")
public class HolaController implements ErrorController {
    @RequestMapping("/")
    public String hola(){
        return "SpringBoot JWT01";
    }
    
    @RequestMapping("/uno")
    public String holaUno(){
        return "hola JWT UNO";
    }
}
