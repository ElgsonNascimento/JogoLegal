package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaLogin extends JFrame {

    private SistemaLogin sistema = new SistemaLogin();

    public TelaLogin() {
        setTitle("JOGO LEGAL");
        setSize(500, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("JOGO LEGAL");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(150, 20, 250, 40);

        JLabel subtitulo = new JLabel("Faça login para continuar");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(155, 60, 220, 20);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(125, 95, 100, 20);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(125, 118, 250, 35);
        txtUsuario.setBackground(new Color(36, 38, 40));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(125, 163, 100, 20);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(125, 186, 250, 35);
        txtSenha.setBackground(new Color(36, 38, 40));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(new LineBorder(Color.GRAY, 1));

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(125, 242, 115, 40);
        btnEntrar.setBackground(new Color(0, 120, 215));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEntrar.setFocusPainted(false);

        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setBounds(260, 242, 115, 40);
        btnCadastrar.setBackground(new Color(0, 160, 80));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCadastrar.setFocusPainted(false);

        JButton btnRecuperar = new JButton("Esqueci minha senha");
        btnRecuperar.setBounds(150, 293, 200, 25);
        btnRecuperar.setBackground(new Color(24, 26, 27));
        btnRecuperar.setForeground(new Color(100, 160, 255));
        btnRecuperar.setBorderPainted(false);
        btnRecuperar.setFocusPainted(false);
        btnRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Separador
        JSeparator sep = new JSeparator();
        sep.setBounds(100, 330, 300, 2);
        sep.setForeground(new Color(60, 60, 60));

        JLabel lblAdm = new JLabel("É administrador?", SwingConstants.CENTER);
        lblAdm.setForeground(new Color(140, 140, 140));
        lblAdm.setFont(new Font("Arial", Font.PLAIN, 11));
        lblAdm.setBounds(125, 337, 250, 18);

        JButton btnCadastrarAdmin = new JButton("Cadastrar Administrador");
        btnCadastrarAdmin.setBounds(140, 360, 220, 30);
        btnCadastrarAdmin.setBackground(new Color(80, 20, 20));
        btnCadastrarAdmin.setForeground(new Color(220, 180, 180));
        btnCadastrarAdmin.setFont(new Font("Arial", Font.PLAIN, 11));
        btnCadastrarAdmin.setFocusPainted(false);
        btnCadastrarAdmin.setBorder(new LineBorder(new Color(120, 40, 40), 1));
        btnCadastrarAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // ── Ações ─────────────────────────────────────────────────────────────

        Runnable acaoLogin = () -> {
            String nome = txtUsuario.getText().trim();
            String pass = new String(txtSenha.getPassword());
            if (sistema.autenticarUsuario(nome, pass)) {
                dispose();
                abrirMenuPorTipo(nome, sistema.getTipo(nome), sistema);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                txtSenha.setText("");
            }
        };

        btnEntrar.addActionListener(_ -> acaoLogin.run());
        txtSenha.addActionListener(_ -> acaoLogin.run());

        btnCadastrar.addActionListener(_ -> { dispose(); new TelaCadastro(sistema); });

        btnRecuperar.addActionListener(_ -> {
            String nome = txtUsuario.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite seu nome de usuário primeiro.");
            } else {
                JOptionPane.showMessageDialog(this, sistema.recuperarSenha(nome));
            }
        });

        btnCadastrarAdmin.addActionListener(_ -> { dispose(); new TelaCadastroAdmin(sistema); });

        painel.add(titulo);           painel.add(subtitulo);
        painel.add(lblUsuario);       painel.add(txtUsuario);
        painel.add(lblSenha);         painel.add(txtSenha);
        painel.add(btnEntrar);        painel.add(btnCadastrar);
        painel.add(btnRecuperar);
        painel.add(sep);
        painel.add(lblAdm);           painel.add(btnCadastrarAdmin);

        add(painel);
        setVisible(true);
    }

    static void abrirMenuPorTipo(String nome, String tipo, SistemaLogin sistema) {
        if (tipo == null) tipo = "usuario";

        if ("criador".equals(tipo)) {
            new TelaCriadorJogo(nome);
        } else {
            new TelaUsuario(nome, sistema);
        }
    }
}
