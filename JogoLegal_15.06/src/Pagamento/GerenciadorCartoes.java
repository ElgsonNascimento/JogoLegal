package Pagamento;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCartoes {

    private static final String ARQUIVO = "cartoes.txt";



    static String hashNumero(String numero) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(numero.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 não disponível", e);
        }
    }


    public static class CartaoSalvo {
        public final String usuario;
        public final String numeroHash;
        public final String ultimos4;
        public final String titular;
        public final int mes;
        public final int ano;

        public CartaoSalvo(String usuario, String numeroHash, String ultimos4,
                           String titular, int mes, int ano) {
            this.usuario    = usuario;
            this.numeroHash = numeroHash;
            this.ultimos4   = ultimos4;
            this.titular    = titular;
            this.mes        = mes;
            this.ano        = ano;
        }

        public String exibir() {
            return String.format("****-****-****-%s  |  %s  |  %02d/%d",
                ultimos4, titular, mes, ano);
        }

        @Override
        public String toString() {
            return usuario + ";" + numeroHash + ";" + ultimos4 + ";"
                 + titular + ";" + mes + ";" + ano;
        }
    }



    public static boolean salvar(String usuario, String numero, String titular, int mes, int ano) {
        String num    = numero.replaceAll("[\\s-]", "");
        String hash   = hashNumero(num);
        String ultimos = num.length() >= 4 ? num.substring(num.length() - 4) : num;

        List<CartaoSalvo> todos = carregarTodos();
        todos.removeIf(c -> c.usuario.equals(usuario) && c.numeroHash.equals(hash));
        todos.add(new CartaoSalvo(usuario, hash, ultimos, titular, mes, ano));
        return escrever(todos);
    }

    public static List<CartaoSalvo> carregarDoUsuario(String usuario) {
        List<CartaoSalvo> resultado = new ArrayList<>();
        for (CartaoSalvo c : carregarTodos()) {
            if (c.usuario.equals(usuario)) resultado.add(c);
        }
        return resultado;
    }

    public static boolean remover(String usuario, String numeroHash) {
        List<CartaoSalvo> todos = carregarTodos();
        boolean removeu = todos.removeIf(c -> c.usuario.equals(usuario)
                                           && c.numeroHash.equals(numeroHash));
        if (removeu) escrever(todos);
        return removeu;
    }



    private static List<CartaoSalvo> carregarTodos() {
        List<CartaoSalvo> lista = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split(";", 6);
                if (p.length == 6) {
                    try {
                        lista.add(new CartaoSalvo(p[0], p[1], p[2], p[3],
                            Integer.parseInt(p[4]), Integer.parseInt(p[5])));
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler cartoes.txt: " + e.getMessage());
        }
        return lista;
    }

    private static boolean escrever(List<CartaoSalvo> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (CartaoSalvo c : lista) {
                bw.write(c.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar cartoes.txt: " + e.getMessage());
            return false;
        }
    }
}
