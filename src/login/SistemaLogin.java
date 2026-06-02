package login;

public class SistemaLogin {

    private Usuario[] usuarios = new Usuario[10];
    private int quantidadeUsuarios = 0;

    // Cadastrar usuário
    public String cadastrarUsuario(String nome, String senha) {

        if (usuarioExiste(nome)) {
            return "Usuário já cadastrado.";
        }

        if (!validarSenha(senha)) {
            return "A senha deve ter pelo menos 6 caracteres.";
        }

        if (quantidadeUsuarios >= usuarios.length) {
            return "Limite de usuários atingido.";
        }

        usuarios[quantidadeUsuarios] = new Usuario(nome, senha);
        quantidadeUsuarios++;

        return "Usuário cadastrado com sucesso!";
    }

    // Autenticar usuário
    public boolean autenticarUsuario(String nome, String senha) {

        for (int i = 0; i < quantidadeUsuarios; i++) {

            if (usuarios[i].getNome().equalsIgnoreCase(nome)
                    && usuarios[i].getSenha().equals(senha)) {

                return true;
            }
        }

        return false;
    }

    // Recuperar senha
    public String recuperarSenha(String nome) {

        for (int i = 0; i < quantidadeUsuarios; i++) {

            if (usuarios[i].getNome().equalsIgnoreCase(nome)) {

                return usuarios[i].getSenha();
            }
        }

        return "Usuário não encontrado.";
    }

    // Validar senha
    public boolean validarSenha(String senha) {

        return senha.length() >= 6;
    }

    // Verificar se usuário já existe
    private boolean usuarioExiste(String nome) {

        for (int i = 0; i < quantidadeUsuarios; i++) {

            if (usuarios[i].getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }
}