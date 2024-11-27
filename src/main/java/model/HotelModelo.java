package model;

import model.repository.ReservaRepository;

import java.util.ArrayList;

public class HotelModelo {

    private ReservaRepository reservaRepository;

    public HotelModelo() {
//        this.reservaRepository = new ReservaRepositoryImpl(); // Inicializamos el repositorio de reservas
    }

    // Obtener lista de reservas
    public ArrayList<ReservaVO> obtenerReservas() throws ExcepcionReserva {
        return this.reservaRepository.ObtenerListaReservas();
    }

    // Añadir una nueva reserva
    public void añadirReserva(ReservaVO reserva) throws ExcepcionReserva {
        this.reservaRepository.addReserva(reserva);
    }

    // Eliminar una reserva
    public void eliminarReserva(Integer idReserva) throws ExcepcionReserva {
        this.reservaRepository.deleteReserva(idReserva);
    }

    // Editar una reserva existente
    public void editarReserva(ReservaVO reserva) throws ExcepcionReserva {
        this.reservaRepository.editReserva(reserva);
    }

}
