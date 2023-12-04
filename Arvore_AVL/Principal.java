import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        long tempoInicial = System.currentTimeMillis();

        // Ler o arquivo de 100.000 números
        String arquivo = "dados100_mil.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] numeros = linha.substring(1, linha.length() - 1).split(", ");
                for (String numero : numeros) {
                    int valor = Integer.parseInt(numero.trim());
                    arvoreAVL.raiz = arvoreAVL.inserir(arvoreAVL.raiz, valor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Impressão em ordem dos dados:");
        arvoreAVL.imprimirEmOrdem(arvoreAVL.raiz);

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;
        System.out.println("\n\nTempo total para impressão: " + tempoTotal + " milissegundos");
    }
}
