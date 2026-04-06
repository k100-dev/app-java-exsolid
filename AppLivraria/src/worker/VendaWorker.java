package worker;

public class VendaWorker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Runnable tarefa = FilaVenda.obter();
                tarefa.run();

            } catch (Exception e) {
                System.out.println("[ERRO WORKER] " + e.getMessage());
            }
        }
    }
}