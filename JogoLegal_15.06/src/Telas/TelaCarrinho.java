package Telas;

import Jogo.Jogo;
import Carrinho.Carrinho;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaCarrinho extends JFrame {

    public TelaCarrinho(String nome, SistemaLogin sistema,
                        Carrinho carrinho,
                        ArrayList<Jogo> bibliotecaJogos,
                        TelaUsuario telaUsuario) {
        setTitle("JOGO LEGAL - Carrinho");
        setSize(500, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("CARRINHO DE COMPRAS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(95, 20, 320, 32);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> listaJogos = new JList<>(modelo);
        listaJogos.setBackground(new Color(36, 38, 40));
        listaJogos.setForeground(Color.WHITE);
        listaJogos.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(listaJogos);
        scroll.setBounds(50, 68, 400, 200);
        scroll.setBorder(new LineBorder(new Color(60, 63, 65), 1));

        JLabel lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setForeground(new Color(0, 200, 100));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 17));
        lblTotal.setBounds(50, 278, 300, 26);

        JButton btnRemover = new JButton("REMOVER SELECIONADO");
        btnRemover.setBounds(50, 318, 200, 38);
        btnRemover.setBackground(new Color(180, 40, 40));
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setFocusPainted(false);

        JButton btnLimpar = new JButton("LIMPAR TUDO");
        btnLimpar.setBounds(260, 318, 190, 38);
        btnLimpar.setBackground(new Color(90, 40, 40));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setFocusPainted(false);

        JButton btnFinalizar = new JButton("FINALIZAR COMPRA  →");
        btnFinalizar.setBounds(50, 372, 400, 50);
        btnFinalizar.setBackground(new Color(0, 160, 80));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 15));
        btnFinalizar.setFocusPainted(false);

        JButton btnFechar = new JButton("Continuar comprando");
        btnFechar.setBounds(150, 436, 200, 28);
        btnFechar.setBackground(new Color(24, 26, 27));
        btnFechar.setForeground(new Color(100, 160, 255));
        btnFechar.setBorderPainted(false);
        btnFechar.setFocusPainted(false);
        btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        Runnable atualizarTotal = () -> {
            lblTotal.setText(String.format("Total: R$ %.2f", carrinho.calcularTotal()));
        };

        for (Jogo j : carrinho.getJogos())
            modelo.addElement(String.format("%s — R$ %.2f", j.getNome(), j.getPreco()));
        atualizarTotal.run();

        btnFinalizar.addActionListener(e -> {
            if (carrinho.getJogos().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seu carrinho está vazio.");
                return;
            }
            dispose();
            new TelaPagamento(nome, sistema, carrinho, bibliotecaJogos, telaUsuario);
        });

        btnFechar.addActionListener(e -> dispose());

        btnRemover.addActionListener(e -> {
            int idx = listaJogos.getSelectedIndex();
            if (idx >= 0) {
                Jogo jogo = carrinho.getJogos().get(idx);
                carrinho.removerJogo(jogo);
                modelo.remove(idx);
                atualizarTotal.run();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogo para remover.");
            }
        });

        btnLimpar.addActionListener(e -> {
            carrinho.getJogos().clear();
            modelo.clear();
            atualizarTotal.run();
        });

        painel.add(titulo); painel.add(scroll); painel.add(lblTotal);
        painel.add(btnRemover); painel.add(btnLimpar);
        painel.add(btnFinalizar); painel.add(btnFechar);
        add(painel);
        setVisible(true);
    }
}
