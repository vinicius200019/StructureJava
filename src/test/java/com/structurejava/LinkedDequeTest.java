package com.structurejava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.structurejava.deque.LinkedDeque;

/**
 * Suíte de testes da {@link LinkedDeque}.
 * Cobre os casos CT-DQ do plano de testes e cenários adicionais de borda.
 */
class LinkedDequeTest {

    private LinkedDeque<Integer> deque;

    @BeforeEach
    void preparar() {
        deque = new LinkedDeque<>();
    }

    @Test
    @DisplayName("CT-DQ-01: uso como pilha (addFirst + removeFirst) -> LIFO")
    void usoComoPilha() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(3, deque.removeFirst());
        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("CT-DQ-02: uso como fila (addLast + removeFirst) -> FIFO")
    void usoComoFila() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(1, deque.removeFirst());
        assertEquals(2, deque.removeFirst());
        assertEquals(3, deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("CT-DQ-03: inserções intercaladas -> peekFirst e peekLast corretos")
    void intercalado() {
        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(1);
        deque.addLast(4); // sequência lógica: [1,2,3,4]
        assertEquals(1, deque.peekFirst());
        assertEquals(4, deque.peekLast());
        assertEquals(4, deque.size());
        // remoções pelas duas pontas
        assertEquals(1, deque.removeFirst());
        assertEquals(4, deque.removeLast());
        assertEquals(2, deque.peekFirst());
        assertEquals(3, deque.peekLast());
    }

    @Test
    @DisplayName("CT-DQ-04: removeFirst e removeLast em deque vazio -> null")
    void removeVazio() {
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Extra: removeLast reduz a cauda corretamente (O(1) via prev)")
    void removeLastSequencial() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(3, deque.removeLast());
        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.peekLast());
        assertEquals(1, deque.peekFirst());
        assertEquals(1, deque.size());
    }

    @Test
    @DisplayName("Extra: clear esvazia o deque")
    void clear() {
        deque.addFirst(1);
        deque.addLast(2);
        deque.clear();
        assertTrue(deque.isEmpty());
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }

    @Test
    @DisplayName("Extra: um único elemento removido por ambas as pontas")
    void umElemento() {
        deque.addFirst(7);
        assertEquals(7, deque.removeLast()); // remove o único pela cauda
        assertTrue(deque.isEmpty());
        deque.addLast(8);
        assertEquals(8, deque.removeFirst()); // remove o único pela frente
        assertTrue(deque.isEmpty());
    }
}