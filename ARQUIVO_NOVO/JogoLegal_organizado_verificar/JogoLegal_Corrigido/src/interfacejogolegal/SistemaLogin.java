package interfacejogolegal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {

    // mapa: nome -> {senha, tipo}
    // Tipos: "usuario", "admin", "criador"
    private Map<String, String[]> usuarios = new HashMap<>();
    private static final String ARQUIVO = "usuarios.txt";

    public SistemaLogin() {
        carregarUsuarios();
    }

    // ── Autenticação ──────────────────────────────────────────────────────────

    public boolean autenticarUsuario(String nome, String senha) {
        if (nome == null || senha == null) return false;
        if (!usuarios.containsKey(nome)) return false;
        return usuarios.get(nome)[0].equals(senha);
    }

    // ── Cadastro ──────────────────────────────────────────────────────────────

    public String cadastrarUsuario(String nome, String senha, String tipo) {
        if (nome == null || nome.trim().isEmpty())
            return "Nome de usuário inválido.";
        if (usuarios.containsKey(nome))
            return "Usuário \"" + nome + "\" já existe.";

        String erroSenha = validarSenha(senha);
        if (erroSenha != null)
            return erroSenha;

        usuarios.put(nome.trim(), new String[]{senha, tipo});
        salvarUsuarios();
        return "OK";
    }

    // ── Utilitários ───────────────────────────────────────────────────────────

    public String recuperarSenha(String nome) {
        if (nome == null || nome.trim().isEmpty())
            return "Informe o nome de usuário.";
        if (!usuarios.containsKey(nome))
            return "Usuário \"" + nome + "\" não encontrado.";
        return "Senha de \"" + nome + "\": " + usuarios.get(nome)[0];
    }

    public String validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty())
            return "A senha não pode ser vazia.";
        if (senha.length() < 4)
            return "A senha deve ter pelo menos 4 caracteres.";
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

    // ── Persistência em TXT ───────────────────────────────────────────────────


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
                String[] partes = linha.split(";", 3);
                if (partes.length == 3) {
                    usuarios.put(partes[0], new String[]{partes[1], partes[2]});
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuarios.txt: " + e.getMessage());
        }
    }
}
