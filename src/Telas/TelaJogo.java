package interfacejogolegal;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Tela de detalhes de um jogo.
 * Acessada via catálogo em TelaUsuario.
 * Métodos: Mostrar detalhes, Iniciar jogo, Sair do jogo.
 */
public class TelaJogo extends JFrame {

    public TelaJogo(String nomeJogo, double preco) {
        setTitle("JOGO LEGAL - " + nomeJogo);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        // ── cabeçalho ─────────────────────────────────────────
        JLabel titulo = new JLabel(nomeJogo.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(0, 20, 500, 35);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", preco));
        lblPreco.setForeground(new Color(0, 200, 100));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 18));
        lblPreco.setBounds(0, 54, 500, 24);
        lblPreco.setHorizontalAlignment(SwingConstants.CENTER);

        // ── painel de detalhes ────────────────────────────────
        JPanel painelDetalhe = new JPanel(null);
        painelDetalhe.setBackground(new Color(36, 38, 40));
        painelDetalhe.setBounds(50, 90, 400, 130);
        painelDetalhe.setBorder(new LineBorder(new Color(60, 63, 65), 1));

        JLabel lblTituloDetalhe = new JLabel("Detalhes do jogo");
        lblTituloDetalhe.setForeground(Color.LIGHT_GRAY);
        lblTituloDetalhe.setFont(new Font("Arial", Font.BOLD, 13));
        lblTituloDetalhe.setBounds(12, 10, 200, 20);

        JLabel lblGenero = new JLabel("Gênero: Ação / Aventura");
        lblGenero.setForeground(new Color(200, 200, 200));
        lblGenero.setFont(new Font("Arial", Font.PLAIN, 12));
        lblGenero.setBounds(12, 36, 370, 18);

        JLabel lblAvaliacao = new JLabel("Avaliação: ★★★★☆  (4.0 / 5.0)");
        lblAvaliacao.setForeground(new Color(200, 200, 200));
        lblAvaliacao.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAvaliacao.setBounds(12, 58, 370, 18);

        JLabel lblDescricao = new JLabel("<html>Descrição: Uma experiência incrível com<br>"
            + "gráficos modernos e jogabilidade envolvente.</html>");
        lblDescricao.setForeground(new Color(160, 160, 160));
        lblDescricao.setFont(new Font("Arial", Font.ITALIC, 11));
        lblDescricao.setBounds(12, 80, 370, 36);

        painelDetalhe.add(lblTituloDetalhe);
        painelDetalhe.add(lblGenero);
        painelDetalhe.add(lblAvaliacao);
        painelDetalhe.add(lblDescricao);

        // ── botões principais ─────────────────────────────────
        JButton btnMostrarDetalhes = new JButton("MOSTRAR DETALHES");
        btnMostrarDetalhes.setBounds(50, 245, 185, 45);
        btnMostrarDetalhes.setBackground(new Color(0, 120, 215));
        btnMostrarDetalhes.setForeground(Color.WHITE);
        btnMostrarDetalhes.setFont(new Font("Arial", Font.BOLD, 12));
        btnMostrarDetalhes.setFocusPainted(false);

        JButton btnIniciar = new JButton("▶  INICIAR JOGO");
        btnIniciar.setBounds(265, 245, 185, 45);
        btnIniciar.setBackground(new Color(0, 160, 80));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciar.setFocusPainted(false);

        JButton btnSair = new JButton("SAIR DO JOGO");
        btnSair.setBounds(150, 310, 200, 40);
        btnSair.setBackground(new Color(180, 40, 40));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Arial", Font.BOLD, 13));
        btnSair.setFocusPainted(false);

        // ── label de status ───────────────────────────────────
        JLabel lblStatus = new JLabel(" ");
        lblStatus.setForeground(new Color(0, 200, 100));
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStatus.setBounds(0, 360, 500, 20);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        // ── ações ─────────────────────────────────────────────
        btnMostrarDetalhes.addActionListener(e -> {
            // Exibe o painel de detalhe com destaque
            painelDetalhe.setBorder(new LineBorder(new Color(0, 120, 215), 2));
            lblStatus.setText("Detalhes exibidos com sucesso!");
        });

        btnIniciar.addActionListener(e -> {
            lblStatus.setText("Iniciando \"" + nomeJogo + "\"... boa sorte!");
            JOptionPane.showMessageDialog(this,
                "🎮  Iniciando: " + nomeJogo + "\n\n(Aqui o jogo seria carregado.)",
                "Iniciando Jogo", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSair.addActionListener(e -> {
            lblStatus.setText("Saindo do jogo...");
            int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja sair de \"" + nomeJogo + "\"?", "Sair", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) dispose();
        });

        // ── montagem ──────────────────────────────────────────
        painel.add(titulo);
        painel.add(lblPreco);
        painel.add(painelDetalhe);
        painel.add(btnMostrarDetalhes);
        painel.add(btnIniciar);
        painel.add(btnSair);
        painel.add(lblStatus);

        add(painel);
        setVisible(true);
    }
}
