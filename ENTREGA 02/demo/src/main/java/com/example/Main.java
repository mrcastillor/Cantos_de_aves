package com.example;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Consultar los datos de un Pokémon (ejemplo: "pikachu")
        Proyecto proyecto1 = new Proyecto();
        String pokemonInfo = proyecto1.getPokemonData(null); // "pikachu");
        System.out.println(pokemonInfo);

        AudioData audioData1 = new AudioData(pokemonInfo);

        // guardar audio

        // crear objeto de tipo audio

        // pasar ese objeto a la base de datos
        // System.out.println(audioData1.getAudioData());

        EscrituraArchivo escrituraArchivo1 = new EscrituraArchivo(audioData1.getAudioData());
    }
}