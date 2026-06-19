package com.structurejava.queue;

import com.structurejava.api.QueueADT;
import com.structurejava.node.Node;

/**
 * Implementação de fila (FIFO) baseada em nós encadeados.
 * A inserção ({@code enqueue}) ocorre no fim ({@code tail}) e a remoção
 * ({@code dequeue}) no início ({@code head}), ambas em O(1) graças à
 * manutenção do ponteiro de cauda. Imune a exceções: operações sobre fila
 * vazia retornam {@code null}.
 *
 * @param <T> o tipo dos elementos armazenados na fila.
 */
public class LinkedQueue<T> implements QueueADT<T> {

    /** Nó do início da fila (próximo a sair); {@code null} se vazia. */
    private Node<T> head;

    /** Nó do fim da fila (último a entrar); {@code null} se vazia. */
    private Node<T> tail;

    /** Quantidade de elementos na fila. */
    private int size;

    /**
     * Cria uma fila vazia.
     */
    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ===================================================================
    // BLOCO A — enqueue: insere no fim da fila (após o tail)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(T element) {
        Node<T> novo = new Node<>(element);
        if (head == null) {        // fila vazia: novo é head e tail
            head = novo;
            tail = novo;
        } else {                   // anexa após o tail
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO B — dequeue: remove do início e retorna (null se vazia)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T dequeue() {
        if (head == null) {        // fila vazia: sentinela
            return null;
        }
        Node<T> removido = head;
        head = head.getNext();     // o início passa a ser o próximo
        if (head == null) {        // a fila ficou vazia: zera o tail também
            tail = null;
        }
        size--;
        return removido.getData();
    }

    // ===================================================================
    // BLOCO C — peek: retorna o início sem remover (null se vazia)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
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
        head = null;
        tail = null;
        size = 0;
    }
}