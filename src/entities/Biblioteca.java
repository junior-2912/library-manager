package entities;

import enums.StatusEmprestimo;
import repository.RepositorioEmprestimo;
import repository.RepositorioLivro;
import repository.RepositorioUsuario;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private RepositorioLivro repositorioLivro = new RepositorioLivro();
    private RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
    private RepositorioEmprestimo repositorioEmprestimo = new RepositorioEmprestimo();

    public boolean cadastrarLivro(Livro livro) {
        if (livro != null) {
            return repositorioLivro.salvar(livro);
        }
        return false;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuario != null) {
            return repositorioUsuario.salvar(usuario);
        }
        return false;
    }

    public boolean fazerEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null) {
            return repositorioEmprestimo.salvar(emprestimo);
        }
        return false;
    }

    public List<Livro> listarLivros() {
        return repositorioLivro.buscarTodos();
    }

    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.buscarTodos();
    }

    public List<Emprestimo> listarEmprestimos() {
        return repositorioEmprestimo.buscarTodos();
    }

    public List<Livro> buscarLivrosAtrasados() {
        //Filtra os empréstimos ativos e que não foram devolvidos os livros.
        return repositorioEmprestimo.buscarTodos().stream()
                .filter(emprestimo -> emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATIVO)
                .filter(emprestimo -> emprestimo.getDataDevolucao().isBefore(LocalDate.now()))
                .map(Emprestimo::getLivro)
                .toList();
    }

    public void devolverLivro(int idEmprestimo) {
        //Recebe o emprestimo baseado no ‘id’, se for null, lança uma exceção.
        Emprestimo emprestimo = repositorioEmprestimo.buscarPorId(idEmprestimo);
        //Muda o status do livro e muda o status do empréstimo.
        emprestimo.finalizar();
    }

    public List<Usuario> buscarUsuariosMaisEmprestimos() {
        //Usando groupingBy() transforma a lista de emprestimos em um mapa,
        //usando o usuario como chave e a quantidade de emprestimos como valor.
        Map<Usuario, Long> mapa = repositorioEmprestimo.buscarTodos()
                .stream()
                .collect(Collectors.groupingBy(Emprestimo::getUsuario, Collectors.counting()));

        //Retorna uma lista de Usuarios (key do Map) ordenados pela quantidade de emprestimos em ordem decrescente.
        return mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(5)
                .toList();
    }

    public List<Livro> listarLivrosMaisEmprestados() {
        //Usando groupingBy() transforma a lista de emprestimos em um mapa,
        //usando o livro como chave e a quantidade de emprestimos como valor.
        Map<Livro, Long> mapa = repositorioEmprestimo.buscarTodos()
                .stream()
                .collect(Collectors.groupingBy(Emprestimo::getLivro, Collectors.counting()));

        //Retorna uma lista de Livros (key do Map) ordenados pela quantidade de emprestimos em ordem decrescente.
        return mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
    }

    public Livro buscarLivroPorId(int id) {
        return repositorioLivro.buscarPorId(id);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return repositorioUsuario.buscarPorId(id);
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        return repositorioEmprestimo.buscarPorId(id);
    }
}
