package model.repository.impl;

import Modelo.repository.impl.ConexionJDBC;
import model.ExcepcionPersona;
import model.PersonaVO;
import model.repository.PersonaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaRepositoryImpl implements PersonaRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String query;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;

    public PersonaRepositoryImpl() {
    }

    //acabado
    public ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.query = "SELECT * FROM persona";
            ResultSet rs = this.stmt.executeQuery(this.query);

            while(rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                this.persona = new PersonaVO(nombre, apellidos, direccion, localidad, provincia);
                this.persona.setDni(dni);
                this.personas.add(this.persona);
            }
            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPersona("No se ha podido realizar la operación.");
        }
    }

    //acabado
    public void addPersona(PersonaVO p) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.query = "INSERT INTO persona (dni, nombre, apellidos, direccion, localidad, provincia) VALUES ('" + p.getDni() + "','" + p.getNombre() + "','" + p.getApellidos() + "','" + p.getDireccion() + "','" + p.getLocalidad() + "','" + p.getProvincia() + "');";
            this.stmt.executeUpdate(this.query);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPersona("No se ha podido realizar la operación.");
        }
    }

    //acabado
    public void deletePersona(String dni) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM persona WHERE codigo = %d", dni);
            comando.executeUpdate(sql);
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
            String sql = String.format("UPDATE persona SET nombre = '%s', apellidos = '%s', direccion = '%s', localidad = '%s', provincia = '%s', dni = '%s' WHERE dni = %d", personaVO.getNombre(), personaVO.getApellidos(), personaVO.getDireccion(), personaVO.getLocalidad(), personaVO.getProvincia(), personaVO.getDni());
            this.stmt.executeUpdate(sql);
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
            throw new ExcepcionPersona("No se ha podido realizar la busqueda del DNI.");
        }
    }

}
