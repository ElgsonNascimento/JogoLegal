package interfacejogolegal;
public class Usuario {

    String nome;
    boolean logado;
    double saldo;
    public String[] jogosComprados = new String[10];
    boolean[] instalado = new boolean[10];
    public int quantidadeJogos = 0;

    // Função para o login
    public void login() {
        logado = true;
        System.out.println(nome + " fez login.");
    }

    // Função para o Logout
    public void logout() {
        logado = false;
        System.out.println(nome + " fez logout.");
    }

    // Função para mostrar o saldo
    public void adicionarSaldo(double valor) {
        saldo += valor;
        System.out.println("Saldo atualizado: R$ " + saldo);
    }

    // Função para comprar o jogo
    public void comprarJogo(String nomeJogo) {
        jogosComprados[quantidadeJogos] = nomeJogo;
        instalado[quantidadeJogos] = false;
        quantidadeJogos++;

        System.out.println(nomeJogo + " foi comprado.");
    }

    // Função para mostrar os jogos que foram comprados
    public void mostrarJogosComprados() {

        System.out.println("Jogos comprados:");

        for (int i = 0; i < quantidadeJogos; i++) {

            System.out.println(
                    (i + 1) + " - " +
                            jogosComprados[i] +
                            (instalado[i] ? " (Instalado)" : " (Não instalado)")
            );
        }
    }

    // Função para instalar jogo
    public void instalarJogo(String nomeJogo) {

        for (int i = 0; i < quantidadeJogos; i++) {

            if (jogosComprados[i].equalsIgnoreCase(nomeJogo)) {

                instalado[i] = true;

                System.out.println(nomeJogo + " instalado.");
                return;
            }
        }

        System.out.println("Jogo não encontrado.");
    }

    // Função para desinstalar jogo
    public void desinstalarJogo(String nomeJogo) {

        for (int i = 0; i < quantidadeJogos; i++) {

            if (jogosComprados[i].equalsIgnoreCase(nomeJogo)) {

                instalado[i] = false;

                System.out.println(nomeJogo + " desinstalado.");
                return;
            }
        }

        System.out.println("Jogo não encontrado.");
    }
}

