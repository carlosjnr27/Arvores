import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class OperacoesArvore {
    private static final int NUMERO_OPERACOES = 50000;
    private ArvoreRubroNegra arvoreRN;

    public OperacoesArvore() {
        arvoreRN = new ArvoreRubroNegra();
    }

    public void realizarOperacoes() {
        long tempoInicial = System.currentTimeMillis();

        String arquivo = "dados5.txt";
        lerArquivoEInserir(arquivo);

        long tempoFinalInsercao = System.currentTimeMillis();
        long tempoInsercaoTotal = tempoFinalInsercao - tempoInicial;
        System.out.println("Tempo total para inserção dos 100.000 números: " + tempoInsercaoTotal + " milissegundos");
        System.out.println("Imprimindo dados após inserção e balanceamento:");
        arvoreRN.imprimirArvore();
        System.out.println(" ");

        Random random = new Random();
        int numerosInseridos = 0;
        int numerosRemovidos = 0;
        int numerosContados = 0;

        // Realizar operações com os números sorteados
        for (int i = 0; i < NUMERO_OPERACOES; i++) {
            int numero = random.nextInt(19999) - 9999; // Números entre -9999 e 9999

            if (numero % 3 == 0) {
                arvoreRN.inserir(numero);
                numerosInseridos++;
            } else if (numero % 5 == 0) {
                arvoreRN.remover(numero);
                numerosRemovidos++;
            } else {
                int quantidade = arvoreRN.contar(numero);
                numerosContados += quantidade;
            }
        }

        System.out.println("Números aleatórios inseridos na árvore: " + numerosInseridos);
        System.out.println("Números aleatórios removidos da árvore: " + numerosRemovidos);
        System.out.println("Quantidade de números contados na árvore: " + numerosContados);

    }

    private void lerArquivoEInserir(String nomeArquivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] numeros = linha.substring(1, linha.length() - 1).split(", ");
                for (String numero : numeros) {
                    int valor = Integer.parseInt(numero.trim());
                    arvoreRN.inserir(valor);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OperacoesArvore operacoesArvore = new OperacoesArvore();
        operacoesArvore.realizarOperacoes();
    }
}
