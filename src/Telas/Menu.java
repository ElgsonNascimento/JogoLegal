package interfacejogolegal;

/**
 * Gerencia a exibição dos menus do sistema.
 * Responsável: Elgson
 * Métodos: exibirMenuPrincipal, exibirMenuUsuario, exibirMenuAdm
 */
public class Menu {

    /**
     * Exibe o menu principal (tela de boas-vindas antes do login).
     * Opções: Login, Cadastrar, Sair.
     */
    public void exibirMenuPrincipal() {
        System.out.println("==========================================");
        System.out.println("          BEM-VINDO AO JOGO LEGAL        ");
        System.out.println("==========================================");
        System.out.println("  [1] Login");
        System.out.println("  [2] Cadastrar");
        System.out.println("  [0] Sair");
        System.out.println("==========================================");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Exibe o menu do usuário comum.
     * Opções: Ver catálogo, Carrinho, Minha biblioteca, Logout.
     */
    public void exibirMenuUsuario(String nomeUsuario) {
        System.out.println("==========================================");
        System.out.println("  JOGO LEGAL — Olá, " + nomeUsuario + "!");
        System.out.println("==========================================");
        System.out.println("  [1] Ver catálogo de jogos");
        System.out.println("  [2] Meu carrinho");
        System.out.println("  [3] Minha biblioteca (jogos comprados)");
        System.out.println("  [0] Logout");
        System.out.println("==========================================");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Exibe o menu do administrador.
     * Opções: Remover usuário, Adicionar jogo, Remover jogo, Relatórios, Logout.
     */
    public void exibirMenuAdm(String nomeAdmin) {
        System.out.println("==========================================");
        System.out.println("  PAINEL ADMIN — " + nomeAdmin.toUpperCase());
        System.out.println("==========================================");
        System.out.println("  [1] Remover usuário");
        System.out.println("  [2] Adicionar jogo ao catálogo");
        System.out.println("  [3] Remover jogo do catálogo");
        System.out.println("  [4] Visualizar relatórios");
        System.out.println("  [0] Logout");
        System.out.println("==========================================");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Exibe o menu do criador de jogo.
     * Opções: Publicar jogo, Editar jogo, Remover jogo, Ver vendas, Logout.
     */
    public void exibirMenuCriador(String nomeCriador) {
        System.out.println("==========================================");
        System.out.println("  PAINEL CRIADOR — " + nomeCriador.toUpperCase());
        System.out.println("==========================================");
        System.out.println("  [1] Publicar jogo");
        System.out.println("  [2] Editar jogo");
        System.out.println("  [3] Remover jogo");
        System.out.println("  [4] Ver vendas");
        System.out.println("  [0] Logout");
        System.out.println("==========================================");
        System.out.print("Escolha uma opção: ");
    }
}
