package model.repository.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Persona;
import java.sql.*;

public class PersonaDAO {

    public ObservableList<Persona> getAllPersonas() {
        ObservableList<Persona> personas = FXCollections.observableArrayList();

        String query = "SELECT * FROM persona"; // Cambia el nombre de la tabla si es necesario

        // Crear una instancia de ConexionJDBC
        ConexionJDBC conexion = new ConexionJDBC();

        try (Connection conn = conexion.conectarBD();  // Usar conectarBD en lugar de getConnection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Conexión establecida correctamente.");

            while (rs.next()) {
                personas.add(new Persona(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }
}
