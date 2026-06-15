package Biblioteca;

import Usuarios.Usuario;
import Jogo.Jogo;
import GerenciadorJogos.CatalogoGlobal;
import java.util.ArrayList;

public class BibliotecaJogos {


    public ArrayList<Jogo> listarJogos(CatalogoGlobal catalogo) {
        ArrayList<Jogo> lista = catalogo.listar();
        System.out.println("=== LISTA DE JOGOS ===");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getNome());
        }
        return lista;
    }


    public Jogo buscarJogo(String nomeJogo, CatalogoGlobal catalogo) {
        Jogo encontrado = catalogo.buscarPorNome(nomeJogo);
        if (encontrado != null) {
            System.out.println("Jogo encontrado: " + encontrado.getNome());
        } else {
            System.out.println("Jogo não encontrado.");
        }
        return encontrado;
    }


    public ArrayList<Jogo> mostrarJogosNaoComprados(Usuario usuario, CatalogoGlobal catalogo, ArrayList<Jogo> bibliotecaUsuario) {
        System.out.println("=== JOGOS NÃO COMPRADOS ===");
        ArrayList<Jogo> naoComprados = new ArrayList<>();

        for (Jogo jogo : catalogo.listar()) {
            boolean comprado = false;
            for (Jogo b : bibliotecaUsuario) {
                if (b.getNome().equalsIgnoreCase(jogo.getNome())) {
                    comprado = true;
                    break;
                }
            }
            if (!comprado) {
                naoComprados.add(jogo);
                System.out.println(jogo.getNome() + " - Este jogo não foi comprado ainda.");
            }
        }
        return naoComprados;
    }
}
