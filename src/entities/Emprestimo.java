package entities;

import enums.StatusEmprestimo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int id;
    private StatusEmprestimo statusEmprestimo;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataDevolucao, int id) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.id = id;
        this.statusEmprestimo = StatusEmprestimo.ATIVO;
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

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public void finalizar(){
        statusEmprestimo = StatusEmprestimo.FINALIZADO;
        livro.devolver();
    }

    @Override
    public String toString() {
        return "-------------------------------------------\n" + id + " - " + usuario.getNome()
                + " - " + livro.getTitulo() + "\n"
                + "Data de emprestimo: " + dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ". Data de devolucao: " + dataDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "\n" + statusEmprestimo;
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
