package service;

import model.Livro;

public class GerenciadorEstoque {

    public void adicionarEstoque(Livro livro, int quantidade) {
        livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() + quantidade);
        System.out.println(quantidade + " unidades adicionadas de \"" + livro.getTitulo() + "\".");
    }

    public void removerEstoque(Livro livro, int quantidade) {
        if (livro.getQuantidadeEstoque() >= quantidade) {
            livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() - quantidade);
            System.out.println(quantidade + " unidades removidas de \"" + livro.getTitulo() + "\".");
        } else {
            System.out.println("Erro: estoque insuficiente para \"" + livro.getTitulo() + "\".");
        }
    }
}
