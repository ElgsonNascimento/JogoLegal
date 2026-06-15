package Pagamento;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class GerenciadorVendas {

    private static final String ARQUIVO = "vendas.txt";
    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public static void registrarVendas(java.util.ArrayList<Jogo.Jogo> jogos) {
        if (jogos == null || jogos.isEmpty()) return;
        Map<String, int[]> mapa = carregarMapa();

        Map<String, String[]> dados = carregarDados();

        for (Jogo.Jogo j : jogos) {
            String chave = j.getNome().toLowerCase();
            if (dados.containsKey(chave)) {
                int atual = Integer.parseInt(dados.get(chave)[1]);
                dados.get(chave)[1] = String.valueOf(atual + 1);
            } else {
                dados.put(chave, new String[]{ j.getNome(), "1", j.getCriador() });
            }
        }
        salvarDados(dados);
    }


    public static Map<String, String[]> carregarDados() {
        Map<String, String[]> mapa = new LinkedHashMap<>();
        File f = new File(ARQUIVO);
        if (!f.exists()) return mapa;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split(";", 3);
                if (p.length >= 2) {
                    String nomeOriginal = p[0];
                    String qtd     = p[1];
                    String criador = p.length == 3 ? p[2] : "";
                    mapa.put(nomeOriginal.toLowerCase(),
                             new String[]{ nomeOriginal, qtd, criador });
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler vendas.txt: " + e.getMessage());
        }
        return mapa;
    }


    public static int vendasDo(String nomeJogo) {
        Map<String, String[]> dados = carregarDados();
        String[] entry = dados.get(nomeJogo.toLowerCase());
        if (entry == null) return 0;
        try { return Integer.parseInt(entry[1]); }
        catch (NumberFormatException e) { return 0; }
    }

    private static void salvarDados(Map<String, String[]> dados) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (String[] v : dados.values()) {
                bw.write(v[0] + ";" + v[1] + ";" + v[2]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas.txt: " + e.getMessage());
        }
    }


    private static Map<String, int[]> carregarMapa() { return new LinkedHashMap<>(); }
}
