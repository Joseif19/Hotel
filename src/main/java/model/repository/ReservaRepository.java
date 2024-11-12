package model.repository;

import model.ExcepcionPersona;
import model.ExcepcionReserva;
import model.ReservaVO;

import java.util.ArrayList;

public interface ReservaRepository {

    ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionReserva;

    void addReserva(ReservaVO var1) throws ExcepcionReserva;

    void deleteReserva(Integer var1) throws ExcepcionReserva;

    void editReserva(ReservaVO var1) throws ExcepcionReserva;

    int lastId() throws ExcepcionReserva;

}
