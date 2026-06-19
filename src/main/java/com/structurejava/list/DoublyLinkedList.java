package com.structurejava.list;

import java.util.Comparator;

import com.structurejava.api.ListADT;
import com.structurejava.node.DoubleNode;

/**
 * Implementação de lista duplamente encadeada.
 * Cada nó mantém referências para o anterior ({@code prev}) e o próximo
 * ({@code next}), permitindo navegação bidirecional. A lista guarda
 * referências para o primeiro nó ({@code head}) e o último ({@code tail}),
 * além de um contador de tamanho, garantindo inserção e remoção nas pontas
 * em tempo O(1). Imune a exceções: operações inválidas retornam valores
 * sentinela.
 *
 * @param <T> o tipo dos elementos armazenados na lista.
 */
public class DoublyLinkedList<T> implements ListADT<T> {

    /** Referência ao primeiro nó da lista; {@code null} se vazia. */
    private DoubleNode<T> head;

    /** Referência ao último nó da lista; {@code null} se vazia. */
    private DoubleNode<T> tail;

    /** Quantidade de elementos atualmente na lista. */
    private int size;

    /**
     * Cria uma lista vazia.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ===================================================================
    // BLOCO A — Consultas de estado (size, isEmpty)
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
    // Liga o novo nó após o tail: tail.next = novo e novo.prev = tail.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T element) {
        DoubleNode<T> novo = new DoubleNode<>(element);
        if (head == null) {            // lista vazia: novo é head e tail
            head = novo;
            tail = novo;
        } else {                       // anexa após o tail, ligando os dois sentidos
            novo.setPrev(tail);
            tail.setNext(novo);
            tail = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO C — Inserção no início (addFirst)
    // Liga o novo nó antes do head: novo.next = head e head.prev = novo.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(T element) {
        DoubleNode<T> novo = new DoubleNode<>(element);
        if (head == null) {            // lista vazia: novo é head e tail
            head = novo;
            tail = novo;
        } else {                       // novo passa a anteceder o head
            novo.setNext(head);
            head.setPrev(novo);
            head = novo;
        }
        size++;
    }

    // ===================================================================
    // BLOCO D — Inserção em posição (addAt)
    // Valida o índice; usa addFirst/add nos extremos. No caso geral, localiza
    // o nó que está na posição e insere o novo ANTES dele, religando os
    // quatro ponteiros envolvidos (anterior <-> novo <-> alvo).
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAt(int index, T element) {
        if (index < 0 || index > size) {   // índice inválido: imune a exceção
            return false;
        }
        if (index == 0) {                  // início
            addFirst(element);
            return true;
        }
        if (index == size) {               // fim
            add(element);
            return true;
        }
        DoubleNode<T> alvo = node(index);  // nó atualmente na posição
        DoubleNode<T> anterior = alvo.getPrev();
        DoubleNode<T> novo = new DoubleNode<>(element);
        // religa: anterior <-> novo
        novo.setPrev(anterior);
        anterior.setNext(novo);
        // religa: novo <-> alvo
        novo.setNext(alvo);
        alvo.setPrev(novo);
        size++;
        return true;
    }

    // ===================================================================
    // BLOCO E — Consulta por índice (get)
    // Reaproveita o auxiliar node(index), que escolhe o sentido mais curto
    // de percurso (do head ou do tail) usando a referência prev.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return node(index).getData();
    }

    // ===================================================================
    // BLOCO F — Busca por valor (contains)
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T element) {
        DoubleNode<T> atual = head;
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
    // Localiza o nó alvo e o desliga com o auxiliar unlink(), que cuida de
    // religar prev/next dos vizinhos e atualizar head/tail/size.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T element) {
        DoubleNode<T> atual = head;
        while (atual != null) {
            if (igual(atual.getData(), element)) {
                unlink(atual);
                return true;
            }
            atual = atual.getNext();
        }
        return false;
    }

    // ===================================================================
    // BLOCO H — Remoção por índice (removeAt)
    // Localiza o nó pela posição, desliga com unlink() e devolve o dado.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        DoubleNode<T> alvo = node(index);
        T dado = alvo.getData();
        unlink(alvo);
        return dado;
    }

    // ===================================================================
    // BLOCO I — Inserção ordenada (addSorted)
    // Percorre até o primeiro nó cujo dado é maior que o elemento e insere
    // antes dele. Se nenhum for maior, insere no fim.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSorted(T element, Comparator<T> comparator) {
        DoubleNode<T> atual = head;
        while (atual != null && comparator.compare(atual.getData(), element) < 0) {
            atual = atual.getNext();
        }
        if (atual == null) {           // todos menores: insere no fim
            add(element);
        } else if (atual == head) {    // elemento <= primeiro: insere no início
            addFirst(element);
        } else {                       // insere antes de 'atual'
            DoubleNode<T> anterior = atual.getPrev();
            DoubleNode<T> novo = new DoubleNode<>(element);
            novo.setPrev(anterior);
            novo.setNext(atual);
            anterior.setNext(novo);
            atual.setPrev(novo);
            size++;
        }
    }

    // ===================================================================
    // BLOCO J — Inversão in-place (reverse)
    // Em cada nó, troca os papéis de prev e next. Ao final, troca head e
    // tail. Não aloca nova estrutura.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        DoubleNode<T> atual = head;
        while (atual != null) {
            DoubleNode<T> proximo = atual.getNext(); // guarda antes de trocar
            atual.setNext(atual.getPrev());          // inverte os ponteiros
            atual.setPrev(proximo);
            atual = proximo;                         // avança no sentido original
        }
        DoubleNode<T> temp = head;     // troca head <-> tail
        head = tail;
        tail = temp;
    }

    // ===================================================================
    // BLOCO K — Concatenação (concatenate)
    // Acrescenta ao fim uma cópia dos elementos de 'other'. 'other' não é
    // modificada.
    // ===================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void concatenate(ListADT<T> other) {
        if (other == null) {
            return;
        }
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }

    // ===================================================================
    // BLOCO L — Limpeza (clear)
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

    // ===================================================================
    // BLOCO M — Auxiliares privados (node, unlink, igual)
    // node(index): retorna o nó na posição, percorrendo pelo lado mais curto.
    // unlink(no): desliga um nó religando seus vizinhos e ajustando head/tail.
    // igual(a,b): compara conteúdo com segurança contra null.
    // ===================================================================

    /**
     * Retorna o nó localizado na posição informada, percorrendo a lista a
     * partir da extremidade mais próxima (head ou tail) para reduzir o número
     * de passos.
     *
     * @param index posição válida (0 &lt;= index &lt; size).
     * @return o nó naquela posição.
     */
    private DoubleNode<T> node(int index) {
        if (index < size / 2) {        // mais perto do início
            DoubleNode<T> atual = head;
            for (int i = 0; i < index; i++) {
                atual = atual.getNext();
            }
            return atual;
        } else {                       // mais perto do fim: anda de trás pra frente
            DoubleNode<T> atual = tail;
            for (int i = size - 1; i > index; i--) {
                atual = atual.getPrev();
            }
            return atual;
        }
    }

    /**
     * Desliga um nó da lista, religando seus vizinhos e atualizando
     * {@code head}, {@code tail} e {@code size}.
     *
     * @param no o nó a ser removido (não nulo, pertencente à lista).
     */
    private void unlink(DoubleNode<T> no) {
        DoubleNode<T> anterior = no.getPrev();
        DoubleNode<T> proximo = no.getNext();
        if (anterior == null) {        // o nó era o head
            head = proximo;
        } else {
            anterior.setNext(proximo);
        }
        if (proximo == null) {         // o nó era o tail
            tail = anterior;
        } else {
            proximo.setPrev(anterior);
        }
        size--;
    }

    /**
     * Compara dois elementos de forma segura contra {@code null}.
     *
     * @param a primeiro elemento (pode ser {@code null}).
     * @param b segundo elemento (pode ser {@code null}).
     * @return {@code true} se ambos forem {@code null} ou iguais por
     *         {@code equals}.
     */
    private boolean igual(T a, T b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }
}