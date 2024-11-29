package model.repository.impl;

import java.sql.*;

public class ConexionJDBC {

    public ConexionJDBC() {
    }

    public Connection conectarBD() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    ""
            );
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver de la base de datos.");
        }

        return conn;
    }

    public void desconectarBD(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
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
