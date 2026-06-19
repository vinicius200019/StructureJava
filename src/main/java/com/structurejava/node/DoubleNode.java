package com.structurejava.node;

/**
 * Nó de uma estrutura duplamente encadeada.
 * Armazena um dado e referências para o nó anterior e o próximo,
 * permitindo navegação bidirecional.
 *
 * @param <T> o tipo do elemento armazenado no nó.
 */
public class DoubleNode<T> {

    /** O dado (valor) armazenado neste nó. */
    private T data;

    /** Referência para o nó anterior. {@code null} indica início. */
    private DoubleNode<T> prev;

    /** Referência para o próximo nó. {@code null} indica fim. */
    private DoubleNode<T> next;

    /**
     * Cria um nó contendo o dado informado, sem anterior nem sucessor.
     *
     * @param data o elemento a ser armazenado.
     */
    public DoubleNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    /**
     * Retorna o dado armazenado neste nó.
     *
     * @return o elemento armazenado.
     */
    public T getData() {
        return data;
    }

    /**
     * Define o dado armazenado neste nó.
     *
     * @param data o novo elemento a ser armazenado.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Retorna a referência para o nó anterior.
     *
     * @return o nó anterior, ou {@code null} se este for o primeiro.
     */
    public DoubleNode<T> getPrev() {
        return prev;
    }

    /**
     * Define a referência para o nó anterior.
     *
     * @param prev o nó que passará a ser o antecessor.
     */
    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Retorna a referência para o próximo nó.
     *
     * @return o próximo nó, ou {@code null} se este for o último.
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Define a referência para o próximo nó.
     *
     * @param next o nó que passará a ser o sucessor.
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}