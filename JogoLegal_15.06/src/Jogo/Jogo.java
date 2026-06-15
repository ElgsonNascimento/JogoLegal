package Jogo;

import java.util.ArrayList;

public class Jogo {
    public String nome;
    public String descricao;
    public String genero;
    public double preco;
    public String status;
    public String criador;
    public ArrayList<String> historicoStatus;

    public Jogo(String nome, String descricao, String genero, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.genero = genero;
        this.preco = preco;
        this.status = "Parado";
        this.criador = "";
        this.historicoStatus = new ArrayList<String>();
        this.historicoStatus.add("Parado");
    }

    public String getNome()         { return nome; }
    public String getDescricao()    { return descricao; }
    public void   setDescricao(String d) { this.descricao = d; }
    public String getGenero()       { return genero; }
    public double getPreco()        { return preco; }
    public void   setPreco(double p){ this.preco = p; }
    public String getStatus()       { return status; }
    public String getCriador()      { return criador; }
    public void   setCriador(String c) { this.criador = c; }

    public boolean iniciarJogo() {
        this.status = "Rodando";
        this.historicoStatus.add(this.status);
        return true;
    }

    public boolean pausarJogo() {
        this.status = "Pausado";
        this.historicoStatus.add(this.status);
        return true;
    }

    public boolean sairJogo() {
        this.status = "Finalizado";
        this.historicoStatus.add(this.status);
        return true;
    }

    public ArrayList<String> obterHistoricoStatus() { return this.historicoStatus; }
    public ArrayList<String> getHistoricoStatus()   { return obterHistoricoStatus(); }

    public void mostrarDetalhes() { System.out.println(this.toString()); }

    @Override
    public String toString() {
        return "Jogo{nome='" + nome + "', genero='" + genero
            + "', preco=" + preco + ", status='" + status
            + "', criador='" + criador + "'}";
    }
}
