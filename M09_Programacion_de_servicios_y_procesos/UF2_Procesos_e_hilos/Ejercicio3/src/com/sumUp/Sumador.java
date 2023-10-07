package src.com.sumUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sumador {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(Integer.parseInt(reader.readLine())+Integer.parseInt(reader.readLine()));
            reader.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}