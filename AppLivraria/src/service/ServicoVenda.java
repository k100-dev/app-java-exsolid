package service;

import model.Cliente;
import model.Livro;
import worker.FilaVenda;
import util.Logger;

public class ServicoVenda {

    public void venderLivro(Livro livro, Cliente cliente, int quantidade) {

        Logger.log("Recebendo pedido de venda...");

        if (livro.getQuantidadeEstoque() >= quantidade) {

            // 👇 AGORA É ASSÍNCRONO
            FilaVenda.adicionar(() -> processarVenda(livro, cliente, quantidade));

            Logger.log("Venda enviada para processamento em background!");

        } else {
            System.out.println("Estoque insuficiente para o livro \"" + livro.getTitulo() + "\".");
        }
    }

    // 🔥 NOVO MÉTODO (PROCESSAMENTO REAL)
    private void processarVenda(Livro livro, Cliente cliente, int quantidade) {

        try {
            Logger.log("Iniciando processamento da venda...");

            // Simula tempo de processamento
            Thread.sleep(2000);

            double total = livro.getPreco() * quantidade;

            // Atualiza estoque
            livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() - quantidade);

            Logger.log("Estoque atualizado!");

            // Simula envio de confirmação
            enviarConfirmacao(cliente, livro);

            // Mensagem final
            System.out.println("\nVenda realizada com sucesso!");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Quantidade: " + quantidade);
            System.out.println("Total: R$" + total + "\n");

        } catch (Exception e) {
            Logger.log("Erro ao processar venda: " + e.getMessage());

            // 🔁 Retry simples
            Logger.log("Reenfileirando venda...");
            FilaVenda.adicionar(() -> processarVenda(livro, cliente, quantidade));
        }
    }

    // 🔧 MÉTODO AUXILIAR
    private void enviarConfirmacao(Cliente cliente, Livro livro) throws InterruptedException {
        Logger.log("Enviando confirmação para " + cliente.getNome() + "...");
        Thread.sleep(1000);
    }
}