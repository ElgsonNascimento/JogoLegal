package CriadorJogo;

import java.util.ArrayList;
import java.util.Date;
import Jogo.Jogo;
import GerenciadorJogos.GerenciadorJogos;

public class CriadorJogo {
    public int id;
    private String nome;
    private String email;
    private Date dataCriacao;
    private ArrayList<Jogo>jogosCriados;
    public CriadorJogo(String nome,String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = new Date();
        this.jogosCriados = new ArrayList<>();
    }
    public void criarJogo(String nome,String descricao,String genero,double preco,GerenciadorJogos gerenciador){
        Jogo novoJogo = new Jogo(nome,descricao,genero,preco);
        gerenciador.criarJogo(nome,descricao,genero,preco);
        this.jogosCriados.add(novoJogo);
        System.out.println("Jogo" + nome + "criado com sucesso" );
    }
    public ArrayList<Jogo>listarJogosCriados(){
        return this.jogosCriados;
    }
    public void editarJogo(String nome,String novaDescricao,GerenciadorJogos gerenciador){
        for (Jogo jogo : jogosCriados){
            if(jogo.getNome().equalsIgnoreCase(nome)){
                jogo.setDescricao(novaDescricao);
                System.out.println("Jogo" + nome +"editado com sucesso." );
                return;
            }
        }
        System.out.println("Jogo não encontrado na lista deste criador.");
    }
    public void deletarJogo(String nome,GerenciadorJogos gerenciador){
        Jogo jogoParaRemover = null;
        for(Jogo jogo : jogosCriados){
            if (jogo.getNome().equalsIgnoreCase(nome)){
                jogoParaRemover = jogo;
                break;
            }
        }
        if (jogoParaRemover != null){
            gerenciador.removerJogo(nome);
            jogosCriados.remove(jogoParaRemover);
            System.out.println("Jogo" + nome + "deletado com sucesso");
        }else{
            System.out.println("Jogo não encontrado para exclusão");
        }
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public Date getDataCriacao(){
        return this.dataCriacao;
    }
    public int getQuantidadeJogos(){
        return this.jogosCriados.size();
    }
    @Override
    public String toString(){
        return "CriadorJogo{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", jogosCriados=" + jogosCriados.size() +
                '}';
    }
}