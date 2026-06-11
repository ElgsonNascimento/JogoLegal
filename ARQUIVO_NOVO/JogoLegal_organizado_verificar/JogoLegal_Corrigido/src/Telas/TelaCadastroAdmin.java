package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import interfacejogolegal.SistemaLogin;

public class TelaCadastroAdmin extends JFrame {

    // Código necessário para cadastrar um administrador
    private static final String CODIGO_ADMIN = "admin2025";

    public TelaCadastroAdmin(SistemaLogin sistema) {
        setTitle("JOGO LEGAL - Cadastro de Administrador");
        setSize(500, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        // Faixa vermelha indicando área restrita
        JLabel faixa = new JLabel("⚙  ÁREA RESTRITA", SwingConstants.CENTER);
        faixa.setForeground(Color.WHITE);
        faixa.setFont(new Font("Arial", Font.BOLD, 12));
        faixa.setOpaque(true);
        faixa.setBackground(new Color(160, 30, 30));
        faixa.setBounds(0, 0, 500, 28);

        JLabel titulo = new JLabel("CADASTRO DE ADMINISTRADOR");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(60, 40, 400, 32);

        JLabel subtitulo = new JLabel("Apenas pessoal autorizado");
        subtitulo.setForeground(new Color(200, 100, 100));
        subtitulo.setBounds(163, 72, 210, 20);

        JLabel lblNome = new JLabel("Nome de usuário");
        lblNome.setForeground(Color.WHITE);
        lblNome.setBounds(100, 105, 160, 20);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(100, 128, 300, 35);
        txtNome.setBackground(new Color(36, 38, 40));
        txtNome.setForeground(Color.WHITE);
        txtNome.setCaretColor(Color.WHITE);
        txtNome.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblSenha = new JLabel("Senha (mínimo 4 caracteres)");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(100, 173, 230, 20);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 196, 300, 35);
        txtSenha.setBackground(new Color(36, 38, 40));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblCodigo = new JLabel("Código de autorização");
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setBounds(100, 241, 230, 20);

        JPasswordField txtCodigo = new JPasswordField();
        txtCodigo.setBounds(100, 264, 300, 35);
        txtCodigo.setBackground(new Color(36, 38, 40));
        txtCodigo.setForeground(Color.WHITE);
        txtCodigo.setCaretColor(Color.WHITE);
        txtCodigo.setBorder(new LineBorder(new Color(160, 30, 30), 1));

        JButton btnCadastrar = new JButton("CADASTRAR ADMINISTRADOR");
        btnCadastrar.setBounds(100, 325, 300, 45);
        btnCadastrar.setBackground(new Color(160, 30, 30));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCadastrar.setFocusPainted(false);

        JButton btnVoltar = new JButton("Voltar ao login");
        btnVoltar.setBounds(175, 378, 150, 25);
        btnVoltar.setBackground(new Color(24, 26, 27));
        btnVoltar.setForeground(new Color(100, 160, 255));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnCadastrar.addActionListener(_ -> {
            String nome   = txtNome.getText().trim();
            String senha  = new String(txtSenha.getPassword());
            String codigo = new String(txtCodigo.getPassword());

            if (!CODIGO_ADMIN.equals(codigo)) {
                JOptionPane.showMessageDialog(this,
                    "Código de autorização inválido.", "Acesso negado", JOptionPane.ERROR_MESSAGE);
                txtCodigo.setText("");
                return;
            }

            String resultado = sistema.cadastrarUsuario(nome, senha, "admin");
            if (!"OK".equals(resultado)) {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                "Administrador \"" + nome + "\" cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new TelaLogin();
        });

        btnVoltar.addActionListener(_ -> { dispose(); new TelaLogin(); });

        painel.add(faixa);
        painel.add(titulo);      painel.add(subtitulo);
        painel.add(lblNome);     painel.add(txtNome);
        painel.add(lblSenha);    painel.add(txtSenha);
        painel.add(lblCodigo);   painel.add(txtCodigo);
        painel.add(btnCadastrar); painel.add(btnVoltar);

        add(painel);
        setVisible(true);
    }
}
