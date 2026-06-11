package Telas;

import GerenciadorJogos.CatalogoGlobal;
import Jogo.Jogo;
import interfacejogolegal.SistemaLogin;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class TelaUsuario extends JFrame {

    private final String nome;
    private final SistemaLogin sistema;
    private JPanel painelJogos;
    private final ArrayList<Jogo> bibliotecaJogos = new ArrayList<>();
    private final ArrayList<Jogo> carrinhoJogos   = new ArrayList<>();

    public TelaUsuario(String nome, SistemaLogin sistema) {
        this.nome    = nome;
        this.sistema = sistema;

        bibliotecaJogos.addAll(Pagamento.GerenciadorBiblioteca.carregar(nome));

        setTitle("JOGO LEGAL - Painel do Usuário");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("BEM-VINDO, " + nome.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(30, 15, 580, 30);

        JLabel sub = new JLabel("Loja — jogos disponíveis para compra");
        sub.setForeground(Color.LIGHT_GRAY);
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setBounds(30, 44, 480, 18);

        JLabel lblCatalogo = new JLabel("CATÁLOGO DE JOGOS");
        lblCatalogo.setForeground(new Color(0, 180, 100));
        lblCatalogo.setFont(new Font("Arial", Font.BOLD, 13));
        lblCatalogo.setBounds(30, 68, 200, 20);

        JButton btnAtualizar = new JButton("⟳  Atualizar");
        btnAtualizar.setBounds(680, 63, 120, 26);
        btnAtualizar.setBackground(new Color(50, 53, 55));
        btnAtualizar.setForeground(new Color(180, 180, 180));
        btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorder(BorderFactory.createLineBorder(new Color(80, 83, 85), 1));

        painelJogos = new JPanel();
        painelJogos.setLayout(new BoxLayout(painelJogos, BoxLayout.Y_AXIS));
        painelJogos.setBackground(new Color(24, 26, 27));

        JScrollPane scroll = new JScrollPane(painelJogos);
        scroll.setBounds(30, 92, 780, 410);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(new Color(24, 26, 27));

        construirCatalogo();

        JButton btnCarrinho = new JButton("🛒  CARRINHO");
        btnCarrinho.setBounds(30, 520, 180, 42);
        btnCarrinho.setBackground(new Color(0, 160, 80));
        btnCarrinho.setForeground(Color.WHITE);
        btnCarrinho.setFont(new Font("Arial", Font.BOLD, 13));
        btnCarrinho.setFocusPainted(false);

        JButton btnBiblioteca = new JButton("📚  BIBLIOTECA");
        btnBiblioteca.setBounds(230, 520, 180, 42);
        btnBiblioteca.setBackground(new Color(0, 100, 200));
        btnBiblioteca.setForeground(Color.WHITE);
        btnBiblioteca.setFont(new Font("Arial", Font.BOLD, 13));
        btnBiblioteca.setFocusPainted(false);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(430, 520, 180, 42);
        btnLogout.setBackground(new Color(180, 40, 40));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogout.setFocusPainted(false);

        btnAtualizar.addActionListener(_ -> construirCatalogo());

        btnCarrinho.addActionListener(_ ->
            new TelaCarrinho(nome, sistema, carrinhoJogos, bibliotecaJogos, this)
        );

        btnBiblioteca.addActionListener(_ ->
            new TelaBiblio(nome, sistema, bibliotecaJogos).setVisible(true)
        );

        btnLogout.addActionListener(_ -> dispose());

        painel.add(titulo);      painel.add(sub);
        painel.add(lblCatalogo); painel.add(btnAtualizar);
        painel.add(scroll);
        painel.add(btnCarrinho); painel.add(btnBiblioteca); painel.add(btnLogout);
        add(painel);
        setVisible(true);
    }

    /**
     * Chamado pela TelaPagamento após compra aprovada.
     * Move os jogos comprados para a biblioteca e reconstrói o catálogo.
     */
    public void onCompraConcluida(ArrayList<Jogo> jogosComprados) {
        for (Jogo j : jogosComprados) {
            boolean jaNaBiblioteca = bibliotecaJogos.stream()
                .anyMatch(b -> b.getNome().equalsIgnoreCase(j.getNome()));
            if (!jaNaBiblioteca) {
                bibliotecaJogos.add(j);
            }
        }
        construirCatalogo(); // oculta da loja os jogos já comprados
    }

    /** Reconstrói o painel ignorando jogos que já estão na biblioteca do usuário. */
    public void construirCatalogo() {
        painelJogos.removeAll();

        ArrayList<Jogo> catalogo = CatalogoGlobal.getInstance().listar();

        // Filtra jogos que o usuário já possui
        ArrayList<Jogo> disponiveis = new ArrayList<>();
        for (Jogo j : catalogo) {
            boolean jaComprado = bibliotecaJogos.stream()
                .anyMatch(b -> b.getNome().equalsIgnoreCase(j.getNome()));
            if (!jaComprado) disponiveis.add(j);
        }

        if (disponiveis.isEmpty()) {
            JLabel vazio = new JLabel(catalogo.isEmpty()
                ? "Nenhum jogo disponível no momento. Aguarde publicações dos criadores."
                : "Você já possui todos os jogos disponíveis! Confira sua biblioteca. 📚");
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
        linha.setPreferredSize(new Dimension(760, 58));
        linha.setMaximumSize(new Dimension(760, 58));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblNome = new JLabel(jogo.getNome());
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.BOLD, 13));
        lblNome.setBounds(10, 6, 220, 20);

        JLabel lblGenero = new JLabel("Gênero: " + jogo.getGenero());
        lblGenero.setForeground(new Color(160, 160, 160));
        lblGenero.setFont(new Font("Arial", Font.PLAIN, 11));
        lblGenero.setBounds(10, 30, 220, 16);

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", jogo.getPreco()));
        lblPreco.setForeground(new Color(0, 200, 100));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 13));
        lblPreco.setBounds(250, 19, 120, 20);

        JButton btnVerDetalhes = new JButton("Ver detalhes");
        btnVerDetalhes.setBounds(390, 13, 115, 30);
        btnVerDetalhes.setBackground(new Color(0, 100, 200));
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.PLAIN, 11));
        btnVerDetalhes.setFocusPainted(false);
        btnVerDetalhes.addActionListener(_ ->
            JOptionPane.showMessageDialog(this,
                "<html><b>" + jogo.getNome() + "</b><br>" +
                "Gênero: "    + jogo.getGenero()    + "<br>" +
                "Descrição: " + jogo.getDescricao() + "<br>" +
                "Preço: R$ "  + String.format("%.2f", jogo.getPreco()) + "</html>",
                "Detalhes do Jogo", JOptionPane.INFORMATION_MESSAGE)
        );

        JButton btnAdd = new JButton("+ Carrinho");
        btnAdd.setBounds(515, 13, 230, 30);
        btnAdd.setBackground(new Color(0, 160, 80));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(_ -> {
            boolean jaNoCarrinho = carrinhoJogos.stream()
                .anyMatch(j -> j.getNome().equalsIgnoreCase(jogo.getNome()));
            if (jaNoCarrinho) {
                JOptionPane.showMessageDialog(this,
                    "\"" + jogo.getNome() + "\" já está no carrinho.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            carrinhoJogos.add(new Jogo(jogo.getNome(), jogo.getDescricao(),
                                        jogo.getGenero(), jogo.getPreco()));
            JOptionPane.showMessageDialog(this,
                "\"" + jogo.getNome() + "\" adicionado ao carrinho! 🛒");
        });

        linha.add(lblNome);
        linha.add(lblGenero);
        linha.add(lblPreco);
        linha.add(btnVerDetalhes);
        linha.add(btnAdd);
        return linha;
    }
}
