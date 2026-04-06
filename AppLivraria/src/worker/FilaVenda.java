package worker;

import java.util.LinkedList;
import java.util.Queue;

public class FilaVenda {

    private static final Queue<Runnable> fila = new LinkedList<>();

    public static synchronized void adicionar(Runnable tarefa) {
        fila.add(tarefa);
        FilaVenda.class.notify();
    }

    public static synchronized Runnable obter() throws InterruptedException {
        while (fila.isEmpty()) {
            FilaVenda.class.wait();
        }
        return fila.poll();
    }
}