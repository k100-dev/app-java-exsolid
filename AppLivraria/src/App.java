import model.Autor;
import model.Cliente;
import model.Livro;
import repository.LivroRepository;
import service.GerenciadorEstoque;
import service.ServicoVenda;

public class App {
    public static void main(String[] args) {

        // Criando autores
        Autor autor1 = new Autor("George Orwell", "Inglaterra");
        Autor autor2 = new Autor("Machado de Assis", "Brasil");

        // Criando livros
        Livro livro1 = new Livro("1984", autor1, 49.90, 10);
        Livro livro2 = new Livro("Dom Casmurro", autor2, 39.90, 5);

        // Criando repositório e adicionando livros
        LivroRepository repositorio = new LivroRepository();
        repositorio.adicionar(livro1);
        repositorio.adicionar(livro2);

        // Criando serviços
        GerenciadorEstoque estoque = new GerenciadorEstoque();
        ServicoVenda venda = new ServicoVenda();

        // Criando cliente
        Cliente cliente = new Cliente("Tatiane", "tatiane@email.com");

        // Listando livros
        System.out.println("=== Catálogo de Livros ===");
        for (Livro livro : repositorio.listar()) {
            System.out.println(livro);
        }

        // Venda
        venda.venderLivro(livro1, cliente, 2);

        // Atualizando estoque
        estoque.removerEstoque(livro2, 1);
        estoque.adicionarEstoque(livro1, 3);

        // Listando novamente
        System.out.println("\n=== Estoque Atualizado ===");
        for (Livro livro : repositorio.listar()) {
            System.out.println(livro);
        }
    }
}
