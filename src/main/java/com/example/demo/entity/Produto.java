package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

public class Produto {

    @Setter
    @Getter
    private Long id;
    private String nome;
    private Double preco;

    public Produto(Long id, String nome, Double preco) {

        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {

    }
}
