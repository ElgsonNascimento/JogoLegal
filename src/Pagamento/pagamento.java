public class pagamento{
    double valor;
    String metodo;
    String status:
    String dataProcessamento;
    public pagamento(double valor,String metodo,String status,String dataProcessamento){
        this.valor=valor;
        this.metodo=metodo;
        this.status=status;
        this.dataProcessamento=dataProcessamento
    }
    public boolean validarPagamento(){
        return this.valor>0;
    }
    public String toString() {
        return "Pagamento{" +
                ", valor=" + valor +
                ", metodo='" + metodo + '\'' +
                ", status='" + status + '\'' +
                ", dataProcessamento='" + dataProcessamento + '\'' +
                '}';
    }
}
