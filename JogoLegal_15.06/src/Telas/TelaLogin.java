package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaLogin extends JFrame {

    private SistemaLogin sistema = new SistemaLogin();

    public TelaLogin() {
        setTitle("JOGO LEGAL");
        setSize(500, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("JOGO LEGAL");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(150, 25, 250, 40);

        JLabel subtitulo = new JLabel("Faça login para continuar");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(155, 65, 220, 20);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(125, 100, 100, 20);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(125, 122, 250, 35);
        txtUsuario.setBackground(new Color(36, 38, 40));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(125, 167, 100, 20);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(125, 190, 250, 35);
        txtSenha.setBackground(new Color(36, 38, 40));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(new LineBorder(Color.GRAY, 1));

        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(125, 248, 115, 42);
        btnEntrar.setBackground(new Color(0, 120, 215));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEntrar.setFocusPainted(false);

        JButton btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setBounds(260, 248, 115, 42);
        btnCadastrar.setBackground(new Color(0, 160, 80));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCadastrar.setFocusPainted(false);



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

        btnEntrar.addActionListener(e -> acaoLogin.run());
        txtSenha.addActionListener(e -> acaoLogin.run());
        btnCadastrar.addActionListener(e -> { dispose(); new TelaCadastro(sistema); });

        painel.add(titulo);      painel.add(subtitulo);
        painel.add(lblUsuario);  painel.add(txtUsuario);
        painel.add(lblSenha);    painel.add(txtSenha);
        painel.add(btnEntrar);   painel.add(btnCadastrar);

        add(painel);
        setVisible(true);
    }

    static void abrirMenuPorTipo(String nome, String tipo, SistemaLogin sistema) {
        if (tipo == null) tipo = "usuario";
        switch (tipo) {
            case "criador" -> new TelaCriadorJogo(nome);
            case "admin"   -> new TelaAdministrador(nome, sistema);
            default        -> new TelaUsuario(nome, sistema);
        }
    }
}
