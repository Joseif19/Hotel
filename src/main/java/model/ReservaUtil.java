package model;

import java.util.ArrayList;

public class ReservaUtil {

    public static ReservaVO toReservaVO(Reserva reserva) {
        if (reserva == null) return null;

        ReservaVO reservaVO = new ReservaVO();
        reservaVO.setIdReserva(reserva.getIdReserva());
        reservaVO.setFechaLlegada(reserva.getFechaLlegada());
        reservaVO.setFechaSalida(reserva.getFechaSalida());
        reservaVO.setNumHabitaciones(reserva.getNumHabitaciones());
        reservaVO.setTipoHabitacion(reserva.getTipoHabitacion());
        reservaVO.setFumador(reserva.isFumador());
        reservaVO.setRegAlojamiento(reserva.getRegAlojamiento());
        reservaVO.setPersonaFK(reserva.getPersonaFK());

        return reservaVO;
    }

    public static Reserva toReserva(ReservaVO reservaVO) {
        if (reservaVO == null) return null;

        Reserva reserva = new Reserva();
        reserva.setIdReserva(reservaVO.getIdReserva() != null ? reservaVO.getIdReserva() : 0);
        reserva.setFechaLlegada(reservaVO.getFechaLlegada());
        reserva.setFechaSalida(reservaVO.getFechaSalida());
        reserva.setNumHabitaciones(reservaVO.getNumHabitaciones() != null ? reservaVO.getNumHabitaciones() : 0);
        reserva.setTipoHabitacion(reservaVO.getTipoHabitacion() != null ? reservaVO.getTipoHabitacion() : "--Tipo Habitacion--");
        reserva.setFumador(reservaVO.getFumador() != null ? reservaVO.getFumador() : false);
        reserva.setRegAlojamiento(reservaVO.getRegAlojamiento() != null ? reservaVO.getRegAlojamiento() : "--Reg Alojamiento--");
        reserva.setPersonaFK(reservaVO.getPersonaFK() != null ? reservaVO.getPersonaFK() : 0);

        return reserva;
    }

    public static ArrayList<Reserva> toReservaList(ArrayList<ReservaVO> reservaVOList) {
        ArrayList<Reserva> reservaList = new ArrayList<>();
        if (reservaVOList != null) {
            for (ReservaVO reservaVO : reservaVOList) {
                reservaList.add(toReserva(reservaVO));
            }
        }
        return reservaList;
    }

    public static ArrayList<ReservaVO> toReservaVOList(ArrayList<Reserva> reservaList) {
        ArrayList<ReservaVO> reservaVOList = new ArrayList<>();
        if (reservaList != null) {
            for (Reserva reserva : reservaList) {
                reservaVOList.add(toReservaVO(reserva));
            }
        }
        return reservaVOList;
    }
}
