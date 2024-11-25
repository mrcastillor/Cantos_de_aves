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