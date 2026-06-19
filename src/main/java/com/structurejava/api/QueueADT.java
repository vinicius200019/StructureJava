package com.structurejava.api;

/**
 * Contrato do Tipo Abstrato de Dados Fila (FIFO – First In, First Out).
 * Estende {@link LinearStructure}. As operações de remoção e consulta
 * são imunes a exceções: em fila vazia retornam {@code null}.
 *
 * @param <T> o tipo dos elementos armazenados na fila.
 */
public interface QueueADT<T> extends LinearStructure<T> {

    /**
     * Insere um elemento no final da fila.
     *
     * @param element o elemento a ser enfileirado.
     */
    void enqueue(T element);

    /**
     * Remove e retorna o elemento do início da fila.
     *
     * @return o primeiro elemento, ou {@code null} se a fila estiver vazia.
     */
    T dequeue();

    /**
     * Retorna, sem remover, o elemento do início da fila.
     *
     * @return o primeiro elemento, ou {@code null} se a fila estiver vazia.
     */
    T peek();
}