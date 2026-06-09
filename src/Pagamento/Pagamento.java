package Pagamento;

import java.util.Date;
import java.util.Random;

public class Pagamento{
    public static enum TipoPagamento{
        PIX,
        SALDO
    }
    public int id;
    public double valor;
    public TipoPagamento tipoPagamento;
    public String status;
    public Date dataPagamento;
    public String chavePixRecebedor;
    public double saldoDisponivel;
    public String comprovante;
    public Pagamento(int id, double valor, TipoPagamento tipoPagamento) {
        this.id = id;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.status = "Pendente";
        this.dataPagamento = new Date();
        this.saldoDisponivel = 0.0;
    }
    public void processarPagamento() {
        System.out.println("\nPROCESSANDO PAGAMENTO");
        if (this.tipoPagamento == TipoPagamento.PIX) {
            this.chavePixRecebedor = gerarChavePixAleatoria();
            System.out.println("Chave PIX: " + this.chavePixRecebedor);
            System.out.println("Valor: R$ " + this.valor);
        }
        this.status = "Processando";
        System.out.println("Processando...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Processamento interrompido!");
            return;
        }
        this.status = "Aprovado";
        this.dataPagamento = new Date();
        if (this.tipoPagamento == TipoPagamento.PIX) {
            this.comprovante = gerarComprovantePix();
        } else {
            this.comprovante = gerarComprovanteSaldo();
        }

        System.out.println("Pagamento aprovado");
        System.out.println("Comprovante: " + comprovante + "\n");
    }
    private String gerarChavePixAleatoria() {
        return "1629461812000180";
    }
    private String gerarComprovantePix() {
        String numeroAleatorio = String.format("%016d", new Random().nextLong() & Long.MAX_VALUE);
        return "PIX-" + numeroAleatorio.substring(0, 16);
    }
    private String gerarComprovanteSaldo() {
        String numeroAleatorio = String.format("%016d", new Random().nextLong() & Long.MAX_VALUE);
        return "SALDO-" + numeroAleatorio.substring(0, 16);
    }
    public void adicionarSaldo(double valor) {
        if (valor > 0) {
            this.saldoDisponivel += valor;
            System.out.println("Saldo adicionado: R$ " + String.format("%.2f", valor));
            System.out.println("Saldo total: R$ " + String.format("%.2f", saldoDisponivel));
        } else {
            System.out.println("Valor inválido para adicionar saldo!");
        }
    }
    public double obterSaldo() {
        return this.saldoDisponivel;
    }
    public String obterComprovante() {
        return this.comprovante;
    }
    public String obterStatus() {
        return this.status;
    }
    public String obterChavePix() {
        return this.chavePixRecebedor;
    }
    public int getId() {
        return this.id;
    }
    public double getValor() {
        return this.valor;
    }
    public TipoPagamento getTipoPagamento() {
        return this.tipoPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +                 // ← SEM ASPAS (é int)
                ", valor=R$ " + String.format("%.2f", valor) +
                ", tipo=" + tipoPagamento +
                ", status='" + status + '\'' +
                ", chave='" + chavePixRecebedor + '\'' +
                ", comprovante='" + comprovante + '\'' +
                ", data=" + dataPagamento +
                '}';
    }
}