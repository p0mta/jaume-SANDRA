package jaume;

public class CFil extends Thread {
    private String nombreProceso;
    private int temporizacion;

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public void setTemporizacion(int temporizacion) {
        this.temporizacion = temporizacion;
    }

    @Override
    public void run() {
        // Código del proceso hijo
        try {
            Thread.sleep(temporizacion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Proceso hijo " + nombreProceso + " terminado.");
    }
}
