package Telas;

import Jogo.Jogo;
import Pagamento.*;
import Carrinho.Carrinho;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaPagamento extends JFrame {

    private static final AtomicInteger contadorId = new AtomicInteger(1);

    public TelaPagamento(String nomeUsuario, SistemaLogin sistema, Carrinho carrinho, ArrayList<Jogo> bibliotecaJogos, TelaUsuario telaUsuario) {
        setTitle("JOGO LEGAL - Pagamento");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));


        JLabel titulo = new JLabel("PAGAMENTO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(0, 20, 500, 35);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        double total = carrinho.calcularTotal();
        final double totalFinal = total;

        JLabel lblTotal = new JLabel(String.format("Total a pagar: R$ %.2f", totalFinal));
        lblTotal.setForeground(new Color(0, 200, 100));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 17));
        lblTotal.setBounds(0, 58, 500, 24);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel lblMetodo = new JLabel("Método de pagamento:");
        lblMetodo.setForeground(Color.WHITE);
        lblMetodo.setBounds(70, 98, 220, 20);

        String[] metodos = {"PIX", "Cartão de Crédito/Débito", "Saldo da Conta"};
        JComboBox<String> comboMetodo = new JComboBox<>(metodos);
        comboMetodo.setBounds(70, 122, 360, 35);
        comboMetodo.setBackground(new Color(36, 38, 40));
        comboMetodo.setForeground(Color.WHITE);
        comboMetodo.setFont(new Font("Arial", Font.PLAIN, 13));


        JPanel painelForm = new JPanel(null);
        painelForm.setBackground(new Color(30, 32, 34));
        painelForm.setBorder(new LineBorder(new Color(55, 58, 60), 1));
        painelForm.setBounds(70, 172, 360, 230);


        JPanel formPix = new JPanel(null);
        formPix.setBackground(new Color(30, 32, 34));
        formPix.setBounds(0, 0, 360, 230);

        JLabel lblChaveTit = new JLabel("Chave PIX (recebedor):");
        lblChaveTit.setForeground(Color.LIGHT_GRAY);
        lblChaveTit.setFont(new Font("Arial", Font.PLAIN, 12));
        lblChaveTit.setBounds(14, 20, 300, 18);

        JLabel lblChaveVal = new JLabel("1629461812000180");
        lblChaveVal.setForeground(new Color(0, 200, 100));
        lblChaveVal.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblChaveVal.setBounds(14, 42, 320, 20);

        JLabel lblPixInfo = new JLabel("<html>Após clicar em <b>Processar</b>, o pagamento<br>"
                + "será simulado automaticamente.</html>");
        lblPixInfo.setForeground(new Color(160, 160, 160));
        lblPixInfo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPixInfo.setBounds(14, 70, 320, 40);

        formPix.add(lblChaveTit); formPix.add(lblChaveVal); formPix.add(lblPixInfo);


        JPanel formCartao = new JPanel(null);
        formCartao.setBackground(new Color(30, 32, 34));
        formCartao.setBounds(0, 0, 360, 230);
        formCartao.setVisible(false);


        JLabel lblSalvos = new JLabel("Cartões salvos:");
        lblSalvos.setForeground(Color.LIGHT_GRAY);
        lblSalvos.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalvos.setBounds(14, 8, 160, 16);

        List<GerenciadorCartoes.CartaoSalvo> cartoesSalvos =
                GerenciadorCartoes.carregarDoUsuario(nomeUsuario);

        DefaultComboBoxModel<String> modeloSalvos = new DefaultComboBoxModel<>();
        modeloSalvos.addElement("— Novo cartão —");
        for (GerenciadorCartoes.CartaoSalvo cs : cartoesSalvos) {
            modeloSalvos.addElement(cs.exibir());
        }

        JComboBox<String> comboCartoesSalvos = new JComboBox<>(modeloSalvos);
        comboCartoesSalvos.setBounds(14, 26, 330, 28);
        comboCartoesSalvos.setBackground(new Color(42, 44, 46));
        comboCartoesSalvos.setForeground(Color.WHITE);
        comboCartoesSalvos.setFont(new Font("Arial", Font.PLAIN, 11));


        JTextField txtNumCartao = criarCampo(formCartao, "Número do cartão:", 14, 60);
        JTextField txtTitular   = criarCampo(formCartao, "Nome do titular:", 14, 112);
        JTextField txtCVV       = criarCampoSmall(formCartao, "CVV:", 14, 164, 75);
        JTextField txtMesVenc   = criarCampoSmall(formCartao, "Mês:", 105, 164, 55);
        JTextField txtAnoVenc   = criarCampoSmall(formCartao, "Ano:", 176, 164, 70);


        JCheckBox chkSalvar = new JCheckBox("Salvar cartão para próximas compras");
        chkSalvar.setBounds(14, 202, 310, 22);
        chkSalvar.setBackground(new Color(30, 32, 34));
        chkSalvar.setForeground(new Color(180, 180, 180));
        chkSalvar.setFont(new Font("Arial", Font.PLAIN, 11));
        chkSalvar.setFocusPainted(false);
        chkSalvar.setSelected(true);


        comboCartoesSalvos.addActionListener(e -> {
            int idx = comboCartoesSalvos.getSelectedIndex();
            if (idx > 0 && idx - 1 < cartoesSalvos.size()) {
                GerenciadorCartoes.CartaoSalvo cs = cartoesSalvos.get(idx - 1);
                txtNumCartao.setText("****-****-****-" + cs.ultimos4);
                txtTitular.setText(cs.titular);
                txtMesVenc.setText(String.valueOf(cs.mes));
                txtAnoVenc.setText(String.valueOf(cs.ano));
                txtCVV.setText("");
                chkSalvar.setSelected(false);
            } else {
                txtNumCartao.setText("");
                txtTitular.setText("");
                txtCVV.setText("");
                txtMesVenc.setText("");
                txtAnoVenc.setText("");
                chkSalvar.setSelected(true);
            }
        });

        formCartao.add(lblSalvos);
        formCartao.add(comboCartoesSalvos);
        formCartao.add(chkSalvar);


        JPanel formSaldo = new JPanel(null);
        formSaldo.setBackground(new Color(30, 32, 34));
        formSaldo.setBounds(0, 0, 360, 230);
        formSaldo.setVisible(false);

        double saldoAtual = telaUsuario.getSaldo();

        JLabel lblSaldoTit = new JLabel("Saldo disponível na conta:");
        lblSaldoTit.setForeground(Color.LIGHT_GRAY);
        lblSaldoTit.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSaldoTit.setBounds(14, 20, 280, 18);

        JLabel lblSaldoValor = new JLabel(String.format("R$ %.2f", saldoAtual));
        lblSaldoValor.setForeground(saldoAtual >= totalFinal ? new Color(0, 200, 100) : new Color(220, 80, 80));
        lblSaldoValor.setFont(new Font("Arial", Font.BOLD, 20));
        lblSaldoValor.setBounds(14, 44, 200, 30);

        JLabel lblSaldoNota = new JLabel(String.format(
            "<html>Total da compra: <b style='color:#00c864'>R$ %.2f</b></html>", totalFinal));
        lblSaldoNota.setForeground(new Color(160, 160, 160));
        lblSaldoNota.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSaldoNota.setBounds(14, 84, 300, 20);

        JLabel lblSaldoAposCompra = new JLabel(String.format(
            "<html>Saldo após compra: <b>R$ %.2f</b></html>", saldoAtual - totalFinal));
        lblSaldoAposCompra.setForeground(new Color(160, 160, 160));
        lblSaldoAposCompra.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSaldoAposCompra.setBounds(14, 106, 300, 20);
        lblSaldoAposCompra.setVisible(saldoAtual >= totalFinal);

        JLabel lblSaldoInsuficiente = new JLabel("<html><b style='color:#dc5050'>Saldo insuficiente para esta compra.</b><br>"
            + "Adicione saldo na tela principal.</html>");
        lblSaldoInsuficiente.setForeground(new Color(220, 80, 80));
        lblSaldoInsuficiente.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSaldoInsuficiente.setBounds(14, 106, 320, 36);
        lblSaldoInsuficiente.setVisible(saldoAtual < totalFinal);

        formSaldo.add(lblSaldoTit);
        formSaldo.add(lblSaldoValor);
        formSaldo.add(lblSaldoNota);
        formSaldo.add(lblSaldoAposCompra);
        formSaldo.add(lblSaldoInsuficiente);


        painelForm.add(formPix);
        painelForm.add(formCartao);
        painelForm.add(formSaldo);


        comboMetodo.addActionListener(e -> {
            String sel = (String) comboMetodo.getSelectedItem();
            formPix.setVisible("PIX".equals(sel));
            formCartao.setVisible("Cartão de Crédito/Débito".equals(sel));
            formSaldo.setVisible("Saldo da Conta".equals(sel));
        });


        JButton btnProcessar = new JButton("PROCESSAR PAGAMENTO");
        btnProcessar.setBounds(70, 420, 360, 50);
        btnProcessar.setBackground(new Color(0, 160, 80));
        btnProcessar.setForeground(Color.WHITE);
        btnProcessar.setFont(new Font("Arial", Font.BOLD, 14));
        btnProcessar.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar e voltar ao carrinho");
        btnCancelar.setBounds(130, 484, 240, 28);
        btnCancelar.setBackground(new Color(24, 26, 27));
        btnCancelar.setForeground(new Color(180, 80, 80));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnCancelar.addActionListener(e -> { dispose(); new TelaCarrinho(nomeUsuario, sistema, carrinho, bibliotecaJogos, telaUsuario); });


        btnProcessar.addActionListener(e -> {
            if (carrinho.getJogos().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Seu carrinho está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String metodo = (String) comboMetodo.getSelectedItem();
            int pid = contadorId.getAndIncrement();

            Pagamento pagamento = null;
            String erroValidacao = null;


            final String[] dadosCartao = new String[5];

            if ("PIX".equals(metodo)) {
                pagamento = new PagamentoPix(pid, totalFinal);

            } else if ("Cartão de Crédito/Débito".equals(metodo)) {
                String numRaw  = txtNumCartao.getText().trim();
                String titular = txtTitular.getText().trim();
                String cvv     = txtCVV.getText().trim();
                String mesStr  = txtMesVenc.getText().trim();
                String anoStr  = txtAnoVenc.getText().trim();

                if (numRaw.isEmpty() || titular.isEmpty() || cvv.isEmpty()
                        || mesStr.isEmpty() || anoStr.isEmpty()) {
                    erroValidacao = "Preencha todos os dados do cartão.";
                } else {
                    try {
                        int mes = Integer.parseInt(mesStr);
                        int ano = Integer.parseInt(anoStr);
                        dadosCartao[1] = titular;
                        dadosCartao[2] = cvv;
                        dadosCartao[3] = mesStr;
                        dadosCartao[4] = anoStr;


                        int idxSalvo = comboCartoesSalvos.getSelectedIndex();
                        boolean usandoCartaoSalvo = idxSalvo > 0 && idxSalvo - 1 < cartoesSalvos.size();

                        if (usandoCartaoSalvo) {

                            dadosCartao[0] = numRaw;
                            pagamento = new PagamentoCartao(pid, totalFinal, numRaw, titular, cvv, mes, ano, true);
                        } else {

                            String num = numRaw.replaceAll("[\\s-]", "");
                            dadosCartao[0] = num;
                            pagamento = new PagamentoCartao(pid, totalFinal, num, titular, cvv, mes, ano);
                        }
                    } catch (NumberFormatException ex) {
                        erroValidacao = "Mês e ano de vencimento devem ser numéricos.";
                    }
                }

            } else {
                double saldoConta = telaUsuario.getSaldo();
                if (saldoConta < totalFinal) {
                    erroValidacao = String.format(
                        "Saldo insuficiente!\nSaldo disponível: R$ %.2f\nTotal da compra: R$ %.2f",
                        saldoConta, totalFinal);
                } else {
                    PagamentoSaldo ps = new PagamentoSaldo(pid, totalFinal);
                    ps.adicionarSaldo(saldoConta);
                    pagamento = ps;
                }
            }

            if (erroValidacao != null) {
                JOptionPane.showMessageDialog(this, erroValidacao, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            btnProcessar.setEnabled(false);
            btnProcessar.setText("Processando...");

            final Pagamento pagFinal = pagamento;
            final boolean salvarCartao = chkSalvar.isSelected() && "Cartão de Crédito/Débito".equals(metodo);

            new Thread(() -> {
                pagFinal.processarPagamento();

                SwingUtilities.invokeLater(() -> {
                    btnProcessar.setEnabled(true);
                    btnProcessar.setText("PROCESSAR PAGAMENTO");


                    if ("Aprovado".equals(pagFinal.obterStatus()) && salvarCartao
                            && dadosCartao[0] != null) {
                        GerenciadorCartoes.salvar(
                            nomeUsuario,
                            dadosCartao[0],
                            dadosCartao[1],
                            Integer.parseInt(dadosCartao[3]),
                            Integer.parseInt(dadosCartao[4])
                        );
                    }


                    if ("Aprovado".equals(pagFinal.obterStatus()) && pagFinal instanceof PagamentoSaldo) {
                        double novoSaldo = telaUsuario.getSaldo() - pagFinal.getValor();
                        telaUsuario.setSaldo(novoSaldo);
                    }

                    exibirResultado(pagFinal, carrinho, bibliotecaJogos, telaUsuario, nomeUsuario, sistema);
                });
            }).start();
        });


        painel.add(titulo);       painel.add(lblTotal);
        painel.add(lblMetodo);    painel.add(comboMetodo);
        painel.add(painelForm);
        painel.add(btnProcessar); painel.add(btnCancelar);

        add(painel);
        setVisible(true);
    }


    private void exibirResultado(Pagamento pag, Carrinho carrinho,
                                  ArrayList<Jogo> biblioteca, TelaUsuario telaUsuario,
                                  String nome, SistemaLogin sistema) {
        boolean aprovado = "Aprovado".equals(pag.obterStatus());
        String icone  = aprovado ? "" : "";
        String titulo = aprovado ? "Pagamento Aprovado!" : "Pagamento Recusado";

        ArrayList<Jogo> jogosCarrinho = carrinho.getJogos();

        StringBuilder sb = new StringBuilder();
        sb.append(icone).append("  ").append(titulo).append("\n\n");
        sb.append(String.format("Tipo:        %s%n", pag.getTipoPagamento()));
        sb.append(String.format("Valor:       R$ %.2f%n", pag.getValor()));
        sb.append(String.format("Status:      %s%n", pag.obterStatus()));

        if (aprovado && pag.obterComprovante() != null)
            sb.append(String.format("Comprovante: %s%n", pag.obterComprovante()));

        if (!aprovado) {
            if (pag instanceof PagamentoCartao) {
                    PagamentoCartao pc = (PagamentoCartao) pag;
                    if (!pc.isCartaoValido()) sb.append("\nMotivo: número de cartão inválido.");
                }
            else if (pag instanceof PagamentoSaldo) {
                    PagamentoSaldo ps = (PagamentoSaldo) pag;
                    sb.append(String.format("%nSaldo disponível: R$ %.2f%nMotivo: saldo insuficiente.", ps.obterSaldo()));
                }
        }

        if (aprovado && pag instanceof PagamentoCartao)
            sb.append("\n\n Cartão salvo para futuras compras.");

        if (aprovado && !jogosCarrinho.isEmpty())
            sb.append("\n  Jogos adicionados à sua biblioteca!");

        JOptionPane.showMessageDialog(this, sb.toString(), titulo,
            aprovado ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

        if (aprovado) {

            if (!jogosCarrinho.isEmpty()) {
                GerenciadorBiblioteca.salvar(nome, jogosCarrinho);
                GerenciadorVendas.registrarVendas(jogosCarrinho);
            }

            if (telaUsuario != null) {
                telaUsuario.onCompraConcluida(new ArrayList<>(jogosCarrinho));

                carrinho.finalizarCompra(telaUsuario.getUsuario());
            }
            dispose();
        }
    }


    private JTextField criarCampo(JPanel p, String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl.setBounds(x, y, 320, 16);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 18, 330, 28);
        tf.setBackground(new Color(42, 44, 46));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(new LineBorder(Color.GRAY, 1));
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        p.add(lbl); p.add(tf);
        return tf;
    }

    private JTextField criarCampoSmall(JPanel p, String label, int x, int y, int w) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl.setBounds(x, y, w + 30, 16);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 18, w, 28);
        tf.setBackground(new Color(42, 44, 46));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(new LineBorder(Color.GRAY, 1));
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        p.add(lbl); p.add(tf);
        return tf;
    }
}
