package Pagamento;

import Jogo.Jogo;
import java.io.*;
import java.util.ArrayList;


public class GerenciadorBiblioteca {

    private static String caminho(String usuario) {
        return "biblioteca_" + usuario + ".txt";
    }


    public static void salvar(String usuario, ArrayList<Jogo> jogos) {
        ArrayList<String> existentes = new ArrayList<>();
        File arquivo = new File(caminho(usuario));

        if (arquivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null)
                    existentes.add(linha.trim().toLowerCase());
            } catch (IOException e) {
                System.err.println("Erro ao ler biblioteca: " + e.getMessage());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            for (Jogo j : jogos) {
                String entrada = j.getNome() + ";" + j.getDescricao() + ";"
                               + j.getGenero() + ";" + j.getPreco();
                if (!existentes.contains(entrada.toLowerCase())) {
                    bw.write(entrada);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar biblioteca: " + e.getMessage());
        }
    }


    public static ArrayList<Jogo> carregar(String usuario) {
        ArrayList<Jogo> jogos = new ArrayList<>();
        File arquivo = new File(caminho(usuario));
        if (!arquivo.exists()) return jogos;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split(";", 4);
                if (p.length == 4) {
                    try {
                        jogos.add(new Jogo(p[0], p[1], p[2], Double.parseDouble(p[3])));
                    } catch (NumberFormatException e) {
                        System.err.println("Linha inválida na biblioteca: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar biblioteca: " + e.getMessage());
        }
        return jogos;
    }
}
