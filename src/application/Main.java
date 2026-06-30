package application;

import entities.Biblioteca;
import entities.Livro;
import exceptions.ElementoNaoEncontradoException;
import exceptions.LimiteEmprestimoUsuarioException;
import exceptions.LivroNaoDisponivelException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try (Scanner entrada = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("Digite um numero: ");
                    System.out.println("0 - Sair");
                    System.out.println("1 - Cadastrar livro");
                    System.out.println("2 - Cadastrar usuario");
                    System.out.println("3 - Fazer emprestimo");
                    System.out.println("4 - Devolver livro");
                    System.out.println("5 - Listar livros");
                    System.out.println("6 - Listar usuários");
                    System.out.println("7 - Listar emprestimos");
                    System.out.println("8 - Buscar por ID");
                    System.out.println("9 - Livros atrasados");
                    System.out.println("10 - Usuarios com mais emprestimos");
                    System.out.println("11 - Top 5 livros mais emprestados");

                    int indice = entrada.nextInt();
                    entrada.nextLine();
                    if (indice == 0) break;

                    switch (indice) {
                        case 1 -> {
                            System.out.print("Digite o nome do livro: ");
                            String nome = entrada.nextLine();
                            System.out.print("Digite o autor do livro: ");
                            String autor = entrada.nextLine();
                            System.out.print("Digite o ISBN: ");
                            int isbn = entrada.nextInt();
                            entrada.nextLine();

                            if (biblioteca.cadastrarLivro(new Livro(nome, autor, isbn))) {
                                System.out.println("Livro cadastrado com sucesso! ");
                            } else {
                                System.out.println("Nao foi possivel cadastrar esse livro. Tente novamente!");
                            }
                        }

                    }
                } catch (LivroNaoDisponivelException | LimiteEmprestimoUsuarioException |
                         ElementoNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }


            }
        }

    }
}
