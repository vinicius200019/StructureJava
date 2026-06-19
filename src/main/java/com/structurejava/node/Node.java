package com.structurejava.node;

/**
 * Nó de uma estrutura encadeada simples.
 * Armazena um elemento de dado e uma referência ao próximo nó.
 *
 * @param <T> o tipo do elemento armazenado no nó.
 */
public class Node<T> {

    /** O dado (valor) armazenado neste nó. */
    private T data;

    /** Referência para o próximo nó da estrutura. {@code null} indica fim. */
    private Node<T> next;

    /**
     * Cria um nó contendo o dado informado, sem sucessor.
     *
     * @param data o elemento a ser armazenado.
     */
    public Node(T data) {
        this.data = data;
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
     * Retorna a referência para o próximo nó.
     *
     * @return o próximo nó, ou {@code null} se este for o último.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Define a referência para o próximo nó.
     *
     * @param next o nó que passará a ser o sucessor.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}