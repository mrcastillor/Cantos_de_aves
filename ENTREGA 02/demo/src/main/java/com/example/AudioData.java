package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AudioData {
    String dir;

    AudioData(String audio) {
        this.dir = audio;
    }

    public BufferedReader getAudioData() {

        String urlString = dir; // = "https://xeno-canto.org/api/2/recordings?query=loc:bogota"; // +
        // pokemonName.toLowerCase();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        // StringBuilder responseContent = new StringBuilder();

        try

        {
            // Crear objeto URL con la URL del Pokémon
            URL url = new URL(urlString);

            // Abrir la conexión
            connection = (HttpURLConnection) url.openConnection();

            // Establecer el método de solicitud como GET
            connection.setRequestMethod("GET");

            // Establecer tiempo de espera para la conexión
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Verificar el código de respuesta HTTP
            int status = connection.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("Error: " + status + " - No se pudo obtener información del Pokémon.");
            }

            // Leer la respuesta del servidor
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // return toString(reader);
            
            return reader;

        } catch (Exception e) {
            e.printStackTrace();
            return null;// "Error al obtener los datos del Pokémon.";
        } finally {
            // Cerrar el lector y la conexión
            try {
                if (reader != null)
                    reader.close();
                if (connection != null)
                    connection.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}