package Pagamento;

import java.util.Date;

public class PagamentoSaldo extends Pagamento {

    private double saldoDisponivel;

    public PagamentoSaldo(int id, double valor) {
        super(id, valor, TipoPagamento.SALDO);
        this.saldoDisponivel = 0.0;
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

    @Override
    public void processarPagamento() {
        System.out.println("\nPROCESSANDO PAGAMENTO SALDO");
        System.out.println("Valor: R$ " + String.format("%.2f", valor));
        System.out.println("Saldo disponível: R$ " + String.format("%.2f", saldoDisponivel));

        if (this.saldoDisponivel >= this.valor) {
            this.status = "Processando";
            System.out.println("Processando...");
            this.saldoDisponivel -= this.valor;
            this.status = "Aprovado";
            this.dataPagamento = new Date();
            this.comprovante = gerarComprovante();

            System.out.println("Pagamento SALDO APROVADO!");
            System.out.println("Comprovante: " + comprovante);
            System.out.println(" Saldo restante: R$ " + String.format("%.2f", saldoDisponivel) + "\n");
        } else {
            this.status = "Falha";
            System.out.println("ERRO: Saldo insuficiente!");
        }
    }
    private String gerarComprovante() {
        return "SALDO-" + System.currentTimeMillis();
    }
    public double obterSaldo() {
        return this.saldoDisponivel;
    }
}