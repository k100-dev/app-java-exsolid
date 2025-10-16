package service;

import model.Cliente;
import model.Livro;

public class ServicoVenda {

    public void venderLivro(Livro livro, Cliente cliente, int quantidade) {
        if (livro.getQuantidadeEstoque() >= quantidade) {
            double total = livro.getPreco() * quantidade;
            livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() - quantidade);
            System.out.println("\nVenda realizada com sucesso!");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Quantidade: " + quantidade);
            System.out.println("Total: R$" + total + "\n");
        } else {
            System.out.println("Estoque insuficiente para o livro \"" + livro.getTitulo() + "\".");
        }
    }
}
