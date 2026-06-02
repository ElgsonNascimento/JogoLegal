package login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaLogin extends JFrame {

    private SistemaLogin sistema = new SistemaLogin();

    public TelaLogin() {

        setTitle("JOGO LEGAL");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setBackground(new Color(24, 26, 27));
        painel.setLayout(null);

        JLabel titulo = new JLabel("JOGO LEGAL");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setBounds(150, 20, 250, 40);

        JLabel subtitulo = new JLabel("Sistema de Login");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(185, 60, 150, 20);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(125, 100, 100, 20);

        JTextField usuario = new JTextField();
        usuario.setBounds(125, 125, 250, 35);
        usuario.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(125, 170, 100, 20);

        JPasswordField senha = new JPasswordField();
        senha.setBounds(125, 195, 250, 35);
        senha.setBorder(new LineBorder(Color.GRAY, 1));

        JButton entrar = new JButton("ENTRAR");
        entrar.setBounds(170, 250, 160, 40);
        entrar.setBackground(new Color(0, 120, 215));
        entrar.setForeground(Color.WHITE);
        entrar.setFocusPainted(false);

        JButton cadastrar = new JButton("CADASTRAR");
        cadastrar.setBounds(170, 300, 160, 40);
        cadastrar.setBackground(new Color(0, 120, 215));
        cadastrar.setForeground(Color.WHITE);


        JButton recuperar = new JButton("RECUPERAR SENHA");
        recuperar.setBounds(150, 350, 200, 25);
        recuperar.setBackground(new Color(0, 120, 215));
        recuperar.setForeground(Color.WHITE);

        entrar.addActionListener(e -> {

            String nome = usuario.getText();
            String pass = new String(senha.getPassword());

            if (sistema.autenticarUsuario(nome, pass)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login realizado com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário ou senha incorretos."
                );
            }
        });

        cadastrar.addActionListener(e -> {

            String nome = usuario.getText();
            String pass = new String(senha.getPassword());

            String resultado =
                    sistema.cadastrarUsuario(nome, pass);

            JOptionPane.showMessageDialog(
                    this,
                    resultado
            );
        });

        recuperar.addActionListener(e -> {

            String nome = usuario.getText();

            JOptionPane.showMessageDialog(
                    this,
                    sistema.recuperarSenha(nome)
            );
        });

        painel.add(titulo);
        painel.add(subtitulo);
        painel.add(lblUsuario);
        painel.add(usuario);
        painel.add(lblSenha);
        painel.add(senha);
        painel.add(entrar);
        painel.add(cadastrar);
        painel.add(recuperar);

        add(painel);
        setVisible(true);
    }
}