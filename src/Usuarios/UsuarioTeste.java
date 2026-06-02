package Usuarios;

public class UsuarioTeste {

    public static void main(String[] args) {
        System.out.println("========== TESTE DA CLASSE USUARIO ==========\n");

        // Criar um novo usuário
        Usuario user = new Usuario();
        user.nome = "João Silva";
        user.saldo = 100.0;

        // Teste Login
        System.out.println("--- Teste 1: Login ---");
        user.login();
        System.out.println("Usuário logado? " + user.logado + "\n");

        // Teste Adicionar saldo
        System.out.println("--- Teste 2: Adicionar Saldo ---");
        user.adicionarSaldo(50.0);
        System.out.println("Saldo atual: R$ " + user.saldo + "\n");

        // Teste Comprar jogos
        System.out.println("--- Teste 3: Comprar Jogos ---");
        user.comprarJogo("The Witcher 3");
        user.comprarJogo("Cyberpunk 2077");
        user.comprarJogo("Elden Ring");
        System.out.println();

        // Teste Mostrar jogos comprados
        System.out.println("--- Teste 4: Mostrar Jogos Comprados ---");
        user.mostrarJogosComprados();
        System.out.println();

        // Teste Instalar um jogo
        System.out.println("--- Teste 5: Instalar Jogo ---");
        user.instalarJogo("The Witcher 3");
        user.instalarJogo("Cyberpunk 2077");
        System.out.println();

        // Teste Mostrar jogos após instalação
        System.out.println("--- Teste 6: Jogos Após Instalação ---");
        user.mostrarJogosComprados();
        System.out.println();

        // Teste Desinstalar um jogo
        System.out.println("--- Teste 7: Desinstalar Jogo ---");
        user.desinstalarJogo("The Witcher 3");
        System.out.println();

        // Teste Mostrar jogos após desinstalação
        System.out.println("--- Teste 8: Jogos Após Desinstalação ---");
        user.mostrarJogosComprados();
        System.out.println();

        // Teste Tentativa de acessar jogo inexistente
        System.out.println("--- Teste 9: Jogo Inexistente ---");
        user.instalarJogo("Jogo Fake");
        System.out.println();

        // Teste Logout
        System.out.println("--- Teste 10: Logout ---");
        user.logout();
        System.out.println("Usuário logado? " + user.logado + "\n");

        System.out.println("========== TESTES CONCLUÍDOS ==========");
    }
}

