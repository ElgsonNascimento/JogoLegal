package interfacejogolegal;

import java.util.HashMap;
import java.util.Map;


public class SistemaLogin {

    // mapa: nome -> {senha, tipo}
    // Tipos: "usuario", "admin", "criador"
    private Map<String, String[]> usuarios = new HashMap<>();


    public boolean autenticarUsuario(String nome, String senha) {
        if (nome == null || senha == null) return false;
        if (!usuarios.containsKey(nome)) return false;
        return usuarios.get(nome)[0].equals(senha);
    }

    public String cadastrarUsuario(String nome, String senha, String tipo) {
        if (nome == null || nome.trim().isEmpty())
            return "Nome de usuário inválido.";
        if (usuarios.containsKey(nome))
            return "Usuário \"" + nome + "\" já existe.";

        String erroSenha = validarSenha(senha);
        if (erroSenha != null)
            return erroSenha;

        usuarios.put(nome.trim(), new String[]{senha, tipo});
        return "OK";
    }

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
        return null; // vish deu errado
    }

    public String getTipo(String nome) {
        if (!usuarios.containsKey(nome)) return null;
        return usuarios.get(nome)[1];
    }

    public boolean removerUsuario(String nome) {
        if (!usuarios.containsKey(nome)) return false;
        usuarios.remove(nome);
        return true;
    }

    public boolean usuarioExiste(String nome) {
        return usuarios.containsKey(nome);
    }
}
