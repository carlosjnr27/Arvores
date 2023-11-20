import java.util.LinkedList;

public class AlgoritmoDSW {

    public static LinkedList<Integer> transformaArvoreEmLista(NoArvore tree) {
        LinkedList<Integer> lista = new LinkedList<>();
        if (tree != null) {
            lista.addAll(transformaArvoreEmLista(tree.esquerda));
            lista.add(tree.info);
            lista.addAll(transformaArvoreEmLista(tree.direita));
        }
        return lista;
    }

    public static LinkedList<Integer> realizarRotacoesParaEquilibrar(LinkedList<Integer> lista) {
        int n = lista.size();
        int m = (int) Math.pow(2, (int) (Math.log(n + 1) / Math.log(2))) - 1;
        for (int i = 0; i < n - m; i++) {
            int no1 = lista.removeFirst();
            int no2 = lista.removeFirst();
            lista.addLast(no2);
            lista.addLast(no1);
        }
        while (m > 1) {
            m /= 2;

            for (int i = 0; i < m; i++) {
                int no1 = lista.removeFirst();
                int no2 = lista.removeFirst();
                lista.addLast(no2);
                lista.addLast(no1);
            }
        }
        return lista;
    }

    public NoArvore reconstruirArvore(LinkedList<Integer> lista) {
        NoArvore tree = null;

        if (!lista.isEmpty()) {
            // Configurar a raiz da árvore
            int raizValor = lista.removeFirst();
            tree = new NoArvore();
            tree.info = raizValor;

            // Reconstruir a árvore a partir da lista
            NoArvore current = tree;
            while (!lista.isEmpty()) {
                int noValor = lista.removeFirst();
                current = current.Insere(current, noValor);
            }
        }

        return tree;
    }
}
