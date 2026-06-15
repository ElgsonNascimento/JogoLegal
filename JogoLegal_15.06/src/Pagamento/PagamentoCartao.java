package Pagamento;

import java.util.Date;
import java.util.Calendar;

public class PagamentoCartao extends Pagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String cvv;
    private int mesVencimento;
    private int anoVencimento;
    private boolean cartaoValido;

    public PagamentoCartao(int id, double valor, String numeroCartao, String nomeTitular,
                            String cvv, int mesVencimento, int anoVencimento) {
        super(id, valor, TipoPagamento.CARTAO);
        this.numeroCartao  = numeroCartao;
        this.nomeTitular   = nomeTitular;
        this.cvv           = cvv;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cartaoValido  = validarNumero() && validarVencimento();
    }


    public PagamentoCartao(int id, double valor, String numeroMascarado, String nomeTitular,
                            String cvv, int mesVencimento, int anoVencimento, boolean cartaoJaValidado) {
        super(id, valor, TipoPagamento.CARTAO);
        this.numeroCartao  = numeroMascarado;
        this.nomeTitular   = nomeTitular;
        this.cvv           = cvv;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cartaoValido  = cartaoJaValidado && validarVencimento();
    }




    private boolean validarNumero() {
        String num = numeroCartao.replaceAll("[\\s\\-]", "");
        if (num.length() < 13 || num.length() > 19) return false;
        for (char c : num.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean validarVencimento() {
        if (mesVencimento < 1 || mesVencimento > 12) return false;
        Calendar cal = Calendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);
        int mesAtual = cal.get(Calendar.MONTH) + 1;
        if (anoVencimento < anoAtual) return false;
        if (anoVencimento == anoAtual && mesVencimento < mesAtual) return false;
        return true;
    }



    @Override
    public void processarPagamento() {
        System.out.println("\n=== PROCESSANDO PAGAMENTO CARTAO ===");
        System.out.println("Cartao: " + obterNumeroCartao());
        System.out.println("Titular: " + nomeTitular);
        System.out.println("Valor: R$ " + String.format("%.2f", valor));

        if (cartaoValido) {
            this.status = "Processando";
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            this.status      = "Aprovado";
            this.dataPagamento = new Date();
            this.comprovante   = "CARTAO-" + System.currentTimeMillis();
            System.out.println("Pagamento CARTAO APROVADO! Comprovante: " + comprovante);
        } else {
            this.status = "Falha";
            System.out.println("ERRO: Cartao invalido!");
        }
    }



    public String obterNumeroCartao() {
        String num = numeroCartao.replaceAll("[\\s\\-]", "");
        return num.length() >= 4
            ? "****-****-****-" + num.substring(num.length() - 4)
            : "****-****-****-" + num;
    }

    public String obterNomeTitular() { return nomeTitular; }

    public boolean isCartaoValido() { return cartaoValido; }
}
