package Administrador;

import java.util.ArrayList;
import java.util.Date;

import GerenciadorJogos.GerenciadorJogos;

public class Administrador {
    private String nome;
    private String email;
    private Date dataCriacao;
    private ArrayList<String> permissoes;

    public Administrador(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.dataCriacao = new Date();
        this.permissoes = new ArrayList<>();
    }


    public void removerJogo(String nome, GerenciadorJogos gerenciador) {
        if (verificarPermissao("remover_jogos")) {
            gerenciador.removerJogo(nome);
            System.out.println("Jogo '" + nome + "' removido com sucesso pelo administrador " + this.nome);
        } else {
            System.out.println("Permissão negada para remover jogos.");
        }
    }


    public ArrayList<String> listarPermissoes() {
        return new ArrayList<>(this.permissoes);
    }


    public void adicionarPermissao(String permissao) {
        this.permissoes.add(permissao);
        System.out.println("Permissão '" + permissao + "' adicionada ao administrador " + this.nome);
    }


    public boolean verificarPermissao(String permissao) {
        return this.permissoes.contains(permissao);
    }


    public void gerarRelatorio(GerenciadorJogos gerenciador) {
        if (verificarPermissao("visualizar_relatorios")) {
            System.out.println("\nRELATÓRIO DE JOGOS");
            System.out.println("Jogos Ativos: " + gerenciador.listarJogosAtivos().size());
            System.out.println("Jogos Removidos: " + gerenciador.getJogosRemovidos().size());
            System.out.println("Total de Jogos: " + (gerenciador.listarJogosAtivos().size() + gerenciador.getJogosRemovidos().size()));
        } else {
            System.out.println("Permissão negada para visualizar relatórios.");
        }
    }


    public String getNome() {
        return this.nome;
    }


    public String getEmail() {
        return this.email;
    }


    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", permissoes=" + permissoes +
                '}';
    }
}