package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private IntegerProperty idReserva = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> fechaLlegada = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> fechaSalida = new SimpleObjectProperty<>();
    private IntegerProperty numHabitaciones = new SimpleIntegerProperty();
    private StringProperty tipoHabitacion = new SimpleStringProperty();
    private BooleanProperty fumador = new SimpleBooleanProperty();
    private StringProperty regAlojamiento = new SimpleStringProperty();
    private IntegerProperty personaFK = new SimpleIntegerProperty();

    // Getters y setters usando propiedades JavaFX
    public int getIdReserva() { return idReserva.get(); }
    public void setIdReserva(int idReserva) { this.idReserva.set(idReserva); }
    public IntegerProperty idReservaProperty() { return idReserva; }

    public LocalDate getFechaLlegada() { return fechaLlegada.get(); }
    public void setFechaLlegada(LocalDate fechaLlegada) { this.fechaLlegada.set(fechaLlegada); }
    public ObjectProperty<LocalDate> fechaLlegadaProperty() { return fechaLlegada; }

    public LocalDate getFechaSalida() { return fechaSalida.get(); }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida.set(fechaSalida); }
    public ObjectProperty<LocalDate> fechaSalidaProperty() { return fechaSalida; }

    public int getNumHabitaciones() { return numHabitaciones.get(); }
    public void setNumHabitaciones(int numHabitaciones) { this.numHabitaciones.set(numHabitaciones); }
    public IntegerProperty numHabitacionesProperty() { return numHabitaciones; }

    public String getTipoHabitacion() { return tipoHabitacion.get(); }
    public void setTipoHabitacion(String tipoHabitacion) { this.tipoHabitacion.set(tipoHabitacion); }
    public StringProperty tipoHabitacionProperty() { return tipoHabitacion; }

    public boolean isFumador() { return fumador.get(); }
    public void setFumador(boolean fumador) { this.fumador.set(fumador); }
    public BooleanProperty fumadorProperty() { return fumador; }

    public String getRegAlojamiento() { return regAlojamiento.get(); }
    public void setRegAlojamiento(String regAlojamiento) { this.regAlojamiento.set(regAlojamiento); }
    public StringProperty regAlojamientoProperty() { return regAlojamiento; }

    public int getPersonaFK() { return personaFK.get(); }
    public void setPersonaFK(int personaFK) { this.personaFK.set(personaFK); }
    public IntegerProperty personaFKProperty() { return personaFK; }
}
