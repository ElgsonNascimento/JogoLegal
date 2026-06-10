package Carrinho;

import Jogo.Jogo;
import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Jogo> jogos;

    public Carrinho() {
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public boolean removerJogo(Jogo jogo) {
        return jogos.remove(jogo);
    }

    public ArrayList<Jogo> getJogos() {
        return this.jogos;
    }

    public double calcularTotal() {
        double total = 0;
        for (Jogo jogo : jogos) {
            total += jogo.getPreco();
        }
        return total;
    }

    /**
     * Finaliza a compra dos jogos.
     * @return 1 se sucesso, -1 se vazio, 0 se saldo insuficiente.
     */
    public int finalizarCompra(ArrayList<Jogo> bibliotecaUsuario, double saldoUsuario) {
        if (jogos.isEmpty()) {
            return -1; // Carrinho vazio
        }

        double total = calcularTotal();
        if (saldoUsuario >= total) {
            // Transfere os objetos Jogo diretamente para a lista da biblioteca
            for (Jogo jogo : jogos) {
                // Altera o status padrão caso queira iniciar como "Comprado"
                bibliotecaUsuario.add(jogo);
            }
            jogos.clear(); // Limpa o carrinho pós-compra
            return 1; // Sucesso
        }

        return 0; // Saldo insuficiente
    }
}