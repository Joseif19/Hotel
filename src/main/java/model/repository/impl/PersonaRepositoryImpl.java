package model.repository.impl;

import model.ExcepcionPersona;
import model.PersonaVO;
import model.repository.PersonaRepository;

import java.sql.*;
import java.util.ArrayList;

public class PersonaRepositoryImpl implements PersonaRepository {

    private final model.repository.impl.ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String query;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;

    public PersonaRepositoryImpl() {
    }

    //acabado
    public ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona {
        ArrayList<PersonaVO> personas = new ArrayList<>();
        String query = "SELECT * FROM persona";

        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");

                PersonaVO persona = new PersonaVO(nombre, apellidos, direccion, localidad, provincia);
                persona.setDni(dni);
                personas.add(persona);
            }
        } catch (SQLException e) {
            throw new ExcepcionPersona("No se ha podido realizar la operación.");
        }
        return personas;
    }


    //acabado
    public void addPersona(PersonaVO p) throws ExcepcionPersona {

        String query = "INSERT INTO persona (dni, nombre, apellidos, direccion, localidad, provincia) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, p.getDni());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getApellidos());
            stmt.setString(4, p.getDireccion());
            stmt.setString(5, p.getLocalidad());
            stmt.setString(6, p.getProvincia());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionPersona("No se ha podido realizar la operación.");
        }
    }

    //acabado
    public void deletePersona(String dni) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM persona WHERE dni = '%s'", dni);  // Uso de dni en lugar de codigo
            stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la eliminación.");
        }
    }

    //acabado
    public void editPersona(PersonaVO personaVO) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE persona SET nombre = '%s', apellidos = '%s', direccion = '%s', localidad = '%s', provincia = '%s' WHERE dni = '%s'",
                    personaVO.getNombre(), personaVO.getApellidos(), personaVO.getDireccion(),
                    personaVO.getLocalidad(), personaVO.getProvincia(), personaVO.getDni());
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (Exception var4) {
            throw new ExcepcionPersona("No se ha podido realizar la edición.");
        }
    }

    //acabado
    public String lastDni() throws ExcepcionPersona {
        String lastDni = "";

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT dni FROM persona ORDER BY dni DESC LIMIT 1"); registro.next();
                lastDni = registro.getString("dni")) {
            }

            return lastDni;
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la búsqueda del DNI.");
        }
    }

}
