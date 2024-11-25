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