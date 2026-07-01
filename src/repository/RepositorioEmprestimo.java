package repository;

import enums.StatusLivro;
import exceptions.ElementoNaoEncontradoException;
import exceptions.LimiteEmprestimoUsuarioException;
import exceptions.LivroNaoDisponivelException;
import entities.Emprestimo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Classe de repositório responsável por armazenar e retornar dados.
public class RepositorioEmprestimo implements Repositorio<Emprestimo> {
    private Set<Emprestimo> emprestimos = new HashSet<>();

    @Override
    public boolean salvar(Emprestimo item) {
        //Verificando se o livro esta disponível.
        if (item.getLivro().getStatusLivro() == StatusLivro.DISPONIVEL) {
            //Verificando se o usuário não ultrapassou o limite de emprestimos.
            if (item.getUsuario().getNEmprestimos() < 5) {
                //Adiciona o emprestimo na lista do usuario.
                item.getUsuario().addEmprestimo(item);
                //Muda o status do livro para Emprestado.
                item.getLivro().emprestar();
                //Salva um arquivo .csv com os dados.
                boolean adicionou = emprestimos.add(item);
                if (adicionou) {
                    adicionarArquivo(item);
                }
                return adicionou;
            } else {
                throw new LimiteEmprestimoUsuarioException("O usuario chegou ao limite de emprestimos permitidos!");
            }
        } else {
            throw new LivroNaoDisponivelException("O livro nao esta disponivel! ");
        }

    }

    public boolean removerEmprestimo(Emprestimo emprestimo) {
        return emprestimos.remove(emprestimo);
    }

    @Override
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }
        throw new ElementoNaoEncontradoException("O emprestimo com id informado nao foi encontrado!");
    }

    @Override
    public List<Emprestimo> buscarTodos() {
        return emprestimos.stream().toList();
    }

    public void adicionarArquivo(Emprestimo item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\windows\\temp\\emprestimos.csv", true))) {
            bw.write(item.getId()
                    + ";" + item.getUsuario().getId()
                    + ";" + item.getLivro().getIsbn()
                    + ";" + item.getDataEmprestimo()
                    + ";" + item.getDataDevolucao()
                    + ";" + item.getStatusEmprestimo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
