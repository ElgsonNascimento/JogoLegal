package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaCadastro extends JFrame {

    public TelaCadastro(SistemaLogin sistema) {
        setTitle("JOGO LEGAL - Cadastro");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("CRIAR CONTA");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(155, 20, 230, 38);

        JLabel subtitulo = new JLabel("Preencha os dados abaixo");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(163, 58, 210, 20);

        JLabel lblNome = new JLabel("Nome de usuário");
        lblNome.setForeground(Color.WHITE);
        lblNome.setBounds(100, 95, 160, 20);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(100, 118, 300, 35);
        txtNome.setBackground(new Color(36, 38, 40));
        txtNome.setForeground(Color.WHITE);
        txtNome.setCaretColor(Color.WHITE);
        txtNome.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblSenha = new JLabel("Senha (mínimo 4 caracteres)");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(100, 163, 230, 20);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 186, 300, 35);
        txtSenha.setBackground(new Color(36, 38, 40));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblTipo = new JLabel("Tipo de conta");
        lblTipo.setForeground(Color.WHITE);
        lblTipo.setBounds(100, 233, 160, 20);

        ButtonGroup grupo = new ButtonGroup();
        JRadioButton rbUsuario = criarRadio("Usuário",         "Compra e joga");
        JRadioButton rbCriador = criarRadio("Criador de Jogo", "Publica jogos");
        JRadioButton rbAdmin   = criarRadio("Administrador",   "Gerencia o sistema");
        rbUsuario.setSelected(true);
        grupo.add(rbUsuario); grupo.add(rbCriador); grupo.add(rbAdmin);

        JPanel painelTipo = new JPanel(new GridLayout(1, 3, 8, 0));
        painelTipo.setBackground(new Color(24, 26, 27));
        painelTipo.setBounds(100, 256, 300, 60);
        painelTipo.add(rbUsuario); painelTipo.add(rbCriador); painelTipo.add(rbAdmin);

        JButton btnCadastrar = new JButton("CADASTRAR E ENTRAR");
        btnCadastrar.setBounds(100, 340, 300, 45);
        btnCadastrar.setBackground(new Color(0, 160, 80));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setFocusPainted(false);

        JButton btnVoltar = new JButton("Voltar ao login");
        btnVoltar.setBounds(175, 395, 150, 25);
        btnVoltar.setBackground(new Color(24, 26, 27));
        btnVoltar.setForeground(new Color(100, 160, 255));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnCadastrar.addActionListener(_ -> {
            String nome  = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword());
            String tipo  = rbAdmin.isSelected()   ? "admin"
                         : rbCriador.isSelected() ? "criador"
                         : "usuario";

            String resultado = sistema.cadastrarUsuario(nome, senha, tipo);
            if (!"OK".equals(resultado)) {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                "Conta criada! Bem-vindo, " + nome + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
            if ("criador".equals(tipo)) {
                new TelaCriadorJogo(nome);
            } else {
                new TelaUsuario(nome, sistema);
            }
        });

        btnVoltar.addActionListener(_ -> { dispose(); new TelaLogin(); });

        painel.add(titulo);    painel.add(subtitulo);
        painel.add(lblNome);   painel.add(txtNome);
        painel.add(lblSenha);  painel.add(txtSenha);
        painel.add(lblTipo);   painel.add(painelTipo);
        painel.add(btnCadastrar); painel.add(btnVoltar);

        add(painel);
        setVisible(true);
    }

    private JRadioButton criarRadio(String label, String sub) {
        JRadioButton rb = new JRadioButton("<html><b>" + label + "</b><br>"
            + "<span style='color:#999;font-size:9px'>" + sub + "</span></html>");
        rb.setBackground(new Color(36, 38, 40));
        rb.setForeground(Color.WHITE);
        rb.setFocusPainted(false);
        rb.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return rb;
    }
}
