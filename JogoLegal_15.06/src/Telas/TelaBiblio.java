package Telas;

import Jogo.Jogo;
import interfacejogolegal.SistemaLogin;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaBiblio extends JFrame {

    public TelaBiblio(String nome, SistemaLogin sistema, ArrayList<Jogo> listaJogos) {

        setTitle("JOGO LEGAL - Biblioteca");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("MINHA BIBLIOTECA");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(30, 20, 400, 40);

        JLabel subtitulo = new JLabel("Jogos adquiridos por: " + nome);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(30, 60, 400, 25);

        JLabel lblSeus = new JLabel("SEUS JOGOS");
        lblSeus.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeus.setForeground(new Color(0, 255, 150));
        lblSeus.setBounds(30, 100, 250, 25);

        JPanel painelJogos = new JPanel();
        painelJogos.setBackground(new Color(24, 26, 27));
        painelJogos.setLayout(new BoxLayout(painelJogos, BoxLayout.Y_AXIS));

        if (listaJogos == null || listaJogos.isEmpty()) {
            JLabel vazio = new JLabel("Você ainda não possui nenhum jogo comprado.");
            vazio.setForeground(Color.LIGHT_GRAY);
            vazio.setFont(new Font("Arial", Font.ITALIC, 14));
            vazio.setAlignmentX(Component.LEFT_ALIGNMENT);
            vazio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            painelJogos.add(vazio);
        } else {
            for (Jogo jogo : listaJogos) {
                JPanel card = new JPanel(null);
                card.setBackground(new Color(36, 38, 40));
                card.setBorder(new LineBorder(new Color(60, 63, 65), 1));
                card.setMaximumSize(new Dimension(640, 70));
                card.setPreferredSize(new Dimension(640, 70));
                card.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel lblNome = new JLabel(" " + jogo.getNome());
                lblNome.setForeground(Color.WHITE);
                lblNome.setFont(new Font("Arial", Font.BOLD, 14));
                lblNome.setBounds(12, 8, 600, 20);

                JLabel lblInfo = new JLabel(jogo.getGenero() + "  •  Status: " + jogo.getStatus()
                        + "  •  R$ " + String.format("%.2f", jogo.getPreco()));
                lblInfo.setForeground(new Color(160, 160, 160));
                lblInfo.setFont(new Font("Arial", Font.PLAIN, 11));
                lblInfo.setBounds(12, 32, 600, 16);

                JLabel lblDesc = new JLabel(jogo.getDescricao());
                lblDesc.setForeground(new Color(120, 120, 120));
                lblDesc.setFont(new Font("Arial", Font.ITALIC, 10));
                lblDesc.setBounds(12, 50, 600, 14);

                card.add(lblNome);
                card.add(lblInfo);
                card.add(lblDesc);

                painelJogos.add(card);
                painelJogos.add(Box.createRigidArea(new Dimension(0, 6)));
            }
        }

        JScrollPane scroll = new JScrollPane(painelJogos);
        scroll.setBounds(30, 135, 640, 300);
        scroll.setBackground(new Color(24, 26, 27));
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setBounds(510, 445, 160, 38);
        btnVoltar.setBackground(new Color(30, 100, 200));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 13));
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> dispose());

        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(lblSeus);
        painel.add(scroll);
        painel.add(btnVoltar);

        add(painel);
    }
}
