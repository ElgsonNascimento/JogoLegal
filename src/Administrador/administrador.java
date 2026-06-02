import java.util.ArrayList;

public class Administrador {
    public String nome;
    public String nivelAcesso;
    public ArrayList<String> usuariosRemovidos;
    public ArrayList<String> jogosAdicionados;
    public ArrayList<String> jogosRemovidos;

    public Administrador(String nome, String nivelAcesso) {
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.usuariosRemovidos = new ArrayList<>();
        this.jogosAdicionados = new ArrayList<>();
        this.jogosRemovidos = new ArrayList<>();
    }

    public String toString() {
        return "Administrador [nome=" + nome + ", nivelAcesso=" + nivelAcesso + "]";
    }

    public boolean removerUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.out.println("Erro: nome de usuário inválido.");
            return false;
        }
        usuariosRemovidos.add(nomeUsuario);
        System.out.println("Usuário " + nomeUsuario + " removido com sucesso.");
        return true;
    }

    public boolean adicionarJogo(String nomeJogo) {
        if (nomeJogo == null || nomeJogo.trim().isEmpty()) {
            System.out.println("Erro: nome do jogo inválido.");
            return false;
        }
        jogosAdicionados.add(nomeJogo);
        System.out.println("Jogo " + nomeJogo + " adicionado com sucesso.");
        return true;
    }

    public boolean removerJogo(String nomeJogo) {
        if (nomeJogo == null || nomeJogo.trim().isEmpty()) {
            System.out.println("Erro: nome do jogo inválido.");
            return false;
        }
        jogosRemovidos.add(nomeJogo);
        System.out.println("Jogo " + nomeJogo + " removido com sucesso.");
        return true;
    }

    public boolean verificarNivelAcesso() {
        return "Super Admin".equals(nivelAcesso) || "Admin".equals(nivelAcesso);
    }

    public ArrayList<String> listarUsuariosRemovidos() {
        return usuariosRemovidos;
    }

    public ArrayList<String> listarJogosAdicionados() {
        return jogosAdicionados;
    }

    public ArrayList<String> listarJogosRemovidos() {
        return jogosRemovidos;
    }
}