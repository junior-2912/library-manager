package repository;

import java.util.List;

public interface Repositorio <T>{
    void salvar(T item);

    List<T> buscarTodos();
}
