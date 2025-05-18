package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    private static final String HELLO_WORD = "[Hello word!]" ;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Endpoint /hello acessado com sucesso!");
        log.info(HELLO_WORD + "Seja bem vindo");
        return "Olá! Bem-vindo à API Spring Boot.";
    }
}
