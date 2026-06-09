//package Carrinho;
//
//import Usuarios.Usuario;
//
//public class TesteCarrinho {
//
//    public static void main(String[] args) {
//
//        // Criando usuário com saldo
//        Usuario usuario = new Usuario("Theo", 300.0);
//
//        // Criando jogos
//        Jogo jogo1 = new Jogo("GTA V", 100.0);
//        Jogo jogo2 = new Jogo("Minecraft", 80.0);
//        Jogo jogo3 = new Jogo("Cyberpunk 2077", 150.0);
//
//        // Criando carrinho
//        Carrinho carrinho = new Carrinho();
//
//        // Adicionando jogos
//        carrinho.adicionarJogo(jogo1);
//        carrinho.adicionarJogo(jogo2);
//        carrinho.adicionarJogo(jogo3);
//
//        // Calculando total
//        System.out.println("Total do carrinho: R$ " + carrinho.calcularTotal());
//
//        // Removendo um jogo
//        carrinho.removerJogo(jogo2);
//
//        // Novo total
//        System.out.println("Novo total do carrinho: R$ " + carrinho.calcularTotal());
//
//        // Finalizando compra
//        carrinho.finalizarCompra(usuario);
//
//        // Ver saldo restante
//        System.out.println("Saldo restante do usuário: R$ " + usuario.getSaldo());
//    }
//}
