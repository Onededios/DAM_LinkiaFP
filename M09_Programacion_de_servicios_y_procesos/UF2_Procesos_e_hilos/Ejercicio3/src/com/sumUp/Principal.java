package src.com.sumUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Principal {
    public static void main(String[] args) {

        try {
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                Process sumador = new ProcessBuilder("java", "M09_Programacion_de_servicios_y_procesos/UF2_Procesos_e_hilos/Ejercicio3/src/com/sumUp/Sumador.java").start();

                BufferedReader sumadorIn = new BufferedReader(new InputStreamReader(sumador.getInputStream()));
                PrintStream sumadorOut = new PrintStream(sumador.getOutputStream(), true);

                System.out.print("\033[1mEscribe el primer número: ");
                int first = Integer.parseInt(userIn.readLine());

                System.out.print("\033[1mEscribe el segundo número: ");
                int second = Integer.parseInt(userIn.readLine());

                if (first == 0 && second == 0) {break;}

                sumadorOut.println(first);
                sumadorOut.println(second);

                System.out.println("\033[0mLa suma es " + sumadorIn.readLine());
            }

            System.out.println("\033[1mFIN");

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
