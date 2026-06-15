package Telas;

import Administrador.Administrador;
import interfacejogolegal.SistemaLogin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaAdministrador extends JFrame {

    private final Administrador admin;

    public TelaAdministrador(String nomeAdmin, SistemaLogin sistema) {
        this.admin = new Administrador(nomeAdmin,
            sistema.isAdminPadrao(nomeAdmin) ? "Super Admin" : "Admin");

        setTitle("JOGO LEGAL - Painel Administrador");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));


        if (sistema.isAdminPadrao(nomeAdmin)) {
            JPanel avisoPanel = new JPanel(null);
            avisoPanel.setBackground(new Color(120, 60, 0));
            avisoPanel.setBounds(0, 0, 500, 30);
            JLabel aviso = new JLabel(
                "  Você está usando o admin padrão (senha: Admin@123). Troque a senha!",
                SwingConstants.CENTER);
            aviso.setForeground(Color.WHITE);
            aviso.setFont(new Font("Arial", Font.BOLD, 11));
            aviso.setBounds(0, 0, 500, 30);
            avisoPanel.add(aviso);
            painel.add(avisoPanel);
        }


        JLabel titulo = new JLabel("PAINEL ADMINISTRADOR");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(80, 40, 360, 32);

        JLabel subtitulo = new JLabel("Olá, " + nomeAdmin + "  —  Gerencie o sistema");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setBounds(100, 74, 320, 20);


        JButton btnNovoAdmin = new JButton("  CADASTRAR NOVO ADMIN");
        estilizar(btnNovoAdmin, new Color(100, 50, 160));
        btnNovoAdmin.setBounds(100, 114, 300, 46);

        JButton btnTrocarSenha = new JButton("  TROCAR MINHA SENHA");
        estilizar(btnTrocarSenha, new Color(40, 80, 140));
        btnTrocarSenha.setBounds(100, 172, 300, 46);

        JButton btnRemoverUsuario = new JButton("  REMOVER USUÁRIO");
        estilizar(btnRemoverUsuario, new Color(160, 40, 40));
        btnRemoverUsuario.setBounds(100, 230, 300, 46);

        JButton btnHistorico = new JButton("  HISTÓRICO DE AÇÕES");
        estilizar(btnHistorico, new Color(60, 80, 50));
        btnHistorico.setBounds(100, 288, 300, 46);

        JButton btnLogout = new JButton("LOGOUT");
        estilizar(btnLogout, new Color(60, 63, 65));
        btnLogout.setBounds(175, 356, 150, 40);



        btnNovoAdmin.addActionListener(e -> cadastrarNovoAdmin(sistema));

        btnTrocarSenha.addActionListener(e -> trocarSenha(nomeAdmin, sistema));

        btnRemoverUsuario.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this,
                "Nome do usuário a remover:", "Remover Usuário", JOptionPane.PLAIN_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) return;
            nome = nome.trim();
            if (nome.equals(nomeAdmin)) {
                JOptionPane.showMessageDialog(this, "Você não pode remover a si mesmo.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!sistema.usuarioExiste(nome)) {
                JOptionPane.showMessageDialog(this,
                    "Usuário \"" + nome + "\" não encontrado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sistema.isAdmin(nome)) {
                JOptionPane.showMessageDialog(this,
                    "Não é possível remover outro administrador.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int conf = JOptionPane.showConfirmDialog(this,
                "Remover \"" + nome + "\" permanentemente?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (conf == JOptionPane.YES_OPTION) {
                sistema.removerUsuario(nome);
                admin.removerUsuario(nome);
                JOptionPane.showMessageDialog(this,
                    " Usuário \"" + nome + "\" removido com sucesso.");
            }
        });

        btnHistorico.addActionListener(e -> {

            StringBuilder sb = new StringBuilder("<html><b>Sessão atual:</b><br><br>");

            sb.append("<b>Admins criados nesta sessão:</b><br>");
            if (admin.listarAdminsAdicionados().isEmpty()) {
                sb.append("&nbsp;&nbsp;(nenhum)<br>");
            } else {
                for (String a : admin.listarAdminsAdicionados())
                    sb.append("&nbsp;&nbsp; ").append(a).append("<br>");
            }

            sb.append("<br><b>Usuários removidos nesta sessão:</b><br>");
            if (admin.listarUsuariosRemovidos().isEmpty()) {
                sb.append("&nbsp;&nbsp;(nenhum)<br>");
            } else {
                for (String u : admin.listarUsuariosRemovidos())
                    sb.append("&nbsp;&nbsp; ").append(u).append("<br>");
            }

            sb.append("<br><hr><b>Histórico completo (historico_admin.txt):</b><br><pre style='font-size:10px'>");
            String hist = Administrador.lerHistorico();

            String[] linhas = hist.split("\n");
            int inicio = Math.max(0, linhas.length - 40);
            for (int i = inicio; i < linhas.length; i++)
                sb.append(linhas[i]).append("<br>");
            sb.append("</pre></html>");

            JScrollPane scroll = new JScrollPane(new JLabel(sb.toString()));
            scroll.setPreferredSize(new Dimension(520, 400));
            JOptionPane.showMessageDialog(this, scroll,
                "Histórico de Ações", JOptionPane.INFORMATION_MESSAGE);
        });

        btnLogout.addActionListener(e -> { dispose(); new TelaLogin(); });


        painel.add(titulo);          painel.add(subtitulo);
        painel.add(btnNovoAdmin);    painel.add(btnTrocarSenha);
        painel.add(btnRemoverUsuario);
        painel.add(btnHistorico);
        painel.add(btnLogout);
        add(painel);
        setVisible(true);
    }



    private void cadastrarNovoAdmin(SistemaLogin sistema) {
        JPanel form = new JPanel(new GridLayout(4, 1, 0, 6));
        form.setPreferredSize(new Dimension(280, 110));

        JTextField txtNome      = new JTextField();
        JPasswordField txtSenha = new JPasswordField();

        form.add(new JLabel("Nome do novo admin:"));
        form.add(txtNome);
        form.add(new JLabel("Senha (8+ chars, 1 especial: !@#$%):"));
        form.add(txtSenha);

        int result = JOptionPane.showConfirmDialog(this, form,
            "Cadastrar Novo Administrador",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) return;

        String nome  = txtNome.getText().trim();
        String senha = new String(txtSenha.getPassword());
        String resp  = sistema.cadastrarUsuario(nome, senha, "admin");

        if ("OK".equals(resp)) {
            admin.registrarNovoAdmin(nome);
            JOptionPane.showMessageDialog(this,
                " Admin \"" + nome + "\" cadastrado!\nEle pode logar normalmente.",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resp, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void trocarSenha(String nomeAdmin, SistemaLogin sistema) {
        JPanel form = new JPanel(new GridLayout(4, 1, 0, 6));
        form.setPreferredSize(new Dimension(280, 110));

        JPasswordField txtAtual = new JPasswordField();
        JPasswordField txtNova  = new JPasswordField();

        form.add(new JLabel("Senha atual:"));  form.add(txtAtual);
        form.add(new JLabel("Nova senha:"));   form.add(txtNova);

        int result = JOptionPane.showConfirmDialog(this, form,
            "Trocar Senha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) return;

        String resp = sistema.trocarSenha(nomeAdmin,
            new String(txtAtual.getPassword()), new String(txtNova.getPassword()));

        if ("OK".equals(resp)) {
            JOptionPane.showMessageDialog(this, " Senha alterada com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resp, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void estilizar(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorder(new LineBorder(cor.darker(), 1));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
