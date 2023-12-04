
public class ArvoreRubroNegra {
    private NoRB raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
        raiz.cor = Cor.PRETO; // Garante que a raiz seja preta
    }

    private NoRB inserirRecursivo(NoRB no, int valor) {
        if (no == null) {
            return new NoRB(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
            no.esquerda.pai = no;
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
            no.direita.pai = no;
        }

        // Casos de ajuste após a inserção
        if (cor(no.direita) == Cor.VERMELHO && cor(no.esquerda) == Cor.PRETO) {
            no = rotacaoEsquerda(no);
        }
        if (cor(no.esquerda) == Cor.VERMELHO && cor(no.esquerda.esquerda) == Cor.VERMELHO) {
            no = rotacaoDireita(no);
        }
        if (cor(no.esquerda) == Cor.VERMELHO && cor(no.direita) == Cor.VERMELHO) {
            inverterCores(no);
        }

        return no;
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
        if (raiz != null) {
            raiz.cor = Cor.PRETO; // Garante que a raiz seja preta após a remoção
        }
    }

    private NoRB removerRecursivo(NoRB no, int valor) {
        if (no == null) {
            return null;
        }

        if (valor < no.valor) {
            no.esquerda = removerRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = removerRecursivo(no.direita, valor);
        } else {
            if (no.esquerda == null) {
                return no.direita;
            } else if (no.direita == null) {
                return no.esquerda;
            } else {
                NoRB sucessor = encontrarMenorNo(no.direita);
                no.valor = sucessor.valor;
                no.direita = removerRecursivo(no.direita, sucessor.valor);
            }
        }

        // Casos de ajuste após a remoção
        if (cor(no.direita) == Cor.VERMELHO && cor(no.esquerda) == Cor.PRETO) {
            no = rotacaoEsquerda(no);
        }
        if (cor(no.esquerda) == Cor.VERMELHO && cor(no.esquerda.esquerda) == Cor.VERMELHO) {
            no = rotacaoDireita(no);
        }
        if (cor(no.esquerda) == Cor.VERMELHO && cor(no.direita) == Cor.VERMELHO) {
            inverterCores(no);
        }

        return no;
    }

    private Cor cor(NoRB no) {
        return (no == null) ? Cor.PRETO : no.cor;
    }

    private NoRB encontrarMenorNo(NoRB no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    public int contar(int valor) {
        return contarRecursivo(raiz, valor);
    }

    private int contarRecursivo(NoRB no, int valor) {
        if (no == null) {
            return 0;
        }

        int contagem = 0;
        if (no.valor == valor) {
            contagem++;
        }

        contagem += contarRecursivo(no.esquerda, valor);
        contagem += contarRecursivo(no.direita, valor);

        return contagem;
    }

    private NoRB rotacaoEsquerda(NoRB no) {
        NoRB direitaNo = no.direita;
        no.direita = direitaNo.esquerda;
        if (no.direita != null) {
            no.direita.pai = no;
        }
        direitaNo.pai = no.pai;
        if (no.pai == null) {
            raiz = direitaNo;
        } else if (no == no.pai.esquerda) {
            no.pai.esquerda = direitaNo;
        } else {
            no.pai.direita = direitaNo;
        }
        direitaNo.esquerda = no;
        no.pai = direitaNo;
        return direitaNo;
    }

    private NoRB rotacaoDireita(NoRB no) {
        NoRB esquerdaNo = no.esquerda;
        no.esquerda = esquerdaNo.direita;
        if (no.esquerda != null) {
            no.esquerda.pai = no;
        }
        esquerdaNo.pai = no.pai;
        if (no.pai == null) {
            raiz = esquerdaNo;
        } else if (no == no.pai.direita) {
            no.pai.direita = esquerdaNo;
        } else {
            no.pai.esquerda = esquerdaNo;
        }
        esquerdaNo.direita = no;
        no.pai = esquerdaNo;
        return esquerdaNo;
    }

    private void inverterCores(NoRB no) {
        no.cor = no.cor == Cor.VERMELHO ? Cor.PRETO : Cor.VERMELHO;
        if (no.esquerda != null) {
            no.esquerda.cor = no.esquerda.cor == Cor.VERMELHO ? Cor.PRETO : Cor.VERMELHO;
        }
        if (no.direita != null) {
            no.direita.cor = no.direita.cor == Cor.VERMELHO ? Cor.PRETO : Cor.VERMELHO;
        }
    }

    public void imprimirArvore() {
        imprimirEmOrdem(raiz);
    }

    private void imprimirEmOrdem(NoRB no) {
        if (no != null) {
            imprimirEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            imprimirEmOrdem(no.direita);
        }
    }
}
