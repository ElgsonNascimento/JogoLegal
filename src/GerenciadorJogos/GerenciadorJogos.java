package GerenciadorJogos;
import java.util.ArrayList;
import Jogo.Jogo;

public class GerenciadorJogos {
    public ArrayList<Jogo> jogosAtivos;
    public ArrayList<Jogo> jogosRemovidos;

    public GerenciadorJogos() {
        this.jogosAtivos = new ArrayList<>();
        this.jogosRemovidos = new ArrayList<>();
    }

    public boolean criarJogo(String nome, String descricao, String genero){
        Jogo novoJogo = new Jogo(nome,descricao,genero);
        jogosAtivos.add(novoJogo);
        System.out.println("Jogo criado com sucesso: " + nome);
        return true;
    }
    public boolean removerJogo(String nome){
        Jogo jogo = obterJogo(nome);
        if (jogo != null){
            jogosAtivos.remove(jogo);
            jogosRemovidos.add(jogo);
            System.out.println("Jogo removido: " + nome);
            return true;
        }
        return false;
    }
    public boolean iniciarJogo(String nome){
        Jogo jogo = obterJogo(nome);
        if(jogo != null){
            jogo.iniciarJogo();
            return true;
        }
        return false;
    }
    public boolean pausarJogo(String nome){
        Jogo jogo = obterJogo(nome);
        if(jogo != null){
            jogo.pausarJogo();
            return true;
        }
        return false;
    }
    public boolean finalizarJogo(String nome){
        Jogo jogo = obterJogo(nome);
        if (jogo != null){
            jogo.sairJogo();
            jogosAtivos.remove(jogo);
            jogosRemovidos.add(jogo);
            return true;
        }
        return false;
    }
    public ArrayList<Jogo>listarJogosAtivos(){
        return this.jogosAtivos;
    }
    public ArrayList<Jogo>getJogosRemovidos(){
        return this.jogosRemovidos;
    }
    public Jogo obterJogo(String nome){
        for (Jogo j : jogosAtivos){
            if (j.getNome().equalsIgnoreCase(nome)){
                return j;
            }
        }
        return null;
    }
    public ArrayList<Jogo>listarJogosPorCriador(int idCriador){
        return this.jogosAtivos;
    }
    @Override
    public String toString(){
        return "GerenciadorJogos{jogosAtivos=" + jogosAtivos.size()+",jogosRemovidos=" + jogosRemovidos.size() +"}";
    }

}






