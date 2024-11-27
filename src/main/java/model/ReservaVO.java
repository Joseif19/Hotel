package model;

import java.util.Date;

public class ReservaVO {
    private Integer idReserva;
    private String fechaLlegada;
    private String fechaSalida;
    private String tipoHabitacion;
    private Boolean fumador; // Asumiendo que fumador es un valor booleano
    private String regAlojamiento;
    private Integer personaFK;

    // Getters y Setters
    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public String getRegAlojamiento() {
        return regAlojamiento;
    }

    public void setRegAlojamiento(String regAlojamiento) {
        this.regAlojamiento = regAlojamiento;
    }

    public Integer getPersonaFK() {
        return personaFK;
    }

    public void setPersonaFK(Integer personaFK) {
        this.personaFK = personaFK;
    }
}
