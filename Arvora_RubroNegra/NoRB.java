enum Cor {
    VERMELHO,
    PRETO
}
public class NoRB {
    int valor;
    Cor cor;
    NoRB pai;
    NoRB esquerda;
    NoRB direita;
    
    public NoRB(int valor) {
        this.valor = valor;
        this.cor = Cor.VERMELHO;
        this.pai = null;
        this.esquerda = null;
        this.direita = null;
    }
}
