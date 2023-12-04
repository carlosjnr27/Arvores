public class ArvoreAVL {
    NoAVL raiz;

    int altura(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    int obterBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = maximo(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = maximo(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    NoAVL inserir(NoAVL no, int valor) {
        if (no == null) {
            return new NoAVL(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        } else {
            return no;
        }

        no.altura = 1 + maximo(altura(no.esquerda), altura(no.direita));

        int balanceamento = obterBalanceamento(no);

        // Casos de rotação
        if (balanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    void imprimirEmOrdem(NoAVL no) {
        if (no != null) {
            imprimirEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            imprimirEmOrdem(no.direita);
        }
    }

    
}