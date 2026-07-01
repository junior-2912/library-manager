package entities;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String nome;
    private int id;
    private Set<Emprestimo> emprestimos = new HashSet<>();

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public int getNEmprestimos() {
        return emprestimos.size();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public boolean addEmprestimo(Emprestimo emprestimo) {
        return emprestimos.add(emprestimo);
    }

    public boolean removerEmprestimo(Emprestimo emprestimo) {
        return emprestimos.remove(emprestimo);
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + emprestimos.stream()
                .map(emprestimo -> emprestimo.getLivro().getTitulo()
                        + " - Status do emprestimo: " + emprestimo.getStatusEmprestimo())
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
