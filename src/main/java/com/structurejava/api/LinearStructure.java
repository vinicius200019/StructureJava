package com.structurejava.api;

/**
 * Contrato base comum a todas as estruturas lineares da biblioteca.
 * Define as operações fundamentais de consulta de estado e limpeza.
 *
 * @param <T> o tipo dos elementos armazenados na estrutura.
 */
public interface LinearStructure<T> {

    /**
     * Indica se a estrutura não contém elementos.
     *
     * @return {@code true} se, e somente se, {@code size() == 0}.
     */
    boolean isEmpty();

    /**
     * Retorna a quantidade de elementos atualmente na estrutura.
     *
     * @return o número de elementos; sempre maior ou igual a zero.
     */
    int size();

    /**
     * Remove todos os elementos, deixando a estrutura vazia.
     * Após a chamada, {@code size()} é igual a zero.
     */
    void clear();
}