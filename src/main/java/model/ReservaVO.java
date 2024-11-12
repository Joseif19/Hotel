package model;

import java.util.Date;

public class ReservaVO {
    Integer id_reserva;
    Date fechaEntrada;
    Date fechaSalida;
    Integer numHabitaciones;
    String tipoHabitacion;
    Boolean fumador;
    String regimenAlojamiento;

    public ReservaVO(Date fechaEntrada, Date fechaSalida, Integer numHabitaciones) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
    }

    public ReservaVO(Integer id_reserva, Date fechaEntrada, Date fechaSalida, Integer numHabitaciones, String tipoHabitacion, Boolean fumador, String regimenAlojamiento) {
        this.id_reserva = id_reserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.fumador = fumador;
        this.regimenAlojamiento = regimenAlojamiento;
    }

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
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

    public String getRegimenAlojamiento() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(String regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }
}
