package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Slf4j
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        log.info("Listando todos os produtos");
        return produtoService.listaTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        log.info("Buscando produto com ID: {}", id);
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            log.info("Produto encontrado: {}", produto);
            return ResponseEntity.ok(produto);
        } else {
            log.warn("Produto com ID {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        log.info("Atualizando produto com ID: {}", id);
        produto.setId(id);
        Produto atualizado = produtoService.atualizar(produto);
        if (atualizado != null) {
            log.info("Produto atualizado: {}", atualizado);
            return ResponseEntity.ok(atualizado);
        } else {
            log.warn("Produto com ID {} não encontrado para atualização", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("Deletando produto com ID: {}", id);
        boolean removido = produtoService.deletar(id);
        if (removido) {
            log.info("Produto com ID {} deletado com sucesso", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Produto com ID {} não encontrado para deletar", id);
            return ResponseEntity.notFound().build();
        }
    }

}
