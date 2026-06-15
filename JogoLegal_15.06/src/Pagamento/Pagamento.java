package Pagamento;

import java.util.Date;

public abstract class Pagamento {
    public enum TipoPagamento {
        PIX,
        CARTAO,
        SALDO
    }
    protected int id;
    protected double valor;
    protected TipoPagamento tipoPagamento;
    protected String status;
    protected Date dataPagamento;
    protected String comprovante;
    protected Pagamento(int id, double valor, TipoPagamento tipoPagamento) {
        this.id = id;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.status = "Pendente";
        this.dataPagamento = new Date();
    }
    public abstract void processarPagamento();
    public String obterComprovante() {
        return comprovante;
    }
    public String obterStatus() {
        return status;
    }
    public int getId() {
        return id;
    }
    public double getValor() {
        return valor;
    }
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", valor=R$ " + String.format("%.2f", valor) +
                ", tipo=" + tipoPagamento +
                ", status='" + status + '\'' +
                ", comprovante='" + comprovante + '\'' +
                ", data=" + dataPagamento +
                '}';
    }
}
