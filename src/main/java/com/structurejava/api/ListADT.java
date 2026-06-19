package com.structurejava.api;

import java.util.Comparator;

/**
 * Contrato do Tipo Abstrato de Dados Lista.
 * Estende {@link LinearStructure} com operações de inserção, remoção,
 * busca e manipulação. Todas as operações são imunes a exceções:
 * situações inválidas retornam valores sentinela ({@code null},
 * {@code false}) em vez de lançar exceções.
 *
 * @param <T> o tipo dos elementos armazenados na lista.
 */
public interface ListADT<T> extends LinearStructure<T> {

    /**
     * Insere um elemento no final da lista.
     *
     * @param element o elemento a ser inserido.
     */
    void add(T element);

    /**
     * Insere um elemento no início da lista.
     *
     * @param element o elemento a ser inserido.
     */
    void addFirst(T element);

    /**
     * Insere um elemento na posição indicada, deslocando os demais.
     *
     * @param index   a posição de inserção (0 &lt;= index &lt;= size).
     * @param element o elemento a ser inserido.
     * @return {@code true} se inserido; {@code false} se o índice for inválido.
     */
    boolean addAt(int index, T element);

    /**
     * Insere um elemento mantendo a ordem definida pelo comparador.
     * Pré-condição: a lista já deve estar ordenada segundo {@code comparator}.
     *
     * @param element    o elemento a ser inserido.
     * @param comparator o critério de ordenação.
     */
    void addSorted(T element, Comparator<T> comparator);

    /**
     * Remove a primeira ocorrência do elemento informado.
     *
     * @param element o elemento a ser removido.
     * @return {@code true} se removido; {@code false} se não encontrado.
     */
    boolean remove(T element);

    /**
     * Remove e retorna o elemento da posição indicada.
     *
     * @param index a posição (0 &lt;= index &lt; size).
     * @return o elemento removido, ou {@code null} se o índice for inválido.
     */
    T removeAt(int index);

    /**
     * Retorna, sem remover, o elemento da posição indicada.
     *
     * @param index a posição (0 &lt;= index &lt; size).
     * @return o elemento, ou {@code null} se o índice for inválido.
     */
    T get(int index);

    /**
     * Verifica se a lista contém o elemento informado.
     *
     * @param element o elemento procurado.
     * @return {@code true} se o elemento existir na lista.
     */
    boolean contains(T element);

    /**
     * Inverte a ordem dos elementos da lista no próprio local (in-place),
     * sem alocar uma nova estrutura.
     */
    void reverse();

    /**
     * Acrescenta ao final desta lista todos os elementos de outra lista.
     * A lista {@code other} não é modificada.
     *
     * @param other a lista cujos elementos serão copiados; não nula.
     */
    void concatenate(ListADT<T> other);
}