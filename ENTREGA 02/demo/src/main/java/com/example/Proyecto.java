package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Proyecto {
    // Método para consultar el API de PokeAPI y obtener información de un Pokémon
    public static String getPokemonData(String pokemonName) {
        String urlString = "https://xeno-canto.org/api/2/recordings?query=loc:bogota"; // + pokemonName.toLowerCase();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder responseContent = new StringBuilder();
        String urlAudio = "";

        try {
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
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            // Convertir la respuesta en JSON y extraer algunos datos
            JSONObject pokemonJson = new JSONObject(responseContent.toString());

            // Obtener el nombre del Pokémon
            // String name = pokemonJson.getString("recordings");

            // Obtener el peso del Pokémon
            // int weight = pokemonJson.getInt("alt");

            // Obtener la lista de tipos del Pokémon
            int idjson = 0;
            String pais = "";
            String especie = "";
            // String urlAudio = "";
            String name2 = "";

            String buscarLoc = "Esmeraldas: Awacachi Biological Corridor, Río Bogotá area"; // Línea 766 API

            for (int i = 0; i < pokemonJson.getJSONArray("recordings").length(); i++) {

                if (buscarLoc.compareTo(
                        pokemonJson.getJSONArray("recordings")
                                .getJSONObject(i)
                                // .getJSONObject("recordings")
                                .getString("loc")) == 0) {

                    idjson = pokemonJson.getJSONArray("recordings")
                            .getJSONObject(i)
                            // .getJSONObject("recordings")
                            .getInt("id"); // == 261549

                    pais = pokemonJson.getJSONArray("recordings")
                            .getJSONObject(i)
                            // .getJSONObject("recordings")
                            .getString("cnt");

                    especie = pokemonJson.getJSONArray("recordings")
                            .getJSONObject(i)
                            // .getJSONObject("recordings")
                            .getString("sp");

                    urlAudio = pokemonJson.getJSONArray("recordings")
                            .getJSONObject(i)
                            // .getJSONObject("recordings")
                            .getString("file");

                    name2 = pokemonJson.getJSONArray("recordings")
                            .getJSONObject(i)
                            // .getJSONObject("recordings")
                            .getString("en");

                    i = pokemonJson.getJSONArray("recordings").length();
                }

                // if (i < pokemonJson.getJSONArray("recordings").length() - 1) {
                // types += ", ";
                // }
            }

            // Retornar los datos obtenidos
            // return "Nombre: " + name + "\nPeso: " + weight + "\nTipos: " + types;
            // return "\nid: " + idjson + "\ncnt: " + pais + "\nespecie: " + especie +
            // "\nfile: " + urlAudio + "\nen: " + name2;
            return urlAudio;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener los datos del Pokémon.";
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

    // public static void main(String[] args) {
    // }

}
