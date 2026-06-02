import java.util.ArrayList;

public class Carrinho {
//    private ArrayList<Jogo> jogos;

    public Carrinho() {
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
        System.out.println(jogo.getNome() + " foi adicionado ao carrinho.");
    }

    public void removerJogo(Jogo jogo) {
        if (jogos.remove(jogo)) {
            System.out.println(jogo.getNome() + " foi removido do carrinho.");
        } else {
            System.out.println("Esse jogo não está no carrinho.");
        }
    }

    public double calcularTotal() {
        double total = 0;

        for (Jogo jogo : jogos) {
            total += jogo.getPreco();
        }

        return total;
    }

    public void finalizarCompra(Usuario usuario) {
        double total = calcularTotal();

        if (jogos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        if (usuario.getSaldo() >= total) {
            usuario.setSaldo(usuario.getSaldo() - total);

            for (Jogo jogo : jogos) {
                usuario.instalarJogo(jogo);
            }

            jogos.clear();
            System.out.println("Compra finalizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
}