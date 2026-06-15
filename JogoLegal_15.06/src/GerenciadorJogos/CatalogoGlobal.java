package GerenciadorJogos;

import Jogo.Jogo;

import java.io.*;
import java.util.ArrayList;

public class CatalogoGlobal {

    private static CatalogoGlobal instancia;
    private final ArrayList<Jogo> jogosPublicados = new ArrayList<>();
    private final ArrayList<Jogo> jogosRemovidos  = new ArrayList<>();

    private static final String ARQUIVO = "catalogo.txt";

    private CatalogoGlobal() {
        carregarCatalogo();
    }

    public static CatalogoGlobal getInstance() {
        if (instancia == null) {
            instancia = new CatalogoGlobal();
        }
        return instancia;
    }



    public void publicarJogo(Jogo jogo) {
        for (Jogo j : jogosPublicados) {
            if (j.getNome().equalsIgnoreCase(jogo.getNome())) return;
        }
        jogosPublicados.add(jogo);
        salvarCatalogo();
    }

    public boolean criarJogo(String nome, String descricao, String genero, double preco) {
        Jogo novoJogo = new Jogo(nome, descricao, genero, preco);
        publicarJogo(novoJogo);
        return true;
    }



    public boolean removerJogo(String nome) {
        Jogo alvo = buscarPorNome(nome);
        if (alvo != null) {
            jogosPublicados.remove(alvo);
            jogosRemovidos.add(alvo);
            salvarCatalogo();
            return true;
        }
        return false;
    }



    public boolean editarJogo(String nome, String novaDescricao, Double novoPreco) {
        Jogo j = buscarPorNome(nome);
        if (j == null) return false;
        if (novaDescricao != null && !novaDescricao.isEmpty()) j.setDescricao(novaDescricao);
        if (novoPreco != null && novoPreco >= 0) j.setPreco(novoPreco);
        salvarCatalogo();
        return true;
    }



    public boolean iniciarJogo(String nome) {
        Jogo jogo = buscarPorNome(nome);
        if (jogo != null) { jogo.iniciarJogo(); return true; }
        return false;
    }

    public boolean pausarJogo(String nome) {
        Jogo jogo = buscarPorNome(nome);
        if (jogo != null) { jogo.pausarJogo(); return true; }
        return false;
    }

    public boolean finalizarJogo(String nome) {
        Jogo jogo = buscarPorNome(nome);
        if (jogo != null) {
            jogo.sairJogo();
            jogosPublicados.remove(jogo);
            jogosRemovidos.add(jogo);
            salvarCatalogo();
            return true;
        }
        return false;
    }



    public Jogo buscarPorNome(String nome) {
        for (Jogo j : jogosPublicados) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        return null;
    }

    public ArrayList<Jogo> listar() {
        return new ArrayList<>(jogosPublicados);
    }

    public ArrayList<Jogo> getJogosRemovidos() {
        return new ArrayList<>(jogosRemovidos);
    }



    private void salvarCatalogo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Jogo j : jogosPublicados) {

                String desc = j.getDescricao().replace(";", "\\;");
                bw.write(j.getNome() + ";" + desc + ";" + j.getGenero()
                    + ";" + j.getPreco() + ";" + j.getCriador());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar catalogo.txt: " + e.getMessage());
        }
    }

    private void carregarCatalogo() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] p = linha.split("(?<!\\\\);", 5);
                if (p.length >= 4) {
                    String nome    = p[0];
                    String desc    = p[1].replace("\\;", ";");
                    String genero  = p[2];
                    double preco;
                    try { preco = Double.parseDouble(p[3]); }
                    catch (NumberFormatException e) { continue; }
                    String criador = p.length == 5 ? p[4] : "";
                    Jogo jogo = new Jogo(nome, desc, genero, preco);
                    jogo.setCriador(criador);
                    jogosPublicados.add(jogo);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar catalogo.txt: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CatalogoGlobal{jogosPublicados=" + jogosPublicados.size()
            + ", jogosRemovidos=" + jogosRemovidos.size() + "}";
    }
}
