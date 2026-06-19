package com.structurejava.list;

import java.util.Comparator;

import com.structurejava.api.ListADT;
import com.structurejava.node.Node;

/**
 * Implementação de lista simplesmente encadeada.
 * Mantém referências para o primeiro nó ({@code head}) e o último
 * ({@code tail}), além de um contador de tamanho, garantindo inserção
 * no início e no fim em tempo O(1). Imune a exceções: operações
 * inválidas retornam valores sentinela.
 *
 * @param <T> o tipo dos elementos armazenados na lista.
 */
public class SinglyLinkedList<T> implements ListADT<T> {

    /** Referência ao primeiro nó da lista; {@code null} se vazia. */
    private Node<T> head;

    /** Referência ao último nó da lista; {@code null} se vazia. */
    private Node<T> tail;

    /** Quantidade de elementos atualmente na lista. */
    private int size;

    /**
     * Cria uma lista vazia.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ===================================================================
    // BLOCO A — Consultas de estado (size, isEmpty)
    // Retornam informações sobre a lista sem percorrê-la. Como o contador
    // 'size' é mantido atualizado a cada operação, ambas são O(1).
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

    // ===================================================================
    // BLOCO B — Inserção no fim (add)
    // Cria um nó e o anexa após o tail atual. Se a lista estava vazia, o
    // novo nó passa a ser head e tail ao mesmo tempo. O(1) graças ao tail.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T element) {
        Node<T> novo = new Node<>(element);
        if (head == null) {        // lista vazia: novo nó é head e tail
            head = novo;
            tail = novo;
        } else {                   // anexa após o tail atual
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO C — Inserção no início (addFirst)
    // Espelho do add: o novo nó aponta para o antigo head e passa a ser o
    // novo head. Se a lista estava vazia, também vira o tail. O(1).
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(T element) {
        Node<T> novo = new Node<>(element);
        if (head == null) {        // lista vazia: novo é head e tail
            head = novo;
            tail = novo;
        } else {                   // novo aponta para o antigo head
            novo.setNext(head);
            head = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO D — Inserção em posição (addAt)
    // Valida o índice (retorna false se inválido, sem exceção). Reaproveita
    // addFirst/add nos extremos; no caso geral, caminha até o nó anterior à
    // posição e religa os ponteiros para encaixar o novo nó.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAt(int index, T element) {
        if (index < 0 || index > size) {   // índice inválido: imune a exceção
            return false;
        }
        if (index == 0) {                  // início: reaproveita addFirst
            addFirst(element);
            return true;
        }
        if (index == size) {               // fim: reaproveita add
            add(element);
            return true;
        }
        // caso geral: parar no nó ANTERIOR à posição de inserção
        Node<T> anterior = head;
        for (int i = 0; i < index - 1; i++) {
            anterior = anterior.getNext();
        }
        Node<T> novo = new Node<>(element);
        novo.setNext(anterior.getNext()); // novo aponta para quem estava em index
        anterior.setNext(novo);           // anterior passa a apontar para novo
        size++;
        return true;
    }

    // ===================================================================
    // BLOCO E — Consulta por índice (get)
    // Caminha do head dando 'index' passos e retorna o dado. Índice válido
    // vai de 0 a size-1; fora disso retorna null. Acesso é O(n).
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {   // índice inválido: retorna sentinela
            return null;
        }
        Node<T> atual = head;
        for (int i = 0; i < index; i++) {   // caminha até a posição desejada
            atual = atual.getNext();
        }
        return atual.getData();
    }

    // ===================================================================
    // BLOCO F — Busca por valor (contains)
    // Percorre a lista comparando cada dado (via 'igual', seguro contra
    // null). Retorna true assim que encontra, false se chegar ao fim.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T element) {
        Node<T> atual = head;
        while (atual != null) {
            if (igual(atual.getData(), element)) {
                return true;
            }
            atual = atual.getNext();
        }
        return false;
    }

    // ===================================================================
    // BLOCO G — Remoção por valor (remove)
    // Trata a remoção do primeiro nó como caso especial. No caso geral usa
    // dois ponteiros (anterior/atual) para 'pular' o nó alvo. Mantém tail e
    // size coerentes. Retorna false se o elemento não existir.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T element) {
        if (head == null) {                 // lista vazia: nada a remover
            return false;
        }
        if (igual(head.getData(), element)) {   // remoção do primeiro
            head = head.getNext();
            if (head == null) {             // a lista ficou vazia
                tail = null;
            }
            size--;
            return true;
        }
        // procura mantendo referência ao nó anterior
        Node<T> anterior = head;
        Node<T> atual = head.getNext();
        while (atual != null) {
            if (igual(atual.getData(), element)) {
                anterior.setNext(atual.getNext());  // "pula" o nó removido
                if (atual == tail) {        // removeu o último: atualiza tail
                    tail = anterior;
                }
                size--;
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        }
        return false;                       // não encontrado
    }

    // ===================================================================
    // BLOCO H — Remoção por índice (removeAt)
    // Mesma lógica do remove, mas localizando por posição e devolvendo o
    // dado removido (retorno T). Índice inválido retorna null.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeAt(int index) {
        if (index < 0 || index >= size) {   // índice inválido: sentinela
            return null;
        }
        if (index == 0) {                   // remoção do primeiro
            Node<T> removido = head;
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
            return removido.getData();
        }
        // caminha até o nó anterior ao alvo
        Node<T> anterior = head;
        for (int i = 0; i < index - 1; i++) {
            anterior = anterior.getNext();
        }
        Node<T> removido = anterior.getNext();
        anterior.setNext(removido.getNext());   // desliga o alvo
        if (removido == tail) {             // removeu o último
            tail = anterior;
        }
        size--;
        return removido.getData();
    }

    // ===================================================================
    // BLOCO I — Auxiliar de comparação (igual)
    // Compara dois elementos por conteúdo (equals), tratando null com
    // segurança para nunca lançar NullPointerException. Uso interno.
    // ===================================================================

    /**
     * Compara dois elementos de forma segura contra {@code null}.
     * Usa {@code equals} para comparar conteúdo, não identidade de referência.
     *
     * @param a primeiro elemento (pode ser {@code null}).
     * @param b segundo elemento (pode ser {@code null}).
     * @return {@code true} se ambos forem {@code null} ou se forem iguais
     *         segundo {@code equals}.
     */
    private boolean igual(T a, T b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    // Próximos blocos: addSorted, reverse, concatenate e clear.

    // ===================================================================
    // BLOCO J — Inserção ordenada (addSorted)
    // Pré-condição: a lista já está ordenada segundo o comparador. Percorre
    // até achar a posição correta e insere ali, preservando a ordem. Trata
    // inserção no início e no fim como casos naturais do percurso.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSorted(T element, Comparator<T> comparator) {
        Node<T> novo = new Node<>(element);
        // Caso 1: lista vazia ou elemento <= head -> insere no início
        if (head == null || comparator.compare(element, head.getData()) <= 0) {
            novo.setNext(head);
            head = novo;
            if (tail == null) {        // lista estava vazia
                tail = novo;
            }
            size++;
            return;
        }
        // Caso 2: percorre até achar o nó cujo próximo é maior que o elemento
        Node<T> atual = head;
        while (atual.getNext() != null
                && comparator.compare(atual.getNext().getData(), element) < 0) {
            atual = atual.getNext();
        }
        novo.setNext(atual.getNext()); // encaixa entre 'atual' e o seguinte
        atual.setNext(novo);
        if (novo.getNext() == null) {  // inseriu no fim: atualiza tail
            tail = novo;
        }
        size++;
    }
    // ===================================================================
    // BLOCO K — Inversão in-place (reverse)
    // Inverte o sentido dos ponteiros 'next' sem alocar nova estrutura.
    // Usa três ponteiros (anterior, atual, proximo) e ao final troca os
    // papéis de head e tail.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        Node<T> anterior = null;
        Node<T> atual = head;
        tail = head;                   // o antigo primeiro será o novo último
        while (atual != null) {
            Node<T> proximo = atual.getNext(); // guarda o próximo antes de mexer
            atual.setNext(anterior);   // inverte o ponteiro deste nó
            anterior = atual;          // avança 'anterior'
            atual = proximo;           // avança 'atual'
        }
        head = anterior;               // 'anterior' parou no antigo último nó
    }
    // ===================================================================
    // BLOCO L — Concatenação (concatenate)
    // Acrescenta ao fim desta lista uma CÓPIA dos elementos de 'other'.
    // A lista 'other' não é modificada (copiamos os dados, não os nós).
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void concatenate(ListADT<T> other) {
        if (other == null) {           // nada a concatenar
            return;
        }
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));         // copia cada elemento ao fim desta lista
        }
    }
    // ===================================================================
    // BLOCO M — Limpeza (clear)
    // Desreferencia head e tail e zera o size. Os nós ficam sem referências
    // e são recolhidos pelo coletor de lixo (garbage collector) do Java.
    // ===================================================================

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