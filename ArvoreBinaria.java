import java.util.Scanner;

// Classe Node representa um nó da árvore.
class Node {
    int valor;        // Valor armazenado no nó.
    Node esquerda;    // Referência para o nó à esquerda.
    Node direita;     // Referência para o nó à direita.

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

// Classe ArvoreBinaria representa a árvore binária de busca.
public class ArvoreBinaria {
    private Node raiz;  // Referência para o nó raiz da árvore.

    public ArvoreBinaria() {
        raiz = null;
    }

    // Função para inserir um valor na árvore.
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    // Função auxiliar para inserção recursiva.
    private Node inserirRec(Node raiz, int valor) {
        // Verifica se a raiz é nula (ou seja, se a árvore está vazia).
        if (raiz == null) {
            // Se a raiz for nula, cria um novo nó com o valor fornecido.
            raiz = new Node(valor);
            // Retorna o novo nó como raiz da subárvore.
            return raiz;
        }

        // Caso a raiz não seja nula (árvore não vazia), compara o valor
        // a ser inserido com o valor da raiz para determinar se o novo nó
        // deve ser colocado à esquerda ou à direita da raiz.

        if (valor < raiz.valor) {
            // Se o valor a ser inserido for menor que o valor da raiz,
            // chama a função recursivamente para inserir o valor na subárvore
            // à esquerda da raiz.
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        } else {
            // Se o valor for maior ou igual ao valor da raiz, chama a função
            // recursivamente para inserir o valor na subárvore à direita da raiz.
            raiz.direita = inserirRec(raiz.direita, valor);
        }

        // Retorna a raiz da subárvore atualizada após a inserção.
        return raiz;
    }

    // Função para buscar um valor na árvore.
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    // Função auxiliar para busca recursiva.
    private boolean buscarRec(Node raiz, int valor) {
        // Verifica se a raiz é nula, indicando que a árvore (ou subárvore) está vazia.
        if (raiz == null) {
            // Se a raiz for nula, o valor não foi encontrado, então retorna falso.
            return false;
        }

        // Compara o valor da raiz com o valor a ser buscado.
        if (raiz.valor == valor) {
            // Se o valor da raiz for igual ao valor buscado, retorna verdadeiro,
            // indicando que o valor foi encontrado na árvore.
            return true;
        }

        // Caso o valor não seja encontrado na raiz atual, a função decide
        // em qual subárvore continuar a busca com base na comparação entre
        // o valor buscado e o valor da raiz.

        if (valor < raiz.valor) {
            // Se o valor for menor que o valor da raiz, a busca continua
            // na subárvore à esquerda da raiz, chamando a função recursivamente.
            return buscarRec(raiz.esquerda, valor);
        } else {
            // Se o valor for maior que o valor da raiz, a busca continua
            // na subárvore à direita da raiz, chamando a função recursivamente.
            return buscarRec(raiz.direita, valor);
        }
    }

    // Função para remover um valor da árvore.
    public void remover(int valor) {
        if (buscar(valor)) {
            raiz = removerRec(raiz, valor);
            System.out.println("\nValor " + valor + " removido com sucesso. Árvore atualizada:\n");
            imprimir();
        } else {
            System.out.println("\nValor " + valor + " não encontrado.\n");
        }
    }

    // Função auxiliar para remoção recursiva.
    private Node removerRec(Node raiz, int valor) {
        // Verifica se a raiz é nula, indicando que a árvore (ou subárvore) está vazia.
        if (raiz == null) {
            // Se a raiz for nula, a árvore está vazia e não há nada a ser removido,
            // então retorna a raiz (que ainda é nula).
            return raiz;
        }

        if (valor < raiz.valor) {
            // Se o valor a ser removido for menor que o valor da raiz,
            // a função continua a busca na subárvore à esquerda da raiz,
            // chamando a função recursivamente.
            raiz.esquerda = removerRec(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            // Se o valor a ser removido for maior que o valor da raiz,
            // a função continua a busca na subárvore à direita da raiz,
            // chamando a função recursivamente.
            raiz.direita = removerRec(raiz.direita, valor);
        } else {
            // Se o valor a ser removido for igual ao valor da raiz atual,
            // significa que encontramos o nó a ser removido.

            if (raiz.esquerda == null) {
                // Se a subárvore à esquerda for nula, simplesmente retornamos
                // a subárvore à direita, removendo o nó atual.
                return raiz.direita;
            } else if (raiz.direita == null) {
                // Se a subárvore à direita for nula, retornamos a subárvore à esquerda,
                // removendo o nó atual.
                return raiz.esquerda;
            } else {
                // Se o nó a ser removido tem dois filhos, precisamos encontrar o valor
                // mínimo na subárvore à direita (o menor valor maior que o nó a ser removido)
                // e substituí-lo pelo valor do nó atual. Em seguida, removemos o valor mínimo
                // da subárvore à direita.

                raiz.valor = valorMinimo(raiz.direita); // Encontra o valor mínimo na subárvore à direita.
                raiz.direita = removerRec(raiz.direita, raiz.valor); // Remove o valor mínimo.
            }
        }

        // Retorna a raiz da subárvore atualizada após a remoção.
        return raiz;
    }

    // Função auxiliar para encontrar o valor mínimo em uma subárvore.
    private int valorMinimo(Node node) {
        int valorMinimo = node.valor;
        while (node.esquerda != null) {
            valorMinimo = node.esquerda.valor;
            node = node.esquerda;
        }
        return valorMinimo;
    }

    // Função para imprimir a árvore.
    public void imprimir() {
        imprimirRec(raiz, 1);
    }

    // Função auxiliar para impressão recursiva.
    private void imprimirRec(Node raiz, int indent) {
        if (raiz != null) {
            // Se a raiz não for nula, a função continua a recursão.

            // Chama a função recursivamente para imprimir a subárvore à direita da raiz,
            // aumentando o nível de indentação (espaços à esquerda) para melhor visualização.
            imprimirRec(raiz.direita, indent + 4);

            // Imprime espaços em branco para alinhar corretamente o valor da raiz com seu nível de indentação.
            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }

            // Imprime o valor da raiz.
            System.out.println(raiz.valor);

            // Chama a função recursivamente para imprimir a subárvore à esquerda da raiz,
            imprimirRec(raiz.esquerda, indent + 4);

            // Imprime uma linha em branco para separar visualmente as subárvores.
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("\n=========================");
        System.out.println("Árvore binária de buscas");
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=========================");
            System.out.println("1) Inserção");
            System.out.println("2) Busca");
            System.out.println("3) Remoção");
            System.out.println("4) Impressão");
            System.out.println("=========================");
            System.out.print("Escolha uma opção: ");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("\nDigite o valor para inserção: ");
                    int valorInserir = scanner.nextInt();
                    arvore.inserir(valorInserir);
                    System.out.println("\nValor inserido com sucesso!\n");
                    break;
                case 2:
                    System.out.print("\nDigite o valor para busca: ");
                    int valorBuscar = scanner.nextInt();
                    boolean encontrado = arvore.buscar(valorBuscar);
                    if (encontrado) {
                        System.out.println("\nValor " + valorBuscar + " encontrado! (Verdadeiro)\n");
                    } else {
                        System.out.println("\nValor não encontrado. (Falso)\n");
                    }
                    break;
                case 3:
                    System.out.print("\nDigite o valor para remoção: ");
                    int valorRemover = scanner.nextInt();
                    arvore.remover(valorRemover);
                    break;
                case 4:
                    System.out.println("\nÁrvore Binária de Busca:\n");
                    arvore.imprimir();
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        }
    }
}
