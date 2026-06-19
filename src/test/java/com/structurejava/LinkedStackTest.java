package com.structurejava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.structurejava.stack.LinkedStack;

/**
 * Suíte de testes da {@link LinkedStack}.
 * Cobre os casos CT-ST do plano de testes e cenários adicionais de borda.
 */
class LinkedStackTest {

    private LinkedStack<Integer> pilha;

    @BeforeEach
    void preparar() {
        pilha = new LinkedStack<>();
    }

    @Test
    @DisplayName("CT-ST-01: push 5 e pop 5 -> ordem LIFO")
    void ordemLifo() {
        for (int i = 1; i <= 5; i++) {
            pilha.push(i);
        }
        assertEquals(5, pilha.size());
        for (int i = 5; i >= 1; i--) {
            assertEquals(i, pilha.pop()); // sai na ordem inversa
        }
        assertTrue(pilha.isEmpty());
    }

    @Test
    @DisplayName("CT-ST-02: pop em pilha vazia retorna null (sem exceção)")
    void popVazia() {
        assertNull(pilha.pop());
        assertNull(pilha.peek());
        assertTrue(pilha.isEmpty());
        assertEquals(0, pilha.size());
    }

    @Test
    @DisplayName("Extra: peek não remove o topo")
    void peekNaoRemove() {
        pilha.push(10);
        pilha.push(20);
        assertEquals(20, pilha.peek());
        assertEquals(20, pilha.peek()); // continua lá
        assertEquals(2, pilha.size());
    }

    @Test
    @DisplayName("Extra: clear esvazia a pilha")
    void clear() {
        pilha.push(1);
        pilha.push(2);
        pilha.clear();
        assertTrue(pilha.isEmpty());
        assertNull(pilha.pop());
    }
}