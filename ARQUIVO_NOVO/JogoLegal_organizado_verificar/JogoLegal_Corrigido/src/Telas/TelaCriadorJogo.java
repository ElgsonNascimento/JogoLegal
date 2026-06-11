package Telas;

import GerenciadorJogos.CatalogoGlobal;
import Jogo.Jogo;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Painel do Criador de Jogo.
 * Permite: Publicar, Editar, Remover jogos e Ver Vendas.
 * Os jogos publicados ficam visíveis no catálogo de TelaUsuario via CatalogoGlobal.
 */
public class TelaCriadorJogo extends JFrame {

    private final String nomeCriador;
    /** Jogos criados/publicados por este criador nesta sessão. */
    private final ArrayList<Jogo> meusjogos = new ArrayList<>();
    /** Contador simples de "vendas" para demo. */
    private int totalVendas = 0;

    public TelaCriadorJogo(String nomeCriador) {
        this.nomeCriador = nomeCriador;

        setTitle("JOGO LEGAL - Painel do Criador");
        setSize(500, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        // ── Cabeçalho ─────────────────────────────────────────────────────────
        JLabel titulo = new JLabel("CRIADOR: " + nomeCriador.toUpperCase());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(80, 20, 360, 35);

        JLabel subtitulo = new JLabel("Gerencie seus jogos");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(175, 55, 200, 20);

        // ── Botões ────────────────────────────────────────────────────────────
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
        estilizar(btnVendas, new Color(100, 70, 180));
        btnVendas.setBounds(100, 280, 300, 45);

        JButton btnMeusJogos = new JButton("MEUS JOGOS PUBLICADOS");
        estilizar(btnMeusJogos, new Color(50, 50, 60));
        btnMeusJogos.setBounds(100, 340, 300, 45);

        JButton btnLogout = new JButton("LOGOUT");
        estilizar(btnLogout, new Color(60, 63, 65));
        btnLogout.setBounds(175, 420, 150, 40);

        // ── Label de status ───────────────────────────────────────────────────
        JLabel lblStatus = new JLabel(" ");
        lblStatus.setForeground(new Color(0, 200, 100));
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStatus.setBounds(0, 475, 500, 20);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        // ── Ações ─────────────────────────────────────────────────────────────

        btnPublicar.addActionListener(_ -> publicarJogo(lblStatus));

        btnEditar.addActionListener(_ -> editarJogo(lblStatus));

        btnRemover.addActionListener(_ -> removerJogo(lblStatus));

        btnVendas.addActionListener(_ -> verVendas());

        btnMeusJogos.addActionListener(_ -> listarMeusJogos());

        btnLogout.addActionListener(_ -> {
            dispose();
            new TelaLogin();
        });

        // ── Montagem ──────────────────────────────────────────────────────────
        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(btnPublicar);
        painel.add(btnEditar);
        painel.add(btnRemover);
        painel.add(btnVendas);
        painel.add(btnMeusJogos);
        painel.add(btnLogout);
        painel.add(lblStatus);
        add(painel);
        setVisible(true);
    }

    // ── Lógica de negócio ─────────────────────────────────────────────────────

    /** Abre formulário e publica o jogo no CatalogoGlobal. */
    private void publicarJogo(JLabel lblStatus) {
        JTextField txtNome    = new JTextField();
        JTextField txtDescricao = new JTextField();
        JTextField txtGenero  = new JTextField();
        JTextField txtPreco   = new JTextField();

        Object[] campos = {
            "Nome do jogo:",    txtNome,
            "Descrição:",       txtDescricao,
            "Gênero:",          txtGenero,
            "Preço (R$):",      txtPreco
        };

        int ok = JOptionPane.showConfirmDialog(
            this, campos, "Publicar Novo Jogo", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (ok != JOptionPane.OK_OPTION) return;

        String nome     = txtNome.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String genero   = txtGenero.getText().trim();
        String precoStr = txtPreco.getText().trim();

        if (nome.isEmpty() || genero.isEmpty() || precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha todos os campos obrigatórios (nome, gênero e preço).",
                "Campos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr.replace(",", "."));
            if (preco < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Preço inválido. Use somente números (ex: 29.90).",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (descricao.isEmpty()) descricao = "Sem descrição.";

        Jogo novoJogo = new Jogo(nome, descricao, genero, preco);
        meusjogos.add(novoJogo);
        CatalogoGlobal.getInstance().publicarJogo(novoJogo);

        lblStatus.setText("✔  \"" + nome + "\" publicado com sucesso!");
        JOptionPane.showMessageDialog(this,
            "Jogo \"" + nome + "\" publicado!\nAgora aparece no catálogo dos usuários.",
            "Publicado", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Permite editar descrição e/ou preço de um jogo já publicado. */
    private void editarJogo(JLabel lblStatus) {
        if (meusjogos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Você ainda não publicou nenhum jogo.", "Sem jogos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] nomes = meusjogos.stream().map(Jogo::getNome).toArray(String[]::new);
        String escolhido = (String) JOptionPane.showInputDialog(
            this, "Selecione o jogo para editar:", "Editar Jogo",
            JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]
        );
        if (escolhido == null) return;

        Jogo jogo = meusjogos.stream()
            .filter(j -> j.getNome().equals(escolhido))
            .findFirst().orElse(null);
        if (jogo == null) return;

        JTextField txtDescricao = new JTextField(jogo.getDescricao());
        JTextField txtPreco     = new JTextField(String.valueOf(jogo.getPreco()));

        Object[] campos = {
            "Nova descrição:", txtDescricao,
            "Novo preço (R$):", txtPreco
        };

        int ok = JOptionPane.showConfirmDialog(
            this, campos, "Editar: " + escolhido,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        if (ok != JOptionPane.OK_OPTION) return;

        String novaDesc  = txtDescricao.getText().trim();
        String novoPreco = txtPreco.getText().trim();

        if (!novaDesc.isEmpty()) jogo.setDescricao(novaDesc);
        try {
            double p = Double.parseDouble(novoPreco.replace(",", "."));
            if (p >= 0) jogo.setPreco(p);
        } catch (NumberFormatException ignored) {}

        // Sincroniza no CatalogoGlobal
        Jogo global = CatalogoGlobal.getInstance().buscarPorNome(escolhido);
        if (global != null) {
            global.setDescricao(jogo.getDescricao());
            global.setPreco(jogo.getPreco());
        }

        lblStatus.setText("✔  \"" + escolhido + "\" atualizado com sucesso!");
        JOptionPane.showMessageDialog(this,
            "Jogo \"" + escolhido + "\" atualizado no catálogo.",
            "Editado", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Remove um jogo da lista do criador e do CatalogoGlobal. */
    private void removerJogo(JLabel lblStatus) {
        if (meusjogos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Você ainda não publicou nenhum jogo.", "Sem jogos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] nomes = meusjogos.stream().map(Jogo::getNome).toArray(String[]::new);
        String escolhido = (String) JOptionPane.showInputDialog(
            this, "Selecione o jogo para remover:", "Remover Jogo",
            JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]
        );
        if (escolhido == null) return;

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja remover \"" + escolhido + "\"?\n" +
            "Ele será retirado do catálogo imediatamente.",
            "Confirmar Remoção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        meusjogos.removeIf(j -> j.getNome().equals(escolhido));
        CatalogoGlobal.getInstance().removerJogo(escolhido);

        lblStatus.setText("✔  \"" + escolhido + "\" removido do catálogo.");
        JOptionPane.showMessageDialog(this,
            "Jogo \"" + escolhido + "\" removido com sucesso.",
            "Removido", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Exibe relatório simples de vendas. */
    private void verVendas() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════\n");
        sb.append("  RELATÓRIO DE VENDAS\n");
        sb.append("  Criador: ").append(nomeCriador).append("\n");
        sb.append("═══════════════════════════════\n\n");

        if (meusjogos.isEmpty()) {
            sb.append("  Nenhum jogo publicado ainda.\n");
        } else {
            sb.append(String.format("  %-22s  %s%n", "JOGO", "PREÇO"));
            sb.append("  ───────────────────────────────\n");
            for (Jogo j : meusjogos) {
                sb.append(String.format("  %-22s  R$ %.2f%n", j.getNome(), j.getPreco()));
            }
            sb.append("\n  Total publicados: ").append(meusjogos.size()).append("\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setBackground(new Color(30, 32, 34));
        area.setForeground(new Color(200, 220, 200));
        area.setEditable(false);
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(360, 220));

        JOptionPane.showMessageDialog(this, scroll, "Vendas / Publicações",
            JOptionPane.PLAIN_MESSAGE);
    }

    /** Lista todos os jogos publicados por este criador. */
    private void listarMeusJogos() {
        if (meusjogos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Você ainda não publicou nenhum jogo nesta sessão.",
                "Meus Jogos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JPanel lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        lista.setBackground(new Color(24, 26, 27));

        for (Jogo j : meusjogos) {
            JPanel card = new JPanel(null);
            card.setBackground(new Color(36, 38, 40));
            card.setBorder(new LineBorder(new Color(60, 63, 65), 1));
            card.setPreferredSize(new Dimension(380, 60));
            card.setMaximumSize(new Dimension(380, 60));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblNome = new JLabel(j.getNome());
            lblNome.setForeground(Color.WHITE);
            lblNome.setFont(new Font("Arial", Font.BOLD, 13));
            lblNome.setBounds(8, 6, 200, 20);

            JLabel lblGenero = new JLabel("Gênero: " + j.getGenero());
            lblGenero.setForeground(Color.LIGHT_GRAY);
            lblGenero.setFont(new Font("Arial", Font.PLAIN, 11));
            lblGenero.setBounds(8, 28, 180, 18);

            JLabel lblPreco = new JLabel(String.format("R$ %.2f", j.getPreco()));
            lblPreco.setForeground(new Color(0, 200, 100));
            lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
            lblPreco.setBounds(240, 20, 100, 20);

            card.add(lblNome);
            card.add(lblGenero);
            card.add(lblPreco);

            lista.add(card);
            lista.add(Box.createRigidArea(new Dimension(0, 4)));
        }

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(400, 260));
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(24, 26, 27));

        JOptionPane.showMessageDialog(this, scroll,
            "Meus Jogos Publicados (" + meusjogos.size() + ")",
            JOptionPane.PLAIN_MESSAGE);
    }

    // ── Utilitário ────────────────────────────────────────────────────────────
    private void estilizar(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
    }
}
