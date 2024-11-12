package model;

public class ExcepcionReserva extends Exception {
    private String mensaje;

    public ExcepcionReserva() {
    }

    public ExcepcionReserva(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
