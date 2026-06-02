public class jogo {
    String nome;
    String descricao;
    String genero;
    String status;
    public jogo(String nome,String descricao,String genero){
        this.nome=nome;
        this.descricao=descricao;
        this.genero=genero;
        this.status=status;
    }
    public void mostrarDetalhes(){
        System.out.println("Nome:" + nome);
        System.out.println("Descrição:" + descricao);
        System.out.println("Gênero:" + genero);
        System.out.println("Status:" + status);
    }
    public boolean iniciarJogo(){
        status="Rodando";
        return true;
    }
    public boolean sairJogo(){
        status="Finalizado";
        return false;
    }
    @Override
    public String toString(){
        return "Jogo [nome=" + nome + ", descricao=" + descricao + ", status=" + status + ", genero=" + genero + "]";
    }

}
