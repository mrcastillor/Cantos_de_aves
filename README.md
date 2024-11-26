
# Cantos de Aves en Java

Miguel Lara Ballen, José Mateus Malagón, Manuel Rolando Castillo Rodríguez, Lenin Fabian Cruz González
Facultad de Ingeniería de Sistemas, Escuela Tecnológica Instituto Técnico Central
TS3B: Programación II
Ing. Sebastián Aguilera Novoa
16 de septiembre de 2024

## Descripción

El proyecto pretende desarrollar un software en el lenguaje de programación Java que permite visualizar y analizar grabaciones de sonido de cantos de aves. 
Es importante crear herramientas para visualizar y analizar los sonidos de las aves para facilitar el trabajo de investigación de los biólogos y zoólogos que estudian estos animales, esto también lleva a promover la conservación de estas especies animales.
Actualmente existen herramientas para analizar diferentes tipos de señales, pero no especializadas en señales de sonido de aves y que cuenten solamente con una funcionalidad mínima básica enfocada solamente en el propósito de visualizar estas señales de audio en particular.

## Planteamiento

A través de algunas bibliotecas de paquetes, Java permite representar datos sonoros de manera gráfica dependiendo también de la técnica utilizada para visualizar datos sonoros. 
En el análisis de señales de audio se buscan patrones repetidos, ciclos de las señales con frecuencias y amplitudes y otras medidas de las señales físicas de audio.

Algunas bibliotecas de métodos y clases para analizar señales de audio en Java incluyen:
JMF (Java Media Framework) que permite capturar, reproducir y transformar señales de información de audio. 
Java Sound API que permite manejar información de audio en Java por medio de la lectura y la escritura de archivos de sonido o la captura y reproducción de sonidos.
JFreeChart permite crear gráficos en dos dimensiones para visualizar información de audio.

Para visualizar información de sonidos y audio se pueden utilizar varias técnicas como las siguientes:
Las ondas sonoras son expresadas como representación de una señal de audio en dos dimensiones, la amplitud de la señal de audio en función del tiempo.
El espectrograma como una representación visual de la frecuencia y la amplitud de las diferentes componentes en las que se puede descomponer matemáticamente una señal de sonido en función del tiempo.

El sonograma como una representación visual de las frecuencias de sonido en escala logarítmica.
Las barras de nivel como una representación visual de las señales de sonido donde se visualiza la amplitud en diferentes bandas de frecuencia.
Las Visualizaciones en tercera dimensión permiten representar información de señales de sonido a través de esferas o paisajes de sonido.
La optimización del código fuente afecta el rendimiento en la visualización de señales de audio y sonidos en tiempo real.
La visualización de información significativa en señales de audio complejas requiere técnicas de procesamiento de señales avanzadas.
Es posible mejorar las aplicaciones desarrolladas para visualizar señales de audio introduciendo funciones interactivas con el usuario como zoom, desplazamiento y selección de diferentes rangos de tiempo.

## Justificación

Poder visualizar sonidos y audio en Java es útil para analizar señales, editar audio y desarrollar aplicaciones interactivas.
Un software para percibir de manera visual señales de sonido de canto de aves ayuda a los estudiantes e investigadores en biología y zoología a visualizar patrones de comunicación o comportamiento de estas especies animales.
Existen soluciones de software para visualizar señales de audio y sonido, pero estas son soluciones generales para muchas aplicaciones de tratamiento de señales, debido a esto tienen muchas funciones complejas no aplicables a el análisis de audio o a el análisis de los cantos de aves. Una aplicación específica solamente para la visualización de sonidos de canto de aves esta parametrizada para este uso especifico y especializado. Esto disminuye la complejidad de su utilización por parte de los usuarios a los que va dirigido.
El desarrollo del proyecto no involucra recursos económicos debido a que su producción es intelectual y virtual en un computador, no involucra ningún equipo físico adicional.

## Objetivos

### Objetivo general

Desarrollar un software en el lenguaje de programación Java que permita visualizar señales de audio del canto de las aves en un computador.

#### Objetivos específicos

Determinar en qué formato de audio hay que convertir los sonidos grabados para ser procesados por el software de visualización.
Codificar en lenguaje de programación Java una interfaz gráfica de usuario (GUI) para cargar archivos de audio en el formato escogido para ser procesados por el sofware de visualización.
Codificar en Java la conversión de las señales de sonido del formato de audio a variables internas de trabajo.
Visualizar las señales de sonido en una interfaz gráfica por medio del uso de librerías de clases y métodos de Java.

---




Conexion a la base de datos
``````java
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
``````java
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
``````java
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
``````java
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


