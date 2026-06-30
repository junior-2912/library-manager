package repository;

import entities.Emprestimo;
import entities.Usuario;
import exceptions.ElementoNaoEncontradoException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RepositorioUsuario implements Repositorio<Usuario> {
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public boolean salvar(Usuario item) {
        adicionarArquivo(item);
        return usuarios.add(item);
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\windows\\temp\\usuarios.csv", true))) {
            bw.write(item.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
