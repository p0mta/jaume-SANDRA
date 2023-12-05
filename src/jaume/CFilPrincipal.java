package jaume;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CFilPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Ejecutar proceso hijo con temporización corta");
            System.out.println("2. Ejecutar proceso hijo con temporización larga");
            System.out.println("3. Modificar temporización del proceso hijo para que termine mucho antes que el padre");
            System.out.println("4. Modificar temporización del proceso hijo para que termine mucho más tarde que el padre");
            System.out.println("5. Ejecutar procesos hijos con temporizaciones diferentes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarProcesoCorto();
                    break;
                case 2:
                    ejecutarProcesoLargo();
                    break;
                case 3:
                    modificarTemporizacionAntesQuePadre();
                    break;
                case 4:
                    modificarTemporizacionDespuesQuePadre();
                    break;
                case 5:
                    ejecutarProcesosDiferentes();
                    break;
                case 6:
                    System.out.println("Adios <3");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 6);
    }

    private static void ejecutarProcesoCorto() {
        CFil hijo = new CFil();
        hijo.setNombreProceso("Corto");
        hijo.setTemporizacion(1000);
        hijo.start();
        esperarFin(hijo);
    }

    private static void ejecutarProcesoLargo() {
        CFil hijo = new CFil();
        hijo.setNombreProceso("Largo");
        hijo.setTemporizacion(5000);
        hijo.start();
        esperarFin(hijo);
    }

    private static void modificarTemporizacionAntesQuePadre() {
        CFil hijo = new CFil();
        hijo.setNombreProceso("Antes Que Padre");
        // Modifica la temporización para que termine mucho antes que el proceso padre
        hijo.setTemporizacion(500);
        hijo.start();
        esperarFin(hijo);
    }

    private static void modificarTemporizacionDespuesQuePadre() {
        CFil hijo = new CFil();
        hijo.setNombreProceso("Despues QuePadre");
        // Modifica la temporización para que termine mucho más tarde que el proceso padre
        hijo.setTemporizacion(10000);
        hijo.start();
        esperarFin(hijo);
    }

    private static void ejecutarProcesosDiferentes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de procesos hijos: ");
        int numProcesosHijos = scanner.nextInt();

        // Límite superior para el número de procesos hijos
        if (numProcesosHijos > 10) {
            System.out.println("Número de procesos hijos exageradamente grande. Limitado a 10.");
            numProcesosHijos = 10;
        }

        List<CFil> listaDeHijos = new ArrayList<>();

        for (int i = 1; i <= numProcesosHijos; i++) {
            CFil hijo = new CFil();
            hijo.setNombreProceso(Integer.toString(i));
            hijo.setTemporizacion(i * 1000);
            hijo.start();
            listaDeHijos.add(hijo);
        }

        for (CFil hijo : listaDeHijos) {
            esperarFin(hijo);
        }
    }

    private static void esperarFin(CFil hijo) {
        try {
            hijo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Proceso padre esperando a que termine el proceso hijo.");
    }
}

