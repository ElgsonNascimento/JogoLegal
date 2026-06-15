package interfacejogolegal;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {

    private Map<String, String[]> usuarios = new HashMap<>();
    private static final String ARQUIVO = "usuarios.txt";


    private static final String ADMIN_PADRAO_NOME  = "admin";
    private static final String ADMIN_PADRAO_SENHA = "Admin@123";

    public SistemaLogin() {
        carregarUsuarios();
        criarAdminPadraoSeNecessario();
    }



    private void criarAdminPadraoSeNecessario() {
        boolean temAdmin = usuarios.values().stream()
            .anyMatch(v -> "admin".equals(v[1]));
        if (!temAdmin) {
            usuarios.put(ADMIN_PADRAO_NOME,
                new String[]{hashSenha(ADMIN_PADRAO_SENHA), "admin"});
            salvarUsuarios();
            System.out.println("[SISTEMA] Admin padrão criado: usuario=admin  senha=Admin@123");
            System.out.println("[SISTEMA] Troque a senha após o primeiro acesso!");
        }
    }



    public static String hashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 não disponível", e);
        }
    }



    public boolean autenticarUsuario(String nome, String senha) {
        if (nome == null || senha == null) return false;
        if (!usuarios.containsKey(nome)) return false;
        return usuarios.get(nome)[0].equals(hashSenha(senha));
    }



    public String cadastrarUsuario(String nome, String senha, String tipo) {
        if (nome == null || nome.trim().isEmpty())
            return "Nome de usuário inválido.";
        if (usuarios.containsKey(nome))
            return "Usuário \"" + nome + "\" já existe.";

        String erroSenha = validarSenha(senha);
        if (erroSenha != null) return erroSenha;

        usuarios.put(nome.trim(), new String[]{hashSenha(senha), tipo});
        salvarUsuarios();
        return "OK";
    }



    public String trocarSenha(String nome, String senhaAtual, String novaSenha) {
        if (!usuarios.containsKey(nome))
            return "Usuário não encontrado.";
        if (!usuarios.get(nome)[0].equals(hashSenha(senhaAtual)))
            return "Senha atual incorreta.";
        String erro = validarSenha(novaSenha);
        if (erro != null) return erro;

        usuarios.get(nome)[0] = hashSenha(novaSenha);
        salvarUsuarios();
        return "OK";
    }



    public String validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty())
            return "A senha não pode ser vazia.";
        if (senha.length() < 8)
            return "A senha deve ter pelo menos 8 caracteres.";
        if (!senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\\\"\\\\|,.<>\\/?].*"))
            return "A senha deve conter pelo menos um caractere especial (!@#$%^&* etc).";
        return null;
    }

    public String getTipo(String nome) {
        if (!usuarios.containsKey(nome)) return null;
        return usuarios.get(nome)[1];
    }

    public boolean removerUsuario(String nome) {
        if (!usuarios.containsKey(nome)) return false;
        usuarios.remove(nome);
        salvarUsuarios();
        return true;
    }

    public boolean usuarioExiste(String nome) {
        return usuarios.containsKey(nome);
    }


    public boolean isAdminPadrao(String nome) {
        return ADMIN_PADRAO_NOME.equals(nome);
    }


    public boolean isAdmin(String nome) {
        if (!usuarios.containsKey(nome)) return false;
        return "admin".equals(usuarios.get(nome)[1]);
    }



    private void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Map.Entry<String, String[]> entry : usuarios.entrySet()) {
                bw.write(entry.getKey() + ";" + entry.getValue()[0] + ";" + entry.getValue()[1]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuarios.txt: " + e.getMessage());
        }
    }

    private void carregarUsuarios() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split(";", 3);
                if (p.length == 3)
                    usuarios.put(p[0], new String[]{p[1], p[2]});
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuarios.txt: " + e.getMessage());
        }
    }
}
