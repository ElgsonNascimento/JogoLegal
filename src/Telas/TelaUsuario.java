package interfacejogolegal;

import java.awt.*;
import javax.swing.*;

public class TelaUsuario extends JFrame {

    private static final String[][] CATALOGO = {
    };

    private String nome;
    private SistemaLogin sistema;
    private JPanel painelJogos;

    public TelaUsuario(String nome, SistemaLogin sistema) {
        this.nome    = nome;
        this.sistema = sistema;

        setTitle("JOGO LEGAL - Painel do Usuário");
        setSize(540, 620);
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
        scroll.setBounds(30, 92, 480, 395);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(24, 26, 27));

        construirCatalogo();

        JButton btnCarrinho = new JButton("🛒  CARRINHO");
        btnCarrinho.setBounds(30, 505, 150, 42);
        btnCarrinho.setBackground(new Color(0, 160, 80));
        btnCarrinho.setForeground(Color.WHITE);
        btnCarrinho.setFont(new Font("Arial", Font.BOLD, 13));
        btnCarrinho.setFocusPainted(false);

        JButton btnBiblioteca = new JButton("📚  BIBLIOTECA");
        btnBiblioteca.setBounds(195, 505, 150, 42);
        btnBiblioteca.setBackground(new Color(0, 100, 200));
        btnBiblioteca.setForeground(Color.WHITE);
        btnBiblioteca.setFont(new Font("Arial", Font.BOLD, 13));
        btnBiblioteca.setFocusPainted(false);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(360, 505, 150, 42);
        btnLogout.setBackground(new Color(180, 40, 40));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogout.setFocusPainted(false);

        btnCarrinho.addActionListener(e -> new TelaCarrinho(nome, sistema));
        btnBiblioteca.addActionListener(e -> new TelaBiblioteca(nome, sistema));
        btnLogout.addActionListener(e -> { dispose(); new TelaLogin(); });

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
        linha.setPreferredSize(new Dimension(478, 52));
        linha.setMaximumSize(new Dimension(478, 52));

        JLabel lblNome = new JLabel(nome);
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.BOLD, 13));
        lblNome.setBounds(10, 7, 200, 20);

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", preco));
        lblPreco.setForeground(new Color(0, 200, 100));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
        lblPreco.setBounds(10, 28, 120, 16);

        JButton btnVerDetalhes = new JButton("Ver detalhes");
        btnVerDetalhes.setBounds(200, 10, 120, 30);
        btnVerDetalhes.setBackground(new Color(0, 100, 200));
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.PLAIN, 11));
        btnVerDetalhes.setFocusPainted(false);
        btnVerDetalhes.addActionListener(e ->
            JOptionPane.showMessageDialog(null, "Jogo: " + nome + "\nPreço: R$ " + String.format("%.2f", preco)));

        JButton btnAdd = new JButton("+ Carrinho");
        btnAdd.setBounds(330, 10, 125, 30);
        btnAdd.setBackground(new Color(0, 160, 80));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e ->
            JOptionPane.showMessageDialog(null, "\"" + nome + "\" adicionado ao carrinho!"));

        linha.add(lblNome); linha.add(lblPreco);
        linha.add(btnVerDetalhes); linha.add(btnAdd);
        return linha;
    }
}
