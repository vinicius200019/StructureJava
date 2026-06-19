package com.structurejava.stack;

import com.structurejava.api.StackADT;
import com.structurejava.node.Node;

/**
 * Implementação de pilha (LIFO) baseada em nós encadeados.
 * O topo da pilha corresponde ao primeiro nó ({@code head}), de modo que
 * inserção e remoção ocorrem sempre em O(1). Imune a exceções: operações
 * sobre pilha vazia retornam {@code null}.
 *
 * @param <T> o tipo dos elementos armazenados na pilha.
 */
public class LinkedStack<T> implements StackADT<T> {

    /** Nó do topo da pilha; {@code null} se vazia. */
    private Node<T> top;

    /** Quantidade de elementos na pilha. */
    private int size;

    /**
     * Cria uma pilha vazia.
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    // ===================================================================
    // BLOCO A — push: empilha no topo (insere antes do nó de topo atual)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(T element) {
        Node<T> novo = new Node<>(element);
        novo.setNext(top);   // novo aponta para o antigo topo
        top = novo;          // novo passa a ser o topo
        size++;
    }

    // ===================================================================
    // BLOCO B — pop: desempilha o topo e o retorna (null se vazia)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T pop() {
        if (top == null) {   // pilha vazia: sentinela
            return null;
        }
        Node<T> removido = top;
        top = top.getNext(); // o topo passa a ser o nó de baixo
        size--;
        return removido.getData();
    }

    // ===================================================================
    // BLOCO C — peek: retorna o topo sem remover (null se vazia)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T peek() {
        if (top == null) {
            return null;
        }
        return top.getData();
    }

    // ===================================================================
    // BLOCO D — consultas e limpeza (size, isEmpty, clear)
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
        top = null;
        size = 0;
    }
}