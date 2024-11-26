Conexion a la base de datos
``````
package com.canto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            final String URL = "jdbc:mysql://localhost:3306/canto";
            final String USER = "root";
            final String PASSWORD = "21291031";
            
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
}
``````

Crear tabla
``````
package com.canto;

import java.sql.*;

public class CreateTable {
    public static void Create() {
        String query = """
            CREATE TABLE IF NOT EXISTS aves (
                id INT AUTO_INCREMENT PRIMARY KEY,
                loc VARCHAR(100) NOT NULL,
                url VARCHAR(100) NOT NULL,
                file VARCHAR(100) NOT NULL,
                date VARCHAR(100) NOT NULL,
                sp VARCHAR(100) NOT NULL
            );
            """;
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            
            stmt.execute(query);
            System.out.println("Tabla creada con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(int id, String loc, String url, String file, String date, String sp) throws SQLException {
        String query = "INSERT INTO aves (id, loc, url, file, date, sp) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, loc);
            preparedStatement.setString(3, url);
            preparedStatement.setString(4, file);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, sp);

            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Datos insertados correctamente. Filas afectadas: " + rowsInserted);
        }
    }

    public static void main(String[] args) {
        Create();
    }
}
``````



