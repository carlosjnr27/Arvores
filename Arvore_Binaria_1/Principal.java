import java.util.Random;

public class Principal {
    public static void main(String[] args) {
        NoArvore tree = new NoArvore();

        // Sorteie 20 números de 0 a 100
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int numeroSorteado = random.nextInt(101); // Números de 0 a 100
            tree = tree.Insere(tree, numeroSorteado);
        }

        System.out.println("1) Impressão pré-ordem:");
        tree.Imp_Cres(tree);

        System.out.println("\n2) Impressão in-ordem:");
        tree.Imp_Cres(tree);

        System.out.println("\n3) Impressão pós-ordem:");
        printPosOrder(tree);

        System.out.println("\n4) Impressão em nível:");
        printLevelOrder(tree);

        // Retirando 5 elementos da árvore
        for (int i = 0; i < 5; i++) {
            int numeroRemover = random.nextInt(101);
            tree = tree.Retira(tree, numeroRemover);
        }

        System.out.println("\nApós remover 5 elementos:");

        System.out.println("1) Impressão pré-ordem:");
        tree.Imp_Cres(tree);

        System.out.println("\n2) Impressão in-ordem:");
        tree.Imp_Cres(tree);

        System.out.println("\n3) Impressão pós-ordem:");

        printPosOrder(tree);

        System.out.println("\n4) Impressão em nível:");
        printLevelOrder(tree);
    }

    // Método para imprimir pós-ordem
    private static void printPosOrder(NoArvore tree) {
        if (tree != null) {
            printPosOrder(tree.esquerda);
            printPosOrder(tree.direita);
            System.out.print(tree.info + " ");
        }
    }

    // Método para imprimir em nível
    private static void printLevelOrder(NoArvore tree) {
        int height = height(tree);
        for (int i = 1; i <= height; i++) {
            printGivenLevel(tree, i);
        }
    }

    // Método auxiliar para imprimir em nível
    private static void printGivenLevel(NoArvore tree, int level) {
        if (tree == null) {
            return;
        }
        if (level == 1) {
            System.out.print(tree.info + " ");
        } else if (level > 1) {
            printGivenLevel(tree.esquerda, level - 1);
            printGivenLevel(tree.direita, level - 1);
        }
    }

    // Método auxiliar para calcular a altura da árvore
    private static int height(NoArvore tree) {
        if (tree == null) {
            return 0;
        } else {
            int leftHeight = height(tree.esquerda);
            int rightHeight = height(tree.direita);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
