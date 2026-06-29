package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("Digite um numero");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuario");
            int indice = entrada.nextInt();
            if (indice == 0) break;


        }
        entrada.close();
    }
}
