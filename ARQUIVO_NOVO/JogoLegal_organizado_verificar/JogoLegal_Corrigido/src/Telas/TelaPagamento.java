package Telas;

import Jogo.Jogo;
import Pagamento.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

/**
 * Tela de Pagamento.
 * Métodos: PIX, Cartão (com salvar/carregar do TXT), Saldo.
 * Fluxo: TelaCarrinho → TelaPagamento → processa → resultado → limpa carrinho.
 */
public class TelaPagamento extends JFrame {

    private static final AtomicInteger contadorId = new AtomicInteger(1);

    public TelaPagamento(String nomeUsuario, SistemaLogin sistema, ArrayList<Jogo> carrinhoJogos, ArrayList<Jogo> bibliotecaJogos, TelaUsuario telaUsuario) {
        setTitle("JOGO LEGAL - Pagamento");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        // ── Cabeçalho ──────────────────────────────────────────────────────────
        JLabel titulo = new JLabel("PAGAMENTO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(0, 20, 500, 35);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        double total = 0.0;
        if (carrinhoJogos != null) for (Jogo j : carrinhoJogos) total += j.getPreco();
        final double totalFinal = total;

        JLabel lblTotal = new JLabel(String.format("Total a pagar: R$ %.2f", totalFinal));
        lblTotal.setForeground(new Color(0, 200, 100));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 17));
        lblTotal.setBounds(0, 58, 500, 24);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);

        // ── Seleção de método ──────────────────────────────────────────────────
        JLabel lblMetodo = new JLabel("Método de pagamento:");
        lblMetodo.setForeground(Color.WHITE);
        lblMetodo.setBounds(70, 98, 220, 20);

        String[] metodos = {"PIX", "Cartão de Crédito/Débito", "Saldo da Conta"};
        JComboBox<String> comboMetodo = new JComboBox<>(metodos);
        comboMetodo.setBounds(70, 122, 360, 35);
        comboMetodo.setBackground(new Color(36, 38, 40));
        comboMetodo.setForeground(Color.WHITE);
        comboMetodo.setFont(new Font("Arial", Font.PLAIN, 13));

        // ── Painel dinâmico por método ─────────────────────────────────────────
        JPanel painelForm = new JPanel(null);
        painelForm.setBackground(new Color(30, 32, 34));
        painelForm.setBorder(new LineBorder(new Color(55, 58, 60), 1));
        painelForm.setBounds(70, 172, 360, 230);

        // ─────────── FORMULÁRIO PIX ───────────────────────────────────────────
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

        // ─────────── FORMULÁRIO CARTÃO ────────────────────────────────────────
        JPanel formCartao = new JPanel(null);
        formCartao.setBackground(new Color(30, 32, 34));
        formCartao.setBounds(0, 0, 360, 230);
        formCartao.setVisible(false);

        // Combo de cartões salvos
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

        // Campos manuais
        JTextField txtNumCartao = criarCampo(formCartao, "Número do cartão:", 14, 60);
        JTextField txtTitular   = criarCampo(formCartao, "Nome do titular:", 14, 112);
        JTextField txtCVV       = criarCampoSmall(formCartao, "CVV:", 14, 164, 75);
        JTextField txtMesVenc   = criarCampoSmall(formCartao, "Mês:", 105, 164, 55);
        JTextField txtAnoVenc   = criarCampoSmall(formCartao, "Ano:", 176, 164, 70);

        // Checkbox salvar cartão
        JCheckBox chkSalvar = new JCheckBox("Salvar cartão para próximas compras");
        chkSalvar.setBounds(14, 202, 310, 22);
        chkSalvar.setBackground(new Color(30, 32, 34));
        chkSalvar.setForeground(new Color(180, 180, 180));
        chkSalvar.setFont(new Font("Arial", Font.PLAIN, 11));
        chkSalvar.setFocusPainted(false);
        chkSalvar.setSelected(true);

        // Ao selecionar cartão salvo, preenche os campos automaticamente
        comboCartoesSalvos.addActionListener(_ -> {
            int idx = comboCartoesSalvos.getSelectedIndex();
            if (idx > 0 && idx - 1 < cartoesSalvos.size()) {
                GerenciadorCartoes.CartaoSalvo cs = cartoesSalvos.get(idx - 1);
                txtNumCartao.setText(cs.numero);
                txtTitular.setText(cs.titular);
                txtMesVenc.setText(String.valueOf(cs.mes));
                txtAnoVenc.setText(String.valueOf(cs.ano));
                txtCVV.setText(""); // CVV nunca é salvo
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

        // ─────────── FORMULÁRIO SALDO ─────────────────────────────────────────
        JPanel formSaldo = new JPanel(null);
        formSaldo.setBackground(new Color(30, 32, 34));
        formSaldo.setBounds(0, 0, 360, 230);
        formSaldo.setVisible(false);

        JLabel lblSaldoTit = new JLabel("Informe seu saldo disponível (R$):");
        lblSaldoTit.setForeground(Color.LIGHT_GRAY);
        lblSaldoTit.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSaldoTit.setBounds(14, 20, 280, 18);

        JTextField txtSaldo = new JTextField("100,00");
        txtSaldo.setBounds(14, 44, 140, 30);
        txtSaldo.setBackground(new Color(42, 44, 46));
        txtSaldo.setForeground(Color.WHITE);
        txtSaldo.setCaretColor(Color.WHITE);
        txtSaldo.setBorder(new LineBorder(Color.GRAY, 1));
        txtSaldo.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblSaldoNota = new JLabel(String.format(
            "<html>Total da compra: <b style='color:#00c864'>R$ %.2f</b></html>", totalFinal));
        lblSaldoNota.setForeground(new Color(160, 160, 160));
        lblSaldoNota.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSaldoNota.setBounds(14, 84, 300, 20);

        formSaldo.add(lblSaldoTit);
        formSaldo.add(txtSaldo);
        formSaldo.add(lblSaldoNota);

        // ── Montagem do painel de formulários ──────────────────────────────────
        painelForm.add(formPix);
        painelForm.add(formCartao);
        painelForm.add(formSaldo);

        // ── Troca de formulário ao mudar método ────────────────────────────────
        comboMetodo.addActionListener(_ -> {
            String sel = (String) comboMetodo.getSelectedItem();
            formPix.setVisible("PIX".equals(sel));
            formCartao.setVisible("Cartão de Crédito/Débito".equals(sel));
            formSaldo.setVisible("Saldo da Conta".equals(sel));
        });

        // ── Botões ─────────────────────────────────────────────────────────────
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

        btnCancelar.addActionListener(_ -> { dispose(); new TelaCarrinho(nomeUsuario, sistema, carrinhoJogos, bibliotecaJogos, telaUsuario); });

        // ── Ação principal: processar ──────────────────────────────────────────
        btnProcessar.addActionListener(_ -> {
            if (carrinhoJogos == null || carrinhoJogos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Seu carrinho está vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String metodo = (String) comboMetodo.getSelectedItem();
            int pid = contadorId.getAndIncrement();

            Pagamento pagamento = null;
            String erroValidacao = null;

            // Dados do cartão usados também para salvar depois
            final String[] dadosCartao = new String[5]; // num, titular, cvv, mes, ano

            if ("PIX".equals(metodo)) {
                pagamento = new PagamentoPix(pid, totalFinal);

            } else if ("Cartão de Crédito/Débito".equals(metodo)) {
                String num     = txtNumCartao.getText().trim().replaceAll("[\\s-]", "");
                String titular = txtTitular.getText().trim();
                String cvv     = txtCVV.getText().trim();
                String mesStr  = txtMesVenc.getText().trim();
                String anoStr  = txtAnoVenc.getText().trim();

                if (num.isEmpty() || titular.isEmpty() || cvv.isEmpty()
                        || mesStr.isEmpty() || anoStr.isEmpty()) {
                    erroValidacao = "Preencha todos os dados do cartão.";
                } else {
                    try {
                        int mes = Integer.parseInt(mesStr);
                        int ano = Integer.parseInt(anoStr);
                        dadosCartao[0] = num;
                        dadosCartao[1] = titular;
                        dadosCartao[2] = cvv;
                        dadosCartao[3] = mesStr;
                        dadosCartao[4] = anoStr;
                        pagamento = new PagamentoCartao(pid, totalFinal, num, titular, cvv, mes, ano);
                    } catch (NumberFormatException e) {
                        erroValidacao = "Mês e ano de vencimento devem ser numéricos.";
                    }
                }

            } else {
                String saldoStr = txtSaldo.getText().trim().replace(",", ".");
                try {
                    double saldoInformado = Double.parseDouble(saldoStr);
                    PagamentoSaldo ps = new PagamentoSaldo(pid, totalFinal);
                    ps.adicionarSaldo(saldoInformado);
                    pagamento = ps;
                } catch (NumberFormatException e) {
                    erroValidacao = "Valor de saldo inválido.";
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

                    // Salva cartão no TXT se aprovado e checkbox marcado
                    if ("Aprovado".equals(pagFinal.obterStatus()) && salvarCartao
                            && dadosCartao[0] != null) {
                        GerenciadorCartoes.salvar(
                            nomeUsuario,
                            dadosCartao[0],             // numero
                            dadosCartao[1],             // titular
                            Integer.parseInt(dadosCartao[3]),  // mes
                            Integer.parseInt(dadosCartao[4])   // ano
                        );
                    }

                    exibirResultado(pagFinal, carrinhoJogos, bibliotecaJogos, telaUsuario, nomeUsuario, sistema);
                });
            }).start();
        });

        // ── Montagem final ─────────────────────────────────────────────────────
        painel.add(titulo);       painel.add(lblTotal);
        painel.add(lblMetodo);    painel.add(comboMetodo);
        painel.add(painelForm);
        painel.add(btnProcessar); painel.add(btnCancelar);

        add(painel);
        setVisible(true);
    }

    // ── Resultado ─────────────────────────────────────────────────────────────
    private void exibirResultado(Pagamento pag, ArrayList<Jogo> carrinho,
                                  ArrayList<Jogo> biblioteca, TelaUsuario telaUsuario,
                                  String nome, SistemaLogin sistema) {
        boolean aprovado = "Aprovado".equals(pag.obterStatus());
        String icone  = aprovado ? "✅" : "❌";
        String titulo = aprovado ? "Pagamento Aprovado!" : "Pagamento Recusado";

        StringBuilder sb = new StringBuilder();
        sb.append(icone).append("  ").append(titulo).append("\n\n");
        sb.append(String.format("Tipo:        %s%n", pag.getTipoPagamento()));
        sb.append(String.format("Valor:       R$ %.2f%n", pag.getValor()));
        sb.append(String.format("Status:      %s%n", pag.obterStatus()));

        if (aprovado && pag.obterComprovante() != null)
            sb.append(String.format("Comprovante: %s%n", pag.obterComprovante()));

        if (!aprovado) {
            if (pag instanceof PagamentoCartao pc && !pc.isCartaoValido())
                sb.append("\nMotivo: número de cartão inválido.");
            else if (pag instanceof PagamentoSaldo ps)
                sb.append(String.format("%nSaldo disponível: R$ %.2f%nMotivo: saldo insuficiente.", ps.obterSaldo()));
        }

        if (aprovado && pag instanceof PagamentoCartao)
            sb.append("\n\n💾  Cartão salvo para futuras compras.");

        if (aprovado && carrinho != null && !carrinho.isEmpty())
            sb.append("\n📚  Jogos adicionados à sua biblioteca!");

        JOptionPane.showMessageDialog(this, sb.toString(), titulo,
            aprovado ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

        if (aprovado) {
            // Salva jogos comprados no arquivo do usuário
            if (carrinho != null && !carrinho.isEmpty()) {
                GerenciadorBiblioteca.salvar(nome, carrinho);
            }
            // Passa cópia dos jogos comprados para a TelaUsuario antes de limpar
            if (carrinho != null && telaUsuario != null) {
                telaUsuario.onCompraConcluida(new ArrayList<>(carrinho));
                carrinho.clear();
            }
            dispose();
        }
    }

    // ── Helpers campos ────────────────────────────────────────────────────────
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
