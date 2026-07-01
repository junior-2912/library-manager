package entities;

import enums.StatusLivro;

public class Livro {
    private String titulo;
    private String autor;
    private int isbn;
    private StatusLivro statusLivro;

    public Livro(String titulo, String autor, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.statusLivro = StatusLivro.DISPONIVEL;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getIsbn() {
        return isbn;
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    @Override
    public String toString() {
        return "-----------------\n" + titulo + " - " + autor + "\n" + isbn + " - " + statusLivro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;
        return isbn == livro.isbn;
    }

    @Override
    public int hashCode() {
        return isbn;
    }

    public void emprestar() {
        this.statusLivro = StatusLivro.EMPRESTADO;
    }

    public void devolver() {
        this.statusLivro = StatusLivro.DISPONIVEL;
    }
}
