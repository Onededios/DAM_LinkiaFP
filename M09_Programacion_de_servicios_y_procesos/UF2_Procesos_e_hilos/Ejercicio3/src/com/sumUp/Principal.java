package src.com.sumUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Principal {
    public static void main(String[] args) {

        try {
            while (true) {
                Process sumador = new ProcessBuilder("java", "-jar", "Sumador.jar").start();

                BufferedReader sumadorIn = new BufferedReader(new InputStreamReader(sumador.getInputStream()));
                PrintStream sumadorOut = new PrintStream(sumador.getOutputStream(), true);

                int first = getUserNumber();

                int second = getUserNumber();

                if (first == 0 && second == 0) {break;}

                sumadorOut.println(first);
                sumadorOut.println(second);

                System.out.println("La suma es " + sumadorIn.readLine());
            }

            System.out.println("FIN");

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static int getUserNumber() {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Escribe un número entero: ");
            try {
                return Integer.parseInt(userIn.readLine());
            } catch (NumberFormatException e) {
                System.out.println("No es un número válido. Introduce un número entero.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
