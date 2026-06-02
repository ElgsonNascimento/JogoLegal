package Menu;

import java.util.Scanner;

public class menu {

    Scanner scanner = new Scanner(System.in);

    public void exibirMenuPrincipal() {

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n=== JOGO LEGAL ===");
            System.out.println("1 - Loja");
            System.out.println("2 - Biblioteca");
            System.out.println("3 - Login");
            System.out.println("4 - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("Abrindo loja...");
                    break;

                case 2:
                    System.out.println("Abrindo biblioteca...");
                    break;

                case 3:
                    System.out.println("Abrindo login...");
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}