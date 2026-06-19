package com.structurejava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.structurejava.list.SinglyLinkedList;

/**
 * Suíte de testes da {@link SinglyLinkedList}.
 * Cobre os casos CT-SL do plano de testes e cenários adicionais de borda.
 */
class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> lista;

    @BeforeEach
    void preparar() {
        lista = new SinglyLinkedList<>();
    }

    @Test
    @DisplayName("CT-SL-01: add 3 elementos -> size 3 e get(0) é o primeiro")
    void addTresElementos() {
        lista.add(10);
        lista.add(20);
        lista.add(30);
        assertEquals(3, lista.size());
        assertEquals(10, lista.get(0));
        assertEquals(30, lista.get(2));
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("CT-SL-02: addFirst -> elemento vira get(0)")
    void addFirst() {
        lista.add(1);
        lista.addFirst(99);
        assertEquals(99, lista.get(0));
        assertEquals(1, lista.get(1));
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("CT-SL-03: addAt com índice inválido -> false e size inalterado")
    void addAtInvalido() {
        lista.add(1);
        assertFalse(lista.addAt(-1, 5));
        assertFalse(lista.addAt(10, 5));
        assertEquals(1, lista.size());
        // inserção válida no meio
        lista.add(3);
        assertTrue(lista.addAt(1, 2));
        assertEquals(2, lista.get(1));
        assertEquals(3, lista.size());
    }

    @Test
    @DisplayName("CT-SL-04: remove de elemento inexistente -> false")
    void removeInexistente() {
        lista.add(1);
        lista.add(2);
        assertFalse(lista.remove(99));
        assertTrue(lista.remove(2));
        assertEquals(1, lista.size());
        assertFalse(lista.contains(2));
    }

    @Test
    @DisplayName("CT-SL-05: reverse de [1,2,3] -> [3,2,1]; reverse de vazia -> sem erro")
    void reverse() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.reverse();
        assertEquals(3, lista.get(0));
        assertEquals(2, lista.get(1));
        assertEquals(1, lista.get(2));
        // reverse de lista vazia não deve causar erro
        SinglyLinkedList<Integer> vazia = new SinglyLinkedList<>();
        vazia.reverse();
        assertTrue(vazia.isEmpty());
    }

    @Test
    @DisplayName("CT-SL-06: addSorted mantém ordem após inserções")
    void addSorted() {
        Comparator<Integer> comp = Comparator.naturalOrder();
        lista.addSorted(5, comp);
        lista.addSorted(1, comp);
        lista.addSorted(3, comp);
        lista.addSorted(4, comp);
        lista.addSorted(2, comp);
        for (int i = 0; i < lista.size(); i++) {
            assertEquals(i + 1, lista.get(i));
        }
    }

    @Test
    @DisplayName("CT-SL-07: concatenate -> size total correto; other intacta")
    void concatenate() {
        lista.add(1);
        lista.add(2);
        SinglyLinkedList<Integer> outra = new SinglyLinkedList<>();
        outra.add(3);
        outra.add(4);
        lista.concatenate(outra);
        assertEquals(4, lista.size());
        assertEquals(3, lista.get(2));
        assertEquals(4, lista.get(3));
        // other permanece intacta
        assertEquals(2, outra.size());
        // concatenate com null não causa erro
        lista.concatenate(null);
        assertEquals(4, lista.size());
    }

    @Test
    @DisplayName("CT-SL-08: clear -> size 0 e get(0) null")
    void clear() {
        lista.add(1);
        lista.add(2);
        lista.clear();
        assertEquals(0, lista.size());
        assertTrue(lista.isEmpty());
        assertNull(lista.get(0));
    }

    @Test
    @DisplayName("Extra: get e removeAt com índices inválidos retornam null")
    void indicesInvalidos() {
        assertNull(lista.get(0));
        assertNull(lista.get(-1));
        assertNull(lista.removeAt(0));
        assertNull(lista.removeAt(-5));
        lista.add(7);
        assertNull(lista.get(5));
        assertNull(lista.removeAt(5));
    }

    @Test
    @DisplayName("Extra: removeAt no início, meio e fim")
    void removeAtPosicoes() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        assertEquals(2, lista.removeAt(1)); // meio
        assertEquals(1, lista.removeAt(0)); // início
        assertEquals(3, lista.removeAt(0)); // o que sobrou (era o fim)
        assertTrue(lista.isEmpty());
    }

    @Test
    @DisplayName("Extra: contains e remove com valores null")
    void comNull() {
        lista.add(null);
        lista.add(5);
        assertTrue(lista.contains(null));
        assertTrue(lista.remove(null));
        assertFalse(lista.contains(null));
        assertEquals(1, lista.size());
    }

    @Test
    @DisplayName("Extra: addSorted em lista vazia")
    void addSortedVazia() {
        lista.addSorted(42, Comparator.naturalOrder());
        assertEquals(1, lista.size());
        assertEquals(42, lista.get(0));
    }
}