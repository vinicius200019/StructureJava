package com.structurejava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.structurejava.list.DoublyLinkedList;

/**
 * Suíte de testes da {@link DoublyLinkedList}.
 * Cobre os casos CT-DL do plano de testes e cenários adicionais de borda.
 */
class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> lista;

    @BeforeEach
    void preparar() {
        lista = new DoublyLinkedList<>();
    }

    @Test
    @DisplayName("CT-DL-01: removeAt no início, meio e fim mantém consistência")
    void removeAtPosicoes() {
        for (int i = 1; i <= 5; i++) {
            lista.add(i); // [1,2,3,4,5]
        }
        assertEquals(3, lista.removeAt(2)); // meio -> [1,2,4,5]
        assertEquals(1, lista.removeAt(0)); // início -> [2,4,5]
        assertEquals(5, lista.removeAt(2)); // fim -> [2,4]
        assertEquals(2, lista.size());
        assertEquals(2, lista.get(0));
        assertEquals(4, lista.get(1));
    }

    @Test
    @DisplayName("CT-DL-02: percurso por índice após inserções múltiplas")
    void percurso() {
        for (int i = 0; i < 10; i++) {
            lista.add(i * 10);
        }
        // get usa percurso pelo lado mais curto (head ou tail)
        assertEquals(0, lista.get(0));
        assertEquals(40, lista.get(4));
        assertEquals(90, lista.get(9)); // força percurso a partir do tail
    }

    @Test
    @DisplayName("CT-DL-03: reverse troca head e tail; ordem invertida")
    void reverse() {
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.reverse();
        assertEquals(3, lista.get(0));
        assertEquals(2, lista.get(1));
        assertEquals(1, lista.get(2));
        // após reverse, novas operações de borda continuam coerentes
        lista.addFirst(99);
        lista.add(0);
        assertEquals(99, lista.get(0));
        assertEquals(0, lista.get(lista.size() - 1));
    }

    @Test
    @DisplayName("CT-DL-04: concatenate liga corretamente ao fim")
    void concatenate() {
        lista.add(1);
        lista.add(2);
        DoublyLinkedList<Integer> outra = new DoublyLinkedList<>();
        outra.add(3);
        outra.add(4);
        lista.concatenate(outra);
        assertEquals(4, lista.size());
        assertEquals(4, lista.get(3));
        assertEquals(2, outra.size()); // intacta
        lista.concatenate(null);       // null não causa erro
        assertEquals(4, lista.size());
    }

    @Test
    @DisplayName("Extra: addFirst, addAt e remoções por valor")
    void insercoesERemocoes() {
        lista.addFirst(2);
        lista.addFirst(1);
        lista.add(4);
        assertTrue(lista.addAt(2, 3)); // [1,2,3,4]
        assertEquals(3, lista.get(2));
        assertFalse(lista.addAt(99, 0));
        assertTrue(lista.remove(1));   // remove head
        assertTrue(lista.remove(4));   // remove tail
        assertTrue(lista.remove(3));   // remove meio
        assertFalse(lista.remove(123));
        assertEquals(1, lista.size());
    }

    @Test
    @DisplayName("Extra: addSorted mantém ordem")
    void addSorted() {
        Comparator<Integer> comp = Comparator.naturalOrder();
        lista.addSorted(3, comp);
        lista.addSorted(1, comp);
        lista.addSorted(2, comp);
        lista.addSorted(5, comp);
        lista.addSorted(4, comp);
        for (int i = 0; i < lista.size(); i++) {
            assertEquals(i + 1, lista.get(i));
        }
    }

    @Test
    @DisplayName("Extra: índices inválidos retornam sentinela; clear esvazia")
    void bordasEClear() {
        assertNull(lista.get(0));
        assertNull(lista.removeAt(0));
        assertFalse(lista.contains(1));
        lista.add(1);
        lista.add(2);
        lista.clear();
        assertTrue(lista.isEmpty());
        assertNull(lista.get(0));
    }

    @Test
    @DisplayName("Extra: contains e remove com null")
    void comNull() {
        lista.add(null);
        lista.add(7);
        assertTrue(lista.contains(null));
        assertTrue(lista.remove(null));
        assertEquals(1, lista.size());
    }
}