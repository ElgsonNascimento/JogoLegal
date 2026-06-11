package Telas;

public class Jogo {
    private String nome;
    private double preco;

    public Jogo(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
}
