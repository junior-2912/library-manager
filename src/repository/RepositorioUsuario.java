package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Usuario;
import exceptions.ElementoNaoEncontradoException;
//Classe de repositório responsável por armazenar e retornar dados.
public class RepositorioUsuario implements Repositorio<Usuario> {
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public boolean salvar(Usuario item) {
        boolean adicionou = usuarios.add(item);

        if (adicionou) {
            adicionarArquivo(item);
        }

        return adicionou;
    }

    @Override
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        throw new ElementoNaoEncontradoException("O usuario com id informado nao foi encontrado!");
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarios.stream().toList();
    }

    @Override
    public void adicionarArquivo(Usuario item) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/usuarios.csv", true))) {
            bw.write(item.getId() + ";" + item.getNome());
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
