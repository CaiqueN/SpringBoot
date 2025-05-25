package com.example.demo.service;

import com.example.demo.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoService = new ProdutoService();
    }

    @Test
    void deveSalvarComSucesso() {
        Produto produto = new Produto(null, "PS5", 3500.00);
        Produto salvo = produtoService.salvar(produto);

        assertNotNull(salvo.getId());
        assertEquals("PS5", salvo.getNome());
        assertEquals(3500.00, salvo.getPreco());
    }

    @Test
    void deveListarTodosComSucesso() {
        produtoService.salvar(new Produto(null, "Xbox One", 2500.00));
        produtoService.salvar(new Produto(null, "Dualsense", 400.00));

        List<Produto> resultado = produtoService.listaTodos();

        assertEquals(2, resultado.size());
        assertEquals("Xbox One", resultado.get(0).getNome());
        assertEquals("Dualsense", resultado.get(1).getNome());
    }

    @Test
    void deveBuscarComSucesso() {
        Produto salvo = produtoService.salvar(new Produto(null, "Pc Gamer", 4500.00));

        Produto buscar = produtoService.buscar(salvo.getId());

        assertNotNull(buscar);
        assertEquals(salvo.getId(), buscar.getId());
        assertEquals("Pc Gamer", buscar.getNome());
        assertEquals(4500.00, buscar.getPreco());
    }

    @Test
    void deveAtualizarComSucesso() {
        Produto salvo = produtoService.salvar(new Produto(null, "Mouse", 99.90));
        salvo.setPreco(105.50);

        Produto atualizado = produtoService.atualizar(salvo);

        assertNotNull(atualizado);
        assertEquals(salvo.getId(), atualizado.getId());
        assertEquals("Mouse", atualizado.getNome());
        assertEquals(105.50, atualizado.getPreco());
    }

    @Test
    void naoDeveAtualizarComSucesso() {
        Produto naoExistente = new Produto(99L, "Teclado", 150.00);

        Produto resultado = produtoService.atualizar(naoExistente);

        assertNull(resultado);
    }

    @Test
    void deveDeletarComSucesso() {
        Produto salvo = produtoService.salvar(new Produto(null, "Headset", 200.00));

        boolean deletado = produtoService.deletar(salvo.getId());

        assertTrue(deletado);
        assertNull(produtoService.buscar(salvo.getId()));
    }

    @Test
    void naoDeveDeletarComSucesso() {
        boolean resultado = produtoService.deletar(999L);
        assertFalse(resultado);
    }
}
