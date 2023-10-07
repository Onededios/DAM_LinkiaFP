package com.exampleUF2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hijo {
    public static void main(String[] args) {
        String lineaEnvio;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            lineaEnvio = new String();

            lineaEnvio = br.readLine() + "recibida y procesada";
            System.out.println(lineaEnvio);
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
