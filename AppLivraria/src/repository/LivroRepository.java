package repository;

import java.util.ArrayList;
import java.util.List;

import model.Livro;

public class LivroRepository {
    private List<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listar() {
        return livros;
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}
