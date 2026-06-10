package Telas;

import java.awt.*;
import javax.swing.*;
import interfacejogolegal.SistemaLogin;

public class TelaAdministrador extends JFrame {

    public TelaAdministrador(String nomeAdmin, SistemaLogin sistema) {
        setTitle("JOGO LEGAL - Painel Administrador");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("PAINEL ADMINISTRADOR");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(95, 20, 320, 35);

        JLabel subtitulo = new JLabel("Olá, " + nomeAdmin + "  —  Gerencie o sistema");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(100, 55, 320, 20);

        JButton btnRemoverUsuario = new JButton("REMOVER USUÁRIO");
        estilizar(btnRemoverUsuario, new Color(180, 40, 40));
        btnRemoverUsuario.setBounds(100, 100, 300, 45);

        JButton btnAdicionarJogo = new JButton("ADICIONAR JOGO");
        estilizar(btnAdicionarJogo, new Color(0, 120, 215));
        btnAdicionarJogo.setBounds(100, 160, 300, 45);

        JButton btnRemoverJogo = new JButton("REMOVER JOGO");
        estilizar(btnRemoverJogo, new Color(180, 40, 40));
        btnRemoverJogo.setBounds(100, 220, 300, 45);

        JButton btnRelatorio = new JButton("VISUALIZAR RELATÓRIOS");
        estilizar(btnRelatorio, new Color(0, 120, 215));
        btnRelatorio.setBounds(100, 280, 300, 45);

        JButton btnLogout = new JButton("LOGOUT");
        estilizar(btnLogout, new Color(60, 63, 65));
        btnLogout.setBounds(175, 380, 150, 40);

        btnLogout.addActionListener(_ -> { dispose(); new TelaLogin(); });

        painel.add(titulo); painel.add(subtitulo);
        painel.add(btnRemoverUsuario); painel.add(btnAdicionarJogo);
        painel.add(btnRemoverJogo);    painel.add(btnRelatorio);
        painel.add(btnLogout);
        add(painel);
        setVisible(true);
    }

    private void estilizar(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
    }
}
