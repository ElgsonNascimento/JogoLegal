package Telas;

import Telas.TelaLogin;

import java.awt.*;
import javax.swing.*;

public class TelaCriadorJogo extends JFrame {

    public TelaCriadorJogo(String nomeCriador) {
        setTitle("JOGO LEGAL - Painel do Criador");
        setSize(500, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("CRIADOR: " + nomeCriador.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(80, 20, 360, 35);

        JLabel subtitulo = new JLabel("Gerencie seus jogos");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(175, 55, 200, 20);

        JButton btnPublicar = new JButton("PUBLICAR JOGO");
        estilizar(btnPublicar, new Color(0, 160, 80));
        btnPublicar.setBounds(100, 100, 300, 45);

        JButton btnEditar = new JButton("EDITAR JOGO");
        estilizar(btnEditar, new Color(0, 120, 215));
        btnEditar.setBounds(100, 160, 300, 45);

        JButton btnRemover = new JButton("REMOVER JOGO");
        estilizar(btnRemover, new Color(180, 40, 40));
        btnRemover.setBounds(100, 220, 300, 45);

        JButton btnVendas = new JButton("VER VENDAS");
        estilizar(btnVendas, new Color(0, 120, 215));
        btnVendas.setBounds(100, 280, 300, 45);

        JButton btnLogout = new JButton("LOGOUT");
        estilizar(btnLogout, new Color(60, 63, 65));
        btnLogout.setBounds(175, 380, 150, 40);

        btnLogout.addActionListener(_ -> { dispose(); new TelaLogin(); });

        painel.add(titulo); painel.add(subtitulo);
        painel.add(btnPublicar); painel.add(btnEditar);
        painel.add(btnRemover);  painel.add(btnVendas);
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
