package GerenciadorJogos;

import Jogo.Jogo;
import java.util.ArrayList;


public class CatalogoGlobal {

    private static CatalogoGlobal instancia;
    private final ArrayList<Jogo> jogosPublicados = new ArrayList<>();

    private CatalogoGlobal() {}

    public static CatalogoGlobal getInstance() {
        if (instancia == null) {
            instancia = new CatalogoGlobal();
        }
        return instancia;
    }

 
    public void publicarJogo(Jogo jogo) {
        // Evita duplicatas pelo nome
        for (Jogo j : jogosPublicados) {
            if (j.getNome().equalsIgnoreCase(jogo.getNome())) return;
        }
        jogosPublicados.add(jogo);
    }

 
    public boolean removerJogo(String nome) {
        return jogosPublicados.removeIf(j -> j.getNome().equalsIgnoreCase(nome));
    }

  
    public Jogo buscarPorNome(String nome) {
        for (Jogo j : jogosPublicados) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        return null;
    }


    public ArrayList<Jogo> listar() {
        return new ArrayList<>(jogosPublicados);
    }
}
