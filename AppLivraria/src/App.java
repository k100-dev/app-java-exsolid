import model.Autor;
import model.Cliente;
import model.Livro;

import repository.LivroRepository;

import service.GerenciadorEstoque;
import service.ServicoVenda;

import worker.VendaWorker; // 👈 NOVO

public class App {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        APP LIVRARIA");
        System.out.println("=================================\n");

        // 👇 INICIANDO WORKER
        Thread worker = new Thread(new VendaWorker());
        worker.setDaemon(true);
        worker.start();

        System.out.println("Worker iniciado!\n");

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

        // Exibindo catálogo
        System.out.println("📚 Catálogo de Livros:");
        for (Livro livro : repositorio.listar()) {
            System.out.println(livro);
        }

        System.out.println("\n--- Realizando venda ---");

        // Venda de livro (AGORA ASSÍNCRONA)
        venda.venderLivro(livro1, cliente, 2);

        System.out.println("\n--- Atualizando estoque ---");

        // Atualização de estoque
        estoque.removerEstoque(livro2, 1);
        estoque.adicionarEstoque(livro1, 3);

        // Exibindo estoque atualizado
        System.out.println("\n📦 Estoque Atualizado:");
        for (Livro livro : repositorio.listar()) {
            System.out.println(livro);
        }

        System.out.println("\nSistema finalizado.");
    }
}