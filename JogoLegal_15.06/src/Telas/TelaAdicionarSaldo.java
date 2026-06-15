package Telas;

import Pagamento.*;
import interfacejogolegal.SistemaLogin;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaAdicionarSaldo extends JFrame {

    private static final AtomicInteger contadorId = new AtomicInteger(100);

    public TelaAdicionarSaldo(String nomeUsuario, SistemaLogin sistema, TelaUsuario telaUsuario) {
        setTitle("JOGO LEGAL - Adicionar Saldo");
        setSize(500, 570);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));


        JLabel titulo = new JLabel("ADICIONAR SALDO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 20, 500, 32);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSaldoAtual = new JLabel(
            String.format("Saldo atual: R$ %.2f", telaUsuario.getSaldo()));
        lblSaldoAtual.setForeground(new Color(160, 160, 160));
        lblSaldoAtual.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSaldoAtual.setBounds(0, 55, 500, 20);
        lblSaldoAtual.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel lblValorTit = new JLabel("Valor a adicionar (R$):");
        lblValorTit.setForeground(Color.WHITE);
        lblValorTit.setBounds(70, 90, 200, 20);

        JTextField txtValor = new JTextField("50,00");
        txtValor.setBounds(70, 113, 360, 36);
        txtValor.setBackground(new Color(36, 38, 40));
        txtValor.setForeground(new Color(0, 220, 110));
        txtValor.setCaretColor(Color.WHITE);
        txtValor.setBorder(new LineBorder(new Color(0, 140, 70), 1));
        txtValor.setFont(new Font("Arial", Font.BOLD, 16));


        JLabel lblMetodo = new JLabel("Método de pagamento:");
        lblMetodo.setForeground(Color.WHITE);
        lblMetodo.setBounds(70, 162, 220, 20);

        String[] metodos = {"PIX", "Cartão de Crédito/Débito"};
        JComboBox<String> comboMetodo = new JComboBox<>(metodos);
        comboMetodo.setBounds(70, 185, 360, 35);
        comboMetodo.setBackground(new Color(36, 38, 40));
        comboMetodo.setForeground(Color.WHITE);
        comboMetodo.setFont(new Font("Arial", Font.PLAIN, 13));


        JPanel painelForm = new JPanel(null);
        painelForm.setBackground(new Color(30, 32, 34));
        painelForm.setBorder(new LineBorder(new Color(55, 58, 60), 1));
        painelForm.setBounds(70, 232, 360, 218);


        JPanel formPix = new JPanel(null);
        formPix.setBackground(new Color(30, 32, 34));
        formPix.setBounds(0, 0, 360, 218);

        JLabel lblChaveTit = new JLabel("Chave PIX (recebedor):");
        lblChaveTit.setForeground(Color.LIGHT_GRAY);
        lblChaveTit.setFont(new Font("Arial", Font.PLAIN, 12));
        lblChaveTit.setBounds(14, 20, 300, 18);

        JLabel lblChaveVal = new JLabel("1629461812000180");
        lblChaveVal.setForeground(new Color(0, 200, 100));
        lblChaveVal.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblChaveVal.setBounds(14, 42, 320, 20);

        JLabel lblPixInfo = new JLabel(
            "<html>Após clicar em <b>Confirmar</b>, o pagamento<br>será simulado automaticamente.</html>");
        lblPixInfo.setForeground(new Color(160, 160, 160));
        lblPixInfo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPixInfo.setBounds(14, 72, 320, 40);

        formPix.add(lblChaveTit); formPix.add(lblChaveVal); formPix.add(lblPixInfo);


        JPanel formCartao = new JPanel(null);
        formCartao.setBackground(new Color(30, 32, 34));
        formCartao.setBounds(0, 0, 360, 218);
        formCartao.setVisible(false);

        JLabel lblSalvos = new JLabel("Cartões salvos:");
        lblSalvos.setForeground(Color.LIGHT_GRAY);
        lblSalvos.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalvos.setBounds(14, 8, 160, 16);

        List<GerenciadorCartoes.CartaoSalvo> cartoesSalvos =
            GerenciadorCartoes.carregarDoUsuario(nomeUsuario);

        DefaultComboBoxModel<String> modeloSalvos = new DefaultComboBoxModel<String>();
        modeloSalvos.addElement("— Novo cartão —");
        for (GerenciadorCartoes.CartaoSalvo cs : cartoesSalvos)
            modeloSalvos.addElement(cs.exibir());

        JComboBox<String> comboCartoesSalvos = new JComboBox<String>(modeloSalvos);
        comboCartoesSalvos.setBounds(14, 26, 330, 28);
        comboCartoesSalvos.setBackground(new Color(42, 44, 46));
        comboCartoesSalvos.setForeground(Color.WHITE);
        comboCartoesSalvos.setFont(new Font("Arial", Font.PLAIN, 11));

        JTextField txtNumCartao = criarCampo(formCartao, "Número do cartão:", 14, 60);
        JTextField txtTitular   = criarCampo(formCartao, "Nome do titular:",  14, 112);
        JTextField txtCVV       = criarCampoSmall(formCartao, "CVV:",  14,  164, 75);
        JTextField txtMes       = criarCampoSmall(formCartao, "Mês:", 105,  164, 55);
        JTextField txtAno       = criarCampoSmall(formCartao, "Ano:", 176,  164, 70);

        JCheckBox chkSalvar = new JCheckBox("Salvar cartão para próximas compras");
        chkSalvar.setBounds(14, 200, 320, 22);
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
                txtMes.setText(String.valueOf(cs.mes));
                txtAno.setText(String.valueOf(cs.ano));
                txtCVV.setText("");
                chkSalvar.setSelected(false);
            } else {
                txtNumCartao.setText(""); txtTitular.setText("");
                txtCVV.setText(""); txtMes.setText(""); txtAno.setText("");
                chkSalvar.setSelected(true);
            }
        });

        formCartao.add(lblSalvos);
        formCartao.add(comboCartoesSalvos);
        formCartao.add(chkSalvar);

        painelForm.add(formPix);
        painelForm.add(formCartao);

        comboMetodo.addActionListener(e -> {
            boolean isPix = "PIX".equals(comboMetodo.getSelectedItem());
            formPix.setVisible(isPix);
            formCartao.setVisible(!isPix);
        });


        JButton btnConfirmar = new JButton("CONFIRMAR PAGAMENTO");
        btnConfirmar.setBounds(70, 464, 360, 48);
        btnConfirmar.setBackground(new Color(0, 160, 80));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(185, 522, 130, 26);
        btnCancelar.setBackground(new Color(24, 26, 27));
        btnCancelar.setForeground(new Color(180, 80, 80));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dispose());


        btnConfirmar.addActionListener(e -> {
            double valorAdd;
            try {
                valorAdd = Double.parseDouble(txtValor.getText().trim().replace(",", "."));
                if (valorAdd <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe um valor válido maior que zero.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String metodo = (String) comboMetodo.getSelectedItem();
            int pid = contadorId.getAndIncrement();
            Pagamento pagamento = null;
            String erroValidacao = null;
            final String[] dadosCartao = new String[5];

            if ("PIX".equals(metodo)) {
                pagamento = new PagamentoPix(pid, valorAdd);
            } else {
                String numRaw  = txtNumCartao.getText().trim();
                String titular = txtTitular.getText().trim();
                String cvv     = txtCVV.getText().trim();
                String mesStr  = txtMes.getText().trim();
                String anoStr  = txtAno.getText().trim();

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
                            pagamento = new PagamentoCartao(pid, valorAdd, numRaw, titular, cvv, mes, ano, true);
                        } else {
                            String num = numRaw.replaceAll("[\\s-]", "");
                            dadosCartao[0] = num;
                            pagamento = new PagamentoCartao(pid, valorAdd, num, titular, cvv, mes, ano);
                        }
                    } catch (NumberFormatException ex) {
                        erroValidacao = "Mês e ano devem ser numéricos.";
                    }
                }
            }

            if (erroValidacao != null) {
                JOptionPane.showMessageDialog(this, erroValidacao, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            btnConfirmar.setEnabled(false);
            btnConfirmar.setText("Processando...");

            final Pagamento pagFinal = pagamento;
            final double valorFinal = valorAdd;
            final boolean salvarCartao = chkSalvar.isSelected()
                && "Cartão de Crédito/Débito".equals(metodo);

            new Thread(new Runnable() {
                public void run() {
                    pagFinal.processarPagamento();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            btnConfirmar.setEnabled(true);
                            btnConfirmar.setText("CONFIRMAR PAGAMENTO");

                            boolean aprovado = "Aprovado".equals(pagFinal.obterStatus());

                            if (aprovado && salvarCartao && dadosCartao[0] != null) {
                                GerenciadorCartoes.salvar(nomeUsuario, dadosCartao[0], dadosCartao[1],
                                    Integer.parseInt(dadosCartao[3]), Integer.parseInt(dadosCartao[4]));
                            }

                            if (aprovado) {
                                double novoSaldo = telaUsuario.getSaldo() + valorFinal;
                                telaUsuario.setSaldo(novoSaldo);
                                JOptionPane.showMessageDialog(TelaAdicionarSaldo.this,
                                    String.format(" R$ %.2f adicionados ao seu saldo!\n\nNovo saldo: R$ %.2f",
                                        valorFinal, novoSaldo),
                                    "Saldo Adicionado!", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                String motivo = "";
                                if (pagFinal instanceof PagamentoCartao) {
                                    PagamentoCartao pc = (PagamentoCartao) pagFinal;
                                    if (!pc.isCartaoValido()) motivo = "\nMotivo: número de cartão inválido.";
                                }
                                JOptionPane.showMessageDialog(TelaAdicionarSaldo.this,
                                    " Pagamento recusado." + motivo,
                                    "Falha", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                }
            }).start();
        });


        painel.add(titulo);       painel.add(lblSaldoAtual);
        painel.add(lblValorTit);  painel.add(txtValor);
        painel.add(lblMetodo);    painel.add(comboMetodo);
        painel.add(painelForm);
        painel.add(btnConfirmar); painel.add(btnCancelar);
        add(painel);
        setVisible(true);
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
