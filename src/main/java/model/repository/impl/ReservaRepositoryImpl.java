package model.repository.impl;

import model.ReservaVO;
import model.ExcepcionReserva;
import model.repository.ReservaRepository;

import java.sql.*;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private ArrayList<ReservaVO> reservas;

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionReserva {
        ArrayList<ReservaVO> reservas = new ArrayList<>();
        String query = "SELECT id_reserva, fechaLlegada, fechaSalida, numHabitaciones, tipoHabitacion, fumadorSN, regimenAlojamiento, dni_personaFK FROM reservas";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReservaVO reserva = new ReservaVO(
                        rs.getInt("id_reserva"),
                        rs.getDate("fechaLlegada") != null ? rs.getDate("fechaLlegada").toLocalDate() : null,
                        rs.getDate("fechaSalida") != null ? rs.getDate("fechaSalida").toLocalDate() : null,
                        rs.getInt("numHabitaciones"),
                        rs.getString("tipoHabitacion"),
                        rs.getBoolean("fumadorSN"),
                        rs.getString("regimenAlojamiento"),
                        rs.getInt("dni_personaFK")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la operación: " + e.getMessage());
        }
        return reservas;
    }



    @Override
    public void addReserva(ReservaVO r) throws ExcepcionReserva {
        String query = "INSERT INTO reservas (fechaLlegada, fechaSalida, numHabitaciones, tipoHabitacion, fumadorSN, regimenAlojamiento, dni_personaFK) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(r.getFechaLlegada()));
            stmt.setDate(2, Date.valueOf(r.getFechaSalida()));
            stmt.setInt(3, r.getNumHabitaciones());
            stmt.setString(4, r.getTipoHabitacion());
            stmt.setBoolean(5, r.getFumador());
            stmt.setString(6, r.getRegAlojamiento());
            stmt.setInt(7, r.getPersonaFK()); // Relación con cliente

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionReserva("Error al agregar la reserva: " + e.getMessage());
        }
    }


    @Override
    public void deleteReserva(Integer idReserva) throws ExcepcionReserva {
        String query = "DELETE FROM reservas WHERE id_reserva = ?";

        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idReserva);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new ExcepcionReserva("No se encontró la reserva con el ID proporcionado.");
            }

        } catch (SQLException e) {
            throw new ExcepcionReserva("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    @Override
    public void editReserva(ReservaVO reservaVO) throws ExcepcionReserva {
        String query = "UPDATE reservas SET fechaLlegada = ?, fechaSalida = ?, numHabitaciones = ?, tipoHabitacion = ?, fumadorSN = ?, regimenAlojamiento = ? WHERE id_reserva = ?";

        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(reservaVO.getFechaLlegada()));
            stmt.setDate(2, Date.valueOf(reservaVO.getFechaSalida()));
            stmt.setInt(3, reservaVO.getNumHabitaciones());
            stmt.setString(4, reservaVO.getTipoHabitacion());
            stmt.setBoolean(5, reservaVO.getFumador());
            stmt.setString(6, reservaVO.getRegAlojamiento());
            stmt.setInt(7, reservaVO.getIdReserva());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new ExcepcionReserva("No se encontró la reserva con el ID proporcionado.");
            }

        } catch (SQLException e) {
            throw new ExcepcionReserva("Error al editar la reserva: " + e.getMessage());
        }
    }

    @Override
    public int lastId() throws ExcepcionReserva {
        String query = "SELECT id_reserva FROM reservas ORDER BY id_reserva DESC LIMIT 1";

        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("id_reserva");
            } else {
                return 0; // No se encontraron registros
            }

        } catch (SQLException e) {
            throw new ExcepcionReserva("Error al obtener el último ID de reserva: " + e.getMessage());
        }
    }
}
