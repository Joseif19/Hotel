package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {

    private final IntegerProperty idReserva;
    private final ObjectProperty<LocalDate> fechaLlegada;
    private final ObjectProperty<LocalDate> fechaSalida;
    private final StringProperty tipoHabitacion;
    private final BooleanProperty fumador;
    private final StringProperty regAlojamiento;
    private final IntegerProperty personaFK;

    public Reserva() {
        this(0, null, null, "--Tipo Habitacion--", false, "--Reg Alojamiento--", 0);
    }

    public Reserva(int idReserva, LocalDate fechaLlegada, LocalDate fechaSalida, String tipoHabitacion, boolean fumador, String regAlojamiento, int personaFK) {
        this.idReserva = new SimpleIntegerProperty(idReserva);
        this.fechaLlegada = new SimpleObjectProperty<>(fechaLlegada);
        this.fechaSalida = new SimpleObjectProperty<>(fechaSalida);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regAlojamiento = new SimpleStringProperty(regAlojamiento);
        this.personaFK = new SimpleIntegerProperty(personaFK);
    }

    public int getIdReserva() {
        return idReserva.get();
    }
    public void setIdReserva(int idReserva) {
        this.idReserva.set(idReserva);
    }
    public IntegerProperty idReservaProperty() {
        return idReserva;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }
    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }
    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }
    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion.set(tipoHabitacion);
    }
    public StringProperty tipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public boolean isFumador() {
        return fumador.get();
    }
    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }
    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public String getRegAlojamiento() {
        return regAlojamiento.get();
    }
    public void setRegAlojamiento(String regAlojamiento) {
        this.regAlojamiento.set(regAlojamiento);
    }
    public StringProperty regAlojamientoProperty() {
        return regAlojamiento;
    }

    public int getPersonaFK() {
        return personaFK.get();
    }
    public void setPersonaFK(int personaFK) {
        this.personaFK.set(personaFK);
    }
    public IntegerProperty personaFKProperty() {
        return personaFK;
    }

}
