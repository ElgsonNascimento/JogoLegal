package CriadorJogo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Jogo.Jogo;
import GerenciadorJogos.CatalogoGlobal;

public class CriadorJogo {
    private String nome;
    private String email;
    private ArrayList<Jogo> jogosCriados;

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public CriadorJogo(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.jogosCriados = new ArrayList<Jogo>();
        carregarJogosCriador();
    }



    private String arquivo() {

        return "jogos_" + nome.replaceAll("[^a-zA-Z0-9_\\-]", "_") + ".txt";
    }



    public Jogo criarJogo(String nome, String descricao, String genero,
                           double preco, CatalogoGlobal catalogo) {
        Jogo novoJogo = new Jogo(nome, descricao, genero, preco);
        novoJogo.setCriador(this.nome);
        this.jogosCriados.add(novoJogo);
        if (catalogo != null) catalogo.publicarJogo(novoJogo);
        salvarJogosCriador();
        System.out.println("Jogo " + nome + " criado por " + this.nome);
        return novoJogo;
    }

    public boolean editarJogo(String nome, String novaDescricao,
                               Double novoPreco, CatalogoGlobal catalogo) {
        for (Jogo jogo : jogosCriados) {
            if (jogo.getNome().equalsIgnoreCase(nome)) {
                if (novaDescricao != null && !novaDescricao.isEmpty())
                    jogo.setDescricao(novaDescricao);
                if (novoPreco != null && novoPreco >= 0)
                    jogo.setPreco(novoPreco);
                if (catalogo != null)
                    catalogo.editarJogo(nome, novaDescricao, novoPreco);
                salvarJogosCriador();
                return true;
            }
        }
        return false;
    }

    public boolean deletarJogo(String nome, CatalogoGlobal catalogo) {
        Jogo alvo = null;
        for (Jogo jogo : jogosCriados) {
            if (jogo.getNome().equalsIgnoreCase(nome)) { alvo = jogo; break; }
        }
        if (alvo != null) {
            if (catalogo != null) catalogo.removerJogo(nome);
            jogosCriados.remove(alvo);
            salvarJogosCriador();
            return true;
        }
        return false;
    }



    private void salvarJogosCriador() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo()))) {
            for (Jogo j : jogosCriados) {
                String desc = j.getDescricao().replace(";", "\\;");
                bw.write(j.getNome() + ";" + desc + ";" + j.getGenero()
                    + ";" + j.getPreco());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar " + arquivo() + ": " + e.getMessage());
        }
    }

    private void carregarJogosCriador() {
        File f = new File(arquivo());
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split("(?<!\\\\);", 4);
                if (p.length >= 4) {
                    String nomeJ  = p[0];
                    String desc   = p[1].replace("\\;", ";");
                    String genero = p[2];
                    double preco;
                    try { preco = Double.parseDouble(p[3]); }
                    catch (NumberFormatException e) { continue; }
                    Jogo j = new Jogo(nomeJ, desc, genero, preco);
                    j.setCriador(this.nome);
                    jogosCriados.add(j);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar " + arquivo() + ": " + e.getMessage());
        }
    }



    public ArrayList<Jogo> listarJogosCriados() { return jogosCriados; }
    public String getNome()          { return nome; }
    public String getEmail()         { return email; }
    public int getQuantidadeJogos()  { return jogosCriados.size(); }

    @Override
    public String toString() {
        return "CriadorJogo{nome='" + nome + "', jogos=" + jogosCriados.size() + "}";
    }
}
