package com.structurejava.api;

/**
 * Contrato do Tipo Abstrato de Dados Deque (Double-Ended Queue).
 * Generaliza pilha e fila ao permitir inserção e remoção eficientes
 * em ambas as extremidades. Estende {@link LinearStructure}.
 * Operações sobre deque vazio retornam {@code null}, sem exceções.
 *
 * @param <T> o tipo dos elementos armazenados no deque.
 */
public interface DequeADT<T> extends LinearStructure<T> {

    /**
     * Insere um elemento na frente (início) do deque.
     *
     * @param element o elemento a ser inserido.
     */
    void addFirst(T element);

    /**
     * Insere um elemento na cauda (final) do deque.
     *
     * @param element o elemento a ser inserido.
     */
    void addLast(T element);

    /**
     * Remove e retorna o primeiro elemento do deque.
     *
     * @return o primeiro elemento, ou {@code null} se vazio.
     */
    T removeFirst();

    /**
     * Remove e retorna o último elemento do deque.
     *
     * @return o último elemento, ou {@code null} se vazio.
     */
    T removeLast();

    /**
     * Retorna, sem remover, o primeiro elemento do deque.
     *
     * @return o primeiro elemento, ou {@code null} se vazio.
     */
    T peekFirst();

    /**
     * Retorna, sem remover, o último elemento do deque.
     *
     * @return o último elemento, ou {@code null} se vazio.
     */
    T peekLast();
}