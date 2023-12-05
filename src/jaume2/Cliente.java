package jaume2;

class Cliente extends Thread {
    private final Banco banco;
    private final int idCliente;

    public Cliente(Banco banco, int idCliente) {
        this.banco = banco;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            banco.ingresarBanco(idCliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}