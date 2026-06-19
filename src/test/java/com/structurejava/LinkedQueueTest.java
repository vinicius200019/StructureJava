package com.structurejava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.structurejava.queue.LinkedQueue;

/**
 * Suíte de testes da {@link LinkedQueue}.
 * Cobre os casos CT-QU do plano de testes e cenários adicionais de borda.
 */
class LinkedQueueTest {

    private LinkedQueue<Integer> fila;

    @BeforeEach
    void preparar() {
        fila = new LinkedQueue<>();
    }

    @Test
    @DisplayName("CT-QU-01: enqueue 5 e dequeue 5 -> ordem FIFO")
    void ordemFifo() {
        for (int i = 1; i <= 5; i++) {
            fila.enqueue(i);
        }
        assertEquals(5, fila.size());
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, fila.dequeue()); // sai na mesma ordem que entrou
        }
        assertTrue(fila.isEmpty());
    }

    @Test
    @DisplayName("CT-QU-02: dequeue em fila vazia retorna null")
    void dequeueVazia() {
        assertNull(fila.dequeue());
        assertNull(fila.peek());
        assertTrue(fila.isEmpty());
        assertEquals(0, fila.size());
    }

    @Test
    @DisplayName("Extra: peek mostra o início sem remover")
    void peekNaoRemove() {
        fila.enqueue(10);
        fila.enqueue(20);
        assertEquals(10, fila.peek());
        assertEquals(10, fila.peek());
        assertEquals(2, fila.size());
    }

    @Test
    @DisplayName("Extra: intercalar enqueue/dequeue mantém o tail coerente")
    void intercalado() {
        fila.enqueue(1);
        fila.enqueue(2);
        assertEquals(1, fila.dequeue());
        fila.enqueue(3);
        assertEquals(2, fila.dequeue());
        assertEquals(3, fila.dequeue());
        assertNull(fila.dequeue());
        // volta a usar após esvaziar
        fila.enqueue(9);
        assertEquals(9, fila.peek());
    }

    @Test
    @DisplayName("Extra: clear esvazia a fila")
    void clear() {
        fila.enqueue(1);
        fila.enqueue(2);
        fila.clear();
        assertTrue(fila.isEmpty());
        assertNull(fila.dequeue());
    }
}