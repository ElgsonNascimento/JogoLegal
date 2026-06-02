    public class criadorjogo{
     String nome;
     String descricao;
     double preco;
     int vendas;

     public criadorjogo(String nome,String descricao,double preco,int vendas){
         this.nome=nome;
         this.descricao=descricao
         this.preco=preco
         this.vendas=vendas
     }
     public boolean publicarJogo(){
         return true
     }
     public boolean removerJogo(){
         return true
     }
     public int verVendas(){
         return this.vendas
     }
     public void editarJogo(String nome,String descricao,double preco){
         this.nome=nome
         this.descricao=descricao
         this.preco=preco
     }
     @Override
     public String toString(){
         return "CriadorJogo{" +
                 ", nome='" + nome + '\'' +
                 ", descricao='" + descricao + '\'' +
                 ", preco=" + preco +
                 ", vendas=" + vendas +
                 '}'
     }

    }