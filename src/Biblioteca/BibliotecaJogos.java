package Biblioteca;

import Usuarios.Usuario;
public class BibliotecaJogos {

    String[] jogos = {
            "The Witcher 3",
            "Overwatch",
            "Cyberpunk 2077",
            "Elden Ring",
            "Minecraft",
            "GTA V",
            "Red Dead Redemption 2",
            "FIFA 26",
            "Call of Duty",
            "Super Mario Bross",
            "Mortal Kombat 11",
            "Valorant",
    };

    // Listar os jogos
    public void listarJogos() {

        System.out.println("=== LISTA DE JOGOS ===");

        for (int i = 0; i < jogos.length; i++) {
            System.out.println((i + 1) + " - " + jogos[i]);
        }
    }

    // buscar jogo pelo nome do jogo
    public void buscarJogo(String nomeJogo) {

        for (int i = 0; i < jogos.length; i++) {

            if (jogos[i].equalsIgnoreCase(nomeJogo)) {
                System.out.println("Jogo encontrado: " + jogos[i]);
                return;
            }
        }

        System.out.println("Jogo não encontrado.");
    }

    // mostrar os jogos que não foram comprados
    public void mostrarJogosNaoComprados(Usuario usuario) {

        System.out.println("=== JOGOS NÃO COMPRADOS ===");

        for (int i = 0; i < jogos.length; i++) {

            boolean comprado = false;

            for (int j = 0; j < usuario.quantidadeJogos; j++) {

                if (jogos[i].equalsIgnoreCase(usuario.jogosComprados[j])) {
                    comprado = true;
                    break;
                }
            }

            if (!comprado) {
                System.out.println(jogos[i] + "Este jogo não foi comprado ainda.");

            }
        }
    }
}