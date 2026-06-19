# StructureJava

Biblioteca Java de **Estruturas de Dados Lineares**, construída inteiramente com
alocação dinâmica de nós e **sem utilizar o Java Collections Framework**
(`java.util`). Todas as estruturas são genéricas, documentadas com JavaDoc e
**imunes a exceções**: operações inválidas retornam valores sentinela
(`null`, `false`) em vez de lançar exceções.

## Estruturas disponíveis

| Estrutura | Classe | Principais operações |
|-----------|--------|----------------------|
| Lista simplesmente encadeada | `SinglyLinkedList<T>` | add, addFirst, addAt, addSorted, remove, removeAt, get, contains, reverse, concatenate |
| Lista duplamente encadeada | `DoublyLinkedList<T>` | idem, com navegação bidirecional |
| Pilha (LIFO) | `LinkedStack<T>` | push, pop, peek |
| Fila (FIFO) | `LinkedQueue<T>` | enqueue, dequeue, peek |
| Deque (fila de duas pontas) | `LinkedDeque<T>` | addFirst, addLast, removeFirst, removeLast, peekFirst, peekLast |

Todas implementam a interface base `LinearStructure<T>`, que define `isEmpty()`,
`size()` e `clear()`.

## Arquitetura

O projeto adota um design orientado a interfaces (TADs), organizado em pacotes:

```
com.structurejava
├── api      → interfaces (contratos): LinearStructure, ListADT, StackADT, QueueADT, DequeADT
├── node     → nós: Node (simples) e DoubleNode (duplo)
├── list     → SinglyLinkedList, DoublyLinkedList
├── stack    → LinkedStack
├── queue    → LinkedQueue
└── deque    → LinkedDeque
```

## Requisitos

- Java 17 (JDK)
- Apache Maven 3.x

## Como compilar e testar

Compilar o código:

```bash
mvn compile
```

Rodar os testes (JUnit 5):

```bash
mvn test
```

Verificar a cobertura de testes (mínimo de 90%, exigido pelo build):

```bash
mvn verify
```

Gerar o pacote `.jar` da biblioteca (fica em `target/structurejava-1.0.0.jar`):

```bash
mvn package
```

Gerar a documentação JavaDoc em HTML (fica em `target/site/apidocs/index.html`):

```bash
mvn javadoc:javadoc
```

O relatório de cobertura do JaCoCo é gerado em `target/site/jacoco/index.html`.

## Exemplos de uso

### Lista

```java
SinglyLinkedList<Integer> lista = new SinglyLinkedList<>();
lista.add(1);
lista.add(2);
lista.addFirst(0);
lista.reverse();            // [2, 1, 0]
int x = lista.get(0);       // 2
boolean tem = lista.contains(1); // true
```

### Pilha (LIFO)

```java
LinkedStack<String> pilha = new LinkedStack<>();
pilha.push("a");
pilha.push("b");
String topo = pilha.peek(); // "b"
String saiu = pilha.pop();  // "b"
```

### Fila (FIFO)

```java
LinkedQueue<String> fila = new LinkedQueue<>();
fila.enqueue("a");
fila.enqueue("b");
String primeiro = fila.dequeue(); // "a"
```

### Deque

```java
LinkedDeque<Integer> deque = new LinkedDeque<>();
deque.addFirst(1);
deque.addLast(2);
int frente = deque.removeFirst(); // 1
int cauda  = deque.removeLast();  // 2
```

## Imunidade a exceções

Operações inválidas não lançam exceções:

```java
LinkedStack<Integer> p = new LinkedStack<>();
p.pop();          // retorna null (pilha vazia)
lista.get(999);   // retorna null (índice inválido)
lista.addAt(-1, 5); // retorna false (índice inválido)
```

## Testes e qualidade

- Testes automatizados com **JUnit 5**, uma suíte por estrutura.
- Cobertura de código medida com **JaCoCo** (meta mínima de 90%, verificada
  automaticamente no `mvn verify`).
- Documentação completa via **JavaDoc** em todos os métodos públicos.

## Licença

Projeto acadêmico desenvolvido para a disciplina de Estruturas de Dados.