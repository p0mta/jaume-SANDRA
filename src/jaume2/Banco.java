package jaume2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banco {
    private final Lock lock = new ReentrantLock();
    private final Condition ingreso = lock.newCondition();
    private int clientesEnBanco = 0;

    public void ingresarBanco(int idCliente) throws InterruptedException {
        lock.lock();
        try {
            while (clientesEnBanco >= 2) {
                System.out.println("Cliente " + idCliente + " está esperando para ingresar al banco.");
                ingreso.await();
            }

            System.out.println("Cliente " + idCliente + " ha ingresado al banco.");
            clientesEnBanco++;

        } finally {
            lock.unlock();
        }

        // Simulación de actividad dentro del banco
        Thread.sleep(2000);

        lock.lock();
        try {
            System.out.println("Cliente " + idCliente + " ha salido del banco.");
            clientesEnBanco--;

            // Despierta a otros clientes que están esperando si hay espacio disponible
            ingreso.signalAll();
        } finally {
            lock.unlock();
        }
    }
}