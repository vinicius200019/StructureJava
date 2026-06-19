package com.structurejava.deque;

import com.structurejava.api.DequeADT;
import com.structurejava.node.DoubleNode;

/**
 * Implementação de deque (double-ended queue) baseada em lista duplamente
 * encadeada. Permite inserção e remoção eficientes (O(1)) em ambas as
 * extremidades, generalizando pilha e fila. Imune a exceções: operações
 * sobre deque vazio retornam {@code null}.
 *
 * @param <T> o tipo dos elementos armazenados no deque.
 */
public class LinkedDeque<T> implements DequeADT<T> {

    /** Nó da frente do deque; {@code null} se vazio. */
    private DoubleNode<T> head;

    /** Nó da cauda do deque; {@code null} se vazio. */
    private DoubleNode<T> tail;

    /** Quantidade de elementos no deque. */
    private int size;

    /**
     * Cria um deque vazio.
     */
    public LinkedDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ===================================================================
    // BLOCO A — addFirst: insere na frente (antes do head)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(T element) {
        DoubleNode<T> novo = new DoubleNode<>(element);
        if (head == null) {        // vazio: novo é head e tail
            head = novo;
            tail = novo;
        } else {                   // liga novo <-> head
            novo.setNext(head);
            head.setPrev(novo);
            head = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO B — addLast: insere na cauda (após o tail)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast(T element) {
        DoubleNode<T> novo = new DoubleNode<>(element);
        if (head == null) {        // vazio: novo é head e tail
            head = novo;
            tail = novo;
        } else {                   // liga tail <-> novo
            novo.setPrev(tail);
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO C — removeFirst: remove da frente e retorna (null se vazio)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeFirst() {
        if (head == null) {        // vazio: sentinela
            return null;
        }
        DoubleNode<T> removido = head;
        head = head.getNext();
        if (head == null) {        // ficou vazio: zera o tail
            tail = null;
        } else {
            head.setPrev(null);    // novo head não tem anterior
        }
        size--;
        return removido.getData();
    }

    // ===================================================================
    // BLOCO D — removeLast: remove da cauda e retorna (null se vazio)
    // O(1) porque o tail conhece seu anterior via getPrev().
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeLast() {
        if (tail == null) {        // vazio: sentinela
            return null;
        }
        DoubleNode<T> removido = tail;
        tail = tail.getPrev();
        if (tail == null) {        // ficou vazio: zera o head
            head = null;
        } else {
            tail.setNext(null);    // novo tail não tem próximo
        }
        size--;
        return removido.getData();
    }

    // ===================================================================
    // BLOCO E — peekFirst / peekLast: consultam as pontas sem remover
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T peekFirst() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peekLast() {
        if (tail == null) {
            return null;
        }
        return tail.getData();
    }

    // ===================================================================
    // BLOCO F — consultas e limpeza (size, isEmpty, clear)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}