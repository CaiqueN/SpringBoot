package com.example.demo.service;

import com.example.demo.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    private final Map<Long, Produto> banco = new HashMap<>();
    private Long contadorId = 1L;

    public List<Produto> listaTodos() {
        return new ArrayList<>(banco.values());
    }

    public Produto salvar(Produto produto) {
        produto.setId(contadorId);
        banco.put(contadorId, produto);
        contadorId++;
        return produto;
    }

    //abaixo erro no getId e no return produto
    public Produto atualizar(Produto produto) {
        if (produto.getId() != null && banco.containsKey(produto.getId())) {
            banco.put(produto.getId(), produto);
            return produto;
        }
        return null;
    }

    public boolean deletar(Long id) {
        return banco.remove(id) != null;
    }

    public Produto buscarPorId(Long id) {
        return banco.get(id);
    }
}
