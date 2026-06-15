package Pagamento;

import java.util.Date;

public class PagamentoPix extends Pagamento {

    private final String chavePixRecebedor;

    public PagamentoPix(int id, double valor) {
        super(id, valor, TipoPagamento.PIX);
        this.chavePixRecebedor = "1629461812000180";
    }

    @Override
    public void processarPagamento() {
        System.out.println("\nPROCESSANDO PAGAMENTO PIX");
        System.out.println("Chave PIX: " + chavePixRecebedor);
        System.out.println("Valor: R$ " + valor);

        this.status = "Processando";
        System.out.println("Processando...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Processamento interrompido!");
            return;
        }

        this.status = "Aprovado";
        this.dataPagamento = new Date();
        this.comprovante = gerarComprovantePix();

        System.out.println("Pagamento PIX APROVADO!");
        System.out.println(" Comprovante: " + comprovante + "\n");
    }

    private String gerarComprovantePix() {
        return "PIX-" + System.currentTimeMillis();
    }

    public String obterChavePix() {
        return chavePixRecebedor;
    }
}