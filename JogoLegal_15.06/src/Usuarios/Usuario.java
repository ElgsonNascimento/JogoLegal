package Usuarios;

public class Usuario {

    String nome;
    double saldo;
    public String[] jogosComprados = new String[10];
    public int quantidadeJogos = 0;

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void adicionarSaldo(double valor) {
        saldo += valor;
        System.out.println("Saldo atualizado: R$ " + saldo);
    }


    public void comprarJogo(String nomeJogo) {
        if (quantidadeJogos >= jogosComprados.length) {
            crescerArrays();
        }
        jogosComprados[quantidadeJogos] = nomeJogo;
        quantidadeJogos++;

        System.out.println(nomeJogo + " foi comprado.");
    }

    private void crescerArrays() {
        String[] novosJogos = new String[jogosComprados.length * 2];
        System.arraycopy(jogosComprados, 0, novosJogos, 0, jogosComprados.length);
        jogosComprados = novosJogos;
    }
}
