//package model.repository.impl;
//
//import model.repository.impl.ConexionJDBC;
//import model.ExcepcionReserva;
//import model.ReservaVO;
//import model.repository.ReservaRepository;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class ReservaRepositoryImpl implements ReservaRepository {
//
//    private final ConexionJDBC conexion = new ConexionJDBC();
//    private Statement stmt;
//    private String query;
//    private ArrayList<ReservaVO> reservas;
//    private ReservaVO reserva;
//
//    public ReservaRepositoryImpl() {
//    }
//
//    //acabado
//    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionReserva {
//        try {
//            Connection conn = this.conexion.conectarBD();
//            this.reservas = new ArrayList();
//            this.stmt = conn.createStatement();
//            this.query = "SELECT * FROM reservas";
//            ResultSet rs = this.stmt.executeQuery(this.query);
//
//            while(rs.next()) {
//                Integer id_reserva = rs.getInt("id_reserva");
//                Date fecha_llegada = rs.getDate("fechaLlegada");
//                Date fecha_salida = rs.getDate("fechaSalida");
//                Integer numHabitaciones = rs.getInt("numHabitaciones");
//                String tipoHabitacion = rs.getString("tipoHabitacion");
//                Boolean fumadorSN = rs.getBoolean("fumadorSN");
//                String regimenAlojamiento = rs.getString("regimenAlojamiento");
//                this.reserva = new ReservaVO(id_reserva, fecha_llegada, fecha_salida, numHabitaciones, tipoHabitacion, fumadorSN, regimenAlojamiento);
//                this.reserva.setId_reserva(id_reserva);
//                this.reservas.add(this.reserva);
//            }
//            this.conexion.desconectarBD(conn);
//            return this.reservas;
//        } catch (SQLException var6) {
//            throw new ExcepcionReserva("No se ha podido realizar la operación");
//        }
//    }
//
//    //acabado
//    public void addReserva(ReservaVO r) throws ExcepcionReserva {
//        try {
//            Connection conn = this.conexion.conectarBD();
//            this.stmt = conn.createStatement();
//            this.query = "INSERT INTO reservas (fechaEntrada, fechaSalida, numHabitaciones, tipoHabitacion, fumadorSN, regimenAlojamiento) VALUES ('" + r.getFechaEntrada() + "','" + r.getFechaSalida() + "','" + r.getNumHabitaciones() + "','" + r.getTipoHabitacion() + "','" + r.getFumador() + "','" + r.getRegimenAlojamiento() + "');";
//            this.stmt.executeUpdate(this.query);
//            this.stmt.close();
//            this.conexion.desconectarBD(conn);
//        } catch (SQLException var3) {
//            throw new ExcepcionReserva("No se ha podido realizar la operación");
//        }
//    }
//
//    //acabado
//    public void deleteReserva(Integer idReserva) throws ExcepcionReserva {
//        try {
//            Connection conn = this.conexion.conectarBD();
//            this.stmt = conn.createStatement();
//            Statement comando = conn.createStatement();
//            String sql = String.format("DELETE FROM reservas WHERE codigo = %d", idReserva);
//            comando.executeUpdate(sql);
//            this.conexion.desconectarBD(conn);
//        } catch (SQLException var5) {
//            throw new ExcepcionReserva("No se ha podido realizar la eliminación");
//        }
//    }
//
//    //acabado
//    public void editReserva(ReservaVO reservaVO) throws ExcepcionReserva {
//        try {
//            Connection conn = this.conexion.conectarBD();
//            this.stmt = conn.createStatement();
//            String sql = String.format("UPDATE reservas SET fechaEntrada = '%s', fechaSalida = '%s', numHabitaciones = '%s', tipoHabitacion = '%s', fumadorSN = '%s', regimenAlojamiento = '%s' WHERE codigo = %d", reservaVO.getFechaEntrada(), reservaVO.getFechaSalida(), reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getRegimenAlojamiento(), reservaVO.getId_reserva());
//            this.stmt.executeUpdate(sql);
//        } catch (Exception var4) {
//            throw new ExcepcionReserva("No se ha podido relaizr la edición");
//        }
//    }
//
//    //acabado
//    public int lastId() throws ExcepcionReserva {
//        int lastReservaId = 0;
//
//        try {
//            Connection conn = this.conexion.conectarBD();
//            Statement comando = conn.createStatement();
//
//            for(ResultSet registro = comando.executeQuery("SELECT id_reserva FROM reservas ORDER BY id_reserva DESC LIMIT 1"); registro.next(); lastReservaId = registro.getInt("id_reserva")) {
//            }
//
//            return lastReservaId;
//        } catch (SQLException var5) {
//            throw new ExcepcionReserva("No se ha podido realizar la busqueda del ID");
//        }
//    }
//}
