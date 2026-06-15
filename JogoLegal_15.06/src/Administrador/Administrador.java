package Administrador;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Administrador {
    public String nome;
    public String nivelAcesso;
    private ArrayList<String> usuariosRemovidos;
    private ArrayList<String> adminsAdicionados;

    private static final String ARQUIVO_HISTORICO = "historico_admin.txt";
    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Administrador(String nome, String nivelAcesso) {
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.usuariosRemovidos = new ArrayList<>();
        this.adminsAdicionados = new ArrayList<>();
    }

    public String toString() {
        return "Administrador [nome=" + nome + ", nivelAcesso=" + nivelAcesso + "]";
    }



    public boolean removerUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) return false;
        usuariosRemovidos.add(nomeUsuario);
        registrarHistorico("USUARIO_REMOVIDO", nomeUsuario);
        return true;
    }



    public boolean registrarNovoAdmin(String nomeAdmin) {
        if (nomeAdmin == null || nomeAdmin.trim().isEmpty()) return false;
        adminsAdicionados.add(nomeAdmin);
        registrarHistorico("ADMIN_CRIADO", nomeAdmin);
        return true;
    }



    public boolean verificarNivelAcesso() {
        return "Super Admin".equals(nivelAcesso) || "Admin".equals(nivelAcesso);
    }



    public ArrayList<String> listarUsuariosRemovidos() { return usuariosRemovidos; }
    public ArrayList<String> listarAdminsAdicionados() { return adminsAdicionados; }



    private void registrarHistorico(String acao, String alvo) {
        String linha = LocalDateTime.now().format(FMT)
            + " | ADMIN=" + nome
            + " | ACAO=" + acao
            + " | ALVO=" + alvo;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_HISTORICO, true))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar historico_admin.txt: " + e.getMessage());
        }
    }

    public static String lerHistorico() {
        File f = new File(ARQUIVO_HISTORICO);
        if (!f.exists()) return "(nenhum registro encontrado)";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha).append("\n");
            }
        } catch (IOException e) {
            return "Erro ao ler histórico: " + e.getMessage();
        }
        return sb.length() == 0 ? "(histórico vazio)" : sb.toString().trim();
    }
}
