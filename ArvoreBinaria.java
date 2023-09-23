
import java.util.Scanner;

class Node {
    int valor;
    Node esquerda;
    Node direita;

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

public class ArvoreBinaria {
    private Node raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        } else {
            raiz.direita = inserirRec(raiz.direita, valor);
        }

        return raiz;
    }

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Node raiz, int valor) {
        if (raiz == null) {
            return false;
        }

        if (raiz.valor == valor) {
            return true;
        }

        if (valor < raiz.valor) {
            return buscarRec(raiz.esquerda, valor);
        }
        else {

            return buscarRec(raiz.direita, valor);
        }
    }

    public void remover(int valor) {
        if (buscar(valor)) {
            raiz = removerRec(raiz, valor);
            System.out.println("\nValor " + valor + " removido com sucesso. Árvore atualizada:\n");
            imprimir();
        }
        else {
            System.out.println("\nValor " + valor + " não encontrado.\n");
        }
    }

    private Node removerRec(Node raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = removerRec(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = removerRec(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }
            else {
                raiz.valor = valorMinimo(raiz.direita);
                raiz.direita = removerRec(raiz.direita, raiz.valor);
            }
        }

        return raiz;
    }

    private int valorMinimo(Node node) {
        int valorMinimo = node.valor;
        while (node.esquerda != null) {
            valorMinimo = node.esquerda.valor;
            node = node.esquerda;
        }
        return valorMinimo;
    }

    public void imprimir() {
        imprimirRec(raiz, 1);
    }

    private void imprimirRec(Node raiz, int indent) {
        if (raiz != null) {
            imprimirRec(raiz.direita, indent + 4);
            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }
            System.out.println(raiz.valor);
            imprimirRec(raiz.esquerda, indent + 4);
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
