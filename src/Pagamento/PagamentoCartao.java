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
    public PagamentoCartao(int id, double valor, String numeroCartao, String nomeTitular, String cvv, int mesVencimento, int anoVencimento) {
        super(id, valor, TipoPagamento.CARTAO);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.cvv = cvv;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cartaoValido = validarCartao() && validarVencimento();
    }
    private boolean validarCartao() {
        String num = numeroCartao.replaceAll("[\s-]+", "");
        if (num.length() < 13 || num.length() > 19) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        // Percorre da direita para esquerda
        for (int i = num.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(num.substring(i, i + 1));

            // Duplica dígitos alternados
            if (alternate) {
                n *= 2;
                // Se > 9, subtrai 9
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        // Verifica se é divisível por 10
        return (sum % 10 == 0);
    }
    private boolean validarVencimento(){
        if (mesVencimento<1 ||mesVencimento>12){
            return false;
        }
        Calendar cal = Calendar.getInstance();
        int anoAtual=cal.get(Calendar.YEAR);
        int mesAtual=cal.get(Calendar.MONTH)+1;
        if (anoVencimento<anoAtual){
            return false;
        }
        if (anoVencimento==anoAtual && mesVencimento<mesAtual) {
            return false;
        }
        return true;
    }
    @Override
    public void processarPagamento() {
        System.out.println("\n=== PROCESSANDO PAGAMENTO CARTÃO ===");
        System.out.println("Cartão: " + obterNumeroCartao());
        System.out.println("Titular: " + nomeTitular);
        System.out.println("Valor: R$ " + String.format("%.2f", valor));

        if (cartaoValido) {
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
            this.comprovante = gerarComprovante();

            System.out.println("Pagamento CARTÃO APROVADO!");
            System.out.println("Comprovante: " + comprovante + "\n");
        } else {
            this.status = "Falha";
            System.out.println("ERRO: Cartão inválido!");
            System.out.println("O número do cartão não passou na validação.\n");
        }
    }

    private String gerarComprovante() {
        return "CARTAO-" + System.currentTimeMillis();
    }

    public String obterNumeroCartao() {
        return "****-****-****-" + numeroCartao.substring(numeroCartao.length() - 4);
    }

    public String obterNomeTitular() {
        return nomeTitular;
    }
    public String obterCVV(){
        if (cvv == null || cvv.length()<2){
            return "**";
        }
        return "**" +cvv.substring(cvv.length()-2);
    }
    public boolean isCartaoValido(){
        return cartaoValido;
    }
}