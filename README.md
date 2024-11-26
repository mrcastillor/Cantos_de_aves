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

Insertar datos
``````
package com.canto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertAve {
    // Definir la clase Ave
    public static class Ave {
        int id;
        String loc;
        String url;
        String file;
        String date;
        String sp;

        // Constructor
        public Ave(int id, String loc, String url, String file, String date, String sp) {
            this.id = id;
            this.loc = loc;
            this.url = url;
            this.file = file;
            this.date = date;
            this.sp = sp;
        }
    }
   
    public static Ave ObtenerInformacionAPI(String apiURL) {
        // Implementar la lógica de obtención de datos de la API
        // Por ahora, devolvemos un objeto de prueba
        return new Ave(1, "Colombia", "https://example.com", "file.mp3", "2024-02-20", "Species");
    }

    public static void main(String[] args) {
        String apiURL = "https://xeno-canto.org/api/2/recordings?query=loc:colombia";
    
        Ave ave1 = ObtenerInformacionAPI(apiURL);
        insert(ave1);
        ImprimirTabla();
    }

    public static void ImprimirTabla() {
        // Implementar método para imprimir la tabla
        System.out.println("Tabla de Aves impresa");
    }              

    public static void insert(Ave ave1) {
        String query = "INSERT INTO Aves (id, loc, url, file, `date`, sp) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ave1.id);
            preparedStatement.setString(2, ave1.loc);
            preparedStatement.setString(3, ave1.url);
            preparedStatement.setString(4, ave1.file);
            preparedStatement.setString(5, ave1.date);
            preparedStatement.setString(6, ave1.sp);
            
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Ave insertada correctamente: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
``````

Archivo pom.xml
``````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0</modelVersion>

    <groupId>com.canto</groupId>
    <artifactId>canto1-project</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>22</maven.compiler.source>
        <maven.compiler.target>22</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
</project>
``````


