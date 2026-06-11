package Pagamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorCartoes {

    private static final String ARQUIVO = "cartoes.txt";

    // ── Estrutura de um cartão salvo ──────────────────────────────────────────
    public static class CartaoSalvo {
        public final String usuario;
        public final String numero;     // número completo
        public final String titular;
        public final int mes;
        public final int ano;

        public CartaoSalvo(String usuario, String numero, String titular, int mes, int ano) {
            this.usuario = usuario;
            this.numero  = numero;
            this.titular = titular;
            this.mes     = mes;
            this.ano     = ano;
        }


        public String exibir() {
            String ultimos = numero.length() >= 4
                ? numero.substring(numero.length() - 4) : numero;
            return String.format("****-****-****-%s  |  %s  |  %02d/%d",
                ultimos, titular, mes, ano);
        }

        @Override
        public String toString() {
            return usuario + ";" + numero + ";" + titular + ";" + mes + ";" + ano;
        }
    }


    public static boolean salvar(String usuario, String numero, String titular, int mes, int ano) {
        List<CartaoSalvo> todos = carregarTodos();


        todos.removeIf(c -> c.usuario.equals(usuario)
                         && c.numero.equals(numero));

        todos.add(new CartaoSalvo(usuario, numero, titular, mes, ano));

        return escrever(todos);
    }


    public static List<CartaoSalvo> carregarDoUsuario(String usuario) {
        List<CartaoSalvo> resultado = new ArrayList<>();
        for (CartaoSalvo c : carregarTodos()) {
            if (c.usuario.equals(usuario)) resultado.add(c);
        }
        return resultado;
    }


    public static boolean remover(String usuario, String numero) {
        List<CartaoSalvo> todos = carregarTodos();
        boolean removeu = todos.removeIf(c -> c.usuario.equals(usuario)
                                           && c.numero.equals(numero));
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
                String[] p = linha.split(";", 5);
                if (p.length == 5) {
                    try {
                        lista.add(new CartaoSalvo(p[0], p[1], p[2],
                            Integer.parseInt(p[3]), Integer.parseInt(p[4])));
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
