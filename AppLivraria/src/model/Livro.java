package model;

public class Livro {
    private String titulo;
    private Autor autor;
    private double preco;
    private int quantidadeEstoque;

    public Livro(String titulo, Autor autor, double preco, int quantidadeEstoque) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" de " + autor.getNome() + " - R$" + preco + " (" + quantidadeEstoque + " em estoque)";
    }
}
