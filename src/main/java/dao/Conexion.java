

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/bds01";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
