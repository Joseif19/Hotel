package model.repository.impl;

import java.sql.*;

public class ConexionJDBC {

    public ConexionJDBC() {
    }

    // Método para obtener la conexión a la base de datos
    public Connection conectarBD() throws SQLException {
        Connection conn = null;

        try {
            // Intentamos conectar a la base de datos hotel
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    ""
            );
            // Intentamos cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (SQLException e) {
            // Si ocurre un error SQL, se captura aquí
            System.out.println("Error de SQL: " + e.getMessage());
            e.printStackTrace(); // Esto mostrará más detalles del error
            throw new SQLException("Error al conectar con la base de datos.");
        } catch (ClassNotFoundException e) {
            // Si no se encuentra el driver
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver de la base de datos.");
        }

        return conn; // Retorna la conexión
    }

    // Método para cerrar la conexión a la base de datos
    public void desconectarBD(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                // Si ocurre un error al cerrar la conexión
                System.out.println("\n--- SQLException capturada al cerrar la conexión ---\n");
                while (e != null) {
                    System.out.println("Mensaje:   " + e.getMessage());
                    System.out.println("SQLState:  " + e.getSQLState());
                    System.out.println("ErrorCode: " + e.getErrorCode());
                    e = e.getNextException();
                    System.out.println("");
                }
            }
        }
    }
}
