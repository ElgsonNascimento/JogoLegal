package Telas;

import Jogo.Jogo;
import interfacejogolegal.SistemaLogin;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class TelaBiblio extends JFrame {

    // Adicionado o parâmetro ArrayList<Jogo> listaJogos no construtor
    public TelaBiblio(String nome, SistemaLogin sistema, ArrayList<Jogo> listaJogos) {

        setTitle("JOGO LEGAL - Biblioteca");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        // Título
        JLabel titulo = new JLabel("MINHA BIBLIOTECA");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(30, 20, 400, 40);

        // Subtítulo usando o nome do usuário logado
        JLabel subtitulo = new JLabel("Jogos adquiridos por: " + nome);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(30, 60, 400, 25);

        // Linha verde
        JLabel catalogo = new JLabel("SEUS JOGOS");
        catalogo.setFont(new Font("Arial", Font.BOLD, 24));
        catalogo.setForeground(new Color(0, 255, 150));
        catalogo.setBounds(30, 110, 250, 30);

        // Lista de jogos (TextArea)
        JTextArea areaJogos = new JTextArea();
        areaJogos.setEditable(false);
        areaJogos.setBackground(new Color(35, 38, 40));
        areaJogos.setForeground(Color.WHITE);
        areaJogos.setFont(new Font("Consolas", Font.PLAIN, 16));

        // --- LÓGICA DINÂMICA DA BIBLIOTECA ---
        StringBuilder sb = new StringBuilder();
        if (listaJogos == null || listaJogos.isEmpty()) {
            sb.append("Você ainda não possui nenhum jogo comprado.");
        } else {
            for (Jogo jogo : listaJogos) {
                sb.append(String.format("🎮 %s [%s] - Status: %s\n",
                        jogo.getNome(), jogo.getGenero(), jogo.getStatus()));
                sb.append(String.format("   Descrição: %s\n", jogo.getDescricao()));
//                sb.append("--------------------------------------------------\n");
            }
        }
        areaJogos.setText(sb.toString());
        // -------------------------------------

        JScrollPane scroll = new JScrollPane(areaJogos);
        scroll.setBounds(30, 160, 620, 220);

        // Botão voltar
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setBounds(500, 400, 150, 40);
        btnVoltar.setBackground(new Color(30, 100, 200));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);

        btnVoltar.addActionListener(_ -> dispose());

        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(catalogo);
        painel.add(scroll);
        painel.add(btnVoltar);

        add(painel);
    }
}