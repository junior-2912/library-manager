package application;

import entities.Biblioteca;
import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;
import exceptions.ElementoNaoEncontradoException;
import exceptions.LimiteEmprestimoUsuarioException;
import exceptions.LivroNaoDisponivelException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try (Scanner entrada = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println();
                    System.out.println("Digite um numero: ");
                    System.out.println("0 - Sair");
                    System.out.println("1 - Cadastrar livro");
                    System.out.println("2 - Cadastrar usuário");
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
                    if (indice == 0) {
                        System.out.println("Fechando o programa...");
                        break;
                    }

                    switch (indice) {
                        case 1 -> {
                            System.out.println("---CADASTRO DE LIVRO---");
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
                                System.out.println("Livro é nulo, ou ja existe outro com o mesmo Id!");
                            }
                            System.out.println("----------------------");
                        }
                        case 2 -> {
                            System.out.println("---CADASTRO DE USUARIO---");
                            System.out.print("Digite o nome do usuario: ");
                            String nome = entrada.nextLine();
                            System.out.print("Digite o Id do usuario: ");
                            int id = entrada.nextInt();
                            entrada.nextLine();

                            if (biblioteca.cadastrarUsuario(new Usuario(nome, id))) {
                                System.out.println("Usuario cadastrado com sucesso!");
                            } else {
                                System.out.println("Usuario é nulo, ou ja existe outro com o mesmo Id!");
                            }
                            System.out.println("----------------------");
                        }
                        case 3 -> {
                            System.out.println("---CADASTRO DE EMPRESTIMOS---");
                            System.out.print("Digite o id do usuario que vai fazer o emprestimo: ");
                            Usuario usuario = biblioteca.buscarUsuarioPorId(entrada.nextInt());
                            System.out.print("Digite o ISBN do livro a ser emprestado: ");
                            Livro livro = biblioteca.buscarLivroPorId(entrada.nextInt());
                            System.out.print("Digite o ID do emprestimo: ");
                            int idEmprestimo =  entrada.nextInt();
                            entrada.nextLine();
                            System.out.println("Digite a data de devolucao (dd/mm/aaaa): ");
                            LocalDate dataDevolucao = LocalDate.parse(entrada.nextLine(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));


                            if (biblioteca.fazerEmprestimo(new Emprestimo(usuario, livro, dataDevolucao, idEmprestimo))) {
                                System.out.println("Emprestimo feito com sucesso!");
                            } else {
                                System.out.println("Emprestimo é nulo, ou ja existe outro com o mesmo Id");
                            }

                            System.out.println("---------------------------");
                        }
                        case 4 -> {
                            System.out.println("---DEVOLUÇÃO DE LIVROS---");
                            System.out.print("Digite o id do emprestimo: ");
                            int idEmprestimo = entrada.nextInt();
                            biblioteca.devolverLivro(idEmprestimo);
                            System.out.println("Livro devolvido com sucesso!");
                            System.out.println("--------------------------");
                        }
                        case 5 -> {
                            System.out.println("---LISTAGEM DE LIVROS---");
                            biblioteca.listarLivros().forEach(System.out::println);
                            System.out.println("------------------------");
                        }
                        case 6 -> {
                            System.out.println("---LISTAGEM DE USUÁRIOS---");
                            biblioteca.listarUsuarios().forEach(System.out::println);
                            System.out.println("---------------------------");
                        }
                        case 7 -> {
                            System.out.println("---LISTAGEM DE EMPRÉSTIMOS---");
                            biblioteca.listarEmprestimos().forEach(System.out::println);
                            System.out.println("-----------------------------");
                        }
                        case 8 -> {
                            System.out.println("---BUSCA POR ID---");
                            System.out.print("Digite o item que você quer buscar ('L', 'U', 'E'): ");
                            String item = entrada.nextLine();
                            System.out.print("Digite o id do item: ");
                            int id = entrada.nextInt();

                            switch (item.toUpperCase()) {
                                case "L" -> System.out.println(biblioteca.buscarLivroPorId(id));
                                case "U" -> System.out.println(biblioteca.buscarUsuarioPorId(id));
                                case "E" -> System.out.println(biblioteca.buscarEmprestimoPorId(id));
                                default -> System.out.println("Digite uma letra válida!");
                            }

                        }
                        case 9 -> {
                            System.out.println("---LIVROS ATRASADOS---");
                            biblioteca.buscarLivrosAtrasados().forEach(System.out::println);
                            System.out.println("-----------------------");
                        }
                        case 10 -> {
                            System.out.println("---USUÁRIOS COM MAIS EMPRÉSTIMOS---");
                            biblioteca.buscarUsuariosMaisEmprestimos().forEach(System.out::println);
                            System.out.println("-----------------------------------");
                        }
                        case 11 -> {
                            System.out.println("---TOP 5 LIVROS MAIS EMPRESTADOS---");
                            biblioteca.listarLivrosMaisEmprestados().forEach(System.out::println);
                            System.out.println("-----------------------------------");
                        }
                        default -> {
                            System.out.println("Digite um numero valido!");
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
