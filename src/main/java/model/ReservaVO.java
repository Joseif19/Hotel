package model;

import java.time.LocalDate;

public class ReservaVO {
    private Integer idReserva;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private Integer numHabitaciones;
    private String tipoHabitacion;
    private Boolean fumador;
    private String regAlojamiento;
    private Integer personaFK;

    // Constructor completo
    public ReservaVO(Integer idReserva, LocalDate fechaLlegada, LocalDate fechaSalida, Integer numHabitaciones, String tipoHabitacion, Boolean fumador, String regAlojamiento, Integer personaFK) {
        this.idReserva = idReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.fumador = fumador;
        this.regAlojamiento = regAlojamiento;
        this.personaFK = personaFK;
    }

    // Constructor vacío
    public ReservaVO() {}

    // Getters y setters
    public Integer getIdReserva() { return idReserva; }
    public void setIdReserva(Integer idReserva) { this.idReserva = idReserva; }
    public LocalDate getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDate fechaLlegada) { this.fechaLlegada = fechaLlegada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public Integer getNumHabitaciones() { return numHabitaciones; }
    public void setNumHabitaciones(Integer numHabitaciones) { this.numHabitaciones = numHabitaciones; }
    public String getTipoHabitacion() { return tipoHabitacion; }
    public void setTipoHabitacion(String tipoHabitacion) { this.tipoHabitacion = tipoHabitacion; }
    public Boolean getFumador() { return fumador; }
    public void setFumador(Boolean fumador) { this.fumador = fumador; }
    public String getRegAlojamiento() { return regAlojamiento; }
    public void setRegAlojamiento(String regAlojamiento) { this.regAlojamiento = regAlojamiento; }
    public Integer getPersonaFK() { return personaFK; }
    public void setPersonaFK(Integer personaFK) { this.personaFK = personaFK; }
}
