package com.exampleUF2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Padre {
    public static void main(String args[]) {
        String line;
        try {
            Process hijo = new ProcessBuilder("java", "C:\\Users\\Joel\\source\\repos\\DAM_LinkiaFP\\M09_Programacion_de_servicios_y_procesos\\UF2_Procesos_e_hilos\\example\\src\\com\\exampleUF2\\Hijo.java").start();
            BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream ps = new PrintStream(hijo.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Ejemplo de comunicacion");
            System.out.println("Envia un mensaje al hijo");
            line = in.readLine();
            ps.println(line);

            line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
