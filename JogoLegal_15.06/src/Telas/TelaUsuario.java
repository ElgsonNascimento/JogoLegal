package Telas;

import GerenciadorJogos.CatalogoGlobal;
import Jogo.Jogo;
import interfacejogolegal.SistemaLogin;
import Carrinho.Carrinho;
import Usuarios.Usuario;
import Biblioteca.BibliotecaJogos;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class TelaUsuario extends JFrame {

    private final String nome;
    private final SistemaLogin sistema;
    private JPanel painelJogos;
    private final ArrayList<Jogo> bibliotecaJogos = new ArrayList<>();
    private final Carrinho carrinho = new Carrinho();
    private final Usuario usuario;
    private final BibliotecaJogos bibliotecaJogosCatalogo = new BibliotecaJogos();

    private JLabel lblSaldoValor;

    public TelaUsuario(String nome, SistemaLogin sistema) {
        this.nome    = nome;
        this.sistema = sistema;

        this.usuario = new Usuario(nome);

        bibliotecaJogos.addAll(Pagamento.GerenciadorBiblioteca.carregar(nome));
        for (Jogo j : bibliotecaJogos) {
            usuario.comprarJogo(j.getNome());
        }

        setTitle("JOGO LEGAL - Painel do Usuário");
        setSize(850, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));


        JLabel titulo = new JLabel("BEM-VINDO, " + nome.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(30, 15, 500, 30);

        JLabel sub = new JLabel("Loja — jogos disponíveis para compra");
        sub.setForeground(Color.LIGHT_GRAY);
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setBounds(30, 44, 480, 18);


        JPanel painelSaldo = new JPanel(null);
        painelSaldo.setBackground(new Color(36, 38, 40));
        painelSaldo.setBorder(BorderFactory.createLineBorder(new Color(0, 140, 70), 1));
        painelSaldo.setBounds(620, 10, 200, 52);

        JLabel lblSaldoLabel = new JLabel(" Saldo da conta");
        lblSaldoLabel.setForeground(new Color(160, 160, 160));
        lblSaldoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSaldoLabel.setBounds(10, 6, 180, 16);

        lblSaldoValor = new JLabel(String.format("R$ %.2f", usuario.getSaldo()));
        lblSaldoValor.setForeground(new Color(0, 220, 110));
        lblSaldoValor.setFont(new Font("Arial", Font.BOLD, 16));
        lblSaldoValor.setBounds(10, 24, 120, 22);

        JButton btnAddSaldo = new JButton("+");
        btnAddSaldo.setBounds(158, 20, 30, 24);
        btnAddSaldo.setBackground(new Color(0, 120, 60));
        btnAddSaldo.setForeground(Color.WHITE);
        btnAddSaldo.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddSaldo.setFocusPainted(false);
        btnAddSaldo.setToolTipText("Adicionar saldo");
        btnAddSaldo.addActionListener(e -> new TelaAdicionarSaldo(nome, sistema, this));

        painelSaldo.add(lblSaldoLabel);
        painelSaldo.add(lblSaldoValor);
        painelSaldo.add(btnAddSaldo);


        JLabel lblCatalogo = new JLabel("CATÁLOGO DE JOGOS");
        lblCatalogo.setForeground(new Color(0, 180, 100));
        lblCatalogo.setFont(new Font("Arial", Font.BOLD, 13));
        lblCatalogo.setBounds(30, 72, 200, 20);

        JButton btnAtualizar = new JButton("  Atualizar");
        btnAtualizar.setBounds(700, 68, 120, 26);
        btnAtualizar.setBackground(new Color(50, 53, 55));
        btnAtualizar.setForeground(new Color(180, 180, 180));
        btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorder(BorderFactory.createLineBorder(new Color(80, 83, 85), 1));

        painelJogos = new JPanel();
        painelJogos.setLayout(new BoxLayout(painelJogos, BoxLayout.Y_AXIS));
        painelJogos.setBackground(new Color(24, 26, 27));

        JScrollPane scroll = new JScrollPane(painelJogos);
        scroll.setBounds(30, 98, 790, 410);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(new Color(24, 26, 27));

        construirCatalogo();


        JButton btnCarrinho = new JButton("  CARRINHO");
        btnCarrinho.setBounds(30, 525, 180, 42);
        btnCarrinho.setBackground(new Color(0, 160, 80));
        btnCarrinho.setForeground(Color.WHITE);
        btnCarrinho.setFont(new Font("Arial", Font.BOLD, 13));
        btnCarrinho.setFocusPainted(false);

        JButton btnBiblioteca = new JButton("  BIBLIOTECA");
        btnBiblioteca.setBounds(230, 525, 180, 42);
        btnBiblioteca.setBackground(new Color(0, 100, 200));
        btnBiblioteca.setForeground(Color.WHITE);
        btnBiblioteca.setFont(new Font("Arial", Font.BOLD, 13));
        btnBiblioteca.setFocusPainted(false);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(430, 525, 180, 42);
        btnLogout.setBackground(new Color(180, 40, 40));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogout.setFocusPainted(false);

        btnAtualizar.addActionListener(e -> construirCatalogo());

        btnCarrinho.addActionListener(e ->
            new TelaCarrinho(nome, sistema, carrinho, bibliotecaJogos, this)
        );

        btnBiblioteca.addActionListener(e ->
            new TelaBiblio(nome, sistema, bibliotecaJogos).setVisible(true)
        );

        btnLogout.addActionListener(e -> {
            dispose();
        });

        painel.add(titulo);      painel.add(sub);
        painel.add(painelSaldo);
        painel.add(lblCatalogo); painel.add(btnAtualizar);
        painel.add(scroll);
        painel.add(btnCarrinho); painel.add(btnBiblioteca); painel.add(btnLogout);
        add(painel);
        setVisible(true);
    }

    private void atualizarSaldo() {
        lblSaldoValor.setText(String.format("R$ %.2f", usuario.getSaldo()));
    }

    public double getSaldo() { return usuario.getSaldo(); }

    public Usuario getUsuario() { return usuario; }

    public void setSaldo(double saldo) {
        this.usuario.setSaldo(saldo);
        atualizarSaldo();
    }

    public void onCompraConcluida(ArrayList<Jogo> jogosComprados) {
        for (Jogo j : jogosComprados) {
            boolean jaNaBiblioteca = bibliotecaJogos.stream()
                .anyMatch(b -> b.getNome().equalsIgnoreCase(j.getNome()));
            if (!jaNaBiblioteca) bibliotecaJogos.add(j);
        }
        construirCatalogo();
    }

    public void construirCatalogo() {
        painelJogos.removeAll();
        ArrayList<Jogo> catalogo    = CatalogoGlobal.getInstance().listar();
        ArrayList<Jogo> disponiveis = bibliotecaJogosCatalogo.mostrarJogosNaoComprados(
            usuario, CatalogoGlobal.getInstance(), bibliotecaJogos);

        if (disponiveis.isEmpty()) {
            JLabel vazio = new JLabel(catalogo.isEmpty()
                ? "Nenhum jogo disponível no momento."
                : "Você já possui todos os jogos! Confira sua biblioteca. ");
            vazio.setForeground(new Color(140, 140, 140));
            vazio.setFont(new Font("Arial", Font.ITALIC, 13));
            vazio.setAlignmentX(Component.LEFT_ALIGNMENT);
            vazio.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
            painelJogos.add(vazio);
        } else {
            for (Jogo jogo : disponiveis) {
                painelJogos.add(criarLinha(jogo));
                painelJogos.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        painelJogos.revalidate();
        painelJogos.repaint();
    }

    private JPanel criarLinha(Jogo jogo) {
        JPanel linha = new JPanel(null);
        linha.setBackground(new Color(36, 38, 40));
        linha.setBorder(BorderFactory.createLineBorder(new Color(60, 63, 65), 1));
        linha.setPreferredSize(new Dimension(770, 70));
        linha.setMaximumSize(new Dimension(770, 70));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);


        JPanel imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(50, 54, 58));
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(new Color(0, 140, 70));
                g.setFont(new Font("Arial", Font.BOLD, 18));
                FontMetrics fm = g.getFontMetrics();
                String icon = "";
                g.drawString(icon, (getWidth() - fm.stringWidth(icon)) / 2,
                    (getHeight() + fm.getAscent()) / 2 - 2);
            }
        };
        imgPanel.setBounds(6, 5, 60, 60);
        imgPanel.setOpaque(true);

        JLabel lblNome = new JLabel(jogo.getNome());
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.BOLD, 13));
        lblNome.setBounds(76, 10, 220, 20);

        JLabel lblGenero = new JLabel("Gênero: " + jogo.getGenero());
        lblGenero.setForeground(new Color(160, 160, 160));
        lblGenero.setFont(new Font("Arial", Font.PLAIN, 11));
        lblGenero.setBounds(76, 34, 220, 16);

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", jogo.getPreco()));
        lblPreco.setForeground(new Color(0, 200, 100));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 13));
        lblPreco.setBounds(310, 25, 120, 20);

        JButton btnVerDetalhes = new JButton("Ver detalhes");
        btnVerDetalhes.setBounds(440, 20, 115, 30);
        btnVerDetalhes.setBackground(new Color(0, 100, 200));
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.PLAIN, 11));
        btnVerDetalhes.setFocusPainted(false);
        btnVerDetalhes.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "<html><b>" + jogo.getNome() + "</b><br>" +
                "Gênero: "    + jogo.getGenero()    + "<br>" +
                "Descrição: " + jogo.getDescricao() + "<br>" +
                "Preço: R$ "  + String.format("%.2f", jogo.getPreco()) + "</html>",
                "Detalhes do Jogo", JOptionPane.INFORMATION_MESSAGE)
        );

        JButton btnAdd = new JButton("+ Carrinho");
        btnAdd.setBounds(565, 20, 190, 30);
        btnAdd.setBackground(new Color(0, 160, 80));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> {
            boolean jaNoCarrinho = carrinho.getJogos().stream()
                .anyMatch(j -> j.getNome().equalsIgnoreCase(jogo.getNome()));
            if (jaNoCarrinho) {
                JOptionPane.showMessageDialog(this,
                    "\"" + jogo.getNome() + "\" já está no carrinho.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            carrinho.adicionarJogo(new Jogo(jogo.getNome(), jogo.getDescricao(),
                                        jogo.getGenero(), jogo.getPreco()));
            JOptionPane.showMessageDialog(this,
                "\"" + jogo.getNome() + "\" adicionado ao carrinho! ");
        });

        linha.add(imgPanel);
        linha.add(lblNome);
        linha.add(lblGenero);
        linha.add(lblPreco);
        linha.add(btnVerDetalhes);
        linha.add(btnAdd);
        return linha;
    }
}
