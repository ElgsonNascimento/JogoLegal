package Telas;

import Jogo.Jogo;
import interfacejogolegal.SistemaLogin;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class TelaUsuario extends JFrame {

    private static final String[][] CATALOGO = {
            {"Cyber Quest", "59.90", "RPG"},
            {"Dragon Arena", "39.99", "Ação"},
            {"Space Run", "29.95", "Corrida"}
    };

    private String nome;
    private SistemaLogin sistema;
    private JPanel painelJogos;
    private ArrayList<Jogo> bibliotecaJogos;

    public TelaUsuario(String nome, SistemaLogin sistema) {
        this.nome = nome;
        this.sistema = sistema;
        this.bibliotecaJogos = new ArrayList<>();

        // Carga inicial para testes na biblioteca
        this.bibliotecaJogos.add(new Jogo("Cyber Quest", "RPG Futurista de mundo aberto.", "RPG", 59.90));
        this.bibliotecaJogos.add(new Jogo("Space Run", "Corrida espacial interestelar veloz.", "Corrida", 29.95));

        setTitle("JOGO LEGAL - Painel do Usuário");
        setSize(850, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("BEM-VINDO, " + nome.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(30, 15, 480, 30);

        JLabel sub = new JLabel("Loja — jogos disponíveis para compra");
        sub.setForeground(Color.LIGHT_GRAY);
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setBounds(30, 44, 480, 18);

        JLabel lblCatalogo = new JLabel("CATÁLOGO DE JOGOS");
        lblCatalogo.setForeground(new Color(0, 180, 100));
        lblCatalogo.setFont(new Font("Arial", Font.BOLD, 13));
        lblCatalogo.setBounds(30, 68, 200, 20);

        painelJogos = new JPanel();
        painelJogos.setLayout(new BoxLayout(painelJogos, BoxLayout.Y_AXIS));
        painelJogos.setBackground(new Color(24, 26, 27));

        JScrollPane scroll = new JScrollPane(painelJogos);
        scroll.setBounds(30, 92, 650, 395);
        scroll.setBorder(null);
        // Garante que a barra horizontal nunca apareça quebrando o layout
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(new Color(24, 26, 27));

        construirCatalogo();

        JButton btnCarrinho = new JButton("🛒  CARRINHO");
        btnCarrinho.setBounds(30, 505, 180, 42);
        btnCarrinho.setBackground(new Color(0, 160, 80));
        btnCarrinho.setForeground(Color.WHITE);
        btnCarrinho.setFont(new Font("Arial", Font.BOLD, 13));
        btnCarrinho.setFocusPainted(false);

        JButton btnBiblioteca = new JButton("📚  BIBLIOTECA");
        btnBiblioteca.setBounds(230, 505, 180, 42);
        btnBiblioteca.setBackground(new Color(0, 100, 200));
        btnBiblioteca.setForeground(Color.WHITE);
        btnBiblioteca.setFont(new Font("Arial", Font.BOLD, 13));
        btnBiblioteca.setFocusPainted(false);

        btnBiblioteca.addActionListener(_ ->
                new TelaBiblio(nome, sistema, bibliotecaJogos).setVisible(true)
        );

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(430, 505, 180, 42);
        btnLogout.setBackground(new Color(180, 40, 40));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogout.setFocusPainted(false);

        btnCarrinho.addActionListener(_ -> new TelaCarrinho(nome, sistema));
        btnLogout.addActionListener(_ -> { dispose(); });

        painel.add(titulo); painel.add(sub); painel.add(lblCatalogo);
        painel.add(scroll);
        painel.add(btnCarrinho); painel.add(btnBiblioteca); painel.add(btnLogout);

        add(painel);
        setVisible(true);
    }

    private void construirCatalogo() {
        painelJogos.removeAll();
        for (String[] jogo : CATALOGO) {
            painelJogos.add(criarLinha(jogo[0], Double.parseDouble(jogo[1])));
            painelJogos.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        painelJogos.revalidate();
        painelJogos.repaint();
    }

    private JPanel criarLinha(String nome, double preco) {
        JPanel linha = new JPanel(null);
        linha.setBackground(new Color(36, 38, 40));
        linha.setBorder(BorderFactory.createLineBorder(new Color(60, 63, 65), 1));

        // CORREÇÃO: Reduzido de 478 para 450 para compensar o espaço que o JScrollPane rouba na direita
        linha.setPreferredSize(new Dimension(700, 52));
        linha.setMaximumSize(new Dimension(700, 52));

        JLabel lblNome = new JLabel(nome);
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.BOLD, 13));
        lblNome.setBounds(10, 7, 180, 20);

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", preco));
        lblPreco.setForeground(new Color(0, 200, 100));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
        lblPreco.setBounds(10, 28, 120, 16);

        // RESTAURADO: "Ver detalhes" de volta com coordenadas calculadas para o espaço de 450px
        JButton btnVerDetalhes = new JButton("Ver detalhes");
        btnVerDetalhes.setBounds(180, 11, 105, 30);
        btnVerDetalhes.setBackground(new Color(0, 100, 200));
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.PLAIN, 11));
        btnVerDetalhes.setFocusPainted(false);
        btnVerDetalhes.addActionListener(_ ->
                JOptionPane.showMessageDialog(null, "Jogo: " + nome + "\nPreço: R$ " + String.format("%.2f", preco)));

        // AJUSTADO: Movido ligeiramente para a esquerda para alinhar perfeitamente antes da borda
        JButton btnAdd = new JButton("+ Carrinho");
        btnAdd.setBounds(295, 11, 120, 30);
        btnAdd.setBackground(new Color(0, 160, 80));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAdd.setFocusPainted(false);

        btnAdd.addActionListener(_ -> {
            String genero = "Geral";
            for (String[] item : CATALOGO) {
                if (item[0].equals(nome)) {
                    genero = item.length > 2 ? item[2] : "Geral";
                    break;
                }
            }
            Jogo novoJogo = new Jogo(nome, "Adquirido na loja virtual.", genero, preco);
            this.bibliotecaJogos.add(novoJogo); // Simulação de compra direta adicionando à biblioteca
            JOptionPane.showMessageDialog(null, "\"" + nome + "\" adicionado à biblioteca com sucesso!");
        });

        linha.add(lblNome);
        linha.add(lblPreco);
        linha.add(btnVerDetalhes);
        linha.add(btnAdd);
        return linha;
    }
}