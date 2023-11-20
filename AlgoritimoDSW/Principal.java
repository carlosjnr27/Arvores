import java.util.LinkedList;
import java.util.Random;

public class Principal {
    public static void main(String[] args) {
        // Criando a árvore com 100 números aleatórios
        NoArvore tree = new NoArvore();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int numeroAleatorio = random.nextInt(101);
            tree = tree.Insere(tree, numeroAleatorio);
        }

        System.out.println("Árvore antes do balanceamento:");
        tree.Imp_Cres(tree, 0);

        // Implementando o Algoritmo DSW para construir uma árvore binária balanceada
        LinkedList<Integer> lista = AlgoritmoDSW.transformaArvoreEmLista(tree);
        lista = AlgoritmoDSW.realizarRotacoesParaEquilibrar(lista);
        tree = new AlgoritmoDSW().reconstruirArvore(lista);

        System.out.println("\nÁrvore após o primeiro balanceamento:");
        tree.Imp_Cres(tree, 0);

        // Acrescentando 20 números na árvore
        for (int i = 0; i < 20; i++) {
            int numeroAleatorio = random.nextInt(101);
            tree = tree.Insere(tree, numeroAleatorio);
        }

        System.out.println("\nÁrvore antes do segundo balanceamento:");
        tree.Imp_Cres(tree, 0);

        lista = AlgoritmoDSW.transformaArvoreEmLista(tree);
        lista = AlgoritmoDSW.realizarRotacoesParaEquilibrar(lista);
        tree = new AlgoritmoDSW().reconstruirArvore(lista);

        System.out.println("\nÁrvore após o segundo balanceamento:");
        tree.Imp_Cres(tree, 0);
    }
}
