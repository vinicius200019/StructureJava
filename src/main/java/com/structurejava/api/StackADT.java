package com.structurejava.api;

/**
 * Contrato do Tipo Abstrato de Dados Pilha (LIFO – Last In, First Out).
 * Estende {@link LinearStructure}. As operações de remoção e consulta
 * são imunes a exceções: em pilha vazia retornam {@code null}.
 *
 * @param <T> o tipo dos elementos armazenados na pilha.
 */
public interface StackADT<T> extends LinearStructure<T> {

    /**
     * Insere um elemento no topo da pilha.
     *
     * @param element o elemento a ser empilhado.
     */
    void push(T element);

    /**
     * Remove e retorna o elemento do topo da pilha.
     *
     * @return o elemento do topo, ou {@code null} se a pilha estiver vazia.
     */
    T pop();

    /**
     * Retorna, sem remover, o elemento do topo da pilha.
     *
     * @return o elemento do topo, ou {@code null} se a pilha estiver vazia.
     */
    T peek();
}