package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;
import Telas.TelaCarrinho;

public class TelaPagamento extends JFrame {

    public TelaPagamento(String nome, SistemaLogin sistema) {
        setTitle("JOGO LEGAL - Pagamento");
        setSize(500, 460);
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

        JLabel lblTotal = new JLabel("Total a pagar: R$ --");
        lblTotal.setForeground(new Color(0, 200, 100));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 17));
        lblTotal.setBounds(0, 58, 500, 24);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblMetodo = new JLabel("Método de pagamento:");
        lblMetodo.setForeground(Color.WHITE);
        lblMetodo.setBounds(80, 100, 200, 20);

        String[] metodos = {"Cartão de Crédito", "Cartão de Débito", "PIX", "Boleto"};
        JComboBox<String> comboMetodo = new JComboBox<>(metodos);
        comboMetodo.setBounds(80, 124, 340, 35);
        comboMetodo.setBackground(new Color(36, 38, 40));
        comboMetodo.setForeground(Color.WHITE);

        JLabel lblNumero = new JLabel("Número do cartão:");
        lblNumero.setForeground(Color.WHITE);
        lblNumero.setBounds(80, 172, 200, 20);

        JTextField campoNumero = new JTextField();
        campoNumero.setBounds(80, 196, 340, 35);
        campoNumero.setBackground(new Color(36, 38, 40));
        campoNumero.setForeground(Color.WHITE);
        campoNumero.setCaretColor(Color.WHITE);
        campoNumero.setBorder(new LineBorder(Color.GRAY, 1));

        comboMetodo.addActionListener(_ -> {
            String m = (String) comboMetodo.getSelectedItem();
            boolean cartao = m != null && m.startsWith("Cartão");
            lblNumero.setVisible(cartao);
            campoNumero.setVisible(cartao);
        });

        JButton btnProcessar = new JButton("PROCESSAR PAGAMENTO");
        btnProcessar.setBounds(80, 252, 340, 50);
        btnProcessar.setBackground(new Color(0, 160, 80));
        btnProcessar.setForeground(Color.WHITE);
        btnProcessar.setFont(new Font("Arial", Font.BOLD, 14));
        btnProcessar.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar e voltar ao carrinho");
        btnCancelar.setBounds(130, 318, 240, 28);
        btnCancelar.setBackground(new Color(24, 26, 27));
        btnCancelar.setForeground(new Color(180, 80, 80));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnCancelar.addActionListener(_ -> { dispose(); new TelaCarrinho(nome, sistema); });

        painel.add(titulo);    painel.add(lblTotal);
        painel.add(lblMetodo); painel.add(comboMetodo);
        painel.add(lblNumero); painel.add(campoNumero);
        painel.add(btnProcessar); painel.add(btnCancelar);

        add(painel);
        setVisible(true);
    }
}
