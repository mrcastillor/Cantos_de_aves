package com.example;

import java.io.*;

public class EscrituraArchivo implements Serializable {
    BufferedReader salida;

    EscrituraArchivo(BufferedReader reader) {
        this.salida = reader;

        // Creamos un objeto de tipo fila para asignarle un archivo
        File archivo = new File("escritura.dat");

        try {
            // Para poder escribir utilizaremos un FileOutputStream pasandole
            // como referencia el archivo de tipo File.
            FileOutputStream fos = new FileOutputStream(archivo);

            // Y crearemos también una instancia del tipo ObjectOutputStream
            // al que le pasaremos por parámetro
            // el objeto de tipo FileOutputStream
            ObjectOutputStream escribir = new ObjectOutputStream(fos);

            // Escribimos los objetos en el archivo.
            escribir.writeObject(salida);

            // Cerramos los objetos para no consumir recursos.
            escribir.close();
            fos.close();

        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo. " + e.getMessage());
        }
    }
}
