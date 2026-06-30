package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int id;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataDevolucao, int id) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "\n " + usuario + " - " + livro + "\n"
                + "Data de emprestimo: " + dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ". Data de devolucao: " + dataDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Emprestimo that = (Emprestimo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
