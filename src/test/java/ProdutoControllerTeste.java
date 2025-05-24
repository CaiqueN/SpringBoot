package com.example.demo.controller;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void deveSalvarProdutoComSucesso() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto criado");
        produto.setPreco(100.0);

        when(produtoService.salvar(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/produtos/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Produto Teste\", \"preco\": 100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Produto criado"))
                .andExpect(jsonPath("$.preco").value(100.0));
    }

    @Test
    void deveAtualizarProdutoComSucesso() throws Exception {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1L);
        produtoAtualizado.setNome("Produto atualizado");
        produtoAtualizado.setPreco(20.0);

        when(produtoService.atualizar(any(Produto.class))).thenReturn(produtoAtualizado);

        mockMvc.perform(put("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Produto atualizado\", \"preco\": 20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Produto atualizado"))
                .andExpect(jsonPath("$.preco").value(20.0));
    }

    @Test
    void deveBuscarProdutoComSucesso() throws Exception {
        Produto produtoBuscado = new Produto();
        produtoBuscado.setId(1L);
        produtoBuscado.setNome("Produto encontrado");
        produtoBuscado.setPreco(3000.0);

        when(produtoService.buscarPorId(1L)).thenReturn(produtoBuscado);

        mockMvc.perform(get("/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Produto encontrado"))
                .andExpect(jsonPath("$.preco").value(3000.0));
    }

    @Test
    void deveDeletarProdutoComSucesso() throws Exception {
        Long id = 1L;

        when(produtoService.deletar(id)).thenReturn(true);

        mockMvc.perform(delete("/produtos/{id}", id))
                .andExpect(status().isNoContent());
    }
}